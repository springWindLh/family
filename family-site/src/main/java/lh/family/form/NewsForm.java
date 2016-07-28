package lh.family.form;

import lh.family.form.support.BaseForm;
import lh.family.model.News;

import java.util.Date;

/**
 * Created by lh on 2016/4/19.
 */
public class NewsForm extends BaseForm {
    private String title;
    private String status;
    private String content;
    private Date createdTime;
    private String userName;

    public NewsForm() {
    }

    public NewsForm(News news) {
        this.id = news.getId();
        this.title = news.getTitle();
        this.status = news.getStatus().name();
        this.content = news.getContent();
        this.createdTime = news.getCreatedTime();
        this.userName = news.getUser().getName();
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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
