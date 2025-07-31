package com.nhson.lunarcalendarservice.core;

public class Main {
    public static void main(String[] args) {
        // Test chuyển đổi từ dương lịch sang âm lịch
        System.out.println("=== Test chuyển đổi Dương lịch -> Âm lịch ===");
        LunarDate lunar = Converter.solarToLunar(29, 7, 2025);
        System.out.printf("Dương lịch: 29/7/2025\n");
        System.out.printf("Âm lịch: %d/%d%s/%d\n",
                lunar.day, lunar.month, lunar.leap ? "n" : "", lunar.year);

        // Test chuyển đổi ngược lại
        System.out.println("\n=== Test chuyển đổi Âm lịch -> Dương lịch ===");
        SolarDate solarDate = Converter.lunarToSolar(lunar.day, lunar.month, lunar.year, lunar.leap);
        System.out.printf("Âm lịch: %d/%d%s/%d\n",
                lunar.day, lunar.month, lunar.leap ? "n" : "", lunar.year);
        System.out.printf("Dương lịch: %d/%d/%d\n",
                solarDate.getDay(), solarDate.getYear(), solarDate.getYear());

        // Tính Can-Chi
        System.out.println("\n=== Can-Chi ===");
        String[] canChi = Converter.getCanChi(lunar);
        System.out.println("Ngày: " + canChi[0]);
        System.out.println("Tháng: " + canChi[1]);
        System.out.println("Năm: " + canChi[2]);

        // Test thêm một số ngày khác
        System.out.println("\n=== Test thêm các ngày khác ===");
        testDate(1, 1, 2025, "Tết Dương lịch");
        testDate(29, 1, 2025, "Tết Nguyên đán");
        testDate(15, 8, 2025, "Rằm tháng 8");
        testDate(10, 3, 2025, "Giỗ Tổ Hùng Vương");
    }

    private static void testDate(int dd, int mm, int yy, String note) {
        System.out.printf("\n%s - Dương lịch: %d/%d/%d\n", note, dd, mm, yy);
        LunarDate lunar = Converter.solarToLunar(dd, mm, yy);
        System.out.printf("Âm lịch: %d/%d%s/%d\n",
                lunar.day, lunar.month, lunar.leap ? "n" : "", lunar.year);

        // Kiểm tra chuyển đổi ngược
        SolarDate solarDate = Converter.lunarToSolar(lunar.day, lunar.month, lunar.year, lunar.leap);
        boolean isCorrect = (solarDate.getDay() == dd && solarDate.getMonth() == mm && solarDate.getYear() == yy);
        System.out.printf("Kiểm tra ngược: %d/%d/%d - %s\n",
                solarDate.getDay(), solarDate.getMonth(), solarDate.getYear(),
                isCorrect ? "✓ Đúng" : "✗ Sai");
    }
}