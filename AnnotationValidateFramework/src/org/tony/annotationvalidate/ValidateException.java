/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tony.annotationvalidate;


/**
 *The validate exception
 * @author jiangtch
 */
public class ValidateException extends Exception{
    
	/**
	 * generatored serial ID
	 */
	private static final long serialVersionUID = -5374157736257347033L;

	public ValidateException(Exception ex){
    	super(ex.getMessage(), ex);
    }

    public ValidateException(String message){
        super(message);
    }

    public ValidateException(String message, Exception ex){
        super(message, ex);
    }
}
