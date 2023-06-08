/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author ❣🚀❣ Vitor de Jesus ❣🚀❣
 */

public class Calculadora extends JFrame {

    private JTextField display;

    public Calculadora() {
        super("Calculadora");

        display = new JTextField(10);
        display.setEditable(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String button : buttons) {
            JButton btn = new JButton(button);
            btn.addActionListener(new ButtonClickListener());
            buttonPanel.add(btn);
        }

        getContentPane().add(display, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String buttonText = source.getText();

            if (buttonText.equals("=")) {
                // Avaliar a expressão matemática
                String expression = display.getText();
                double result = evaluateExpression(expression);
                display.setText(Double.toString(result));
                System.out.println(result);
            } else {
                // Atualizar o campo de texto
                String currentText = display.getText();
                display.setText(currentText + buttonText);
            }
        }

        private double evaluateExpression(String expression) {          
            double result = 0;
            try {
                ScriptEngineManager manager = new ScriptEngineManager();
                ScriptEngine engine = manager.getEngineByName("javascript");
                result = (double) engine.eval(expression);
            } catch (ScriptException ex) {
                JOptionPane.showMessageDialog(null, "Erro na expressão", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculadora();
            }
        });
    }
}

