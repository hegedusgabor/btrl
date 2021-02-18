package ro.btrl.validation.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.btrl.validation.demo.entity.Client;
import ro.btrl.validation.demo.service.ClientsService;


@RestController
@RequestMapping("/api")
public class ClientRestController {

	private ClientsService clientService;
	
	@Autowired
	public ClientRestController(ClientsService theclientService) {
		clientService = theclientService;
	}
	
	// add mapping for GET /clients/{cnp}
	
	@GetMapping("/clients/{cnp}")
	public Boolean getClient(@PathVariable String cnp) {
		
		Client theClient = clientService.findBycnp(cnp);
		
		if (theClient == null) {
			throw new RuntimeException("Client cnp not found - " + cnp);
		}
		
		return true;
	}

		
}










