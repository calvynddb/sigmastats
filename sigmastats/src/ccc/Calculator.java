package ccc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.formdev.flatlaf.intellijthemes.*;


public class Calculator {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                String labelFont = "WorkSan-Regular";
                UIManager.setLookAndFeel(new FlatArcDarkOrangeIJTheme());
                UIManager.put("defaultFont", new Font(labelFont, Font.PLAIN, 14));
                UIManager.put("Button.arc", 15); // full-rounded buttons
                UIManager.put("Component.arc", 15); // rounded text fields
                UIManager.put("TextComponent.arc", 15);
                UIManager.put("Button.font", new Font(labelFont, Font.BOLD, 14));
                UIManager.put("TextField.font", new Font(labelFont, Font.PLAIN, 14));
                UIManager.put("Label.font", new Font(labelFont, Font.PLAIN, 13));
                UIManager.put("Label.foreground", new Color(200, 200, 255));

            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        });
        CalculatorFrame frame = new CalculatorFrame();
        frame.setVisible(true);
    }
}