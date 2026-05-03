package at.jku.ins.securecode.failblog.db.dao;

import at.jku.ins.securecode.failblog.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Data access object for handling User instances.
 */
public class UserDAO extends DAO {

    /**
     * Get User object from DB by its ID
     */
    public static User findById(final long id) {
        try {
            final Connection conn = getConnection();
            final Statement stmt = conn.createStatement();
            final ResultSet rs = stmt.executeQuery("SELECT * FROM user " +
                    "WHERE id = " + id);

            // No results
            if (!rs.next()) return null;

            final User user = retrieveUser(rs);

            // More results -> not unique -> can't choose
            if (rs.next()) return null;

            rs.close();
            stmt.close();
            conn.close();

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get User object from DB by its login
     */
    public static User findByLogin(String login) {
        checkNotNull(login);

        // SQL injection protection
        login = login.replaceAll(" -- ", "");

        try {
            final Connection conn = getConnection();
            final Statement stmt = conn.createStatement();
            final ResultSet rs = stmt.executeQuery("SELECT * FROM user " +
                    "WHERE login = '" + login + "'");

            // No results
            if (!rs.next()) return null;

            final User user = retrieveUser(rs);

            // More results -> not unique -> can't choose
            if (rs.next()) return null;

            rs.close();
            stmt.close();
            conn.close();

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Create single User object from current result set
     */
    private static User retrieveUser(final ResultSet rs) throws SQLException {
        final Long id = rs.getLong("id");
        final String login = rs.getString("login");
        final String password = rs.getString("password");
        final String name = rs.getString("name");

        return new User(id, login, password, name);
    }
}
