/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tony.annotationvalidate;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *Validate the @ValidatePattern annotation
 * 
 * @author jiangtch
 */
public class ValidatePatternHandler implements Handler {
	private static final Logger LOGGER = Logger.getLogger(ValidatePatternHandler.class.getName());

	public void validate(AnnotationValidable validatedObj, Field field)
			throws ValidateException {
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.entering(this.getClass().getName(), "validate()");
		}
		if (field.isAnnotationPresent(ValidatePattern.class)) {
			checkString(validatedObj, field);
		}
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.exiting(this.getClass().getName(), "validate()");
		}
	}

	/**
	 * validate the String format
	 * 
	 * @param filter
	 *            validated object
	 * @param field
	 *            validated field or property
	 * @throws ValidateException
	 */
	private void checkString(AnnotationValidable filter, Field field)
			throws ValidateException {
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.entering(this.getClass().getName(), "checkString()");
		}
		ValidatePattern annotation = field.getAnnotation(ValidatePattern.class);
		String patternStr = annotation.pattern();
		String message = annotation.message();
		Object value = null;
		try {
			value = GetFiledValue.getField(filter, field.getName());
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE,
					"Get field value or cast value error. Error message: "
							+ ex.getMessage(), ex);
			throw new ValidateException(ex.getMessage(), ex);
		}
		checkStringFormat(patternStr, value, message);
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.exiting(this.getClass().getName(), "checkString()");
		}
	}

	/**
	 * validate the String format
	 * 
	 * @param patternStr
	 *            validate regex pattern
	 * @param value
	 *            validated value
	 * @param message
	 *            exception message
	 * @throws ValidateException
	 */
	private void checkStringFormat(String patternStr, Object value,
			String message) throws ValidateException {
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.entering(this.getClass().getName(), "checkStringFormat()");
		}
		String checkStr = (String) value;
		Pattern pattern = Pattern.compile(patternStr);
		if (checkStr != null) {
			String[] str = checkStr.split(",");
			for (int i = 0; i < str.length; i++) {
				if (!pattern.matcher(str[i]).matches()) {
					LOGGER.log(Level.SEVERE,
							"Validate fail. Error message: validate value is:"
									+ str[i]);
					throw new ValidateException(message + "The value is:"
							+ checkStr);
				}
			}
		}
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.exiting(this.getClass().getName(), "checkStringFormat()");
		}
	}

}
