import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


class Menu extends JMenuBar {
    private GraphAlgo graphAlgo;
    private JMenuBar menuBar;

    public Menu(Graph g) {
        this.graphAlgo = new GraphAlgo(g);

        initUI();
    }

    public Menu() {
        initUI();
    }

    private void initUI() {
        createMenuBar();
        setVisible(true);
        setSize(500, 500);

    }

    private void createMenuBar() {

        menuBar = new JMenuBar();
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
                JFileChooser fileChooser = new JFileChooser();
                int r = fileChooser.showOpenDialog(buttonLoad);
                if (r == JFileChooser.APPROVE_OPTION) {
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    Ex2.runGUI(file.getAbsolutePath());
                }
            }
        });

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int r = fileChooser.showSaveDialog(null);
                if (r == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                        graphAlgo.save(file.getAbsolutePath());
                }
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
                JTextField tfID = new JTextField(10);
                JTextField tfPos = new JTextField(10);
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
                        GUI gui = new GUI(graphAlgo);
                        gui.run();
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
                        graphAlgo.getGraph().removeNode(id);
                        GUI gui = new GUI(graphAlgo);
                        gui.run();
                    }
                });
                panelID.add(labelID);
                panelID.add(tfID);
                panelID.add(send);
                frame1.add(panelID);
            });

            addEdge.addActionListener(e13 -> {
                JPanel panel2 = new JPanel(new GridLayout());
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
                JTextField tfSource = new JTextField(5);
                JTextField tfDest = new JTextField(5);
                JTextField tfWeight = new JTextField(5);
                JButton send = new JButton("Send");
                send.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int source = Integer.parseInt(tfSource.getText());
                        int dest = Integer.parseInt(tfDest.getText());
                        double weight = Double.parseDouble(tfWeight.getText());
                        graphAlgo.getGraph().connect(source, dest, weight);
                        GUI gui = new GUI(graphAlgo);
                        gui.run();
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
                        graphAlgo.getGraph().removeEdge(source, dest);
                        GUI gui = new GUI(graphAlgo);
                        gui.run();
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
                panel2.setSize(300, 300);
                frame.setSize(500, 500);
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
                        new DisplayMessage("" + is);
                    }
                });
                shortestPathDist.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame frame2 = new JFrame();
                        frame2.setSize(400, 400);
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
                                double path = graphAlgo.shortestPathDist(source, dest);
                                new DisplayMessage("" + path);
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
                        JFrame frame2 = new JFrame();
                        frame2.setSize(400, 400);
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
                                List<NodeData> list = graphAlgo.shortestPath(source, dest);
                                int[] keys = new int[list.size()];
                                for (int i = 0; i < keys.length; i++) {
                                    keys[i] = list.get(i).getKey();
                                }
                                String s = "";
                                for (int i = 0; i < keys.length; i++) {
                                    s += keys[i] + "->";
                                }
                                new DisplayMessage(s);
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
                        new DisplayMessage("" + n.getKey());
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
                        JTextField tf = new JTextField(20);
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
                                List<NodeData> list = graphAlgo.tsp(cities);
                                for (int i = 0; i < list.size(); i++) {
                                    System.out.print(list.get(i).getKey());
                                }
                                System.out.println();
                                int[] keys = new int[list.size()];
                                for (int i = 0; i < keys.length; i++) {
                                    keys[i] = list.get(i).getKey();
                                    System.out.print(keys[i] + " ");
                                }
                                String s1 = "";
                                for (int i = 0; i < keys.length; i++) {
                                    s1 += keys[i] + "->";
                                }
                                new DisplayMessage(s1);
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
    }

    public JMenuBar getMenuBar() {
        return this.menuBar;
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            Menu ex = new Menu();
            ex.setVisible(true);
        });
    }
}

