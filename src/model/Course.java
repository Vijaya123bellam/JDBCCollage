package model;

public class Course {
    private int id;
    private String name, duration;

    public Course() {}

    public Course(String name, String duration) {
        this.name = name; this.duration = duration;
    }

    public Course(int id, String name, String duration) {
        this.id = id; this.name = name; this.duration = duration;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDuration() { return duration; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDuration(String duration) { this.duration = duration; }
}
