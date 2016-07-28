package lh.family.model;

import lh.base.support.model.BaseDomain;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by lh on 2016/4/23.
 */
@Entity
@Table(name = "single_detail")
public class SingleDetail extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Type(type = "text")
    @Column(name = "content")
    private String content;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "menu_id")
    private SingleMenu menu;

    public SingleDetail() {
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SingleMenu getMenu() {
        return menu;
    }

    public void setMenu(SingleMenu menu) {
        this.menu = menu;
    }
}
