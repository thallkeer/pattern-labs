package main.lab3.observer;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Gui implements IObservable {
        private JFrame form;
        private List<IObserver> listeners;

        public Gui() {
            listeners = new ArrayList<>();
            form = new JFrame();

            form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(null);
            form.getContentPane().add(panel);

            EyeEllipse leftEye = new EyeEllipse(100,100,75,75,ControlRole.LEFT_EYE);
            EyeEllipse rightEye = new EyeEllipse(400,100,75,75,ControlRole.RIGHT_EYE);

            leftEye.subscribe(this);
            rightEye.subscribe(this);

            leftEye.addClickListener(this);
            rightEye.addClickListener(this);

            panel.add(leftEye);
            panel.add(rightEye);

            NoseTriangle nose = new NoseTriangle(255,250,50,50);
            nose.subscribe(this);
            nose.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    Gui.this.notify(nose.getControlRole());
                }
            });
            panel.add(nose);

            form.pack();
            form.setSize(600, 600);//400 width and 500 height
            panel.setSize(600, 600);
            form.setResizable(false);
            form.setLayout(null);//using no layout managers

            form.setLocationRelativeTo(null);
            form.validate();
            form.setVisible(true);//making the frame visible
        }

        public static void main(String[] args) {
            new Gui();
        }

    @Override
    public void addObserver(IObserver observer) {
        if (observer != null)
            this.listeners.add(observer);
    }

    @Override
    public void notify(ControlRole role) {
        this.listeners.forEach(observer -> observer.update(role));

    }
}


