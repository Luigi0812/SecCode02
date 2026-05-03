package at.jku.ins.securecode.failblog.ui;

import at.jku.ins.securecode.failblog.db.dao.CommentDAO;
import at.jku.ins.securecode.failblog.db.dao.UserDAO;
import at.jku.ins.securecode.failblog.model.Article;
import at.jku.ins.securecode.failblog.model.Comment;
import at.jku.ins.securecode.failblog.model.User;
import at.jku.ins.securecode.failblog.ui.comment.CommentData;
import at.jku.ins.securecode.failblog.ui.login.LoginData;
import at.jku.ins.securecode.failblog.ui.login.LoginLogoutComponent;
import at.jku.ins.securecode.failblog.ui.search.SearchComponent;
import at.jku.ins.securecode.failblog.ui.view.ArticleDetailView;
import at.jku.ins.securecode.failblog.ui.view.ArticleListView;
import at.jku.ins.securecode.failblog.ui.view.SearchView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.Notification.Type;

import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Main web UI controller
 */

@Theme("failblog")
public class FailblogUI extends UI {

    public static final String HOME_VIEW = "";
    public static final String ARTICLE_VIEW = "article";
    public static final String SEARCH_VIEW = "search";

    private Navigator navigator;
    private User loggedInUser;

    /**
     * Build the basic layout of the UI
     */
    @Override
    protected void init(final VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("container");
        setContent(layout);

        Map<String, String[]> parameters = request.getParameterMap();
        parameters.get("search");
        // header
        final Label title = new Label("Fail Blog");
        title.setId("title");
        layout.addComponent(title);

        final Label subTitle = new Label("A complete security fail!");
        subTitle.setId("subtitle");
        layout.addComponent(subTitle);

        // rest
        final HorizontalLayout main = new HorizontalLayout();
        main.setId("main");
        main.setWidth("100%");
        layout.addComponent(main);

        // content
        Panel content = new Panel();
        content.setId("content");
        main.addComponent(content);
        main.setExpandRatio(content, 1.0f);

        navigator = new Navigator(this, content);
        navigator.addView(HOME_VIEW, new ArticleListView());
        navigator.addView(ARTICLE_VIEW, new ArticleDetailView());
        navigator.addView(SEARCH_VIEW, new SearchView());

        // side bar
        final VerticalLayout side = new VerticalLayout();
        side.setId("side");
        side.setSpacing(true);
        main.addComponent(side);
        main.setExpandRatio(side, .7f);

        side.addComponent(new LoginLogoutComponent());
        side.addComponent(new SearchComponent());

        if (getPage().getUriFragment() != null && isKnownDestinationPage(getPage().getUriFragment())) {
            // Navigate to the search result page
            navigator.navigateTo(getPage().getUriFragment().substring(1));
        } else {
            // start with a list of all articles
            showArticleList();
        }
    }

    public static FailblogUI getInstance() {
        return (FailblogUI) UI.getCurrent();
    }

    private boolean isKnownDestinationPage(String uriFragment) {
        return uriFragment.startsWith("!" + SEARCH_VIEW) || uriFragment.startsWith("!" + ARTICLE_VIEW);
    }

    /**
     * Show list of all articles
     */
    public void showArticleList() {
        navigator.navigateTo(HOME_VIEW);
    }

    /**
     * Show list of all articles matching a certain query
     */
    public void showSearchResult(final String query) {
        checkNotNull(query);
        navigator.navigateTo(SEARCH_VIEW + "/" + query);
    }

    /**
     * Show single article identified by its ID
     */
    public void showArticle(final Long id) {
        checkNotNull(id);
        navigator.navigateTo(ARTICLE_VIEW + "/" + id);
    }

    /**
     * Add a comment to an article
     */
    public Comment addComment(final Article article, final CommentData commentData) {
        checkNotNull(article);
        checkNotNull(commentData);

        if (!isLoggedIn()) {
            return null;
        } else {
            final Comment comment = new Comment(commentData.getText(), loggedInUser);
            article.addComment(comment);                        // add to in-memory object
            CommentDAO.addComment(comment, article.getId());    // add to DB
            return comment;                                        // for adding to the UI
        }
    }

    /**
     * Login a user identified by the given login data
     */
    public boolean login(final LoginData loginData) {
        checkNotNull(loginData);

        // Check if user exists
        final User userLoggingIn = UserDAO.findByLogin(loginData.getLogin());
        if (userLoggingIn != null) {

            // Check password
            if (userLoggingIn.getPassword().equals(loginData.getPassword())) {
                Notification.show("Logged in");
                loggedInUser = userLoggingIn;
                showArticleList();
                return true;
            }
        }

        Notification.show("Login failed", Type.WARNING_MESSAGE);
        return false;
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    /**
     * Get currently logged in user
     */
    public User getUser() {
        return loggedInUser;
    }

    /**
     * Logout user
     */
    public boolean logout() {
        Notification.show("Logging out");
        loggedInUser = null;
        showArticleList();
        return true;
    }

}