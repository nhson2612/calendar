package com.nhson.userservice.application.client;

public class LunarDate {
    private int day;
    private int month;
    private int year;
    private boolean leap;
    private long jd;

    public LunarDate(int day, int month, int year, boolean leap, long jd) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.leap = leap;
        this.jd = jd;
    }
}