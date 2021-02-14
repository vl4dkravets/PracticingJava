package gui;

import logic.Robot;

import javax.swing.*;

public class RobotFrame extends JFrame
{
    private Timer timer, timer1;
    private final RobotPathComponent rpc;
    private final int NUM_OF_LINES;
    int count = 0;

    public RobotFrame(Robot robot, int lines) {
        rpc = new RobotPathComponent(robot);

        NUM_OF_LINES = lines;
        //Initialize timer for drawing
        initTimerOperation();

        setTitle("Robot Frame");

        //Adding component to client's main panel(display)
        add(rpc);

        setBounds(100, 100, 500, 500);
    }

    private void initTimerOperation() {
        /*
        Each half of a second, the timer fires an event which performs
        the action specified in actionPerformed() method (lambda)
         */
        timer = new Timer(250, e -> {
            if(rpc.getTotNumOfLines() < NUM_OF_LINES){
                rpc.addDrawingTile();
                rpc.repaint();
            } else {
                timer.stop();
                System.out.println("done");
            }
        });
        timer.start();
    }

}