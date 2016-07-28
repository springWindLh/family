package lh.family.model;


import lh.base.support.model.BaseDomain;

import javax.persistence.*;

/**
 * Created by lh on 2016/4/8.
 */
@Entity
@Table(name = "comment")
public class Comment extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "content")
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;
    @Column(name = "target_id", nullable = true)
    private long targetId;
    @Column(name = "target_type", nullable = true)
    private TargetType targetType;

    public Comment() {
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getTargetId() {
        return targetId;
    }

    public void setTargetId(long targetId) {
        this.targetId = targetId;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(TargetType targetType) {
        this.targetType = targetType;
    }

    public static enum TargetType {
        NEWS("新闻"),
        SINGLE_DETAIL("单项");

        private String value;

        private TargetType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
