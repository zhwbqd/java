/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package com.hp.test;

public class ChildException extends ParentException
{

    /**
     * serialVersionUID
     * long
     */
    private static final long serialVersionUID = 1L;

    public ChildException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
