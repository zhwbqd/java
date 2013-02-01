package zhwb.study.mailsender.bean;
public class ResponseStatusCode
{

    /** The Constant SUCCESS. */
    public static final int SUCCESS = 1;

    /** The Constant EMAIL_VERIFICATION_FAILED. */
    public static final int EMAIL_VERIFICATION_FAILED = 2;

    /** The Constant EMAIL_TEMPLATE_PROCESS_FAILED. */
    public static final int EMAIL_TEMPLATE_PROCESS_FAILED = 4;

    /** The Constant WORKING_QUEUE_FULL. */
    public static final int WORKING_QUEUE_FULL = 5;

    /** The status code. */
    private int statusCode;

    /**
     * Gets the status code.
     *
     * @return the status code
     */
    public final int getStatusCode()
    {
        return statusCode;
    }

    /**
     * Sets the status code.
     *
     * @param statusCode the new status code
     */
    public final void setStatusCode(final int statusCode)
    {
        this.statusCode = statusCode;
    }

    /**
     * Instantiates a new response status code.
     */
    public ResponseStatusCode()
    {
        super();
    }

    /**
     * Instantiates a new response status code.
     *
     * @param status the status
     */
    public ResponseStatusCode(final int status)
    {
        super();
        this.statusCode = status;
    }
}