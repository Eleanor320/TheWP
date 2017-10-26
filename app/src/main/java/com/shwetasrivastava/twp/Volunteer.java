package com.shwetasrivastava.twp;

public class Volunteer {

    public String v_name;
    public String email;
    public String phone;
    int attendance=0;
    public  String center;

    public Volunteer(String v_name, String email, String phone, int attendance, String center) {
        this.v_name = v_name;
        this.email = email;
        this.phone = phone;
        this.attendance = attendance;
        this.center = center;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getV_name() {
        return v_name;
    }

    public void setV_name(String v_name) {
        this.v_name = v_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
