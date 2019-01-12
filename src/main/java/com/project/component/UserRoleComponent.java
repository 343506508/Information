package com.project.component;

import com.project.component.base.BaseComponent;
import org.springframework.stereotype.Component;

/**
 * Created by Admin on 2018/1/10.
 */
@Component
public class UserRoleComponent extends BaseComponent {
    private Integer id;
    private Integer roleid;
    private Integer functionid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getFunctionid() {
        return functionid;
    }

    public void setFunctionid(Integer functionid) {
        this.functionid = functionid;
    }
}
