import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.Iterator;

public class GUI extends JFrame {


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        MenuBar menuBar = new MenuBar();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Draw draw = new Draw();
        Menu menu = new Menu(draw.getGraph());
        menu.setMenuBar(menuBar);
        frame.setSize(400, 400);
        menu.add(draw);
        frame.add(menu);
        frame.setVisible(true);

    }
}