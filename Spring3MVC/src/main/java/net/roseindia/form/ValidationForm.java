package net.roseindia.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class ValidationForm {
	@NotEmpty
	@Size(min = 1, max = 20)
	private String userName;
	@NotNull
	@NumberFormat(style = Style.NUMBER)
	@Min(1)
	@Max(110)
	private Integer age;
	@NotEmpty(message = "Password must not be blank.")
	@Size(min = 1, max = 10, message = "Password must between 1 to 10 Characters.")
	private String password;

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge() {
		return age;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
}
