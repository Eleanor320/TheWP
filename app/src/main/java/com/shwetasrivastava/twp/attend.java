package com.shwetasrivastava.twp;

/**
 * Created by Shweta Srivastava on 8/4/2017.
 */

public class attend {

    String date, attend;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAttend() {
        return attend;
    }

    public void setAttend(String attend) {
        this.attend = attend;
    }

    public attend(String date, String attend) {

        this.date = date;
        this.attend = attend;
    }
}
