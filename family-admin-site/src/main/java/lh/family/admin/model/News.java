package lh.family.admin.model;


import lh.base.support.model.CanLogicDelDomain;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by lh on 2016/4/7.
 */
@Entity
@Table(name = "news")
public class News extends CanLogicDelDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Type(type = "text")
    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "recommend")
    private boolean recommend = false;

    @Column(name = "roll_img")
    private String rollImg;

    @Column(name = "weight")
    private int weight;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public String getRollImg() {
        return rollImg;
    }

    public void setRollImg(String rollImg) {
        this.rollImg = rollImg;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public static enum Status {
        ONLINE("上线"),
        OFFLINE("下线");
        private String value;

        private Status(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
