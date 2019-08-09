package com.taotao.common.pojo;

import java.io.Serializable;

public class ActiveMq implements Serializable {
    private String op;
    private Long id;
    private Long[] ids;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

}
