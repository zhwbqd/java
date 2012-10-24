/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package com.hp.test;

public class ParentException extends Exception
{
    /**
     * serialVersionUID
     * long
     */
    private static final long serialVersionUID = 1L;

    public ParentException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
