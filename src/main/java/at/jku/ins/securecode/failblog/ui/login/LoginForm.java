package at.jku.ins.securecode.failblog.ui.login;

import com.vaadin.data.Binder;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

import static com.google.common.base.Preconditions.checkNotNull;

public class LoginForm extends CustomComponent {

    private final Binder<LoginData> binder;

    public LoginForm() {
        final FormLayout mainLayout = new FormLayout();
        mainLayout.setSizeUndefined();
        mainLayout.setSpacing(false);
        mainLayout.setId("login-logout");
        setCompositionRoot(mainLayout);

        binder = new Binder<>(LoginData.class);
        setLoginData(new LoginData());

        TextField login = new TextField("Login", "login");
        login.setId("login");
        binder.forField(login).bind(LoginData::getLogin, LoginData::setLogin);
        mainLayout.addComponent(login);

        TextField password = new TextField("Password", "password");
        password.setId("password");
        binder.forField(password).bind(LoginData::getPassword, LoginData::setPassword);
        mainLayout.addComponent(password);
    }

    public Binder<LoginData> getBinder() {
        return binder;
    }

    public LoginData getLoginData() {
        return binder.getBean();
    }

    public void setLoginData(final LoginData loginData) {
        binder.setBean(checkNotNull(loginData));
    }

}
