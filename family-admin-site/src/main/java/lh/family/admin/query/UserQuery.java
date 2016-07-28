package lh.family.admin.query;

import lh.base.support.dao.QueryItem;
import lh.family.admin.query.support.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lh on 2016/4/27.
 */
public class UserQuery extends Query {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QueryItem> asQueryItems() {
        List<QueryItem> queryItems = new ArrayList<>();
        if (name != null && !name.isEmpty()) {
            queryItems.add(new QueryItem("name", name, QueryItem.OperatorType.LIKE));
        }
        queryItems.add(new QueryItem("del", false));
        return queryItems;
    }
}
