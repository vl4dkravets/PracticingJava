package gui;

import logic.Robot;
import logic.RobotPath;

import javax.swing.*;
import java.awt.*;

public class RobotPathComponent extends JComponent
{
    private int totNumOfLines = 0;
    private final Robot robot;

    public RobotPathComponent(Robot robot) {
        this.robot = robot;
    }

    public void addDrawingTile(){
        totNumOfLines++;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int currLines = 0;

        for (RobotPath rl : robot.getLines()) {
            if(currLines < totNumOfLines) {
                currLines++;

                // Coordinates for each line
                int x1 = (int) Math.round(rl.getX1());
                int y1 = (int) Math.round(rl.getY1());
                int x2 = (int) Math.round(rl.getX2());
                int y2 = (int) Math.round(rl.getY2());

                g.drawLine(x1, y1, x2, y2);
            }
        }
    }

    public int getTotNumOfLines() {
        return totNumOfLines;
    }
}