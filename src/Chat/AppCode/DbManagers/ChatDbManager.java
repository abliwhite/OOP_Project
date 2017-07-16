package Chat.AppCode.DbManagers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;

import com.mysql.jdbc.Statement;

import Chat.Models.DbModels.GroupChat;
import Chat.Models.DbModels.Lobby;
import Common.AppCode.DaoController;
import Database.DbCertificate;
import Subject.Models.DbModels.SubjectComponentType;

public class ChatDbManager extends DaoController implements ChatDbManagerInterface {

	public static final String CHAT_DB_MANAGER_ATTRIBUTE = "Chat Db Manager Attribute";

	private List<String> groupChatColumnNames;

	public ChatDbManager(DataSource pool) {
		super(pool);
		groupChatColumnNames = getColumnsNames(DbCertificate.GroupChatTable.TABLE_NAME);
	}

	@Override
	public List<GroupChat> getAllGroupChat() {
		List<GroupChat> result = new ArrayList<GroupChat>();

		try {
			java.sql.Connection con = getConnection();
			java.sql.Statement st = con.createStatement();

			String selectAllQuery = generator.getSelectAllQuery(DbCertificate.GroupChatTable.TABLE_NAME);

			ResultSet rs = st.executeQuery(selectAllQuery);

			result = getGroupChatList(rs);

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private List<GroupChat> getGroupChatList(ResultSet rs) throws SQLException {
		List<GroupChat> result = new ArrayList<GroupChat>();

		while (rs.next()) {
			int id = rs.getInt(DbCertificate.GroupChatTable.COLUMN_NAME_ID);
			int creatorID = rs.getInt(DbCertificate.GroupChatTable.COLUMN_NAME_CREATOR_ID);
			int activeStatusID = rs.getInt(DbCertificate.GroupChatTable.COLUMN_NAME_ACTIVE_STATUS_ID);
			int lobbyID = rs.getInt(DbCertificate.GroupChatTable.COLUMN_NAME_LOBBY_ID);
			int privacyStatusID = rs.getInt(DbCertificate.GroupChatTable.COLUMN_NAME_PRIVACY_STATUS_ID);
			String name = rs.getString(DbCertificate.GroupChatTable.COLUMN_NAME_NAME);
			String createDate = rs.getString(DbCertificate.GroupChatTable.COLUMN_NAME_CREATE_DATE);

			GroupChat temp = new GroupChat(id, name, createDate, lobbyID, creatorID, privacyStatusID, activeStatusID);
			result.add(temp);
		}

		return result;
	}

	@Override
	public void addGroupChat(GroupChat groupChat) {
		try {
			java.sql.Connection con = getConnection();
			String insertQuery = generator.getInsertQuery(groupChatColumnNames,
					DbCertificate.GroupChatTable.TABLE_NAME);

			con.createStatement().executeQuery(generator.getUseDatabaseQuery());
			java.sql.PreparedStatement st = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

			setValues(getGroupChatValues(groupChat), st);
			st.executeUpdate();

			try (ResultSet generatedKeys = st.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					groupChat.setId(generatedKeys.getInt(1));
				} else {
					throw new SQLException();
				}
			}

			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<String> getGroupChatValues(GroupChat groupChat) {
		return Arrays.asList(String.valueOf(groupChat.getId()), String.valueOf(groupChat.getLobbyID()),
				String.valueOf(groupChat.getCreatorID()), groupChat.getCreateDate(), groupChat.getName(),
				String.valueOf(groupChat.getPrivacyStatusID()), String.valueOf(groupChat.getActiveStatusID()));
	}

	@Override
	public List<GroupChat> getGroupChatByUserId(int userId) {
		List<GroupChat> result = new ArrayList<GroupChat>();

		try {
			java.sql.Connection con = getConnection();

			String selectQuery = "SELECT " + DbCertificate.GroupChatTable.COLUMN_NAME_ID + ","
					+ DbCertificate.GroupChatTable.COLUMN_NAME_ACTIVE_STATUS_ID + ","
					+ DbCertificate.GroupChatTable.COLUMN_NAME_PRIVACY_STATUS_ID + ","
					+ DbCertificate.GroupChatTable.COLUMN_NAME_CREATOR_ID + ","
					+ DbCertificate.GroupChatTable.COLUMN_NAME_LOBBY_ID + ","
					+ DbCertificate.GroupChatTable.COLUMN_NAME_NAME + ","
					+ DbCertificate.GroupChatTable.COLUMN_NAME_CREATE_DATE + " FROM "
					+ DbCertificate.UserGroupChatTable.TABLE_NAME + " INNER JOIN "
					+ DbCertificate.GroupChatTable.TABLE_NAME + " ON "
					+ DbCertificate.UserGroupChatTable.COLUMN_NAME_GROUP_CHAT_ID + " = "
					+ DbCertificate.GroupChatTable.COLUMN_NAME_ID + " WHERE "
					+ DbCertificate.UserGroupChatTable.COLUMN_NAME_USER_ID + " ?";

			java.sql.PreparedStatement st = con.prepareStatement(selectQuery);
			st.executeQuery(generator.getUseDatabaseQuery());

			setValues(Arrays.asList(String.valueOf(userId)), st);
			ResultSet rs = st.executeQuery(selectQuery);

			result = getGroupChatList(rs);

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Lobby getLobbyByComponentId(int componentId) {
		Lobby result = null;
		try {
			java.sql.Connection con = getConnection();
			String selectQuery = generator.getSelectByIDQuery(DbCertificate.LobbyTable.TABLE_NAME,
					DbCertificate.LobbyTable.COLUMN_NAME_SUBJECT_COMPONENT_ID, 1);

			java.sql.PreparedStatement st = con.prepareStatement(selectQuery);
			st.executeQuery(generator.getUseDatabaseQuery());

			setValues(Arrays.asList(String.valueOf(componentId)), st);

			ResultSet rs = st.executeQuery(selectQuery);

			result = getLobby(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	private Lobby getLobby(ResultSet rs) throws SQLException {
		rs.next();
		Lobby lobby = new Lobby(rs.getInt(DbCertificate.LobbyTable.COLUMN_NAME_ID),
				rs.getInt(DbCertificate.LobbyTable.COLUMN_NAME_SUBJECT_COMPONENT_ID));
		
		return lobby;
	}

}
