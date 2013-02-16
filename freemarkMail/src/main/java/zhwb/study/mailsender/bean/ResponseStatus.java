
package zhwb.study.mailsender.bean;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class ResponseStatus
{
    private boolean isSuccess;

    private String errorMessage;

    private Map<String, String> errorMap = new LinkedHashMap<String, String>();

    private Set<String> failEmailList = new LinkedHashSet<String>();

    private Set<String> successEmailList = new LinkedHashSet<String>();

    public Map<String, String> getErrorMap()
    {
        return errorMap;
    }

    public void addIntoErrorMap(final String key, final String value)
    {
        errorMap.put(key, value);
    }

    public Set<String> getSuccessEmailList()
    {
        return successEmailList;
    }

    public void setSuccessEmail(final String successEmail)
    {
        this.successEmailList.add(successEmail);
    }

    public Set<String> getFailEmailList()
    {
        return failEmailList;
    }

    public void setFailEmail(final String failEmail)
    {
        this.failEmailList.add(failEmail);
    }

    public boolean isSuccess()
    {
        return isSuccess;
    }

    public void setSuccess(final boolean isSuccess)
    {
        this.isSuccess = isSuccess;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage)
    {
        this.errorMessage = errorMessage;
    }
}
