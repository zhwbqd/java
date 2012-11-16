package zhwb.ldap;

public class CoreServiceException extends RuntimeException
{
	/** Serialization UID */
	private static final long serialVersionUID = -2273851219063514588L;

	/** Status Code - Unique Identifier Code for the Status */
	private int code;

	/** Status Description - Explanation for the Status Code's Usage */
	private String description;

	/**
	 * Status Code Constructor
	 * @param errorCode Status Code
	 * @param errorDescription Status Description
	 */
	public CoreServiceException( final int errorCode, final String errorDescription )
	{
		super( errorCode + " : " + errorDescription );
		this.code = errorCode;
		this.description = errorDescription;
	}

	/**
	 * Status Code Constructor
	 * @param errorCode Status Code
	 * @param errorDescription Status Description
	 * @param cause Throwable Cause
	 */
	public CoreServiceException( final int errorCode, final String errorDescription, final Throwable cause )
	{
		super( errorCode + " : " + errorDescription, cause );
		this.code = errorCode;
		this.description = errorDescription;
	}

	/**
	 * Default Constructor
	 */
	public CoreServiceException()
	{
		super();
	}

	/**
	 * String Constructor
	 * @param message Status Message
	 */
	public CoreServiceException( final String message )
	{
		super( message );
	}

	/**
	 * Status Message & Exception Constructor
	 * @param message Status Message
	 * @param cause Throwable Exception
	 */
	public CoreServiceException( final String message, final Throwable cause )
	{
		super( message, cause );
	}

	/**
	 * Exception Constructor
	 * @param cause Throwable Exception
	 */
	public CoreServiceException( final Throwable cause )
	{
		super( cause );
	}

	/**
	 * Get Status Code
	 * @return Status Code
	 */
	public final int getCode()
	{
		return code;
	}

	/**
	 * Set Status Code
	 * @param code Status Code
	 */
	public final void setCode( final int code )
	{
		this.code = code;
	}

	/**
	 * Get Status Description
	 * @return Status Description
	 */
	public final String getDescription()
	{
		return description;
	}

	/**
	 * Set Status Description
	 * @param description Status Description
	 */
	public final void setDescription( final String description )
	{
		this.description = description;
	}
}
