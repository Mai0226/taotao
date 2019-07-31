package com.taotao.commom.pojo;

import java.io.Serializable;

public class QueryVo implements Serializable {
    private Long[] ids;

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }
}
