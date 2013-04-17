package net.roseindia.controllers;

import net.roseindia.form.ValidationForm;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/validationform.html")
public class ValidationController {

	// Display the form on the get request
	@RequestMapping(method = RequestMethod.GET)
	public String showValidatinForm(Map model) {
		ValidationForm validationForm = new ValidationForm();
		model.put("validationForm", validationForm);
		return "validationform";
	}

	// Process the form.
	@RequestMapping(method = RequestMethod.POST)
	public String processValidatinForm(@Valid ValidationForm validationForm,
			BindingResult result, Map model) {
		if (result.hasErrors()) {
			return "validationform";
		}
		// Add the saved validationForm to the model
		model.put("validationForm", validationForm);
		return "validationsuccess";
	}

}
