package at.jku.ins.securecode.failblog.db.dao;

import at.jku.ins.securecode.failblog.db.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Data access object base class with common functionality.
 */
public abstract class DAO {

    /**
     * Get connection to DB provided by data source
     */
    protected static Connection getConnection() throws SQLException {
        final DataSource dataSource = DataSourceFactory.getDataSource();
        return dataSource.getConnection();
    }
}
