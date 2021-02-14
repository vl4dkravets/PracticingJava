package robot;

import gui.RobotFrame;
import logic.Robot;

import javax.swing.*;

public class RobotManager
{
    public static void main(String[] args) {
        //Total number of sides of the figure
        final int COUNT = 12;
        // Length of a side's figure
        final int SIDE = 100;

        Robot robot = new Robot(200, 50);
        //Creates a closed figure
        for (int i = 0; i < COUNT; i++) {
            robot.forward(SIDE);
            robot.setCourse(robot.getCourse() + 360 / COUNT);
        }

        //Creating a form for drawing
        RobotFrame rf = new RobotFrame(robot, COUNT);
        rf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rf.setVisible(true);
    }
}