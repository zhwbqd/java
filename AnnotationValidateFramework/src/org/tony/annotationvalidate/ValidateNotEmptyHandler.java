package org.tony.annotationvalidate;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Validate the @ValidateNotNull annotation
 * 
 * @author jiangtch
 * 
 */
public class ValidateNotEmptyHandler implements Handler {

	private static final Logger LOGGER = Logger.getLogger(ValidateNotEmptyHandler.class.getName());

	public void validate(AnnotationValidable validatedObj, Field field)
			throws ValidateException {
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.entering(this.getClass().getName(), "validate()");
		}
		if (field.isAnnotationPresent(ValidateNotEmpty.class)) {
			checkNotEmpty(validatedObj, field);
		}
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.entering(this.getClass().getName(), "validate()");
		}
	}

	@SuppressWarnings("unchecked")
	private void checkNotEmpty(AnnotationValidable filter, Field field)
			throws ValidateException {
		if (LOGGER.isLoggable(Level.FINER)) {
			LOGGER.entering(this.getClass().getName(), "checkNotNull()");
		}
		String message = field.getAnnotation(ValidateNotEmpty.class).message();
		Collection dest = null;
		try {
			dest = (Collection) GetFiledValue.getField(filter, field.getName());
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE,
					"Get field value or cast value error. Error message: "
							+ ex.getMessage(), ex);
			throw new ValidateException(ex.getMessage(), ex);
		}
		if (dest != null) {
			if (dest.size() == 0) {
				LOGGER.log(Level.SEVERE,
						"The collection "+ field.getName() +" is empty.");
				throw new ValidateException(message + "The collection of "
						+ field.getName() + " size is:0");
			}
		}
	}

}
