package at.jku.ins.securecode.failblog.ui.search;

import at.jku.ins.securecode.failblog.ui.FailblogUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Displays a search form
 */
public class SearchComponent extends CustomComponent {

    public SearchComponent() {
        VerticalLayout layout = new VerticalLayout();
        setCompositionRoot(layout);

        final Label loginHeading = new Label("Search");
        loginHeading.setStyleName("subheading");
        layout.addComponent(loginHeading);

        final SearchForm form = new SearchForm();
        layout.addComponent(form);

        layout.addComponent(new Button("Search", (ClickListener) event -> {
            FailblogUI.getInstance().showSearchResult(form.getSearchData().getQuery());
        }));
    }

}
