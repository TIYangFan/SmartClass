package com.example.smartclass.bean;

/**
 * Created by YangFan
 * On 2019/3/1
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class StudentStatusDetailsBean {

    private String during;
    private String lesson_duration;
    private String class_no;
    private String stu_id;
    private String name;

    public String getDuring() {
        return during;
    }

    public void setDuring(String during) {
        this.during = during;
    }

    public String getLesson_duration() {
        return lesson_duration;
    }

    public void setLesson_duration(String lesson_duration) {
        this.lesson_duration = lesson_duration;
    }

    public String getClass_no() {
        return class_no;
    }

    public void setClass_no(String class_no) {
        this.class_no = class_no;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
