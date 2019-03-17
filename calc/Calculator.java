package calc;

import calc.interfaces.InputData;
import calc.interfaces.Operation;
import calc.interfaces.OperationFactory;

import java.util.Scanner;

class Calculator implements InputData {
    private final static String MESSAGE_RUN = "Калькулятор запущен!";
    private final static String MESSAGE_FIRST = "Введите первый аргумент: ";
    private final static String MESSAGE_SECOND = "Введите второй аргумент: ";
    private final static String MESSAGE_OPERATION = "Введите операцию: ";
    private final static String MESSAGE_RESULT = "Результат: ";
    private final static String MESSAGE_ERROR = "Пустая строка! Программа завершена!";

    Calculator() {
        System.out.println(MESSAGE_RUN);
    }

    private Scanner scan = new Scanner(System.in);
    private OperationFactory myOpFactory = new MyOpFactory();

    private double exec(double a, double b, Operation operation) {
        return operation.exec(a, b);
    }

    @Override
    public void dataIn() {
        do {
            try {
                System.out.print(MESSAGE_FIRST);
                double a = Double.parseDouble(scan.nextLine());
                System.out.print(MESSAGE_OPERATION);
                String op = scan.nextLine();
                System.out.print(MESSAGE_SECOND);
                double b = Double.parseDouble(scan.nextLine());
                dataOut(exec(a, b, myOpFactory.getOpInstance(op)));
            } catch (NumberFormatException e) {
                System.out.println(MESSAGE_ERROR);
                System.exit(1);
            }
        } while (true);
    }

    @Override
    public void dataOut(Double result) {
        System.out.println(MESSAGE_RESULT + result);
    }
}
