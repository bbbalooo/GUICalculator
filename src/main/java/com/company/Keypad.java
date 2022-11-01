package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.function.BiConsumer;

import static com.company.Key.*;


class Keypad extends JPanel {

    private final Calculator calculator;

    Keypad(Calculator calculator) {
        this.calculator = calculator;
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new GridLayout(keyMap.length, keyMap.length));

        Arrays.stream(keyMap)
                .flatMap(Arrays::stream)
                .map(KeyButton::new)
                .forEach(this::add);
    }

    private final Key[][] keyMap = {
            {MPLUS, MMINUS, PERCENT, CLEAR    },
            {SEVEN, EIGHT,  NINE,    DIVIDE   },
            {FOUR,  FIVE,   SIX,     MULTIPLY },
            {ONE,   TWO,    THREE,   MINUS    },
            {ZERO,  DOT,    EQUAL,   PLUS     }
    };

    private class KeyButton extends JButton implements Command {

        private BiConsumer<Calculator, String> consumer;

        KeyButton(Key key) {
            super(key.toString());
            this.addActionListener(calculator);
            this.consumer = key.getAction();
            setFont(new Font("Consolas", Font.BOLD, 30));
            setFocusable(false);
        }

        @Override
        public void execute() {
            consumer.accept(calculator, getText());
        }
    }
}
