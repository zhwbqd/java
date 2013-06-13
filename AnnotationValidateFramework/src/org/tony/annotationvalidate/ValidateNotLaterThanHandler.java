/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tony.annotationvalidate;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Validate the @ValidateBeforeThan annotation
 * 
 * @author jiangtch
 */
public class ValidateNotLaterThanHandler implements Handler {
	private static final Logger LOGGER = Logger.getLogger(ValidateNotLaterThanHandler.class.getName());

	public void validate(AnnotationValidable validatedObj, Field field)
			throws ValidateException {
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.entering(this.getClass().getName(), "validate()");
		}
		if (field.isAnnotationPresent(ValidateNotLaterThan.class)) {
			checkTime(validatedObj, field);
		}
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.exiting(this.getClass().getName(), "validate()");
		}
	}

	/**
	 * validate if the start time is early than the end time
	 * 
	 * @param filter
	 *            validated object
	 * @param field
	 *            validated field or property
	 * @throws ValidateException
	 */
	private void checkTime(AnnotationValidable filter, Field field)
			throws ValidateException {
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.entering(this.getClass().getName(), "checkTime()");
		}
		ValidateNotLaterThan annotation = field
				.getAnnotation(ValidateNotLaterThan.class);
		String sTime = field.getName();
		String eTime = annotation.laterTime();
		String message = annotation.message();
		Date startTime = null;
		Date endTime = null;
		try {
			startTime = (Date) GetFiledValue.getField(filter, sTime);
			endTime = (Date) GetFiledValue.getField(filter, eTime);
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE,
					"Get field value or cast value error. Error message: "
							+ ex.getMessage(), ex);
			throw new ValidateException(ex.getMessage(), ex);
		}
		if (startTime != null && endTime != null) {
			if (startTime.after(endTime)) {
				LOGGER.log(Level.SEVERE,
						"Validate fail. Error message: startTime is:"
								+ startTime + ", endTime is:" + endTime);
				throw new ValidateException(message + "The startTime is:"
						+ startTime + ",the endTime is:" + endTime);
			}
		}else{
			LOGGER.log(Level.SEVERE,
					"Validate fail. Error message: startTime or endTime is null,startTime is:"
							+ startTime + ", endTime is:" + endTime);
			throw new ValidateException(message + "StartTime or endTime is null.The startTime is:"
					+ startTime + ",the endTime is:" + endTime);
		}
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.exiting(this.getClass().getName(), "checkTime()");
		}
	}
}
