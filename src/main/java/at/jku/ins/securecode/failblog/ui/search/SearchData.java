package at.jku.ins.securecode.failblog.ui.search;

import static com.google.common.base.Preconditions.checkNotNull;

public class SearchData {

    private String query;

    public SearchData() {
        this("");
    }

    public SearchData(String query) {
        this.setQuery(checkNotNull(query));
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

}
