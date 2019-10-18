package cn.rui0.core.model.po.common;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "test1")
public class Test1 {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String name;
 @OneToMany(mappedBy ="test1",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Test2> test2s;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Test2> getTest2s() {
        return test2s;
    }

    public void setTest2s(List<Test2> test2s) {
        this.test2s = test2s;
    }

}
