package com.nhson.lunarcalendarservice.core;

public class LunarDate {
    protected int day;
    protected int month;
    protected int year;
    protected boolean leap;
    protected long jd;

    public LunarDate(int day, int month, int year, boolean leap, long jd) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.leap = leap;
        this.jd = jd;
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

    public boolean isLeap() {
        return leap;
    }

    public void setLeap(boolean leap) {
        this.leap = leap;
    }

    public long getJd() {
        return jd;
    }

    public void setJd(long jd) {
        this.jd = jd;
    }
}
