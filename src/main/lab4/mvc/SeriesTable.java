package main.lab4.mvc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class SeriesTable extends JTable {
    DefaultTableModel tableModel;
    Vector<String> headers;

    public SeriesTable(){
        headers = new Vector<>();
        headers.add("X");
        headers.add("Y");

        tableModel = new DefaultTableModel(headers,0);

        setModel(tableModel);
        setFillsViewportHeight(true);
    }

    public void addRow(double x, double y) {
        tableModel.addRow(new Object[]{x, y});
    }

    public int removeSelectedRow() {
        int rowToDelete = this.getSelectedRow();
        if (rowToDelete != -1)
            tableModel.removeRow(rowToDelete);
        return rowToDelete;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column != 1;
    }
}
