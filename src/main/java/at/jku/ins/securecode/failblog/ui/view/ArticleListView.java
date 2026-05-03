package at.jku.ins.securecode.failblog.ui.view;

import at.jku.ins.securecode.failblog.db.dao.ArticleDAO;
import at.jku.ins.securecode.failblog.ui.article.ArticleList;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Panel;

/**
 * Shows list of all articles
 */
public class ArticleListView extends Panel implements View {

    @Override
    public void enter(ViewChangeEvent event) {
        String query = event.getParameters();
        setContent(new ArticleList(ArticleDAO.findAll(), query));
    }

}
