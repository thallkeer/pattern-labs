package main.lab4.mvc;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYBarDataset;
import org.jfree.data.xy.XYSeries;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class View extends JFrame {
    private final int height = 800;
    private final int width = 1200;

    private JPanel mainPanel;
    private SeriesTable seriesTable;
    private TextPanel textPanel;
    private JFreeChart chart;
    private ChartPanel cp;
    private GridBagConstraints gbc;

    public View() {
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(width, height);
        mainPanel = new JPanel();

        mainPanel.setSize(width, height);
        GridBagLayout gbl = new GridBagLayout();
        mainPanel.setLayout(gbl);

        chart = ChartFactory.createXYLineChart("f(x) = (3-x)^2 - 2",
                "x", "y", null, PlotOrientation.VERTICAL, false, true,
                true);
        cp = new ChartPanel(chart);

        seriesTable = new SeriesTable();

        JScrollPane scrollPane = new JScrollPane(seriesTable);
        JPanel tablePanel = new JPanel(new BorderLayout());
        textPanel = new TextPanel();

        tablePanel.add(scrollPane, BorderLayout.NORTH);
        tablePanel.add(textPanel, BorderLayout.SOUTH);
        tablePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        gbc = new GridBagConstraints();

        AddPanel(cp, 0, 8, 0.8);
        AddPanel(tablePanel, 9, 2, 0.5);

        this.setContentPane(mainPanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void drawChart(XYBarDataset ds) {
        chart.getXYPlot().setDataset(ds);
        cp.repaint();
    }

    private void AddPanel(JPanel panel, int gridx, int gridwidth, double weightx) {
        gbc.gridx = gridx;
        gbc.gridy = 0;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = 1;
        gbc.weightx = weightx;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 10, 0, 0);
        mainPanel.add(panel, gbc);
    }

    public void populateWithSeries(XYSeries series) {
        for (int i = 0; i < series.getItemCount(); i++) {
            seriesTable.addRow(
                    series.getX(i).doubleValue(),
                    series.getY(i).doubleValue()
            );
        }
    }

    public void addRow(double x, double y) {
        seriesTable.addRow(x, y);
    }

    public int removeSelectedRow() {
        return seriesTable.removeSelectedRow();
    }

    public TextPanel getTextPanel() {
        return textPanel;
    }

    static class TextPanel extends JPanel {
        JFormattedTextField textField;
        JLabel textLabel;
        JButton addBtn;
        JButton deleteBtn;

        TextPanel() {
            this.setLayout(new BorderLayout());
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('.');

            NumberFormat format = new DecimalFormat("0.00", symbols);
            format.setMaximumFractionDigits(2);

            NumberFormatter formatter = new NumberFormatter(format);
            formatter.setMinimum(-10000.0);
            formatter.setMaximum(10000.0);
            formatter.setAllowsInvalid(false);

            textField = new JFormattedTextField(formatter);
            textField.setValue(0.0);
            textLabel = new JLabel("Input x value: ");
            addBtn = new JButton("Add");
            deleteBtn = new JButton("Delete");

            add(textLabel, BorderLayout.WEST);
            add(textField, BorderLayout.CENTER);
            add(addBtn, BorderLayout.EAST);
            add(deleteBtn, BorderLayout.PAGE_END);
        }
    }
}
