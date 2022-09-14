package ru.primath.Manager;

import ru.primath.Objects.NewCouple;
import ru.primath.Objects.Primat;
import ru.primath.Objects.Schedule.Couple;
import ru.primath.Objects.Schedule.Schedule;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class ScheduleManager {

    private static LocalTime c1b = LocalTime.parse("09:30:00");
    private static LocalTime c2b = LocalTime.parse("11:15:00");
    private static LocalTime c3b = LocalTime.parse("13:35:00");
    private static LocalTime c4b = LocalTime.parse("15:20:00");
    private static LocalTime c1e = LocalTime.parse("11:05:00");
    private static LocalTime c2e = LocalTime.parse("12:50:00");
    private static LocalTime c3e = LocalTime.parse("15:10:00");
    private static LocalTime c4e = LocalTime.parse("16:55:00");
    static Schedule schedule;
    private static HashMap<String, HashMap<Byte, NewCouple>> newSchedule = new HashMap<>();

    public ScheduleManager() {
        newSchedule.put("firstDenominator", new HashMap<>());
        newSchedule.put("firstNumerator", new HashMap<>());
        newSchedule.put("secondDenominator", new HashMap<>());
        newSchedule.put("secondNumerator", new HashMap<>());
    }

    public static void addToSchedule(String map, Byte id, NewCouple couple) {
        newSchedule.get(map).put(id,couple);
    }

    private static NewCouple getFromSchedule(String map, Byte id) {
        return newSchedule.get(map).get(id);
    }

    public static NewCouple getNextCouple(Primat primat) {
        byte id = getCurrentCoupleId();
        NewCouple couple = getFromSchedule(getMapName(primat.getSubGroup()), id);
        while (couple.getTitle().equals("none")) {
            id++;
            id %= 21;
        }
        couple.id = id;
        return couple;
    }
    private static int getCurrentTimeFlag() {

        LocalTime now = LocalTime.now();
        if (now.isBefore(c1b)) {
            System.out.println("r1");
            return 0;
        } else if (now.isBefore(c2b)) {
            System.out.println("r2");
            return 1;
        } else if (now.isBefore(c3b)) {
            System.out.println("r3");
            return 2;
        } else if (now.isBefore(c4e)) {
            System.out.println("r4");
            return 3;
        } else{
            System.out.println("r4");
            return 4;
        }
    }

    private static int getCurrentDay() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        return (calendar.get(Calendar.DAY_OF_WEEK) + 6)%7;
    }

    private static byte getCurrentCoupleId() {
        int day = getCurrentDay();
        if (day == 6) {
            day = 0;
        }
        System.out.println(getCurrentTimeFlag() + 4 * (day - 1));
        return (byte) (getCurrentTimeFlag() + 4 * (day - 1));
    }
    private static boolean isDenominator() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        return calendar.get(Calendar.WEEK_OF_MONTH) % 2 == 0;
    }

    private static String getMapName(int subGroup) {
        if (isDenominator()) {
            if (subGroup == 1) {
                return "firstDenominator";
            } else {
                return "secondDenominator";
            }
        } else {
            if (subGroup == 1) {
                return "firstNumerator";
            } else {
                return "secondNumerator";
            }
        }
    }
}
