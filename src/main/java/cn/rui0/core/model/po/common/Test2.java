package cn.rui0.core.model.po.common;

import lombok.Cleanup;

import javax.persistence.*;

@Entity
@Table(name = "test2")
public class Test2 {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id2;
    private String name2;

    @ManyToOne
    @JoinColumn(name = "id")
    private Test1 test1;

    public long getId2() {
        return id2;
    }

    public void setId2(long id2) {
        this.id2 = id2;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public Test1 getTest1() {
        return test1;
    }

    public void setTest1(Test1 test1) {
        this.test1 = test1;
    }
}
