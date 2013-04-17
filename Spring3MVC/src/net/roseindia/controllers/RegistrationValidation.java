package net.roseindia.controllers;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import net.roseindia.form.Registration;

@Component("registrationValidator")
public class RegistrationValidation {
	public boolean supports(Class<?> klass) {
		return Registration.class.isAssignableFrom(klass);
	}

	public void validate(Object target, Errors errors) {
		Registration registration = (Registration) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName",
				"NotEmpty.registration.userName",
				"User Name must not be Empty.");
		String userName = registration.getUserName();
		if ((userName.length()) > 50) {
			errors.rejectValue("userName",
					"lengthOfUser.registration.userName",
					"User Name must not more than 50 characters.");
		}
		if (!(registration.getPassword()).equals(registration
				.getConfirmPassword())) {
			errors.rejectValue("password",
					"matchingPassword.registration.password",
					"Password and Confirm Password Not match.");
		}
	}
}
