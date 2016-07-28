package lh.family.form;

import lh.family.form.support.BaseForm;
import lh.family.model.SingleDetail;

import java.util.Date;

/**
 * Created by lh on 2016/4/27.
 */
public class SingleDetailForm extends BaseForm {
    private String content;
    private Long menuId;
    private String menuName;

    public SingleDetailForm() {
    }

    public SingleDetailForm(SingleDetail detail) {
        this.id = detail.getId();
        this.menuId = detail.getMenu().getId();
        this.menuName = detail.getMenu().getName();
        this.content = detail.getContent();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
