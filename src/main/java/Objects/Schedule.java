package Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
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
class Up{
    public ArrayList<Week> week;
}
class Down{
    public ArrayList<Week> week;
}
class Week{
    public ArrayList<Day> day;
}
class Day{
    public String name;
    public String office;
}

















