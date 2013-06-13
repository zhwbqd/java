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
 *This annotation is used to validate String,it support regex.
 * @author jiangtch
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValidatePattern {

    String pattern();

    String message() default "This value is not valid format.";
}
