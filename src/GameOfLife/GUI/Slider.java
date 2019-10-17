package GameOfLife.GUI;

import javax.swing.*;

public class Slider extends JSlider{
    Slider() {
        this.setMajorTickSpacing(5);
        this.setMinorTickSpacing(1);
        this.setPaintTicks(true);
        this.addChangeListener(new SliderChangeListener());
    }
}
