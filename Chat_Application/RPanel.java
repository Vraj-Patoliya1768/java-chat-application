package Chat_Application;

import javax.swing.*;
import java.awt.*;

class RPanel extends JPanel {

    private int cornerRadius;
    private Color backgroundColor;

    // Constructor with radius and background color
    public RPanel(){


    }
    public RPanel(int radius, Color bgColor) {
        this.cornerRadius = radius;
        this.backgroundColor = bgColor;
        setOpaque(false);
    }

    // New constructor to accept a layout manager
    public RPanel(LayoutManager layout) {
        super(layout);
        this.cornerRadius = 0;
        this.backgroundColor = Color.LIGHT_GRAY;
        setOpaque(false);
    }

    // Optional: Also support layout + radius + color
    public RPanel(LayoutManager layout, int radius, Color bgColor) {
        super(layout);
        this.cornerRadius = radius;
        this.backgroundColor = bgColor;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
    }
}
