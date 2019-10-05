package main.lab3.templateMethod;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class MainPanel extends JPanel {
    private ControlPanel controlPanel = new ControlPanel();
    private DrawCanvas canvas = new DrawCanvas();
    private int width = 600;
    private int height = 600;
    private Vector<BouncingShape> shapeList = new Vector<>(10);

    public MainPanel() {
        controlPanel.setBackground(Color.GRAY);

        controlPanel.btnStart.addActionListener(actionEvent -> {
            Class cls = controlPanel.getSelectedShape().getClass();
            BouncingShape shape = null;
            try {
                shape = (BouncingShape) cls.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            shapeList.add(shape);
        });
        this.setLayout(new BorderLayout());
        this.add(canvas, BorderLayout.CENTER);
        this.add(controlPanel, BorderLayout.PAGE_END);

        start();
    }

    private void start() {
        Thread t = new Thread(() -> {
            while (true) {
                update();
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    private void update() {
        for (BouncingShape shape : shapeList)
            shape.move();
    }

    class DrawCanvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (BouncingShape shape : shapeList)
                shape.draw(g);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(width, height);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame gui = new JFrame();
            gui.setResizable(false);
            gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            gui.setSize(600, 600);
            gui.setContentPane(new MainPanel());
            gui.setLocationRelativeTo(null);
            //gui.pack();
            gui.setVisible(true);
        });
    }
}


