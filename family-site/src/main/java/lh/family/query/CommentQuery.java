package lh.family.query;

import lh.base.support.dao.QueryItem;
import lh.family.query.support.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by lh on 2016/5/15.
 */
public class CommentQuery extends Query {
    private String targetType;
    private Long targetId;

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public List<QueryItem> asQueryItems() {
        List<QueryItem> items = new ArrayList<>();
        if (Optional.ofNullable(targetId).isPresent()) {
            QueryItem queryItem = new QueryItem("targetId", targetId);
            items.add(queryItem);
        }
        if (Optional.ofNullable(targetType).isPresent() && !targetType.isEmpty()) {
            QueryItem queryItem = new QueryItem("targetType", targetType);
            items.add(queryItem);
        }
        return items;
    }
}
