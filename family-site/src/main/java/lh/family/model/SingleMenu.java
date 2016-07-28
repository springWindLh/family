package lh.family.model;

import lh.base.support.model.BaseDomain;
import lh.family.model.SingleDetail;

import javax.persistence.*;

/**
 * Created by lh on 2016/4/23.
 */
@Entity
@Table(name = "single_menu")
public class SingleMenu extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "weight")
    private int weight;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "menu")
    private SingleDetail detail;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public SingleMenu() {
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public SingleDetail getDetail() {
        return detail;
    }

    public void setDetail(SingleDetail detail) {
        this.detail = detail;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static enum Status {
        ONLINE("上线"),
        OFFLINE("下线");
        private String value;

        private Status(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
