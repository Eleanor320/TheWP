package com.shwetasrivastava.twp;

/**
 * Created by Shweta Srivastava on 6/18/2017.
 */

public class Student {
     String name;

    String getLevel() {
        return level;
    }
    void setLevel(String level) {
        this.level = level;
    }

    String level;

    Student(String name, String level) {
            this.name = name;
            this.level=level;

    }

    Student(String name) {
        this.name = name;
    }

    Student() {
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }
}
