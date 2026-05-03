package at.jku.ins.securecode.failblog.db.datasource;

import javax.sql.DataSource;

/**
 * Get data source required for connecting to the database.
 */
public class DataSourceFactory {

    public static DataSource getDataSource() {
        return SimpleConnectionDatasource.getInstance();
    }
}
