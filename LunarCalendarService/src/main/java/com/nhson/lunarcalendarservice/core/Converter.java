package com.nhson.lunarcalendarservice.core;

import java.util.ArrayList;
import java.util.List;

import static com.nhson.lunarcalendarservice.core.Constant.*;

public class Converter {
    public static long jdn(int dd, int mm, int yy) {
        int a = (14 - mm) / 12;
        int y = yy + 4800 - a;
        int m = mm + 12 * a - 3;
        return dd + (153 * m + 2) / 5 + 365L * y + y / 4 - y / 100 + y / 400 - 32045;
    }

    public static int[] jdnToDate(long jd) {
        long Z = jd;
        long F, A, alpha, B, C, D, E;
        if (Z < 2299161) {
            A = Z;
        } else {
            alpha = (long) ((Z - 1867216.25) / 36524.25);
            A = Z + 1 + alpha - alpha / 4;
        }
        B = A + 1524;
        C = (long) ((B - 122.1) / 365.25);
        D = (long) (365.25 * C);
        E = (long) ((B - D) / 30.6001);

        int dd = (int) (B - D - (long) (30.6001 * E));
        int mm = (E < 14) ? (int) (E - 1) : (int) (E - 13);
        int yy = (mm < 3) ? (int) (C - 4715) : (int) (C - 4716);

        return new int[]{dd, mm, yy};
    }

    private static List<LunarDate> decodeLunarYear(int yy, int k) {
        List<LunarDate> lunarDates = new ArrayList<>();
        int[] monthLengths = {29, 30};
        int offsetOfTet = k >> 17;
        int leapMonth = k & 0xf;
        int leapMonthLength = monthLengths[(k >> 16) & 0x1];
        long solarNY = jdn(1, 1, yy);
        long currentJD = solarNY + offsetOfTet;
        int[] regularMonths = new int[12];
        int j = k >> 4;
        for (int i = 0; i < 12; i++) {
            regularMonths[11 - i] = monthLengths[j & 0x1];
            j >>= 1;
        }
        if (leapMonth == 0) {
            for (int mm = 1; mm <= 12; mm++) {
                lunarDates.add(new LunarDate(1, mm, yy, false, currentJD));
                currentJD += regularMonths[mm - 1];
            }
        } else {
            for (int mm = 1; mm <= leapMonth; mm++) {
                lunarDates.add(new LunarDate(1, mm, yy, false, currentJD));
                currentJD += regularMonths[mm - 1];
            }
            lunarDates.add(new LunarDate(1, leapMonth, yy, true, currentJD));
            currentJD += leapMonthLength;
            // Các tháng sau tháng nhuận
            for (int mm = leapMonth + 1; mm <= 12; mm++) {
                lunarDates.add(new LunarDate(1, mm, yy, false, currentJD));
                currentJD += regularMonths[mm - 1];
            }
        }
        return lunarDates;
    }

    private static int getYearCode(int yy) {
        if (yy < 2100) {
            return TK21[yy - 2000];
        } else {
            return TK22[yy - 2100];
        }
    }

    public static LunarDate solarToLunar(int dd, int mm, int yy) {
        long jd = jdn(dd, mm, yy);
        int yearCode = getYearCode(yy);
        List<LunarDate> lunarYear = decodeLunarYear(yy, yearCode);
        if (jd < lunarYear.get(0).jd) {
            yearCode = getYearCode(yy - 1);
            lunarYear = decodeLunarYear(yy - 1, yearCode);
        }
        for (int i = lunarYear.size() - 1; i >= 0; i--) {
            LunarDate lunar = lunarYear.get(i);
            if (jd >= lunar.jd) {
                int days = (int) (jd - lunar.jd) + 1;
                return new LunarDate(
                        days,
                        lunar.month,
                        lunar.year,
                        lunar.leap,
                        jd
                );
            }
        }
        return null;
    }

    public static SolarDate lunarToSolar(int dd, int mm, int yy, boolean leap) {
        int yearCode = getYearCode(yy);
        List<LunarDate> lunarYear = decodeLunarYear(yy, yearCode);
        for (LunarDate lunarMonth : lunarYear) {
            if (lunarMonth.month == mm && lunarMonth.leap == leap) {
                long targetJD = lunarMonth.jd + dd - 1; // Sửa lỗi: trừ 1 vì ngày 1 tháng
                int[] ints = jdnToDate(targetJD);
                return new SolarDate(ints);
            }
        }
        return null;
    }
    public static SolarDate lunarToSolar(LunarDate lunar) {
        return lunarToSolar(lunar.day, lunar.month, lunar.year, lunar.leap);
    }
    public static String[] getCanChi(LunarDate lunar) {
        String dayName = CAN[(int) ((lunar.jd + 9) % 10)] + " " + CHI[(int) ((lunar.jd + 1) % 12)];
        int monthIndex = (lunar.year * 12 + lunar.month + 3) % 10;
        String monthName = CAN[monthIndex] + " " + CHI[(lunar.month + 1) % 12];
        String yearName = CAN[(lunar.year + 6) % 10] + " " + CHI[(lunar.year + 8) % 12];
        return new String[]{dayName, monthName, yearName};
    }
}