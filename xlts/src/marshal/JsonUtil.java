package marshal;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.Collections;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Core Serializer Utility
 * @author baughe
 * @since 1.0.0
 */
public final class JsonUtil
{
	/** Logger */
	private static final Logger SBSLOGGER = LoggerFactory.getLogger( JsonUtil.class );

	/**
	 * Private Constructor
	 */
	private JsonUtil()
	{
		// Do nothing
	}

	/**
	 * Value Of XML
	 * @param <T> Generic Type
	 * @param clazz Class
	 * @param xml XML String
	 * @return Object
	 */
	public static <T> T valueOfXML( final Class<T> clazz, final String xml )
	{
		try {
			JAXBContext context = JAXBContext.newInstance( clazz );
			Unmarshaller um = context.createUnmarshaller();
			Object obj = um.unmarshal( new StringReader( xml ) );
			return clazz.cast( obj );
		} catch ( JAXBException e ) {
			SBSLOGGER.error( e.getMessage(), e );
		}
		return null;
	}

	/**
	 * To String XML
	 * @param <T> Type
	 * @param clazz Class
	 * @param obj Object
	 * @return XML String
	 */
	public static <T> String toStringXML( final Class<T> clazz, final Object obj )
	{
		StringWriter out;

		out = new StringWriter();
		try {
			JAXBContext context = JAXBContext.newInstance( obj.getClass() );
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
			context.createMarshaller().marshal( obj, out );
			return out.toString();
		} catch ( JAXBException e ) {
			SBSLOGGER.error( e.getMessage(), e );
		}

		return null;
	}

	/**
	 * Value Of
	 * Instantiates a generic class based on the string
	 * Currently, expects all string values to be JSON
	 * Note: Removed the URLDecoder since incoming values are not URL encoded
	 * @param <T> Generic Class
	 * @param clazz Class of <T>
	 * @param value String Serialization of <T>
	 * @return Instance of <T>
	 */
	public static <T> T valueOf( final Class<T> clazz, final String value )
	{
		if ( clazz.isInstance( Collections.EMPTY_LIST ) || clazz.isInstance( Collections.EMPTY_MAP )
				|| clazz.isInstance( Collections.EMPTY_SET ) ) {
			ObjectMapper om = ObjectMapperProvider.getObjectMapper();
			Type type = clazz.getGenericSuperclass();
			JavaType jt = om.getTypeFactory().constructType( type );
            return JsonUtil.<T> valueOfJSON(jt, value);
		}

		// Otherwise return the non-container valueOfJSON()
		return valueOfJSON( clazz, value );
	}

	/**
	 * Value Of
	 * Instantiates a generic class based on the string
	 * Currently, expects all string values to be JSON
	 * Note: Removed the URLDecoder since incoming values are not URL encoded
	 * @param <T> Class Type
	 * @param typeRef JavaType of "container"
	 * @param value String Serialization of <T>
	 * @return Instance of <T>
	 */
	public static <T> T valueOf( final JavaType typeRef, final String value )
	{
        return JsonUtil.<T> valueOfJSON(typeRef, value);
	}

	/**
	 * Value Of JSON for Containers- Deserializes an Object from JSON
	 * @param <T> Generic Type
	 * @param typeRef Type Reference of "container"
	 * @param json JSON String
	 * @return Instance of Class
	 */
	private static <T> T valueOfJSON( final JavaType typeRef, final String json )
	{
		ObjectMapper m = ObjectMapperProvider.getObjectMapper();
		StringReader sr = new StringReader( json );

		try {
			T result = m.readValue( sr, typeRef );
			return result;
		} catch ( JsonParseException e ) {
			SBSLOGGER.error( e.getMessage(), e );
		} catch ( JsonMappingException e ) {
			SBSLOGGER.error( e.getMessage(), e );
		} catch ( IOException e ) {
			SBSLOGGER.error( e.getMessage(), e );
		}

		return null;
	}

	/**
	 * Value Of JSON - Deserializes an Object from JSON
	 * @param <T> Generic Type
	 * @param clazz Class
	 * @param json JSON String
	 * @return Instance of Class
	 */
	private static <T> T valueOfJSON( final Class<T> clazz, final String json )
	{
		ObjectMapper m = ObjectMapperProvider.getObjectMapper();
		StringReader sr = new StringReader( json );

		try {
			T result = m.readValue( sr, clazz );
			return result;
		} catch ( JsonParseException e ) {
			SBSLOGGER.error( e.getMessage(), e );
		} catch ( JsonMappingException e ) {
			SBSLOGGER.error( e.getMessage(), e );
		} catch ( IOException e ) {
			SBSLOGGER.error( e.getMessage(), e );
		}

		return null;
	}

	/**
	 * To JSON String - Serializes an Object to JSON
	 * @param clazz CLass
	 * @param obj Object
	 * @return JSON String
	 */
	public static String toStringJSON( final Class<?> clazz, final Object obj )
	{
		ObjectMapper m = ObjectMapperProvider.getObjectMapper();
		StringWriter sw = new StringWriter();

		try {
			m.writeValue( sw, obj );
			return sw.toString();
		} catch ( JsonGenerationException e ) {
			SBSLOGGER.error( e.getMessage(), e );
		} catch ( JsonMappingException e ) {
			SBSLOGGER.error( e.getMessage(), e );
		} catch ( IOException e ) {
			SBSLOGGER.error( e.getMessage(), e );
		}

		return null;
	}
}
