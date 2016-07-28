package lh.family.admin.form;

import lh.family.admin.form.support.BaseForm;
import lh.family.admin.model.News;

import java.util.Date;

/**
 * Created by lh on 2016/4/19.
 */
public class NewsForm extends BaseForm {
    private String title;
    private String status;
    private String content;
    private Boolean recommend;
    private String rollImg;
    private Integer weight;

    public NewsForm() {
    }

    public NewsForm(News news) {
        this.id = news.getId();
        this.title = news.getTitle();
        this.status = news.getStatus().name();
        this.content = news.getContent();
        this.recommend = news.isRecommend();
        this.rollImg = news.getRollImg();
        this.weight = news.getWeight();
    }

    public News asNews() {
        News news = new News();
        if (this.getId() != null) {
            news.setId(this.getId());
            news.setUpdatedTime(new Date());
        }
        news.setTitle(this.getTitle());
        news.setStatus(News.Status.valueOf(this.getStatus()));
        news.setContent(this.getContent());
        news.setRecommend(this.getRecommend());
        news.setRollImg(this.getRollImg());
        news.setWeight(this.getWeight());
        return news;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public String getRollImg() {
        return rollImg;
    }

    public void setRollImg(String rollImg) {
        this.rollImg = rollImg;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
