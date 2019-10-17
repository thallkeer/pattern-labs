package main.lab4.mvc;

import org.jfree.data.xy.XYBarDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Model {
    XYBarDataset dataset;

    Model(){
        dataset = createDataset();
    }

    public XYBarDataset createDataset() {
        XYSeries series = new XYSeries("series1");

        double x = 0;
        for (int i = 0; i < 10; i++) {
            series.add(x, calculateY(x));
            x += 0.5;
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return new XYBarDataset(dataset, 5);
    }

    public XYBarDataset getDataset(){
        return dataset;
    }

    public void addSeries(double x) {
        XYSeriesCollection collection = (XYSeriesCollection) dataset.getUnderlyingDataset();
        collection.getSeries("series1").addOrUpdate(x, calculateY(x));
    }

    public double calculateY(double x) {
        return 2*x;
    }
}
