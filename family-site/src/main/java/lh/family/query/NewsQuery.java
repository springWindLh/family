package lh.family.query;

import lh.base.support.dao.QueryItem;
import lh.family.model.News;
import lh.family.query.support.LogicDelQuery;

import java.util.List;

/**
 * Created by lh on 2016/6/5.
 */
public class NewsQuery extends LogicDelQuery {
    private List<QueryItem> queryItems;

    public NewsQuery() {
        this.queryItems = super.getItems();
    }

    @Override
    public List<QueryItem> asQueryItems() {
        queryItems = super.asQueryItems();
        queryItems.add(new QueryItem("status", News.Status.ONLINE));
        return queryItems;
    }
}
