package gui;

import logic.Robot;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RobotImageComponent extends JComponent {
    private BufferedImage image;
    private final Robot robot;
    private int index;


    RobotImageComponent(Robot robot) {
        this.robot = robot;

        try {
            image = ImageIO.read(new File("/home/vlad/Downloads/technology.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel picLabel = new JLabel(new ImageIcon(image));
        add(picLabel);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(image != null){
            g.drawImage(image, (int) robot.getLines().get(index).getX2(), (int) robot.getLines().get(index).getY2(), this);
            index++;
        }
    }
}
