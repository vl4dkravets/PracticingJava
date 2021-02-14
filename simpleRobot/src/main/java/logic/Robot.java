package logic;

import java.util.ArrayList;

public class Robot
{
    private double x;
    private double y;
    protected double course = 0;
    //Storage of line objects which hold its coordinates
    private final ArrayList<RobotPath> lines = new ArrayList<>();


    public Robot(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void forward(int distance) {
        //Remember old coordinates
        final double xOld = x;
        final double yOld = y;

        // Calculating new coordinates
        x += distance * Math.cos(course / 180 * Math.PI);
        y += distance * Math.sin(course / 180 * Math.PI);

        //Creating & storing new line using 4 points
        lines.add(new RobotPath(xOld, yOld, x, y));
    }

    public double getCourse() {
        return course;
    }

    public void setCourse(double course) {
        this.course = course;
    }

    public ArrayList<RobotPath> getLines() {
        return lines;
    }
}