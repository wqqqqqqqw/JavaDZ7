package javadz.DZ7.app;

import javadz.DZ7.impl.CalculatorImpl;

public class MyApp extends App {

    CalculatorImpl calcImplLoader;

    CalculatorImpl get() {
        return calcImplLoader;
    }

    void set(CalculatorImpl calcImplLoader) {
        this.calcImplLoader = calcImplLoader;
    }
}
