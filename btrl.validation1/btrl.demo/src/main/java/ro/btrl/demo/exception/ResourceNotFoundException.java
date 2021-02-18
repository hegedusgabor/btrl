package ro.btrl.demo.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String resourceId;

	public ResourceNotFoundException(String resourceId, String message) {
		super(message);
		this.resourceId = resourceId;
	}

}
