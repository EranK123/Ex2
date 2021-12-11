import javax.swing.*;

public class DisplayMessage {

    JFrame frame = new JFrame();


    public DisplayMessage(String s){
        JLabel label = new JLabel(s);
        label.setBounds(0,0,100,100);
        frame.add(label);
        frame.setSize(100,100);
        frame.setVisible(true);
    }
}
