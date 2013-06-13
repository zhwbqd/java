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
 *This annotation is used to validate the string contain specify value.
 * @author jiangtch
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValidateStringIn {
String value();
String message() default "The value is not expected.";
}
