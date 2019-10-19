package main.lab4.mvc.interfaces;

import org.jfree.data.xy.XYBarDataset;
import org.jfree.data.xy.XYSeries;

public interface IModel {
    XYBarDataset getDataset();
    XYSeries getSeries();
    double calculateY(double x);
    void addDataItem(double x);
    void deleteDataItem(int index);
}
