package com.example.wojciech.zpoaplikacja;

public class PPM {

    public double harrisBenedictMethod(int sex, double weight, double height, int age) {
        double index;
        if (sex == 1) {
            index = 655.1 + 9.563 * weight + 1.85 * height - 4.676 * age;
        } else {
            index = 66.5 + 13.75 * weight + 5.003 * height - 6.775 * age;
        }
        return index;
    }



    public double mifflinMethod(int sex, double weight, double height, int age) {
        double index;
        if (sex == 1) {
            index = 10 * weight + 6.25 * height - 5 * age - 161;
        } else {
            index = 10 * weight + 6.25 * height - 5 * age + 5;
        }
        return index;
    }
}
