/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tony.annotationvalidate;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Validate the @ValidateSize annotation
 * 
 * @author jiangtch
 */
public class ValidateSizeHandler implements Handler {
	private static final Logger LOGGER = Logger.getLogger(ValidateSizeHandler.class.getName());

	public void validate(AnnotationValidable validatedObj, Field field)
			throws ValidateException {
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.entering(this.getClass().getName(), "validate()");
		}
		if (field.isAnnotationPresent(ValidateSize.class)) {
			checkSize(validatedObj, field);
		}
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.exiting(this.getClass().getName(), "validate()");
		}
	}

	/**
	 * validate the String length
	 * 
	 * @param filter
	 *            filter validated object
	 * @param field
	 *            validated field or property
	 * @throws ValidateException
	 */
	private void checkSize(AnnotationValidable filter, Field field)
			throws ValidateException {
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.entering(this.getClass().getName(), "checkSize()");
		}
		ValidateSize annotation = field.getAnnotation(ValidateSize.class);
		String minSize = annotation.minSize();
		String maxSize = annotation.maxSize();
		String message = annotation.message();
		int min = 0;
		int max = 0;
		if (!"".equals(minSize)) {
			min = Integer.parseInt(minSize);
		}
		if (!"".equals(maxSize)) {
			max = Integer.parseInt(maxSize);
		}
		String value = null;
		try {
			value = (String) GetFiledValue.getField(filter, field.getName());
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE,
					"Get field value or cast value error. Error message: "
							+ ex.getMessage(), ex);
			throw new ValidateException(ex.getMessage(), ex);
		}
		int size = 0;
		if (!"".equals(value) && value != null) {
			size = value.length();
		}
		if (!"".equals(minSize) && !"".equals(maxSize)) {
			if (size < min || size > max) {
				LOGGER.log(Level.SEVERE,
						"Validate fail. Error message: validate size is:"
								+ size + ",minSize value is:" + min
								+ ",maxSize value is:" + max);
				throw new ValidateException(message + "The size is:" + size
						+ ",The min size should is:" + min
						+ ",the max size should is:" + max);
			}
		}
		if (!"".equals(minSize) && "".equals(maxSize)) {
			if (size < min) {
				LOGGER.log(Level.SEVERE,
						"Validate fail. Error message: validate size is:"
								+ size + ",minSize value is:" + min);
				throw new ValidateException(message + "The size is:" + size
						+ ",The min size should is:" + min);
			}
		}
		if ("".equals(minSize) && !"".equals(maxSize)) {
			if (size > max) {
				LOGGER.log(Level.SEVERE,
						"Validate fail. Error message: validate size is:"
								+ size + ",maxSize value is:" + max);
				throw new ValidateException(message + "The size is:" + size
						+ ",The max size should is:" + max);
			}
		}
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.entering(this.getClass().getName(), "checkSize()");
		}
	}
}
