package Database;

import java.util.Arrays;
import java.util.List;

public final class DbCertificate {

	public static final int DB_ID_COLUMN_INDEX = 1;

	private DbCertificate() {
	}

	public static class UserTable {

		public final static String TABLE_NAME = "users";
		// column names
		public final static String COLUMN_NAME_ID = "ID";
		public final static String COLUMN_NAME_USERNAME = "Username";
		public final static String COLUMN_NAME_PASSWORD = "Password";
		public final static String COLUMN_NAME_EMAIL = "Email";
		public final static String COLUMN_NAME_ROLE = "Role";
		public final static String COLUMN_NAME_GMAIL_ID = "GmailID";
		public final static String COLUMN_NAME_FACEBOOK_ID = "FacebookID";
		public final static String COLUMN_NAME_PROFILE_ID = "ProfileID";

		public static final String ADMIN_ROLE = "Admin";
		public static final String STUDENT_ROLE = "Student";

		public static final String ADMIN_USERNAME = "admin";
		public static final String ADMIN_PASSWORD = "admin";
	}

	public static class UserSubjectTable {

		public final static String TABLE_NAME = "user_subjects";
		// column names
		public final static String COLUMN_NAME_ID = "ID";
		public final static String COLUMN_NAME_USER_ID = "UserID";
		public final static String COLUMN_NAME_SUBJECT_ID = "SubjectID";
	}

	public static class ProfileTable {
		public final static String TABLE_NAME = "user_profiles";

		public final static String COLUMN_NAME_ID = "ID";
		public final static String COLUMN_NAME_NAME = "Name";
		public final static String COLUMN_NAME_SURNAME = "Surname";
		public final static String COLUMN_NAME_GENDER = "Gender";
		public final static String COLUMN_NAME_CREATE_DATE = "CreateDate";

		public static final List<String> GENDER = Arrays.asList("Male", "Female");
	}

	public static class SubjectTermTable {
		public final static String TABLE_NAME = "subject_terms";

		public final static String COLUMN_NAME_ID = "ID";
		public final static String UNIQUE_COLUMN_NAME_ID = TABLE_NAME + "." + COLUMN_NAME_ID;
		public final static String ALTERNATIVE_COLUMN_NAME_ID = "subjectTermID";

		public final static String COLUMN_NAME_NAME = "Name";
		public final static String UNIQUE_COLUMN_NAME_NAME = TABLE_NAME + "." + COLUMN_NAME_NAME;
		public final static String ALTERNATIVE_COLUMN_NAME_NAME = "subjectTermName";
	}

	public static class SubjectTable {
		public final static String TABLE_NAME = "subject";

		public final static String COLUMN_NAME_ID = "ID";
		public final static String UNIQUE_COLUMN_NAME_ID = TABLE_NAME + "." + COLUMN_NAME_ID;

		public final static String COLUMN_NAME_NAME = "Name";
		public final static String UNIQUE_NAME_NAME = TABLE_NAME + "." + COLUMN_NAME_NAME;

		public final static String COLUMN_NAME_TERM_ID = "TermID";
		public final static String COLUMN_NAME_YEAR = "Year";
		public final static String COLUMN_NAME_SUBJECT_INFO_ID = "SubjectInfoID";

	}

	public static class SubjectInfoTable {
		public final static String TABLE_NAME = "subject_info";

		public final static String COLUMN_NAME_ID = "ID";
		public final static String UNIQUE_COLUMN_NAME_ID = TABLE_NAME + "." + COLUMN_NAME_ID;
		public final static String ALTERNATIVE_COLUMN_NAME_ID = "subjectInfoId";

		public final static String COLUMN_NAME_LECTURER_NAME = "LecturerName";
		public final static String COLUMN_NAME_SYLLABUS_PATH = "SyllabusPath";
		public final static String COLUMN_NAME_ECTS = "Ects";
		public final static String COLUMN_NAME_LANGUAGE = "Language";
	}

	public static class SubjectComponentTypeTable {
		public final static String TABLE_NAME = "subject_component_types";

		public final static String COLUMN_NAME_ID = "ID";
		public final static String UNIQUE_COLUMN_NAME_ID = TABLE_NAME + "." + COLUMN_NAME_ID;

		public final static String COLUMN_NAME_NAME = "Name";

	}

	public static class CommonSubjectComponentTable {
		public final static String TABLE_NAME = "common_subject_components";

		public final static String COLUMN_NAME_ID = "ID";
		public final static String UNIQUE_COLUMN_NAME_ID = TABLE_NAME + "." + COLUMN_NAME_ID;

		public final static String COLUMN_NAME_TYPE_ID = "TypeID";
		public final static String COLUMN_NAME_MARKPERCENTAGE = "MarkPercentage";
		public final static String COLUMN_NAME_NUMBER = "Number";
		public final static String COLUMN_NAME_SUBJECT_ID = "SubjectID";
	}

	public static class SubjectComponentMaterialTable {
		public final static String TABLE_NAME = "subject_component_materials";

		public final static String COLUMN_NAME_ID = "ID";
		public final static String COLUMN_NAME_MATERIAL_PATH = "MaterialPath";
		public final static String COLUMN_NAME_UPLOAD_DATE = "UploadDate";
		public final static String COLUMN_NAME_SUBJECT_COMPONENT_ID = "SubjectComponentID";
	}
	public static class UserSubjectComponentTable{
		public final static String TABLE_NAME = "user_subject_components";

		public final static String COLUMN_NAME_ID = "ID";
		public final static String COLUMN_NAME_USER_ID = "UserID";
		public final static String COLUMN_NAME_COMMON_SUBJECT_COMPONENT_ID = "CommonSubjectComponentID";
		public final static String COLUMN_NAME_DEAD_LINE_DATE = "DeadLineDate";
	}
}
