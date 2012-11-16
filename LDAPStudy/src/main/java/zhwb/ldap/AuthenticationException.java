package zhwb.ldap;

public class AuthenticationException extends Exception
{
	/** Serialization UID */
	private static final long serialVersionUID = 6424669635725543115L;

	/** Exception Message */
	private String message;

	/**
	 * Constructor
	 */
	public AuthenticationException()
	{
		super();
	}

	/**
	 * Message Constructor
	 * @param message Exception Message
	 */
	public AuthenticationException( final String message )
	{
		super( message );
		this.message = message;
	}

	/**
	 * Message and Throwable Constructor
	 * @param message Exception Message
	 * @param cause Throwable to copy
	 */
	public AuthenticationException( final String message, final Throwable cause )
	{
		super( message, cause );
		this.message = message;
	}

	/**
	 * Get Message
	 * @return message Exception Message
	 */
	@Override
	public final String getMessage()
	{
		return message;
	}
}
