package example3;

import javax.swing.*;
import java.awt.*;

public class Rectangle extends JComponent {
    private int x;
    private int y;
    private final int width;
    private final int height;

    Rectangle (int w, int h) {
        x = 0;
        y = 0;
        width = w;
        height = h;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.GREEN);

        if(x >= width) { x = 0; }

        g.fillRect(x, y, 50, 50);
    }

    public void updateX() {
        x+=10;
    }

}
