package at.jku.ins.securecode.failblog.ui.login;

import static com.google.common.base.Preconditions.checkNotNull;

public class LoginData {

    private String login;
    private String password;

    public LoginData() {
        this("", "");
    }

    public LoginData(String login, String password) {
        this.login = checkNotNull(login);
        this.password = checkNotNull(password);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
