import api.*;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class Draw extends JComponent {
    public static final int HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
    public static final int WIDTH = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);
    private final GraphAlgo graphAlgo;

    public Draw(Graph g) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        graphAlgo = new GraphAlgo(g);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.white);
        g.setColor(Color.BLUE);
        Iterator<NodeData> nodeIter = graphAlgo.getGraph().nodeIter();
        double xMax = Integer.MIN_VALUE, xMin = Integer.MAX_VALUE, yMax = Integer.MIN_VALUE, yMin = Integer.MAX_VALUE;
        while (nodeIter.hasNext()) {
            NodeData n = nodeIter.next();
            if (n.getLocation().x() > xMax) {
                xMax = n.getLocation().x();
            }
            if (n.getLocation().x() < xMin) {
                xMin = n.getLocation().x();
            }
            if (n.getLocation().y() > yMax) {
                yMax = n.getLocation().y();
            }
            if (n.getLocation().y() < yMin) {
                yMin = n.getLocation().y();
            }


        }
        for (Iterator<NodeData> it = graphAlgo.getGraph().nodeIter(); it.hasNext(); ) {
            NodeData node = it.next();
            double x = node.getLocation().x() - xMin;
            double y = node.getLocation().y() - yMin;

            int placeX = (int) ((x / (xMax - xMin)) * (WIDTH * 0.8)) + (int) (0.06 * WIDTH);
            int placeY = (int) ((y / (yMax - yMin)) * (HEIGHT * 0.8)) + (int) (0.06 * 500);
            g.setColor(Color.BLACK);
            g.fillOval(placeX, placeY, 10, 10);
            g.drawString(""+ node.getKey(), placeX - 5, placeY -5);
        }

        for (Iterator<EdgeData> it = graphAlgo.getGraph().edgeIter(); it.hasNext(); ) {
            EdgeData edge = it.next();
            double xSrc = graphAlgo.getGraph().getNode(edge.getSrc()).getLocation().x() - xMin;
            double ySrc = graphAlgo.getGraph().getNode(edge.getSrc()).getLocation().y() - yMin;
            double xDest = graphAlgo.getGraph().getNode(edge.getDest()).getLocation().x() - xMin;
            double yDest = graphAlgo.getGraph().getNode(edge.getDest()).getLocation().y() - yMin;

            int placeSrcX = (int) ((xSrc / (xMax - xMin)) * (WIDTH * 0.8)) + (int) (0.065 * WIDTH);
            int placeDestX = (int) ((xDest / (xMax - xMin)) * (WIDTH * 0.8)) + (int) (0.065 * WIDTH);
            int placeSrcY = (int) ((ySrc / (yMax - yMin)) * (HEIGHT * 0.8)) + 35;
            int placeDestY = (int) ((yDest / (yMax - yMin)) * (HEIGHT * 0.8)) + 35;

            g.setColor(Color.red);
            g.drawLine(placeSrcX, placeSrcY, placeDestX, placeDestY);
            Font f = g.getFont().deriveFont(6.f);
            g.setFont(f);
            g.drawString("" + edge.getWeight(), placeSrcX , placeDestY);

        }

    }

    public Graph getGraph() {
        return (Graph) this.graphAlgo.getGraph();
    }
}
