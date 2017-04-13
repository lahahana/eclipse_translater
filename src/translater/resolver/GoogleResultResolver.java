package translater.resolver;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import translater.entity.TResult;

public class GoogleResultResolver implements ResultResolver {

	@Override
	public TResult resolve(String str) {
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(str);
		JsonArray array = jsonElement.getAsJsonArray();
		JsonElement e0 = array.get(0);
		String sl = array.get(2).getAsString();
		JsonArray array2 = e0.getAsJsonArray().get(0).getAsJsonArray();
		String r = array2.get(0).getAsString();
		String q = array2.get(1).getAsString();
		int count = array2.get(4).getAsInt();
		TResult result = new TResult(r);
		return result;
	}

}
