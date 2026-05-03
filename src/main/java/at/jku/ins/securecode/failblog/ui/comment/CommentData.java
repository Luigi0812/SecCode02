package at.jku.ins.securecode.failblog.ui.comment;

import static com.google.common.base.Preconditions.checkNotNull;

public class CommentData {

    private String text;

    public CommentData() {
        this("");
    }

    public CommentData(String text) {
        this.setText(checkNotNull(text));
    }

    public String getText() {
        return text;
    }

    public void setText(String query) {
        this.text = query;
    }

}
