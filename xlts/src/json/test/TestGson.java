package json.test;

import java.util.Date;

import com.google.gson.Gson;

public class TestGson {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Gson gs=new Gson();
        System.out.println(gs.toJson(String.valueOf("a")));
        System.out.println(gs.toJson(Integer.valueOf(1)));
        System.out.println(gs.toJson(new Date()));
	}

}
