package main.lab4.mvc;

import org.jfree.data.xy.XYBarDataset;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        initController();
    }

    private void initController() {
        view.getTextPanel().addBtn.addActionListener(e -> onValueAdd());
        XYBarDataset ds = model.getDataset();
        for (int i = 0; i < ds.getSeriesCount(); i++) {
            view.getSeriesTable().addRow(ds.getX(0,i).doubleValue(),ds.getY(0,i).doubleValue());
        }
        view.drawChart(ds);
    }

    private void onValueAdd(){
        View.TextPanel textPanel = view.getTextPanel();
        String xVal = textPanel.textField.getValue().toString();
        double x = Double.parseDouble(xVal);
        model.addSeries(x);
        view.getSeriesTable().addRow(x, model.calculateY(x));
        view.drawChart(model.getDataset());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        ActionEvent actionEvent = event;
    }
}
