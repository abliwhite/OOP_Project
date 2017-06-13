package Common.AppCode;

import java.util.Arrays;
import java.util.List;

public class CommonConstants {

	public static final String AJAX_DATA_ATTRIBUTE_NAME ="data";
	
	public static final int DB_ID_COLUMN_INDEX = 1;
	
	public static final List<String> GENDER = Arrays.asList("Male","Female");
		
	public static final String ADMIN_ROLE = "Admin";
	public static final String STUDENT_ROLE = "Student";
	
	public static final String UNSUCCESSFULL_REGISTRATION_USERNAME_CASE = "Username is already in use!";
	public static final String UNSUCCESSFULL_REGISTRATION_EMAIL_CASE = "Email is already in use!";
	public static final String SUCCESSFULL_MESSAGE = "Success!";
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static String getDatetime(){
		java.util.Date dateTime = new java.util.Date();

		java.text.SimpleDateFormat f = 
		     new java.text.SimpleDateFormat(DATETIME_FORMAT);

		return  f.format(dateTime);
	}
	
}
