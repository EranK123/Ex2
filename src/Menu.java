import api.Graph;
import api.GraphAlgo;
import api.Location;
import api.Node;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

class Menu extends JFrame {
    private Graph g = new Graph();
    private GraphAlgo graphAlgo = new GraphAlgo(g);
    public Menu() {

        initUI();
    }

    private void initUI() {

        createMenuBar();

        setTitle("Simple menu");
        setVisible(true);
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createMenuBar() {

        var menuBar = new JMenuBar();
        var fileMenu = new JMenu("Actions Menu");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        var eMenuItem = new JMenuItem("Exit");
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.addActionListener((event) -> System.exit(0));

        fileMenu.add(eMenuItem);
        menuBar.add(fileMenu);
        JButton buttonLoad = new JButton("Load");
        JButton buttonSave = new JButton("Save");
        JButton buttonEdit = new JButton("Edit");
        JButton buttonRun = new JButton("Run");
        menuBar.add(buttonLoad);
        menuBar.add(buttonSave);
        menuBar.add(buttonEdit);
        menuBar.add(buttonRun);

        buttonLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                JPanel panel = new JPanel();
                panel.setSize(50, 50);
                frame.setSize(300, 300);
                panel.setVisible(true);
                frame.setVisible(true);
                JLabel label = new JLabel("Enter File Name");
                JTextField tf = new JTextField(100);
                String s = tf.getText();
                JButton enter = new JButton("Send");
                enter.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        graphAlgo.load(s);
                    }
                });
                panel.add(label);
                panel.add(enter);
                panel.add(tf);
                frame.add(panel);
            }
        });

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                JPanel panel = new JPanel();
                panel.setSize(50, 50);
                frame.setSize(300, 300);
                panel.setVisible(true);
                frame.setVisible(true);
                JLabel label = new JLabel("Enter File Name");
                JTextField tf = new JTextField(100);
                String s = tf.getText();
                JButton send = new JButton("Send");
                send.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        graphAlgo.save(s);
                    }
                });
                panel.add(label);
                panel.add(send);
                panel.add(tf);
                frame.add(panel);
            }
        });
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setSize(300,300);
                frame.setVisible(true);
                JButton addNode = new JButton("ADD NODE");
                addNode.setBounds(100,100,100,100);
                JButton removeNode = new JButton("REMOVE NODE");
                removeNode.setBounds(20,20,20,20);
                JButton addEdge = new JButton("ADD EDGE");
                addEdge.setBounds(30,30,30,30);
                JButton removeEdge = new JButton("REMOVE EDGE");
                removeEdge.setBounds(40,40,40,40);
                frame.add(addNode);
                frame.add(removeNode);
                frame.add(addEdge);
                frame.add(removeEdge);

                addNode.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame frame = new JFrame();
                        frame.setSize(400,400);
                        frame.setVisible(true);
                        JPanel panelID = new JPanel();
                        panelID.setSize(50,50);
                        panelID.setVisible(true);
                        JPanel panelPos = new JPanel();
                        panelPos.setSize(50,50);
                        panelPos.setVisible(true);
                        JLabel labelID = new JLabel("Enter ID");
                        JLabel labelPos = new JLabel("Enter Pos");
                        JTextField tfID = new JTextField(10);
                        JTextField tfPos = new JTextField(100);
                        String idText = tfID.getText();
                        int id = Integer.parseInt(idText);
                        String posText = tfPos.getText();
                        String[] a = posText.split(",",3);
                        double[] posVal = new double[3];
                        for (int i = 0; i < a.length; i++) {
                            posVal[i] = Double.parseDouble(a[i]);
                        }
                        Location location = new Location(posVal[0], posVal[1], posVal[2]);
                        Node node = new Node(id, location);
                        JButton send = new JButton("Send");
                        send.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                graphAlgo.getGraph().addNode(node);
                            }
                        });
                        panelID.add(labelID);
                        panelPos.add(labelPos);
                        panelID.add(tfID);
                        panelPos.add(tfPos);
                        frame.add(send);
                        frame.add(panelPos);
                        frame.add(panelID);

                    }
                });

            }
        });

        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new Menu();
            ex.setVisible(true);
        });
    }
}

