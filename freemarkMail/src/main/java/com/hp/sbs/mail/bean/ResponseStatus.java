/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package com.hp.sbs.mail.bean;

import java.util.ArrayList;
import java.util.List;

public class ResponseStatus
{
    private boolean isSuccess;

    private String errorMessage;

    private List<String> failEmailList = new ArrayList<String>();

    public List<String> getFailEmailList()
    {
        return failEmailList;
    }

    public void setFailEmail(String failEmailList)
    {
        this.failEmailList.add(failEmailList);
    }

    public boolean isSuccess()
    {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess)
    {
        this.isSuccess = isSuccess;
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
