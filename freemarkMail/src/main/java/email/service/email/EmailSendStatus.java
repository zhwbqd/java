/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package email.service.email;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The Class EmailSendStatus.
 */
public final class EmailSendStatus
{
    /** The need retry. */
    private boolean needRetry;

    /** The error message. */
    private String errorMessage;

    /** The fail email list. */
    private Set<String> failEmailList = new LinkedHashSet<String>();

    /** The success email list. */
    private Set<String> successEmailList = new LinkedHashSet<String>();

    /**
     * Gets the success email list.
     *
     * @return the success email list
     */
    public Set<String> getSuccessEmailList()
    {
        return successEmailList;
    }

    /**
     * Sets the success email.
     *
     * @param successEmail the new success email
     */
    public void addSuccessEmail(final String successEmail)
    {
        this.successEmailList.add(successEmail);
    }

    /**
     * Gets the fail email list.
     *
     * @return the fail email list
     */
    public Set<String> getFailEmailList()
    {
        return failEmailList;
    }

    /**
     * Sets the fail email.
     *
     * @param failEmail the new fail email
     */
    public void addFailEmail(final String failEmail)
    {
        this.failEmailList.add(failEmail);
    }

    /**
     * Gets the error message.
     *
     * @return the error message
     */
    public String getErrorMessage()
    {
        return errorMessage;
    }

    /**
     * Sets the error message.
     *
     * @param errorMessage the new error message
     */
    public void setErrorMessage(final String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    /**
     * Checks if is need retry.
     *
     * @return true, if is need retry
     */
    public boolean isNeedRetry()
    {
        return needRetry;
    }

    /**
     * Sets the need retry.
     *
     * @param needRetry the new need retry
     */
    public void setNeedRetry(final boolean needRetry)
    {
        this.needRetry = needRetry;
    }

    /**
     * Set the property successEmailList.
     *
     * @param successEmailList the successEmailList to set
     */
    public void setSuccessEmailList(final Set<String> successEmailList)
    {
        this.successEmailList = successEmailList;
    }
}
