package main.lab4.mvc;

import main.lab4.mvc.interfaces.IModel;
import main.lab4.mvc.interfaces.IView;

public class Controller {
    private IModel model;
    private IView view;

    public Controller(IModel model, IView view) {
        this.model = model;
        this.view = view;
    }

    public void initController() {
        view.getTextPanel().addBtn.addActionListener(e -> onValueAdd());
        view.getTextPanel().deleteBtn.addActionListener(e -> onValueDelete());
        view.populateWithSeries(model.getSeries());
        view.drawChart(model.getDataset());
    }

    private void onValueDelete() {
        int selectedRow = view.removeSelectedRow();
        if (selectedRow != -1) {
            model.deleteDataItem(selectedRow);
            view.drawChart(model.getDataset());
        }
    }

    private void onValueAdd(){
        View.TextPanel textPanel = view.getTextPanel();
        String xVal = textPanel.textField.getValue().toString();
        textPanel.textField.setValue(0);
        double x = Double.parseDouble(xVal);
        model.addDataItem(x);
        view.addRow(x, model.calculateY(x));
        view.drawChart(model.getDataset());
    }
}
