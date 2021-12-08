import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


class Menu extends JFrame {
    private Graph g = new Graph();
    private  GraphAlgo graphAlgo = new GraphAlgo(g);

    public Menu(Graph g){
        this.graphAlgo = new GraphAlgo(g);
        initUI();
    }

    public Menu() {
        initUI();
    }

    private void initUI() {

        createMenuBar();

        setTitle("Simple menu");
        setVisible(true);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createMenuBar() {

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Actions Menu");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem eMenuItem = new JMenuItem("Exit");
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
                JTextField tf = new JTextField(30);
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
                JTextField tf = new JTextField(30);
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
        buttonEdit.addActionListener(e -> {
            JFrame frame = new JFrame();
            JPanel panel = new JPanel(new GridLayout());
            frame.setSize(300, 300);
            frame.setVisible(true);
            JButton addNode = new JButton("ADD NODE");
            JButton removeNode = new JButton("REMOVE NODE");
            JButton addEdge = new JButton("ADD EDGE");
            JButton removeEdge = new JButton("REMOVE EDGE");
            panel.add(addNode);
            panel.add(removeNode);
            panel.add(addEdge);
            panel.add(removeEdge);
            frame.add(panel);

            addNode.addActionListener(e12 -> {
                JPanel panel2 = new JPanel(new GridLayout());
                JFrame frame1 = new JFrame();
                frame1.setSize(400, 400);
                frame1.setVisible(true);
                JPanel panelID = new JPanel();
                panelID.setSize(50, 50);
                panelID.setVisible(true);
                JPanel panelPos = new JPanel();
                panelPos.setSize(30, 50);
                panelPos.setVisible(true);
                JLabel labelID = new JLabel("Enter ID");
                JLabel labelPos = new JLabel("Enter Pos");
                JTextField tfID = new JTextField(20);
                JTextField tfPos = new JTextField(20);
                JButton send = new JButton("Send");
                send.setSize(30, 30);
                send.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int id = Integer.parseInt(tfID.getText());
                        String posText = tfPos.getText();
                        String[] a = posText.split(",", 3);
                        double[] posVal = new double[3];
                        for (int i = 0; i < a.length; i++) {
                            posVal[i] = Double.parseDouble(a[i]);
                        }
                        Location location = new Location(posVal[0], posVal[1], posVal[2]);
                        Node node = new Node(id, location);
                        graphAlgo.getGraph().addNode(node);
                    }
                });
                panelID.add(labelID);
                panelPos.add(labelPos);
                panelID.add(tfID);
                panelPos.add(tfPos);
                panel2.add(panelPos);
                panel2.add(panelID);
                panel2.add(send);
                frame1.add(panel2);


            });
            removeNode.addActionListener(e13 -> {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(new Dimension(300, 300));
                JFrame frame1 = new JFrame();
                frame1.setSize(400, 400);
                frame1.setVisible(true);
                JPanel panelID = new JPanel();
                panelID.setSize(50, 50);
                panelID.setVisible(true);
                JLabel labelID = new JLabel("Enter ID");
                JTextField tfID = new JTextField(20);
                JButton send = new JButton("Send");
                send.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int id = Integer.parseInt(tfID.getText());
                        NodeData n =  graphAlgo.getGraph().removeNode(id);
                        System.out.println(n.getKey());
                    }
                });
                panelID.add(labelID);
                panelID.add(tfID);
                panelID.add(send);
                frame1.add(panelID);
            });

            addEdge.addActionListener(e13 -> {
                JPanel panel2 = new JPanel(new GridLayout());
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(new Dimension(300, 300));
                JFrame frame1 = new JFrame();
                frame1.setSize(400, 400);
                frame1.setVisible(true);
                JPanel panelSource = new JPanel();
                panelSource.setSize(50, 50);
                panelSource.setVisible(true);
                JPanel panelDest = new JPanel();
                panelDest.setSize(50, 50);
                panelDest.setVisible(true);
                JPanel panelWeight = new JPanel();
                panelWeight.setSize(50, 50);
                panelWeight.setVisible(true);
                JLabel labelSource = new JLabel("Enter source");
                JLabel labelDest = new JLabel("Enter dest");
                JLabel labelWeight = new JLabel("Enter weight");
                JTextField tfSource = new JTextField(10);
                JTextField tfDest = new JTextField(10);
                JTextField tfWeight = new JTextField(10);
                JButton send = new JButton("Send");
                send.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int source = Integer.parseInt(tfSource.getText());
                        int dest = Integer.parseInt(tfDest.getText());
                        double weight = Double.parseDouble(tfWeight.getText());
                        graphAlgo.getGraph().connect(source,dest,weight);
                    }
                });
                panelSource.add(labelSource);
                panelSource.add(tfSource);
                panelDest.add(labelDest);
                panelDest.add(tfDest);
                panelWeight.add(labelWeight);
                panelWeight.add(tfWeight);
                panel2.add(panelSource);
                panel2.add(panelDest);
                panel2.add(panelWeight);
                panel2.add(send);
                frame1.add(panel2);
            });

            removeEdge.addActionListener(e13 -> {
                JPanel panel2 = new JPanel(new GridLayout());
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(new Dimension(300, 300));
                JFrame frame1 = new JFrame();
                frame1.setSize(400, 400);
                frame1.setVisible(true);
                JPanel panelSource = new JPanel();
                panelSource.setSize(50, 50);
                panelSource.setVisible(true);
                JPanel panelDest = new JPanel();
                panelDest.setSize(50, 50);
                panelDest.setVisible(true);
                JLabel labelSource = new JLabel("Enter source");
                JLabel labelDest = new JLabel("Enter dest");
                JTextField tfSource = new JTextField(10);
                JTextField tfDest = new JTextField(10);
                JButton send = new JButton("Send");
                send.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int source = Integer.parseInt(tfSource.getText());
                        int dest = Integer.parseInt(tfDest.getText());
                        EdgeData edgeData = graphAlgo.getGraph().removeEdge(source,dest);
                        System.out.println(edgeData);
                    }
                });
                panelSource.add(labelSource);
                panelSource.add(tfSource);
                panelDest.add(labelDest);
                panelDest.add(tfDest);
                panel2.add(panelSource);
                panel2.add(panelDest);
                panel2.add(send);
                frame1.add(panel2);
            });
        });

        buttonRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel2 = new JPanel(new GridLayout());
                JFrame frame = new JFrame();
                panel2.setSize(100, 100);
                frame.setSize(300, 300);
                panel2.setVisible(true);
                frame.setVisible(true);
                JButton isConnected = new JButton("isConnected");
                JButton shortestPathDist = new JButton("shortestPathDist");
                JButton shortestPath = new JButton("shortestPath");
                JButton center = new JButton("center");
                JButton tsp = new JButton("tsp");

                isConnected.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       boolean is = graphAlgo.isConnected();
                        System.out.println(is);
                    }
                });
                shortestPathDist.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame frame2= new JFrame();
                        frame2.setSize(400,400);
                        frame2.setVisible(true);
                        JPanel panel2 = new JPanel(new GridLayout());
                        JPanel panelSource = new JPanel();
                        panelSource.setSize(50, 50);
                        panelSource.setVisible(true);
                        JPanel panelDest = new JPanel();
                        panelDest.setSize(50, 50);
                        panelDest.setVisible(true);
                        JLabel labelSource = new JLabel("Enter source");
                        JLabel labelDest = new JLabel("Enter dest");
                        JTextField tfSource = new JTextField(10);
                        JTextField tfDest = new JTextField(10);
                        JButton send = new JButton("send");
                        send.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int source = Integer.parseInt(tfSource.getText());
                                int dest = Integer.parseInt(tfDest.getText());
                                graphAlgo.shortestPathDist(source,dest);
                            }
                        });
                        panelSource.add(labelSource);
                        panelSource.add(tfSource);
                        panelDest.add(tfDest);
                        panelDest.add(labelDest);
                        panel2.add(panelSource);
                        panel2.add(panelDest);
                        panel2.add(send);
                        frame2.add(panel2);

                    }
                });
                shortestPath.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame frame2= new JFrame();
                        frame2.setSize(400,400);
                        frame2.setVisible(true);
                        JPanel panel2 = new JPanel(new GridLayout());
                        JPanel panelSource = new JPanel();
                        panelSource.setSize(50, 50);
                        panelSource.setVisible(true);
                        JPanel panelDest = new JPanel();
                        panelDest.setSize(50, 50);
                        panelDest.setVisible(true);
                        JLabel labelSource = new JLabel("Enter source");
                        JLabel labelDest = new JLabel("Enter dest");
                        JTextField tfSource = new JTextField(10);
                        JTextField tfDest = new JTextField(10);
                        JButton send = new JButton("send");
                        send.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int source = Integer.parseInt(tfSource.getText());
                                int dest = Integer.parseInt(tfDest.getText());
                                graphAlgo.shortestPath(source,dest);
                            }
                        });
                        panelSource.add(labelSource);
                        panelSource.add(tfSource);
                        panelDest.add(tfDest);
                        panelDest.add(labelDest);
                        panel2.add(send);
                        panel2.add(panelSource);
                        panel2.add(panelDest);
                        frame2.add(panel2);
                    }
                });
                center.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       NodeData n = graphAlgo.center();
                        System.out.println(n.getKey());
                    }
                });
                tsp.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame frame = new JFrame();
                        JPanel panel = new JPanel();
                        panel.setSize(50, 50);
                        frame.setSize(300, 300);
                        panel.setVisible(true);
                        frame.setVisible(true);
                        JLabel label = new JLabel("Enter nodes id");
                        JTextField tf = new JTextField(30);
                        JButton send = new JButton("Send");
                        send.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                List<NodeData> cities = new ArrayList<>();
                                String s = tf.getText();
                                String[] ids = s.split(",");
                                for (int i = 0; i < ids.length; i++) {
                                    int id = Integer.parseInt(ids[i]);
                                    cities.add(graphAlgo.getGraph().getNode(id));
                                }
                                graphAlgo.tsp(cities);
                            }
                        });
                        panel.add(label);
                        panel.add(send);
                        panel.add(tf);
                        frame.add(panel);
                    }
                });
                panel2.add(isConnected);
                panel2.add(shortestPathDist);
                panel2.add(shortestPath);
                panel2.add(center);
                panel2.add(tsp);
                frame.add(panel2);
            }

        });


        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            Menu ex = new Menu();
            ex.setVisible(true);
        });
    }
}

