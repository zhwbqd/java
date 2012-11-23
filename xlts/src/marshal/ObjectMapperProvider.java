package marshal;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Object Mapper Provider
 * Assists with the serialization process to/from JSON
 * @author baughe
 * @since 1.0.0
 */
@Provider
public class ObjectMapperProvider implements ContextResolver<ObjectMapper>
{
	/** Object Mapper for Serialization */
	public static final ObjectMapper OBJECT_MAPPER;

	/**
	 * Static Initializer
	 */
	static {
		OBJECT_MAPPER = new ObjectMapper();
		OBJECT_MAPPER.configure( DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false );
	}

	/**
	 * Get Context
	 * @param type Class
	 * @return Object Mapper
	 */
	public final ObjectMapper getContext( final Class<?> type )
	{
		return OBJECT_MAPPER;
	}

	/**
	 * Get Object Mapper
	 * @return Object Mapper
	 */
	public static ObjectMapper getObjectMapper()
	{
		return OBJECT_MAPPER;
	}
}
