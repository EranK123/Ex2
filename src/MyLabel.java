import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.http.HttpClient;

public class MyLabel extends JFrame implements ActionListener {
    JTextField textField;
    JLabel label;
    JButton button;

    public MyLabel() throws HeadlessException {
        setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.textField = new JTextField();
        this.textField.setBounds(50,50,100,200);
        this.add(textField);
        this.button = new JButton("idk");
        button.setBounds(50,150,500,20);
        button.addActionListener(this);
        this.add(button);
        label = new JLabel("das");
        label.setBounds(50,50,100,200);
        this.add(label);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MyLabel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String g = textField.getText();

        }catch (Exception exception){
            System.out.println(exception);
        }
    }
}
