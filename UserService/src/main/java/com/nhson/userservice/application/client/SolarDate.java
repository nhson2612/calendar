package com.nhson.userservice.application.client;

public class SolarDate {
    private int day;
    private int month;
    private int year;

    public SolarDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    public SolarDate(int[] date) {
        if (date.length != 3) {
            throw new IllegalArgumentException("Date array must contain exactly 3 elements: [day, month, year]");
        }
        this.day = date[0];
        this.month = date[1];
        this.year = date[2];
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

