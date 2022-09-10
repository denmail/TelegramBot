package Manager;

import Objects.Schedule.Couple;
import Objects.Schedule.Schedule;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScheduleManager {
    static Schedule schedule;
    BufferedReader reader;

    public ScheduleManager() {
        schedule = new Schedule();
        readSchedule();
        System.out.println(schedule.groups.get(1).weeks.get(0).days.get(0).couples.get(0).office);
    }

    public void readSchedule(){
        try {
            reader = Files.newBufferedReader(Paths.get("src/main/resources/Schedule.json"));
            Gson gson = new Gson();
            schedule = gson.fromJson(reader, Schedule.class);
            System.out.println("c3");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Couple getCouple(int group, int week, int day, int noCouple){
        int c = noCouple-1, d = day-1, w = week-1, g = group-1;
        if(c == 3){
            c = 0;
            d ++;
        }
        if(d >= 4) {
            d = 0;
            week++;
        }
        if(week == 2) {
            week = 0;
        }
        System.out.printf("%d, %d, %d, %d\n", g, w, d, c);
        System.out.println(schedule.groups.get(group).weeks.get(week).days.get(day).couples.get(noCouple).name);
        return schedule.groups.get(group).weeks.get(week).days.get(day).couples.get(noCouple);
    }
}
