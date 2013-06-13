/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tony.annotationvalidate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *This annotation is used to validate String is digit only
 * @author jiangtch
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValidateDigit {

    String message() default "The value should be digit only.";
}
