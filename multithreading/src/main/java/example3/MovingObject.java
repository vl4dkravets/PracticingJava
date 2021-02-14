package example3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovingObject extends JFrame implements ActionListener{

    private static final int D_W = 500;
    private static final int D_H = 200;
    private final String START = "START";
    private final String STOP = "STOP";
    private RectangleThread rt = null;
    private final Rectangle r;

    MovingObject() {
        setBounds(100, 100, D_W, D_H);
        r = new Rectangle(D_W, D_H);

        JPanel panel = new JPanel();

        panel.setLayout(new BorderLayout());
        JButton start = new JButton(START);
        start.setActionCommand(START);
        start.addActionListener(this);
        panel.add(start, BorderLayout.EAST);

        JButton stop = new JButton(STOP);
        stop.setActionCommand(STOP);
        stop.addActionListener(this);
        panel.add(stop, BorderLayout.WEST);

        add(r);
        add(panel, BorderLayout.SOUTH);
    }


    public static void main(String[] args) {
        MovingObject cl = new MovingObject();
        cl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cl.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
            if(START.equals(ae.getActionCommand())) {
                if(rt == null) {
                    rt = new RectangleThread(this);
                    rt.startMoving();
                    rt.start();
                }

            }
            if(STOP.equals(ae.getActionCommand())) {
                if (rt != null) {
                    rt.stopMoving();
                    rt = null;
                }
            }
    }

    public void updateRectanglePos() {
        r.updateX();
        r.repaint();
    }

}
