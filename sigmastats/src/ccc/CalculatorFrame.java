package ccc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.formdev.flatlaf.intellijthemes.*;


class CalculatorFrame extends JFrame {
    Font header = new Font("Futura-Bold", Font.PLAIN, 80);
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    JLabel label;
    JTextField field;
    JButton button;
    JLabel result, error;
    JPanel resultPanel;

    static JTextField lcount, lsum, lmean, lmedian, lmode, lmin, lmax, lgeo, lvar, lstd, lsampleVar, lsampleStd;
    static JTextField rcount, rsum, rmean, rmedian, rmode, rmin, rmax, rgeo, rvar, rstd, rsampleVar, rsampleStd;

    public CalculatorFrame() {
        super("SigmaStats");
        setResizable(false);
        setBounds(1000, 300, 500, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5,5,5,5);
        label = new JLabel("SigmaStats");
        label.setFont(header);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(label, gbc);

        label = new JLabel("Enter sample data:");
        gbc.gridy = 1;
        panel.add(label, gbc);

        field = new JTextField(50);
        field.setToolTipText("Enter numbers separated by space or comma.");
        gbc.gridy = 2;
        panel.add(field, gbc);

        JButton buttonStatTests = new JButton("Statistical Tests");
        buttonStatTests.setToolTipText("Press this button to view the the possible statistical tests.");
        gbc.gridy = 3;
        panel.add(buttonStatTests, gbc);

        JButton buttonSortOrder = new JButton("Sort Sample Order");
        buttonSortOrder.setToolTipText("Press this button to sort the sample order.");
        gbc.gridy = 4;
        panel.add(buttonSortOrder, gbc);

        error = new JLabel("");
        gbc.gridy = 5;
        panel.add(error, gbc);

        result = new JLabel("Statistical Results");
        result.setFont(new Font("Futura-Bold", Font.BOLD, 22));
        result.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(result, gbc);

        resultPanel = new JPanel(new GridBagLayout());
        GridBagConstraints rpc = new GridBagConstraints();
        rpc.fill = GridBagConstraints.HORIZONTAL;

        String[] labels = {
            "Count", "Sum", "Mean", "Median", "Mode",
            "Min", "Max", "Geometric Mean", "Variance",
            "Standard Deviation", "Sample Variance", "Sample Std Dev"
        };

        JTextField[] lfields = new JTextField[labels.length];
        JTextField[] rfields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            lfields[i] = new JTextField(labels[i]);
            rfields[i] = new JTextField();
            lfields[i].setEditable(false);
            rfields[i].setEditable(false);

            rpc.weightx = 1.0;
            rpc.weighty = 1.0;
            rpc.fill = GridBagConstraints.BOTH;

            rpc.gridx = 0;
            rpc.gridy = i;
            resultPanel.add(lfields[i], rpc);

            rpc.gridx = 1;
            resultPanel.add(rfields[i], rpc);
        }

        lcount = lfields[0]; rcount = rfields[0];
        lsum = lfields[1]; rsum = rfields[1];
        lmean = lfields[2]; rmean = rfields[2];
        lmedian = lfields[3]; rmedian = rfields[3];
        lmode = lfields[4]; rmode = rfields[4];
        lmin = lfields[5]; rmin = rfields[5];
        lmax = lfields[6]; rmax = rfields[6];
        lgeo = lfields[7]; rgeo = rfields[7];
        lvar = lfields[8]; rvar = rfields[8];
        lstd = lfields[9]; rstd = rfields[9];
        lsampleVar = lfields[10]; rsampleVar = rfields[10];
        lsampleStd = lfields[11]; rsampleStd = rfields[11];

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 6;
        gbc.anchor = GridBagConstraints.PAGE_END;
        panel.add(resultPanel, gbc);

        add(panel);
        field.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
    private void updateStats() {
        String input = field.getText();
        try {
            double[] numbers = Algorithm.parseInput(input);
            int count = Algorithm.count(numbers);
            double sum = Algorithm.sum(numbers);
            double mean = Algorithm.mean(numbers);
            double median = Algorithm.median(numbers);
            double mode = Algorithm.mode(numbers);
            double min = Algorithm.min(numbers);
            double max = Algorithm.max(numbers);
            double geo = Algorithm.geometricMean(numbers);
            double var = Algorithm.variance(numbers);
            double std = Algorithm.stdDev(numbers);
            double svar = Algorithm.sampleVariance(numbers);
            double sstd = Algorithm.sampleStdDev(numbers);

            rcount.setText(Integer.toString(count));
            rsum.setText(String.format("%.2f", sum));
            rmean.setText(String.format("%.2f", mean));
            rmedian.setText(String.format("%.2f", median));
            rmode.setText(String.format("%.2f", mode));
            rmin.setText(String.format("%.2f", min));
            rmax.setText(String.format("%.2f", max));
            rgeo.setText(Double.isNaN(geo) ? "N/A" : String.format("%.2f", geo));
            rvar.setText(String.format("%.2f", var));
            rstd.setText(String.format("%.2f", std));
            rsampleVar.setText(String.format("%.2f", svar));
            rsampleStd.setText(String.format("%.2f", sstd));
            error.setText("");
        } catch (Exception e) {
            rcount.setText("");
            rsum.setText("");
            rmean.setText("");
            rmedian.setText("");
            rmode.setText("");
            rmin.setText("");
            rmax.setText("");
            rgeo.setText("");
            rvar.setText("");
            rstd.setText("");
            rsampleVar.setText("");
            rsampleStd.setText("");
            error.setText("<html><font color=red>Invalid input. Please enter numbers separated by space or comma.</font></html>");
        }
    }
    @Override
    public void insertUpdate(javax.swing.event.DocumentEvent e) { updateStats(); }
    @Override
    public void removeUpdate(javax.swing.event.DocumentEvent e) { updateStats(); }
    @Override
    public void changedUpdate(javax.swing.event.DocumentEvent e) { updateStats(); }
});
    
        buttonStatTests.addActionListener(e -> {
    try {
        double[] numbers = Algorithm.parseInput(field.getText());
        double mean = Algorithm.mean(numbers);
        double stdDev = Algorithm.sampleStdDev(numbers);
        int count = numbers.length;
        StatisticalTestsFrame testsFrame = new StatisticalTestsFrame(mean, stdDev, count);
        testsFrame.setVisible(true);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Please enter valid data before running statistical tests.", "Input Error", JOptionPane.ERROR_MESSAGE);
    }
    
    });

        buttonSortOrder.addActionListener(e -> {
            try {
                double[] numbers = Algorithm.parseInput(field.getText());
                Arrays.sort(numbers);
                field.setText(Arrays.toString(numbers).replaceAll("[\\[\\]]", ""));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid data before sorting.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

    }
}
