import javax.swing.*;
import java.awt.*;

public class GUI {
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame j = new JFrame();
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setSize(screenSize);
        j.getContentPane().setBackground(new Color(0xCC1111));
        j.setTitle("Graph");
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Exec On Graphs");
        menuBar.add(menu);
        j.add(menuBar);

        j.setVisible(true);
    }

}
