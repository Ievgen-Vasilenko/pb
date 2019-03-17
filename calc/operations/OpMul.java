package calc.operations;

import calc.interfaces.Operation;

public class OpMul implements Operation {
    @Override
    public double exec(double a, double b) {
        return a * b;
    }
}
