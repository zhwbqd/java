/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.javamail.bean;

import java.util.List;
import java.util.Map;

public class ResponseStatus
{
    private boolean isSuccess;

    private Map<String, String> errorMapping;

    private List<String> successEmails;

    private String errorMessage;

    public boolean isSuccess()
    {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess)
    {
        this.isSuccess = isSuccess;
    }

    public Map<String, String> getErrorMapping()
    {
        return errorMapping;
    }

    public void setErrorMapping(Map<String, String> errorMapping)
    {
        this.errorMapping = errorMapping;
    }

    public List<String> getSuccessEmails()
    {
        return successEmails;
    }

    public void setSuccessEmails(List<String> successEmails)
    {
        this.successEmails = successEmails;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

}
