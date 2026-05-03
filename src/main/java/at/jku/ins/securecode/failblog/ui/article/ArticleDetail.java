package at.jku.ins.securecode.failblog.ui.article;

import at.jku.ins.securecode.failblog.model.Article;
import at.jku.ins.securecode.failblog.ui.FailblogUI;
import at.jku.ins.securecode.failblog.ui.comment.CommentComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Displays one article
 */
public class ArticleDetail extends CustomComponent {

    private final VerticalLayout layout;

    public ArticleDetail(final Article article) {
        checkNotNull(article);

        layout = new VerticalLayout();
        layout.setId("article-detail");
        setCompositionRoot(layout);

        final Label title = new Label(article.getTitle());
        title.setId("article-title");
        layout.addComponent(title);

        final Label lead = new Label(article.getLead());
        lead.setId("article-lead");
        layout.addComponent(lead);

        final Label text = new Label(article.getText());
        text.setId("article-text");
        layout.addComponent(text);

        layout.addComponent(new CommentComponent(article));

        final Button back = new Button("<< back", (ClickListener) event -> FailblogUI.getInstance().showArticleList());
        back.setStyleName("back");
        layout.addComponent(back);
    }

}
