package httpclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

	@Autowired
	private CustomHttpComponent customHttpComponent;
	
	public void sendRequest(String host) {
    }

}
