package GameOfLife.GUI;

import javax.swing.*;
import java.awt.*;

public class MainForm {
    JFrame jf;
    GamePanel gp;

    public MainForm(GamePanel gp) {
        this.gp = gp;
    }
    public void create() {
        jf = new JFrame("Game of Life");

        jf.setSize(gp.getWidthSize()*gp.getPxSize(), gp.getHeightSize()*gp.getPxSize() + 95);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        Insets insets = panel.getInsets();

        this.gp.setBounds(insets.left, insets.top, gp.getWidthSize()*gp.getPxSize(), gp.getHeightSize()*gp.getPxSize());
        this.gp.setVisible(true);
        panel.add(this.gp);

        JLabel sliderLabel = new JLabel("Move to adjust the speed");
        panel.add(sliderLabel);
        Dimension size = sliderLabel.getPreferredSize();
        sliderLabel.setBounds(insets.left + 32, gp.getHeight() + 10, (int)size.getWidth(),(int)size.getHeight());

        JSlider slider = new Slider();
        panel.add(slider);
        size = slider.getPreferredSize();
        slider.setBounds(insets.left + 25, gp.getHeight() + 30, (int)size.getWidth(),(int)size.getHeight());

        jf.add(panel);

        jf.setVisible(true);
    }
}
