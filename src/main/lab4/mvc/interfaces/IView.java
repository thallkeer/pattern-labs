package main.lab4.mvc.interfaces;

import main.lab4.mvc.View;
import org.jfree.data.xy.XYBarDataset;
import org.jfree.data.xy.XYSeries;

public interface IView {
    void populateWithSeries(XYSeries series);
    void addRow(double x, double y);
    void drawChart(XYBarDataset dataset);
    int removeSelectedRow();
    View.TextPanel getTextPanel();
}
