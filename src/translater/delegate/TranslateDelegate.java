package translater.delegate;

import java.io.IOException;

import translater.entity.GoogleTRequest;
import translater.entity.TRequest;
import translater.requester.HttpRequester;
import translater.url.builder.GoogleApiUrlBuilder;

public class TranslateDelegate {

	private HttpRequester httpRequester = new HttpRequester();

	public String translate(TRequest tRequest) {
		String rawUrl = GoogleApiUrlBuilder.buildUrl((GoogleTRequest)tRequest);
		String translation = "";
		try{
			translation = httpRequester.invokeRequest(rawUrl);
		}catch (IOException e) {
			translation = e.getMessage();
		}
		return translation;
	}
}
