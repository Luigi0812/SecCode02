package at.jku.ins.securecode.failblog.db.datasource;

import org.hsqldb.jdbc.JDBCDataSource;

import javax.sql.DataSource;

/**
 * Simple data source that is created on every instance request.
 */
public class SimpleConnectionDatasource {

    public static DataSource getInstance() {
        JDBCDataSource dataSource = new JDBCDataSource();
        dataSource.setUrl("jdbc:hsqldb:mem:polling");
        dataSource.setUser("SA");
        dataSource.setPassword("");
        return dataSource;
    }
}
