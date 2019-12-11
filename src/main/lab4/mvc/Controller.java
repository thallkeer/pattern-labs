package main.lab4.mvc;

import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void initController() {
        view.getTextPanel().addBtn.addActionListener(e -> onValueAdd());
        view.getTextPanel().deleteBtn.addActionListener(e -> onValueDelete());
        DefaultTableModel tableModel = view.getTableModel();
        tableModel.addTableModelListener(e -> onValueUpdate(e, tableModel));
        view.populateWithSeries(model.getSeries());
        view.drawChart(model.getDataset());

    }

    private void onValueUpdate(TableModelEvent e, DefaultTableModel tableModel) {
        if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 0) {
            int index = e.getFirstRow();
            double val = Double.parseDouble(tableModel.getValueAt(index, 0).toString());
            model.updateDataItem(index, val);
            view.updateY(index, model.calculateY(val));
            view.drawChart(model.getDataset());
        }
    }

    private void onValueDelete() {
        int selectedRow = view.removeSelectedRow();
        if (selectedRow != -1) {
            model.deleteDataItem(selectedRow);
            view.drawChart(model.getDataset());
        }
    }

    private void onValueAdd() {
        View.TextPanel textPanel = view.getTextPanel();
        String xVal = textPanel.textField.getValue().toString();
        textPanel.textField.setValue(0);
        double x = Double.parseDouble(xVal);
        model.addDataItem(x);
        view.addRow(x, model.calculateY(x));
        view.drawChart(model.getDataset());
    }
}
