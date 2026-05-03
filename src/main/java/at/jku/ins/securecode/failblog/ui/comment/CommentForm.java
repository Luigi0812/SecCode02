package at.jku.ins.securecode.failblog.ui.comment;

import com.vaadin.data.Binder;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

import static com.google.common.base.Preconditions.checkNotNull;

public class CommentForm extends CustomComponent {

    private final Binder<CommentData> binder;

    public CommentForm() {
        final FormLayout mainLayout = new FormLayout();
        mainLayout.setSizeUndefined();
        mainLayout.setSpacing(false);
        setCompositionRoot(mainLayout);

        binder = new Binder<>(CommentData.class);
        setCommentData(new CommentData());

        TextField commentField = new TextField();
        mainLayout.addComponent(commentField);
        binder.forField(commentField).bind(CommentData::getText, CommentData::setText);
    }

    public Binder<CommentData> getBinder() {
        return binder;
    }

    public CommentData getCommentData() {
        return binder.getBean();
    }

    public void setCommentData(final CommentData commentData) {
        binder.setBean(checkNotNull(commentData));
    }

}
