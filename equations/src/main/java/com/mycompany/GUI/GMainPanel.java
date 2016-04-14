package com.mycompany.GUI;



import javax.swing.*;
import java.awt.*;

/**
 * Main panel.
 */
public class GMainPanel extends JPanel {
    public GMatrix pm = new GMatrix();

    public GMainPanel() {
        update();
        setLayout(new GridBagLayout());
    }

    //updates grid
    public void update() {
        removeAll();
        add(pm);
    }

    public GMatrix getPm() {
        return pm;
    }

    public void setPm(GMatrix pm) {
        this.pm = pm;
    }
}
