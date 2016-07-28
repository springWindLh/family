package lh.family.admin.query;

import lh.base.support.dao.QueryItem;
import lh.family.admin.query.support.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lh on 2016/5/4.
 */
public class LeavewordQuery extends Query {
    public List<QueryItem> asQueryItems() {
        List<QueryItem> queryItems = new ArrayList<>();
        queryItems.add(new QueryItem("del", false));
        return queryItems;
    }
}
