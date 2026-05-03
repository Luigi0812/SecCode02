package at.jku.ins.securecode.failblog.model;

import com.google.common.base.MoreObjects;

import static com.google.common.base.Preconditions.checkNotNull;

public class User {

    private final Long id;
    private final String login;
    private final String password;
    private final String name;

    public User(final Long id, final String login, final String password, final String name) {
        this.id = checkNotNull(id);
        this.login = checkNotNull(login);
        this.password = checkNotNull(password);
        this.name = checkNotNull(name);
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("login", getLogin())
                .add("name", getName())
                .toString();
    }
}
