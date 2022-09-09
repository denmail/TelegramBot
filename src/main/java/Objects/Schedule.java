package Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Schedule{
    public ArrayList<Group> group;
}
class Group{
    public ArrayList<Second> second;
    public ArrayList<First> first;
}
class First{
    public Up up;
    public Down down;
}
class Second{
    public Up up;
    public Down down;
}
class Down{
    public ArrayList<Day> days;
}
class Up{
    public ArrayList<Day> days;
}
class Day{
    public ArrayList<Monday> monday;
    public ArrayList<Tuesday> tuesday;
    public ArrayList<Wednesday> wednesday;
    public ArrayList<Thursday> thursday;
    public ArrayList<Friday> friday;
}
class Root{
    public Schedule schedule;
}
class Monday{
    public String name;
    public String office;
}
class Tuesday{
    public String name;
    public String office;
}
class Wednesday{
    public String name;
    public String office;
}
class Thursday{
    public String name;
    public String office;
}
class Friday{
    public String name;
    public String office;
}

















