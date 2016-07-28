package lh.family.query.support;

import lh.base.support.dao.QueryItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lh on 2016/6/5.
 */
public class StatusQuery extends Query {
    private List<QueryItem> items;

    public List<QueryItem> asQueryItems() {
        items = new ArrayList<>();
        items.add(new QueryItem("status", "online"));
        return items;
    }

    public List<QueryItem> getItems() {
        return items;
    }

    public void setItems(List<QueryItem> items) {
        this.items = items;
    }
}
