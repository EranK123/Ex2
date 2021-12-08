
import api.*;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class Draw extends JPanel {

    private Graph gg;

    public Draw(){
        this.gg = new Graph();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.white);
        g.setColor(Color.BLUE);
        Graph gg = new Graph();
        GraphAlgo g_algo = new GraphAlgo(gg);
        Location l1 = new Location(15, 300, 3);
        Location l2 = new Location(30.1, 55.6, 3);
        Location l3 = new Location(300, 4, 8);
        Location l4 = new Location(100, 150, 8);
        Location l5 = new Location(150, 70, 1);
        Node n1 = new Node(0, l1);
        Node n2 = new Node(1, l2);
        Node n3 = new Node(2, l3);
        Node n4 = new Node(3, l4);
        Node n5 = new Node(4, l5);
        g_algo.getGraph().addNode(n1);
        g_algo.getGraph().addNode(n2);
        g_algo.getGraph().addNode(n3);
        g_algo.getGraph().addNode(n4);
        g_algo.getGraph().addNode(n5);
        g_algo.getGraph().connect(0, 1, 1);
        g_algo.getGraph().connect(3, 0, 3);
//        g_algo.getGraph().connect(0, 2, 2);
        g_algo.getGraph().connect(1, 3, 4);
//        g_algo.getGraph().connect(2, 3, 5);
        g_algo.getGraph().connect(4, 1, 3);
        g_algo.getGraph().connect(3, 4, 2);


        for (Iterator<NodeData> it = g_algo.getGraph().nodeIter(); it.hasNext(); ) {
            NodeData node = it.next();
            g.setColor(Color.BLACK);
            g.fillOval((int) node.getLocation().x(), (int) node.getLocation().y(), 10, 10);
        }


        for (Iterator<EdgeData> it = g_algo.getGraph().edgeIter(); it.hasNext(); ) {
            EdgeData edge = it.next();
            double xSrc = g_algo.getGraph().getNode(edge.getSrc()).getLocation().x();
            double ySrc = g_algo.getGraph().getNode(edge.getSrc()).getLocation().y();
            double xDest = g_algo.getGraph().getNode(edge.getDest()).getLocation().x();
            double yDest = g_algo.getGraph().getNode(edge.getDest()).getLocation().y();
            g.setColor(Color.red);
            g.drawLine((int) xSrc, (int) ySrc, (int) xDest, (int) yDest);

        }
    }
    public Graph getGraph(){
        return this.gg;
    }

}
