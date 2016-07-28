package lh.family.admin.form;

import lh.base.support.util.Md5Util;
import lh.family.admin.form.support.BaseForm;
import lh.family.admin.model.User;

import java.util.Date;

/**
 * Created by lh on 2016/4/27.
 */
public class UserForm extends BaseForm {
    private String name;
    private String password;
    private String oldPwd;
    private String avatar;
    private String introduce;
    private String role;

    public UserForm() {
    }

    public UserForm(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.oldPwd = user.getPassword();
        this.avatar = user.getAvatar();
        this.introduce = user.getIntroduce();
        this.role = user.getRole().name();
    }

    public User asUser() {
        User user = new User();
        if (this.getId() != null) {
            user.setId(this.getId());
            user.setUpdatedTime(new Date());
            if (!this.oldPwd.equals(this.getPassword())) {
                String md5Pwd = Md5Util.encrypt32(this.getPassword());
                user.setPassword(md5Pwd);
            }else {
                user.setPassword(this.getOldPwd());
            }
        }else {
            user.setPassword(Md5Util.encrypt32(this.getPassword()));
        }
        user.setName(this.getName());
        user.setAvatar(this.getAvatar());
        user.setIntroduce(this.getIntroduce());
        user.setRole(User.Role.valueOf(role));
        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
