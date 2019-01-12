package com.project.component;

import com.project.component.base.BaseComponent;
import org.springframework.stereotype.Component;

/**
 * Created by Admin on 2017/12/28.
 */
@Component
public class ProjectComponent extends BaseComponent {

    private Integer id;
    private String projectname;
    private String number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
