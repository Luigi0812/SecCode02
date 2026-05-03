package at.jku.ins.securecode.failblog.ui.view;

import at.jku.ins.securecode.failblog.db.dao.ArticleDAO;
import at.jku.ins.securecode.failblog.ui.article.ArticleList;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

/**
 * Shows one article
 */
public class SearchView extends Panel implements View {

    @Override
    public void enter(ViewChangeEvent event) {
        String query = event.getParameters();
        if (query != null) {
            setContent(new ArticleList(ArticleDAO.findWhereTitleLike(query), query));
        } else {
            setContent(new Label("Invalid parameters: " + query));
        }
    }

}
