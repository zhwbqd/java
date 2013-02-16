
package zhwb.study.mailsender.utils;

/**
 * The Interface IVerificationMessages.
 */
public interface IVerificationMessages
{
    
    /** The postfix is required. */
    String POSTFIX_IS_REQUIRED = " is required.";

    /** The Constant POSTFIX_NO_NULLS_ALLOWED. */
    String POSTFIX_NO_NULLS_ALLOWED = " cannot be null";

    /** The Constant POSTFIX_EMPTY_NOT_ALLOWED. */
    String POSTFIX_EMPTY_NOT_ALLOWED = " cannot be null or empty";

    /** The Constant POSTFIX_NON_NEGATIVE. */
    String POSTFIX_NON_NEGATIVE = " cannot be negative";

    /** The Constant MSG_LOCALE_CODE. */
    String MSG_LOCALE_CODE_NULL = "A locale code" + POSTFIX_NO_NULLS_ALLOWED;

    /** The Constant MSG_INVALID_INPUTS. */
    String MSG_INVALID_INPUTS = "One or more inputs failed validation tests.";

    /** The Constant MSG_USERID_NULL. */
    String MSG_USERID_NULL = "User Id" + POSTFIX_NO_NULLS_ALLOWED;

    /** The msg tmplte null. */
    String MSG_TMPLTE_NULL = "Template String" + POSTFIX_NO_NULLS_ALLOWED;

    /** The msg tmplte param empty. */
    String MSG_TMPLTE_PARAM_EMPTY = "Template parameter" + POSTFIX_EMPTY_NOT_ALLOWED;

    /** The msg eml addr empty. */
    String MSG_EML_ADDR_EMPTY = "Email address list" + POSTFIX_EMPTY_NOT_ALLOWED;

    /** The msg appid null. */
    String MSG_APPID_NULL = "Application ID" + POSTFIX_NO_NULLS_ALLOWED;

    /** The msg notify type empty. */
    String MSG_NOTIFY_TYPE_EMPTY = "Notification type" + POSTFIX_NO_NULLS_ALLOWED;

    /** The msg user null. */
    String MSG_USER_NULL = "User" + POSTFIX_NO_NULLS_ALLOWED;

    /** The msg user type null. */
    String MSG_USER_TYPE_NULL = "User type code" + POSTFIX_NO_NULLS_ALLOWED;

    /** The msg eml tmplte param info null. */
    String MSG_EML_TMPLTE_PARAM_INFO_NULL = "EmailTemplateParameterInfo bean" + POSTFIX_NO_NULLS_ALLOWED;

    /** The msg tmplte notempty. */
    String MSG_TMPLTE_EMPTY = "Template string" + POSTFIX_EMPTY_NOT_ALLOWED;

    /** The msg eml body null. */
    String MSG_EML_BODY_NULL = "Email body" + POSTFIX_NO_NULLS_ALLOWED;

    /** The msg eml subjct null. */
    String MSG_EML_SUBJCT_NULL = "Email subject line" + POSTFIX_NO_NULLS_ALLOWED;

    /** The msg rcpt eml empty. */
    String MSG_RCPT_EML_EMPTY = "Email recipient email address" + POSTFIX_EMPTY_NOT_ALLOWED;

    /** The msg eml from null. */
    String MSG_EML_FROM_NULL = "From email address" + POSTFIX_NO_NULLS_ALLOWED;
}
