package javadz.DZ7.impl;

import javadz.DZ7.api.Calculator;

public class CalculatorImpl implements Calculator {

    @Override
    public double add(double a, double b) {
        System.out.println("CalculatorImpl add result: " + (a + b));
        return a + b;
    }
}
