package at.jku.ins.securecode.failblog.ui.view;

import at.jku.ins.securecode.failblog.db.dao.ArticleDAO;
import at.jku.ins.securecode.failblog.ui.article.ArticleDetail;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

/**
 * Shows one article
 */
public class ArticleDetailView extends Panel implements View {

    @Override
    public void enter(ViewChangeEvent event) {
        String idString = event.getParameters();
        try {
            long id = Long.parseLong(idString);
            setContent(new ArticleDetail(ArticleDAO.findById(id)));
        } catch (NumberFormatException e) {
            setContent(new Label("Invalid parameters: " + idString));
        }
    }

}
