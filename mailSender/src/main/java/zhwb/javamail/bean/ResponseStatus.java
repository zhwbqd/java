/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package zhwb.javamail.bean;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ResponseStatus
{
    private boolean isSuccess;

	private Set<String> failEmails = new LinkedHashSet<String>();
	private Set<String> successEmails = new LinkedHashSet<String>();
	private List<String> errorMessages = new ArrayList<String>();

	public List<String> getFailEmails() {
		return failEmails;
	}

	public List<String> getSuccessEmails() {
		return successEmails;
	}

    public boolean isSuccess()
    {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess)
    {
        this.isSuccess = isSuccess;
    }

	public void setFailEmail(String mail) {
		failEmails.add(mail);
	}

	public void setSuccessEmail(String mail) {
		successEmails.add(mail);
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessage(String errorMessage) {
		errorMessages.add(errorMessage);
	}

}
