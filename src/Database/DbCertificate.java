package Database;

import java.util.Arrays;
import java.util.List;

public final class DbCertificate {
	
	public static final int DB_ID_COLUMN_INDEX = 1;

	private DbCertificate() {
    }

	
	public static class UserTable {
		
		public final static String TABLE_NAME = "users";
		//column names
		public final static String COLUMN_NAME_ID = "ID";
		public final static String COLUMN_NAME_USERNAME = "Username";
		public final static String COLUMN_NAME_PASSWORD = "Password";
		public final static String COLUMN_NAME_EMAIL = "Email";
		public final static String COLUMN_NAME_ROLE = "Role";
		public final static String COLUMN_NAME_GMAIL_ID= "GmailID";
		public final static String COLUMN_NAME_FACEBOOK_ID = "FacebookID";
		public final static String COLUMN_NAME_PROFILE_ID = "ProfileID";
		
		public static final String ADMIN_ROLE = "Admin";
		public static final String STUDENT_ROLE = "Student";
	}

	public static class ProfileTable {
		public final static String TABLE_NAME = "user_profiles";
		
		public final static String COLUMN_NAME_ID ="ID";
		public final static String COLUMN_NAME_NAME ="Name";
		public final static String COLUMN_NAME_SURNAME ="Surname";
		public final static String COLUMN_NAME_GENDER ="Gender";
		public final static String COLUMN_NAME_CREATE_DATE ="CreateDate";
		public static final List<String> GENDER = Arrays.asList("Male", "Female");
	}
}
