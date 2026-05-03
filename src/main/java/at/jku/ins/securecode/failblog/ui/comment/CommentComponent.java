package at.jku.ins.securecode.failblog.ui.comment;

import at.jku.ins.securecode.failblog.model.Article;
import at.jku.ins.securecode.failblog.model.Comment;
import at.jku.ins.securecode.failblog.ui.FailblogUI;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickListener;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Displays comments and a form to add a comment
 */
public class CommentComponent extends CustomComponent {

    private final VerticalLayout layout;
    private final VerticalLayout container;
    private boolean noCommentsYet = true;

    public CommentComponent(final Article article) {
        checkNotNull(article);

        layout = new VerticalLayout();
        layout.setId("comments");
        setCompositionRoot(layout);

        final Label heading = new Label("Comments");
        heading.setId("heading");
        layout.addComponent(heading);

        // show comment form, if user is logged in
        if (FailblogUI.getInstance().isLoggedIn()) {
            final HorizontalLayout formAndButton = new HorizontalLayout();
            formAndButton.setSpacing(true);
            layout.addComponent(formAndButton);

            final CommentForm form = new CommentForm();
            formAndButton.addComponent(form);

            final Button postButton = new Button("Post", (ClickListener) event -> {
                addComment(FailblogUI.getInstance().addComment(article, form.getCommentData()));
            });
            postButton.setId("post-comment");
            formAndButton.addComponent(postButton);
            formAndButton.setComponentAlignment(postButton, Alignment.MIDDLE_LEFT);
        }

        layout.addComponent(container = new VerticalLayout());

        // display comments
        final List<Comment> comments = article.getComments();
        if (comments.isEmpty()) {
            container.addComponent(new Label("No comments yet."));
        } else {
            noCommentsYet = false;
            for (final Comment comment : comments) {
                addComment(comment);
            }
        }

    }

    /**
     * add comment to display
     */
    private void addComment(final Comment comment) {
        checkNotNull(comment);

        // Remove 'No comments yet' message
        if (noCommentsYet) {
            container.removeAllComponents();
            noCommentsYet = false;
        }

        final VerticalLayout singleComment = new VerticalLayout();
        singleComment.setStyleName("comment");
        container.addComponentAsFirst(singleComment);

        final Label user = new Label(comment.getUser().getName());
        user.setStyleName("author");
        singleComment.addComponent(user);

        final Label text = new Label(comment.getText());
        text.setStyleName("text");
        text.setContentMode(ContentMode.HTML);
        singleComment.addComponent(text);
    }

}
