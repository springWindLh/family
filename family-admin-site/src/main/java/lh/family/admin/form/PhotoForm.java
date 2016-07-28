package lh.family.admin.form;

import lh.family.admin.form.support.BaseForm;
import lh.family.admin.model.Photo;

import java.util.Date;

/**
 * Created by lh on 2016/5/13.
 */
public class PhotoForm extends BaseForm {
    private String url;
    private String description;

    public PhotoForm() {
    }

    public PhotoForm(Photo photo) {
        this.url = photo.getUrl();
        this.description = photo.getDescription();
    }

    public Photo asPhoto() {
        Photo photo = new Photo();
        if (this.getId() != null) {
            photo.setId(this.getId());
            photo.setUpdatedTime(new Date());
        }
        photo.setUrl(this.getUrl());
        photo.setDescription(this.getDescription());
        return photo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
