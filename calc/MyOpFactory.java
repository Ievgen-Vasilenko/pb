package calc;

import calc.interfaces.Operation;
import calc.interfaces.OperationFactory;
import calc.operations.OpDiv;
import calc.operations.OpMinus;
import calc.operations.OpMul;
import calc.operations.OpPlus;

import java.util.Scanner;

public class MyOpFactory implements OperationFactory {
    private final static String MESSAGE_NOT_FOUND_OPERATION = " - ошибка, такой операции не существует!";
    private final static String MESSAGE_REENTRY = "Введите операцию повторно: ";
    @Override
    public Operation getOpInstance(String op) {
        try {
            switch (op) {
                case "+":
                    return new OpPlus();
                case "-":
                    return new OpMinus();
                case "*":
                    return new OpMul();
                case "/":
                    return new OpDiv();
                default:
                    throw new OperationNotFoundException(op + MESSAGE_NOT_FOUND_OPERATION);
            }
        } catch (OperationNotFoundException e) {
            System.out.println(e);
            System.out.print(MESSAGE_REENTRY);
            Scanner scan = new Scanner(System.in);
            String op2 = scan.nextLine();
            return getOpInstance(op2);
        }
    }
}
