package com.project.component;

import com.project.component.base.BaseComponent;
import org.springframework.stereotype.Component;

/**
 * Created by Admin on 2017/12/28.
 */
@Component
public class RoleComponent extends BaseComponent {

    private Integer id;
    private String name;
    private String flag;
    private String level;

    @Override
    public String toString() {
        return "RoleComponent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", flag='" + flag + '\'' +
                ", level='" + level + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
