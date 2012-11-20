package json.test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TestJson {

	/**
	 * @param args
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws JSONException {
		// TODO Auto-generated method stub

		String json="{'name':'Tom','phone':119}";
		
		JSONObject jo=new JSONObject(json);
		
		System.out.println(jo.getString("name"));
		
		json="[{'id':01, 'adress':ooo}, {'id':02, 'adress':ppp}, {'id':03, 'adress':iii}]";
		
		JSONArray ja=new JSONArray(json);
		
		for(int i=0; i< ja.length(); i++){
			//JSONObject j =(JSONObject)ja.get(i);
			JSONObject j =ja.getJSONObject(i);
			System.out.println(j.getInt("id"));
		}
		
	}

}
