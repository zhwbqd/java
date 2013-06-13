package org.tony.annotationvalidate;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Validate the @ValidateNotNull annotation
 * 
 * @author jiangtch
 * 
 */
public class ValidateNotNullHandler implements Handler {
	private static final Logger LOGGER = Logger.getLogger(ValidateNotNullHandler.class.getName());

	public void validate(AnnotationValidable validatedObj, Field field)
			throws ValidateException {
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.entering(this.getClass().getName(), "validate()");
		}
		if (field.isAnnotationPresent(ValidateNotNull.class)) {
			checkNotNull(validatedObj, field);
		}
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.entering(this.getClass().getName(), "validate()");
		}
	}

	private void checkNotNull(AnnotationValidable filter, Field field)
			throws ValidateException {
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.entering(this.getClass().getName(), "checkNotNull()");
		}
		String message = field.getAnnotation(ValidateNotNull.class).message();
		Object dest = null;
		try {
			dest = GetFiledValue.getField(filter, field.getName());
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE,
					"Get field value or cast value error. Error message: "
							+ ex.getMessage(), ex);
			throw new ValidateException(ex.getMessage(), ex);
		}
		if (dest == null) {
			LOGGER.log(Level.SEVERE,
					"Validate fail. Error message: validate value is:null");
			throw new ValidateException(message + "But the value of "
					+ field.getName() + " is Null.");
		}
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.exiting(this.getClass().getName(), "checkNotNull()");
		}
	}
}
