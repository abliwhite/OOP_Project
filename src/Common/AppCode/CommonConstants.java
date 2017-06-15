package Common.AppCode;

import java.util.Arrays;
import java.util.List;

public class CommonConstants {

	public static final String ONLINE_USER_ATTRIBUTE_NAME = "user";
	public static final String AJAX_DATA_ATTRIBUTE_NAME = "data";

	public static final String UNSUCCESSFUL_REGISTRATION_USERNAME_CASE = "Username is already in use!";
	public static final String UNSUCCESSFUL_REGISTRATION_EMAIL_CASE = "Email is already in use!";
	public static final String UNSUCCESSFUL_REGISTRATION = "Please enter another username or email!";
	public static final String SUCCESSFUL_MESSAGE = "Success!";
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static String getDatetime() {
		java.util.Date dateTime = new java.util.Date();

		java.text.SimpleDateFormat f = new java.text.SimpleDateFormat(DATETIME_FORMAT);

		return f.format(dateTime);
	}

}
