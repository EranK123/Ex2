import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.Iterator;

public class GUI extends JFrame {

    private final GraphAlgo graphAlgo;


    public void run(){
        Draw draw = new Draw((Graph) graphAlgo.getGraph());
        Menu menu = new Menu((Graph) graphAlgo.getGraph());
        this.setJMenuBar(menu.getMenuBar());
        setVisible(true);
        this.add(draw);
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.repaint();
        this.setVisible(true);
    }

    public GUI(GraphAlgo g){
        this.graphAlgo = g;
    }

}