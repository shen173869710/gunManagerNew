package com.auto.di.guan.manager.entity;

import com.auto.di.guan.manager.db.UserAction;

import java.io.Serializable;
import java.util.List;

public class TableDataInfo implements Serializable {
    /** 总记录数 */
    private long total;
    /** 列表数据 */
    private List<UserAction> rows;
    /** 消息状态码 */
    private int code;
    /** 消息内容 */
    private int msg;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<UserAction> getRows() {
        return rows;
    }

    public void setRows(List<UserAction> rows) {
        this.rows = rows;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }
}
