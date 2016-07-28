package lh.family.admin.form;

import lh.family.admin.form.support.BaseForm;
import lh.family.admin.model.Leaveword;

/**
 * Created by lh on 2016/4/27.
 */
public class LeavewordForm extends BaseForm {
    private String content;
    private String userName;

    public LeavewordForm() {
    }

    public LeavewordForm(Leaveword leaveword) {
        this.content = leaveword.getContent();
        this.userName = leaveword.getUser().getName();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
