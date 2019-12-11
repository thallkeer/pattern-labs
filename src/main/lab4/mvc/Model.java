package main.lab4.mvc;

import main.lab4.mvc.interfaces.IModel;
import org.jfree.data.xy.XYBarDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Model {
    private XYBarDataset dataset;
    private final String SERIES_NAME = "graph_series";

    Model() {
        dataset = createDataset();
    }

    private XYBarDataset createDataset() {
        XYSeries series = new XYSeries(SERIES_NAME);

        double x = -5;
        for (int i = 0; i < 15; i++) {
            series.add(x, calculateY(x));
            x += 0.5;
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return new XYBarDataset(dataset, 5);
    }

    public XYBarDataset getDataset() {
        return dataset;
    }

    public void addDataItem(double x) {
        getSeries().addOrUpdate(x, calculateY(x));
    }

    public void deleteDataItem(int index) {
        getSeries().remove(index);
    }

    public void updateDataItem(int index, double newValue){
        getSeries().updateByIndex(index,newValue);
    }

    public XYSeries getSeries() {
        XYSeriesCollection collection = (XYSeriesCollection) dataset.getUnderlyingDataset();
        return collection.getSeries(SERIES_NAME);
    }

    public double calculateY(double x) {
        return Math.pow(3-x,2) - 2;
    }
}
