package ru.primath.Manager;

import ru.primath.Objects.Schedule.Couple;
import ru.primath.Objects.Schedule.Schedule;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScheduleManager {
    static Schedule schedule;

    public ScheduleManager() {
        schedule = new Schedule();
        readSchedule();
    }

    public void readSchedule(){
        try {
            FileReader fr = new FileReader("src/main/resources/Schedule.json");
            BufferedReader reader = new BufferedReader(fr));
            Gson gson = new Gson();
            schedule = gson.fromJson(reader, Schedule.class);
            reader.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Couple getCouple(int group, int week, int day, int noCouple){
        int c = noCouple-1, d = day, w = week, g = group-1;
        System.out.printf("%d, %d, %d, %d\n", g, w, d, c);
        System.out.println("w="+w);
        if(c >= 3){
            c = 0;
            d++;
        }
        if(d > 4) {
            d = 0;
            w++;
        }
        w %= 2;
        System.out.printf("%d, %d, %d, %d\n", g, w, d, c);
        System.out.println(schedule.groups.get(g).weeks.get(w).days.get(d).couples.get(c).name);
        return schedule.groups.get(g).weeks.get(w).days.get(d).couples.get(c);
    }
}
