package json.test;

import com.google.gson.Gson;

public class TestGson {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Gson gs=new Gson();
		gs.toJson("a:"+true);
		gs.toJson("b:"+1);
		
		System.out.println(gs);
	}

}
