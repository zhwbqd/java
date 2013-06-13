/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tony.annotationvalidate;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *validate the @ValidateStringIn annotation
 * 
 * @author jiangtch
 */
public class ValidateStringInHandler implements Handler {
	private static final Logger LOGGER = Logger.getLogger(ValidateStringInHandler.class.getName());

	public void validate(AnnotationValidable validatedObj, Field field)
			throws ValidateException {
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.entering(this.getClass().getName(), "validate()");
		}
		if (field.isAnnotationPresent(ValidateStringIn.class)) {
			checkStringIn(validatedObj, field);
		}
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.exiting(this.getClass().getName(), "validate()");
		}
	}

	/**
	 * validate the String value
	 * 
	 * @param filter
	 *            filter validated object
	 * @param field
	 *            validated field or property
	 * @throws ValidateException
	 */
	private void checkStringIn(AnnotationValidable filter, Field field)
			throws ValidateException {
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.entering(this.getClass().getName(), "checkStringIn()");
		}
		ValidateStringIn annotation = field
				.getAnnotation(ValidateStringIn.class);
		String exceptValue = annotation.value();
		String message = annotation.message();
		String value = null;
		try {
			value = (String) GetFiledValue.getField(filter, field.getName());
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE,
					"Get field value or cast value error. Error message:"
							+ ex.getMessage(), ex);
			throw new ValidateException(ex.getMessage(), ex);
		}
		if (!"".equals(value) && value != null) {
			String[] validateValues = exceptValue.split(",");
			List<String> list = Arrays.asList(validateValues);
			if (!list.contains(value)) {
				LOGGER.log(Level.SEVERE,
						"Validate fail. Error message: validate value is:"
								+ value);
				throw new ValidateException(message + "this field value is:"
						+ value + ",the excepted value is:"
						+ Arrays.asList(validateValues));
			}
		}
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.entering(this.getClass().getName(), "checkStringIn()");
		}
	}
}
