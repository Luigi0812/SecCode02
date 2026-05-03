package at.jku.ins.securecode.failblog.ui.search;

import com.vaadin.data.Binder;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

import static com.google.common.base.Preconditions.checkNotNull;

public class SearchForm extends CustomComponent {

    private final Binder<SearchData> binder;

    public SearchForm() {
        final FormLayout mainLayout = new FormLayout();
        mainLayout.setSizeUndefined();
        mainLayout.setSpacing(false);
        setCompositionRoot(mainLayout);

        binder = new Binder<>(SearchData.class);
        setSearchData(new SearchData());

        TextField searchField = new TextField("Text in title", "query");
        binder.forField(searchField).bind(SearchData::getQuery, SearchData::setQuery);
        mainLayout.addComponent(searchField);
    }

    public Binder<SearchData> getBinder() {
        return binder;
    }

    public SearchData getSearchData() {
        return binder.getBean();
    }

    public void setSearchData(final SearchData searchData) {
        binder.setBean(checkNotNull(searchData));
    }

}
