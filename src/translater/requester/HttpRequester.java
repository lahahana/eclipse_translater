package translater.requester;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpRequester {
	
	private OkHttpClient hc;
	
	public HttpRequester() {
		super();
		init();
	}

	private void init() {
		hc = new OkHttpClient.Builder()
					.connectTimeout(1, TimeUnit.SECONDS)
					.build();
	}

	public String invokeRequest(String rawUrl) throws IOException {
		Request request = new Request.Builder()
								.url(rawUrl)
								.build();
		Response response = hc.newCall(request).execute();
		if(response.isSuccessful()) {
			ResponseBody responseBody = response.body();
			byte[] bytes = responseBody.bytes();
			response.close();
			return new String(bytes, "UTF-8");
		}else {
			throw new IOException("Sorry, Remote server is lost");
		}
	}
}
