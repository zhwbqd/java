package zhwb.study.memcache.serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonProtocol implements Serialization{

	protected static final Logger logger = LoggerFactory.getLogger(JsonProtocol.class);

    protected static SerializerFeature[] features;
	
	static{
		features = new SerializerFeature[5];
		features[0] = SerializerFeature.QuoteFieldNames;
		features[1] = SerializerFeature.WriteMapNullValue;
		features[2] = SerializerFeature.WriteNullBooleanAsFalse;
		features[3] = SerializerFeature.WriteNullStringAsEmpty;
		features[4] = SerializerFeature.WriteNullNumberAsZero;
	}
	
	public JsonProtocol(){
		Serializations.registerSerializer(this);
	}
	
	@Override
	public <T> String serialize(T arg) {
		if(arg == null){
			throw new NullPointerException("serilize arg could not be null.");
		}
		return JSON.toJSONString(arg);
	}

	@Override
	public <T> T deserialize(Class<T> clazz, Object obj) {
		if(obj == null || !(obj instanceof String)){
			return null;
		}
		T t = null;
		try {
			t = JSON.parseObject((String)obj, clazz);
		} catch (Exception e) {
			logger.error("failed deserialize use json protocol", e);
		} 
		return t;
	}

	@Override
	public String getProtocol() {
		return "json";
	}
	
}