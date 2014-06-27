package zhwb.study.memcache.serialize;

import java.util.HashMap;
import java.util.Map;

public abstract class Serializations {

	private static Map<String, Serialization> serializers = new HashMap<String, Serialization>();
	
	static{
		registerSerializer(new JsonProtocol());
	}
	
	
	public static Serialization getSerialization(String protocol){
		if(!serializers.containsKey(protocol)){
			throw new IllegalArgumentException("could not find protocol serializer,"+protocol);
			
		}
		return serializers.get(protocol);
	}
	
	public static void registerSerializer(Serialization serializer){
		if(serializer == null ){
			throw new NullPointerException("serializer could not null");
		}
		if(serializer.getProtocol() == null){
			throw new IllegalArgumentException("serializer protocol is required");
		}
		serializers.put(serializer.getProtocol(), serializer);
	}
	
	public static void searchInClassPath(){
		//Serializations.class.getClassLoader().
	}
	
}