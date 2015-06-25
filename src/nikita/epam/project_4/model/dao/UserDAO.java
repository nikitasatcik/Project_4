package nikita.epam.project_4.model.dao;

import nikita.epam.project_4.model.entity.User;

import java.sql.SQLException;
import java.sql.Statement;

public abstract class UserDAO {
	public abstract int insertUser(User user);           // New user is registered.
	public abstract User findUser(User user);            // User wants to login.
	public abstract boolean updateUser(User user);       // Administrator activates/blocks user.
	public abstract Iterable<User> findUnconfirmed();    // Returns unconfirmed user records.
	public abstract int activateBatch(Iterable<User> users); // Activates a collection of users.
	public abstract int deleteBatch(Iterable<User> users); // Deletes a collection of users.

	protected void closeStatement(Statement st){
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				System.out.println(" cannot closeStatement statement");
			}
		}
	}
}
