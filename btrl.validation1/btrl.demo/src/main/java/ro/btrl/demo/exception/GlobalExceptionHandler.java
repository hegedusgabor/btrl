package ro.btrl.demo.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MultipartException.class)
	public String multipartExcHandler(MultipartException e, RedirectAttributes redirectAttributes) {

		redirectAttributes.addFlashAttribute("uploadError", e.getCause().getMessage());
		return "redirect:/clients/list";
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public String maxUploadExcHandler(MaxUploadSizeExceededException e, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("uploadError", e.getCause().getMessage());
		return "redirect:/clients/list";
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public String emptyResultExcHandler(EmptyResultDataAccessException e, RedirectAttributes redirectAttributes) {
		return "redirect:/clients/list";
	}

}