package Database;

import java.util.Arrays;
import java.util.List;

public class DbCertificate {
	
	public static final List<String> GENDER = Arrays.asList("Male","Female");
	
	public static final String ADMIN_ROLE = "Admin";
	public static final String STUDENT_ROLE = "Student";
	public static final int DB_ID_COLUMN_INDEX = 1;
	
	//table names
	public static final String PROFILE_TABLE_NAME = "user_profiles";
	public static final String USER_TABLE_NAME = "users";
	
}
