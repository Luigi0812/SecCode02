package at.jku.ins.securecode.failblog.model;

import com.google.common.base.MoreObjects;

import static com.google.common.base.Preconditions.checkNotNull;

public class Comment {

    private final String text;
    private final User user;

    public Comment(final String text, final User user) {
        this.text = checkNotNull(text);
        this.user = checkNotNull(user);
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("text", getText())
                .add("user", getUser().getName())
                .toString();
    }
}
