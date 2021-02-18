package ro.btrl.demo.response;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClient {
	private Logger logger = Logger.getLogger(getClass().getName());

    private final RestTemplate restTemplate;
    
    @Value("${ext1.url}")
    private String ext1Endpoint;
    
    @Value("${ext2.url}")
    private String ext2Endpoint;
    
    public RestClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    
    public Response getPostWithResponseHandlingExt1(String cnp) {
    	logger.info("getting validation info for cnp in external1 endpoint");
        try {
        return this.restTemplate.getForEntity(ext1Endpoint, Response.class, cnp).getBody();
        } catch (HttpStatusCodeException ex) {
            System.out.println(ex.getResponseBodyAsString());
        }
        return null;
    }
    
    public boolean getPostWithResponseHandlingExt2(String cnp) {
    	logger.info("getting info for existance of user in external2 endpoint");
    	try {
        return this.restTemplate.getForObject(ext2Endpoint, Boolean.class, cnp);
        } catch (HttpStatusCodeException ex) {
            System.out.println(ex.getResponseBodyAsString());
        }
        return false;
    }
    
}
