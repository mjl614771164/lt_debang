package com.leting.statistics.model;

import java.util.Date;

public class Statistics {
    private Integer id;
    private String deviceid;
    private String command;
    private Integer count;
    private Integer state;
    private Date createdate;

    public Statistics() {
    }

    public Statistics(Integer id, String deviceid, String command, Integer count, Integer state, Date createdate) {
        this.id = id;
        this.deviceid = deviceid;
        this.command = command;
        this.count = count;
        this.state = state;
        this.createdate = createdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}
