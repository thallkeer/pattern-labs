package main.lab3.templateMethod;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ControlPanel extends JPanel {
    public JButton btnStart = new JButton("Start");;
    public JButton btnClose = new JButton("Close");
    private JComboBox<BouncingShape> cbChooseShape;

    public ControlPanel(){
        cbChooseShape= new JComboBox();

        Vector<BouncingShape> items = new Vector<>();
        items.add(new Ball());
        items.add(new Square());
        items.add(new Star());

        cbChooseShape.setModel(new DefaultComboBoxModel<BouncingShape>(items));
        cbChooseShape.setSelectedIndex(0);

        this.add(btnStart);
        this.add(btnClose);
        this.add(cbChooseShape);
    }

    public BouncingShape getSelectedShape(){
        return (BouncingShape)cbChooseShape.getSelectedItem();
    }
}
