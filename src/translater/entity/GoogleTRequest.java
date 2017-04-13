package translater.entity;

import java.util.List;

public class GoogleTRequest extends TRequest {
	
	private String client;

	public GoogleTRequest(String sl, String tl, String q, List<String> dt) {
		super(sl, tl, q, dt);
	}

	public GoogleTRequest(String sl, String tl, String q, List<String> dt, String client) {
		super(sl, tl, q, dt);
		this.client = client;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}
}
