package at.jku.ins.securecode.failblog.ui.article;

import at.jku.ins.securecode.failblog.model.Article;
import at.jku.ins.securecode.failblog.ui.FailblogUI;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Displays a list of articles
 */
public class ArticleList extends CustomComponent {

    private final VerticalLayout layout;

    public ArticleList(final List<Article> articles, final String query) {
        checkNotNull(articles);

        layout = new VerticalLayout();
        layout.setStyleName("article-list");
        setCompositionRoot(layout);

        // show query, if this is a search result
        if (query != null && !query.isEmpty()) {
            Label result = new Label("Search result for '" + query + "':");
            result.setContentMode(ContentMode.HTML);
            result.setStyleName("result");
            layout.addComponent(result);
        }

        // add articles to UI
        for (final Article article : articles) {
            addArticle(article);
        }

        // allow returning to article list, if is a search result
        if (query != null && !query.isEmpty()) {
            final Button back = new Button("<< back", (ClickListener) event -> FailblogUI.getInstance().showArticleList());
            back.setStyleName("back");
            layout.addComponent(back);
        }
    }

    /**
     * Add article to UI
     */
    private void addArticle(final Article article) {
        checkNotNull(article);

        final VerticalLayout component = new VerticalLayout();
        component.setStyleName("article");
        layout.addComponent(component);

        final Label title = new Label(article.getTitle());
        title.setStyleName("article-title");
        component.addComponent(title);

        final Label lead = new Label(article.getLead());
        lead.setStyleName("article-lead");
        component.addComponent(lead);

        // link to read full article
        final Button more = new Button(">> more", (ClickListener) event -> FailblogUI.getInstance().showArticle(article.getId()));
        more.setStyleName("article-more");
        component.addComponent(more);
    }

}
