package at.jku.ins.securecode.failblog.model;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class Article {

    private final Long id;

    private final String title;
    private final String lead;
    private final String text;

    private final List<Comment> comments = Lists.newArrayList();

    public Article(final Long id, final String title, final String lead, final String text) {
        this.id = checkNotNull(id);
        this.title = checkNotNull(title);
        this.lead = checkNotNull(lead);
        this.text = checkNotNull(text);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLead() {
        return lead;
    }

    public String getText() {
        return text;
    }

    public List<Comment> getComments() {
        return ImmutableList.copyOf(comments);
    }

    public void addComment(final Comment comment) {
        checkNotNull(comment);
        this.comments.add(comment);
    }

    public void addComments(final List<Comment> comments) {
        checkNotNull(comments);
        this.comments.addAll(comments);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("title", getTitle())
                .toString();
    }

}
