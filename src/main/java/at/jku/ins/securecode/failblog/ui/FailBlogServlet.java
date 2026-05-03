package at.jku.ins.securecode.failblog.ui;

import at.jku.ins.securecode.failblog.db.datasource.DataSourceFactory;
import at.jku.ins.securecode.failblog.db.migration.DbMigrationMarker;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import org.flywaydb.core.Flyway;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * Handles low-level startup/shutdown stuff
 */

@WebServlet(value = "/*", asyncSupported = true)
@VaadinServletConfiguration(productionMode = true, ui = FailblogUI.class)
public class FailBlogServlet extends VaadinServlet {

    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();

        DataSource dataSource = DataSourceFactory.getDataSource();

        /* Setup and populate DB */
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations(getSqlScriptPath())
                .load();
        flyway.migrate();
    }

    @Override
    public void destroy() {
        super.destroy();

        /* Manually de-register JDBC drivers. Prevents Tomcat from complaining about memory leaks */
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                System.err.println(String.format("Error de-registering driver %s", driver));
            }
        }
    }


    /**
     * Get path to package containing SQL scripts
     */
    private static String getSqlScriptPath() {
        return DbMigrationMarker.class.getPackage().getName().replaceAll("\\.", "/");
    }
}
