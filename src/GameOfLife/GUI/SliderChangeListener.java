package GameOfLife.GUI;

import GameOfLife.Inf;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderChangeListener implements ChangeListener {
    private int dPeriod;

    SliderChangeListener() {
        dPeriod = (Inf.maxPeriod - Inf.minPeriod) / 100;
    }
    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        JSlider source = (JSlider)changeEvent.getSource();

        if (!source.getValueIsAdjusting()) {
            int val = (int)source.getValue();

            if (val == 0 && Inf.isRunning == true)
                Inf.isRunning = false;
            else if (val != 0 && Inf.isRunning == false) {
                Inf.isRunning = true;
                Inf.currentPeriod = Inf.minPeriod + val*dPeriod;
            }
            else
                Inf.currentPeriod = Inf.minPeriod + val*dPeriod;

            System.out.println(Inf.isRunning);
            System.out.println(Inf.currentPeriod);
        }
    }
}
