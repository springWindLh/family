package lh.family.admin.query;

import lh.base.support.dao.QueryItem;
import lh.family.admin.query.support.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lh on 2016/4/19.
 */
public class NewsQuery extends Query {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<QueryItem> asQueryItems() {
        List<QueryItem> queryItems = new ArrayList<>();
        if (title != null && !title.isEmpty()) {
            queryItems.add(new QueryItem("title", title, QueryItem.OperatorType.LIKE));
        }
        queryItems.add(new QueryItem("del", false));
        return queryItems;
    }
}
