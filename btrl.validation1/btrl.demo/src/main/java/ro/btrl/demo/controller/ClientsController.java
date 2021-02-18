package ro.btrl.demo.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import ro.btrl.demo.entity.Client;
import ro.btrl.demo.exception.ResourceNotFoundException;
import ro.btrl.demo.response.Response;
import ro.btrl.demo.response.RestClient;
import ro.btrl.demo.service.ClientsService;
import ro.btrl.demo.util.ClientValidation;
import ro.btrl.demo.util.PdfGenaratorUtil;

@SwaggerDefinition(
	    tags = {
	        @Tag(name = "Clients", description = "Controlling the clients ID"),
	    }
	)
@Api(tags = {"Clients"})
@Controller
@RequestMapping("/clients")
public class ClientsController {

	@Autowired
	private PdfGenaratorUtil pdfGenaratorUtil;

	@Autowired
	private RestClient restService;

	@Autowired
	private ClientValidation clientValidation;

	private Logger logger = Logger.getLogger(getClass().getName());

	private ClientsService clientsService;

	private String uploadError;

	public String getUploadError() {
		return uploadError;
	}

	public void setUploadError(String uploadError) {
		this.uploadError = uploadError;
	}

	public ClientsController(ClientsService theClientsService) {
		clientsService = theClientsService;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listClients(Model theModel) {
		logger.info("getting /clients/list");
		// get clients from db
		List<Client> theClients = clientsService.findAll();

		// add to the spring model
		theModel.addAttribute("clients", theClients);
		theModel.addAttribute("uploadError", this.getUploadError());

		if (theClients.isEmpty()) {
			new ResourceNotFoundException("Database is empty", "Clients are not found");
		}

		return "clients/list-clients";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(@Valid @ModelAttribute("client") Client theClient,
			BindingResult theBindingResult, Model theModel) throws Exception {
		logger.info("processing client /clients/processRegistration");

		String name = theClient.getFirstName();
		logger.info("Processing registration form for: " + name);

		// form validation
		if (theBindingResult.hasErrors()) {
			return "clients/clients-form";
		}

		// check the database if user already exists
		Client existing = clientsService.findBycnp(theClient.getCnp());
		if (existing != null) {
			theModel.addAttribute("client", new Client());
			theModel.addAttribute("registrationError", "User already exists.");

			logger.warning("User already exists.");
			return "clients/clients-form";
		}
		if (theClient.getValidTo().before(theClient.getValidFrom())) {
			theModel.addAttribute("registrationError", "Identity validTo is wrong. Should be greater than validFrom");
			theModel.addAttribute("client", theClient);
			return "clients/clients-form";
		}

		// create client account
		clientsService.save(theClient);

		logger.info("Successfully created user: " + name);

		return "redirect:/clients/list";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		logger.info("adding new client in form /clients/showFromForAdd");

		// create model attribute to bind form data
		Client theClient = new Client();

		theModel.addAttribute("client", theClient);

		return "clients/clients-form";
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("clientId") int theId) {
		logger.info("deleting client by id  /clients/delete");

		// delete the employee
		clientsService.deleteById(theId);

		return "redirect:/clients/list";
	}

	@RequestMapping("/download")
	public ResponseEntity<InputStreamResource> downloadFile(@RequestParam String fileName) throws Exception {
		logger.info("download generated pdf /clients/download");

		Client existing = clientsService.findBycnp(fileName.split("\\.")[0]);

		clientValidation.setValidExternal(restService.getPostWithResponseHandlingExt2(existing.getCnp()));
		clientValidation.setValidDate(existing.getValidTo().after(new Date()));

		Response reputationExternal = restService.getPostWithResponseHandlingExt1(existing.getCnp());
		int reputation = reputationExternal != null ? reputationExternal.getReputation() : -1;
		clientValidation.setValidationMessage(reputation);

		boolean enrolmentValid;
		if (clientValidation.isValidExternal() || (reputation > 99 || reputation == -1)
				|| !clientValidation.isValidDate()) {
			enrolmentValid = false;
		} else {
			enrolmentValid = true;
		}

		Map<String, Object> data = new HashMap<>();
		data.put("theClient", existing);
		data.put("valid", clientValidation);
		data.put("enrolmentValid", enrolmentValid);

		ByteArrayInputStream resource = pdfGenaratorUtil.createPdf("validation/clients-template", data);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
				.contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(resource));
	}

	@PostMapping("/upload")
	public String uploadToDB(@RequestParam("file") MultipartFile file, @RequestParam("clientId") int theId,
			RedirectAttributes ra) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		logger.info("uploading to db the signed pdf /clients/upload");

		boolean matchCNP = false;

		if (!fileName.isEmpty()) {

			Client existing = clientsService.findById(theId);

			try {
				if (fileName.split("\\.")[1].equals("pdf") && (fileName.split("\\.")[0].equals(existing.getCnp()))) {
					existing.setDocument(file.getBytes());
					matchCNP = true;
				}

			} catch (MaxUploadSizeExceededException e) {
				e.printStackTrace();
			}
			if (matchCNP) {
				clientsService.save(existing);
				this.setUploadError(null);
			} else {
				this.setUploadError("The filename " + fileName + " does not match CNP :" + existing.getCnp());
			}
		}

		return "redirect:/clients/list";
	}
	
	@GetMapping("/{cnp}/download")
	public ResponseEntity<InputStreamResource> downloadSigned(@PathVariable String cnp) throws IOException {

		logger.info("downloading from database the signed pfd /clients/{cnp}/download");

		Client theClient = clientsService.findBycnp(cnp);

		if (theClient == null) {
			throw new RuntimeException("Client cnp not found - " + cnp);
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + theClient.getCnp() + ".pdf")
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(new ByteArrayInputStream(theClient.getDocument())));
	}

}
