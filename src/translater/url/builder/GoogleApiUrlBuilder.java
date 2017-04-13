package translater.url.builder;

import java.util.List;

import translater.entity.GoogleTRequest;
import translater.entity.TRequest;

public class GoogleApiUrlBuilder {
	
	public static String buildUrl(TRequest tRequest) {
		String baseApi = "http://translate.google.cn/translate_a/single?";
		StringBuilder builder = new StringBuilder(baseApi);
		builder.append("client=");
		builder.append(((GoogleTRequest)tRequest).getClient());
		builder.append('&');
		builder.append("sl=");
		builder.append(tRequest.getSl());
		builder.append('&');
		builder.append("tl=");
		builder.append(tRequest.getTl());
		builder.append(buildDts(((GoogleTRequest)tRequest).getDts()));
		builder.append('&');
		builder.append("q=");
		builder.append(tRequest.getQ());
		return builder.toString();
	}
	
	private static String buildDts(List<String> dts) {
		StringBuilder builder = new StringBuilder();
		for (String dt : dts) {
			builder.append('&');
			builder.append("dt=");
			builder.append(dt);
		}
		return builder.toString();
	}
}
