package com.pojo;

import java.io.Serializable;

// @Alias("role")
public class Role implements Serializable {
    private String id;
    private String rolename;
    private String note;
    private School school;

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", rolename='" + rolename + '\'' +
                ", note='" + note + '\'' +
                ", school=" + school +
                '}';
    }

    public Role() {
    }

    public Role(String id, String rolename, String note) {
        this.id = id;
        this.rolename = rolename;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
