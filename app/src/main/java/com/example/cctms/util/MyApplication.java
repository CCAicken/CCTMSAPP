package com.example.cctms.util;

import android.app.Application;

/**
 * 个人信息全局变量储存
 */
public class MyApplication extends Application {
    private String stuId;
    private String stuName;
    private String stuPhoto;
    private String stupwd;
    private Integer classId;
    private String createTime;
    private String agend;
    private String nation;

    // Property accessors

    public String getStuId() {
        return this.stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return this.stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuPhoto() {
        return this.stuPhoto;
    }

    public void setStuPhoto(String stuPhoto) {
        this.stuPhoto = stuPhoto;
    }

    public String getStupwd() {
        return this.stupwd;
    }

    public void setStupwd(String stupwd) {
        this.stupwd = stupwd;
    }

    public Integer getClassId() {
        return this.classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAgend() {
        return this.agend;
    }

    public void setAgend(String agend) {
        this.agend = agend;
    }

    public String getNation() {
        return this.nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }
}
