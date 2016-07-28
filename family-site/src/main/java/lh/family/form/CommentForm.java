package lh.family.form;

import lh.family.form.support.BaseForm;
import lh.family.model.Comment;

import java.text.SimpleDateFormat;

/**
 * Created by lh on 2016/5/15.
 */
public class CommentForm extends BaseForm {
    private String content;
    private String targetType;
    private Long targetId;
    private String userName;
    private String userAvatar;
    private String createdTime;

    public CommentForm() {
    }

    public Comment asComment() {
        Comment comment = new Comment();
        comment.setContent(this.getContent());
        comment.setTargetType(Comment.TargetType.valueOf(this.getTargetType()));
        comment.setTargetId(this.getTargetId());
        return comment;
    }

    public CommentForm asForm(Comment comment) {
        this.setId(comment.getId());
        this.setUserName(comment.getUser().getName());
        this.setUserAvatar(comment.getUser().getAvatar());
        this.setCreatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(comment.getCreatedTime()));
        this.setContent(comment.getContent());
        return this;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
