package lh.family.admin.form;

import lh.family.admin.form.support.BaseForm;
import lh.family.admin.model.SingleMenu;

import java.util.Date;

/**
 * Created by lh on 2016/4/27.
 */
public class SingleMenuForm extends BaseForm {
    private String name;
    private Integer weight;
    private String status;

    public SingleMenuForm() {
    }

    public SingleMenuForm(SingleMenu menu) {
        this.id = menu.getId();
        this.name = menu.getName();
        this.weight = menu.getWeight();
        this.status = menu.getStatus().name();
    }

    public SingleMenu asSingleMenu() {
        SingleMenu singleMenu = new SingleMenu();
        if (this.getId() != null) {
            singleMenu.setId(this.getId());
            singleMenu.setUpdatedTime(new Date());
        }
        singleMenu.setName(this.getName());
        singleMenu.setWeight(this.getWeight());
        singleMenu.setStatus(SingleMenu.Status.valueOf(this.getStatus()));
        return singleMenu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
