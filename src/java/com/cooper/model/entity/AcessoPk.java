package com.cooper.model.entity;

import java.io.Serializable;
import java.util.Date;

public class AcessoPk implements Serializable {
    private Date data;
    private String ip;

    public AcessoPk() {}

    public AcessoPk(Date data, String ip) {
        this.data = data;
        this.ip = ip;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }
}
