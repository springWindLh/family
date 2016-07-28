package lh.family.form;

import lh.family.form.support.BaseForm;
import lh.family.model.SingleMenu;

import java.util.Date;

/**
 * Created by lh on 2016/4/27.
 */
public class SingleMenuForm extends BaseForm {
    private String name;
    private Integer weight;

    public SingleMenuForm() {
    }

    public SingleMenuForm(SingleMenu menu) {
        this.id = menu.getId();
        this.name = menu.getName();
        this.weight = menu.getWeight();
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
}
