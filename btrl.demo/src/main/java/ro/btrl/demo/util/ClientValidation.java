package ro.btrl.demo.util;

import org.springframework.stereotype.Component;

@Component
public class ClientValidation {
	private boolean validDate;
	private boolean validExternal;
	private int validationMessage;
	
	public ClientValidation() {
	}
	
	public ClientValidation(boolean validDate, boolean validExternal, int validationMessage) {
		this.validDate = validDate;
		this.validExternal = validExternal;
		this.validationMessage = validationMessage;
	}


	public boolean isValidDate() {
		return validDate;
	}


	public void setValidDate(boolean validDate) {
		this.validDate = validDate;
	}


	public boolean isValidExternal() {
		return validExternal;
	}


	public void setValidExternal(boolean validExternal) {
		this.validExternal = validExternal;
	}


	public int getValidationMessage() {
		return validationMessage;
	}


	public void setValidationMessage(int validationMessage) {
		this.validationMessage = validationMessage;
	}


	@Override
	public String toString() {
		return "Validation [validDate=" + validDate + ", validExternal=" + validExternal + ", validationMessage="
				+ validationMessage + "]";
	}
	
	
	
}
