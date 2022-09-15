package ru.primath.Manager;

import ru.primath.Objects.Couple;
import ru.primath.Objects.Primat;
import java.time.LocalTime;
import java.util.*;

public class ScheduleManager {
    private static final LocalTime c1b = LocalTime.parse("09:30:00");
    private static final LocalTime c2b = LocalTime.parse("11:15:00");
    private static final LocalTime c3b = LocalTime.parse("13:35:00");
    private static final LocalTime c4b = LocalTime.parse("15:20:00");

    private static final HashMap<String, HashMap<Byte, Couple>> schedule = new HashMap<>();

    public ScheduleManager() {
        schedule.put("firstDenominator", new HashMap<>());
        schedule.put("firstNumerator", new HashMap<>());
        schedule.put("secondDenominator", new HashMap<>());
        schedule.put("secondNumerator", new HashMap<>());
    }

    public static void addToSchedule(String map, Byte id, Couple couple) {
        schedule.get(map).put(id,couple);
    }

    private static Couple getFromSchedule(String map, Byte id) {
        return schedule.get(map).get(id);
    }

    public static ArrayList<Couple> getCouplesForDay(Primat primat, int day) {
        ArrayList<Couple> couples = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            couples.add(getCouple(primat, (byte) (i + (day - 1) * 4)));
        }
        return couples;
    }

    public static ArrayList<Couple> getSmartCouplesForDay(Primat primat, int day) {
        if (getCurrentTimeFlag() == 5) {
            day++;
            if (day == 6) {
                day = 1;
            }
        }
        return getCouplesForDay(primat, day);
    }

    public static Couple getCouple(Primat primat, byte id) {
        return getFromSchedule(getMapName(primat.getSubGroup()), id);
    }

    public static Couple getNextCouple(Primat primat) {
        byte id = getNextCoupleId();
        Couple couple = getCouple(primat, id);
        System.out.println("para?");
        while (couple.getTitle().equals("none")) {
            id++;
            if (id == 21) {
                id = 1;
            }
            couple = getCouple(primat, id);
        }
        couple.id = id;
        return couple;
    }
    private static int getCurrentTimeFlag() {

        LocalTime now = LocalTime.now();
        if (now.isBefore(c1b)) {
            System.out.println("r1");
            return 1;
        } else if (now.isBefore(c2b)) {
            System.out.println("r2");
            return 2;
        } else if (now.isBefore(c3b)) {
            System.out.println("r3");
            return 3;
        } else if (now.isBefore(c4b)) {
            System.out.println("r4");
            return 4;
        } else{
            System.out.println("r5");
            return 5;
        }
    }

    public static int getCurrentDay() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        return (calendar.get(Calendar.DAY_OF_WEEK) + 6)%7;
    }

    private static byte getNextCoupleId() {
        int day = getCurrentDay();
        if (day == 6) {
            day = 0;
        }
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
