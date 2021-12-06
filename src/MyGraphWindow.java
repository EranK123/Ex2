//import api.GeoLocation;
//import api.Location;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.util.LinkedList;
//
//public class MyGraphWindow extends JFrame {
//    public MyGraphWindow() throws HeadlessException{
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setSize(screenSize);
//        this.add(new MyPanel());
//        this.setVisible(true);
//    }
//    public class MyPanel extends JPanel implements MouseListener {
//        LinkedList<GeoLocation> locations = new LinkedList<>();
//
//        @Override
//        protected void paintComponent(Graphics g) {
//            super.paintComponent(g);
//            for(GeoLocation geoLocation : locations){
//                g.setColor(Color.BLACK);
////                g.fillOval((int) geoLocation.x(), (int) geoLocation.y(), (int) geoLocation.z(), 10,10,10);
//
//            }
//        }
//
//        @Override
//        public void mouseClicked(MouseEvent e) {
//
//        }
//
//        @Override
//        public void mousePressed(MouseEvent e) {
//            GeoLocation location = new Location(e.getX(),e.getY(),)
//
//        }
//
//        @Override
//        public void mouseReleased(MouseEvent e) {
//
//        }
//
//        @Override
//        public void mouseEntered(MouseEvent e) {
//
//        }
//
//        @Override
//        public void mouseExited(MouseEvent e) {
//
//        }
//    }
// }
