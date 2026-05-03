package at.jku.ins.securecode.failblog.ui.login;

import at.jku.ins.securecode.failblog.model.User;
import at.jku.ins.securecode.failblog.ui.FailblogUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Displays a login form / a logout button
 */
public class LoginLogoutComponent extends CustomComponent {

    private final VerticalLayout layout;

    public LoginLogoutComponent() {
        layout = new VerticalLayout();
        setCompositionRoot(layout);
        showLogin();
    }

    public void showLogin() {
        layout.removeAllComponents();

        final Label loginHeading = new Label("Login");
        loginHeading.setStyleName("subheading");
        layout.addComponent(loginHeading);

        final LoginForm form = new LoginForm();
        layout.addComponent(form);

        Button loginButton = new Button("Login", (ClickListener) event -> {
            if (FailblogUI.getInstance().login(form.getLoginData())) {
                showLogout();
            }
        });
        loginButton.setId("login-button");
        layout.addComponent(loginButton);
    }

    public void showLogout() {
        layout.removeAllComponents();

        final Label loginHeading = new Label("Logged in");
        loginHeading.setStyleName("subheading");
        layout.addComponent(loginHeading);

        final User user = FailblogUI.getInstance().getUser();
        Label loggedIn = new Label("Logged in as: " + user.getName());
        loggedIn.setStyleName("user");
        layout.addComponent(loggedIn);

        Button logoutButton = new Button("Logout", (ClickListener) event -> {
            if (FailblogUI.getInstance().logout()) {
                showLogin();
            }
        });
        logoutButton.setId("logout-button");
        layout.addComponent(logoutButton);
    }
}
