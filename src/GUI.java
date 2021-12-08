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
        JPanel panel = new JPanel();
        MenuBar menuBar = new MenuBar();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Draw draw = new Draw();
        Menu menu = new Menu(draw.gg);
        menu.setMenuBar(menuBar);
        frame.setSize(400,400);
        menu.add(draw);
//        panel.add(menu);
//        panel.add(draw);
        frame.add(menu);
//        frame.setMenuBar(menu.getMenuBar());

        frame.setVisible(true);

    }






























//    public GUI(){
//        this.frame = new JFrame();
//        this.panel = new JPanel();
//        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
//        panel.setLayout(new GridLayout(0,1));
//        frame.add(panel, BorderLayout.CENTER);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setTitle("GUI");
//        frame.pack();
//        frame.setVisible(true);
//    }

//    public void create(){
//            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//            JFrame j = new JFrame();
//            j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            j.setSize(screenSize);
//            j.getContentPane().setBackground(new Color(0xCC1111));
//            j.setTitle("Graph");
//            paint(j.getGraphics());
//            j.setVisible(true);
//        }
//
//
//    public void paint(Graphics g) {
//        super.paint(g);
////        Graphics g2 = panel.getGraphics();
//        this.setBackground(Color.WHITE);
////        g.setColor(Color.BLACK);
////        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
////        getJMenuBar().paint(g);
////        GUI.super.paint(g);
////        JPanel panel = new JPanel();
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        panel.setDebugGraphicsOptions(JFrame.EXIT_ON_CLOSE);
//        panel.setSize(screenSize);
//        Graph gg = new Graph();
//        directedWeightedAlgorithms g_algo = new directedWeightedAlgorithms(gg);
//        Location l1 = new Location(1, 2, 3);
//        Location l2 = new Location(3, 5, 3);
//        Location l3 = new Location(5, 4, 8);
//        Location l4 = new Location(6, 1, 8);
//        Location l5 = new Location(2, 7, 1);
//        Node n1 = new Node(0, l1);
//        Node n2 = new Node(1, l2);
//        Node n3 = new Node(2, l3);
//        Node n4 = new Node(3, l4);
//        Node n5 = new Node(4, l5);
//        g_algo.getGraph().addNode(n1);
//        g_algo.getGraph().addNode(n2);
//        g_algo.getGraph().addNode(n3);
//        g_algo.getGraph().addNode(n4);
//        g_algo.getGraph().addNode(n5);
//        g_algo.getGraph().connect(0, 1, 1);
//        g_algo.getGraph().connect(3, 0, 3);
//        g_algo.getGraph().connect(0, 2, 2);
//        g_algo.getGraph().connect(1, 3, 4);
//        g_algo.getGraph().connect(2, 3, 5);
//        g_algo.getGraph().connect(4, 1, 3);
//        g_algo.getGraph().connect(3, 4, 2);
//
////        FontMetrics f = g.getFontMetrics();
//        for (Iterator<EdgeData> it = g_algo.getGraph().edgeIter(); it.hasNext();) {
//            EdgeData edge = it.next();
//            double xSrc = g_algo.getGraph().getNode(edge.getSrc()).getLocation().x();
//            double ySrc = g_algo.getGraph().getNode(edge.getSrc()).getLocation().y();
//            double xDest = g_algo.getGraph().getNode(edge.getDest()).getLocation().x();
//            double yDest = g_algo.getGraph().getNode(edge.getDest()).getLocation().y();
//            g.drawLine((int) xSrc, (int) ySrc, (int) xDest, (int) yDest);
//            this.panel.paint(g);
//
//        }
//
//        for (Iterator<NodeData> it = g_algo.getGraph().nodeIter(); it.hasNext();) {
//            NodeData node = it.next();
//            g.setColor(Color.white);
//            g.fillOval((int)node.getLocation().x(), (int)node.getLocation().y(), 10,10);
//            panel.paint(g);
//        }
//        g.setColor(Color.black);
//        panel.update(g);
//        frame.add(panel);
//    }
}

