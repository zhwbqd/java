/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tony.annotationvalidate;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Validate the @int annotation
 * 
 * @author jiangtch
 */
public class ValidateIntHandler implements Handler {
	private static final Logger LOGGER = Logger.getLogger(ValidateIntHandler.class.getName());

	public void validate(AnnotationValidable validatedObj, Field field)
			throws ValidateException {
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.entering(this.getClass().getName(), "validate()");
		}
		if (field.isAnnotationPresent(ValidateInt.class)) {
			checkInt(validatedObj, field);
		}
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.exiting(this.getClass().getName(), "validate()");
		}
	}

	/**
	 * validate the int type
	 * 
	 * @param filter
	 *            validated object
	 * @param field
	 *            validated field or property
	 * @throws ValidateException
	 */
	private void checkInt(AnnotationValidable filter, Field field)
			throws ValidateException {
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.entering(this.getClass().getName(), "checkInt()");
		}
		ValidateInt annotation = field.getAnnotation(ValidateInt.class);
		int min = annotation.min();
		int max = annotation.max();
		String message = annotation.message();

		Integer destValue = null;
		try {
			destValue = (Integer) GetFiledValue
					.getField(filter, field.getName());
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE,
					"Get field value or cast value error. Error message: "
							+ ex.getMessage(), ex);
			throw new ValidateException(ex.getMessage(), ex);
		}

                if(destValue == null) {
                    return; //NULL value is allowed.
                }
                int value = destValue.intValue();

                if (value < min) {
                        LOGGER.log(Level.SEVERE, "Validate fail. Error message: validate value is:{0},min value is:{1}", new Integer[]{value, min});
                        throw new ValidateException(message + "The value is:" + value
                                        + ",The min value should is:" + min);
                }

                if (value > max) {
                        LOGGER.log(Level.SEVERE, "Validate fail. Error message: validate value is:{0},max value is:{1}", new Integer[]{value, max});
                        throw new ValidateException(message + "The value is:" + value
                                        + ",The max value should is:" + max);
                }

                if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.exiting(this.getClass().getName(), "checkInt()");
		}
	}

}
