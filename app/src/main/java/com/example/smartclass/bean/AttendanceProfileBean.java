package com.example.smartclass.bean;

/**
 * Created by YangFan
 * On 2019/2/26
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class AttendanceProfileBean {

    private boolean status;
    private String lesson_name;
    private String classes;
    private float current_attendance;
    private int total_students;
    private int late;
    private int early;
    private int qingjia;
    private int absent;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getLesson_name() {
        return lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public float getCurrent_attendance() {
        return current_attendance;
    }

    public void setCurrent_attendance(float current_attendance) {
        this.current_attendance = current_attendance;
    }

    public int getTotal_students() {
        return total_students;
    }

    public void setTotal_students(int total_students) {
        this.total_students = total_students;
    }

    public int getLate() {
        return late;
    }

    public void setLate(int late) {
        this.late = late;
    }

    public int getEarly() {
        return early;
    }

    public void setEarly(int early) {
        this.early = early;
    }

    public int getQingjia() {
        return qingjia;
    }

    public void setQingjia(int qingjia) {
        this.qingjia = qingjia;
    }

    public int getAbsent() {
        return absent;
    }

    public void setAbsent(int absent) {
        this.absent = absent;
    }
}
