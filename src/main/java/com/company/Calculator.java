package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class Calculator extends JFrame implements ActionListener {

    private final Expression expression = new Expression();
    private Display display;

    public Calculator() {
        super("Calculator");
        initComponents();
        setSize(300, 350);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        display = new Display();
        panel.add(display);

        panel.add(new Keypad(this));
        this.add(panel);
    }

    void pressedNumber(String number) {
        display.append(number);
    }

    void pressedClear(String clear) {
        expression.clear();
        display.clear();
    }

    void pressedOperator(String operator) {
        Operator.fromString(operator)
                .map(op->expression.calculate(op,display.getValue()))
                .ifPresent(display::setValue);
    }

    void pressedDot(String dot) {
        display.dotPressed();
    }

    void pressedEmpty(String empty){
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Command command = (Command) e.getSource();
        command.execute();
    }
}
