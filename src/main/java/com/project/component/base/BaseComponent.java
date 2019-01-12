package com.project.component.base;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by Admin on 2017/12/28.
 * Base : 是实体类的基类
 */
@Component
public class BaseComponent implements Serializable {
    private static final long serialVersionUID = 1L;

    public int count=10;//每页显示行数
    public int page=0;//当前页数
    private String total;//总数
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
