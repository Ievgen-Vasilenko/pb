package calc;

import calc.interfaces.InputData;
import calc.interfaces.Operation;
import calc.interfaces.OperationFactory;

import java.io.*;
import java.text.MessageFormat;
import java.util.*;

class Calculator implements InputData {
    private final static String MESSAGE_RUN = "Калькулятор запущен!";
    private final static String MESSAGE_SECOND = "Введите аргументы и операцию: ";
    private final static String MESSAGE_RESULT = "Результат: ";
    private final static String MESSAGE_ERROR = "Программа завершена!";
    private final static String SELECT = "Выберите тип ввода данных:\n1. Консольный вариант.\n2. Файл, чтение/запись.\n3. Выход.\n";
    private final static String FILE_NOT_FOUND = "Файл не найден!";
    private final static String INPUT_OP_ERROR = "Недопустимая операция!";

    private MessageFormat form;
    private FileInputStream fis;
    private Properties property = new Properties();
    private FileReader fileReader;
    private FileWriter fileWriter;


    Calculator() {
        System.out.println(MESSAGE_RUN);
    }

    private static Map<String, Operation> operations = new HashMap<>();
    private Scanner scan = new Scanner(System.in);
    private OperationFactory myOpFactory = new MyOpFactory();

    {
        try {
            fis = new FileInputStream("/Users/wasel/IdeaProjects/untitled/src/calc/property.properties");
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        }
        try {
            property.load(fis);
        } catch (IOException e) {
            System.out.println(FILE_NOT_FOUND);
        }
        form = new MessageFormat(property.getProperty("message_format"));
    }

    private double exec(double a, double b, Operation operation) {
        return operation.exec(a, b);
    }

    @Override
    public void dataIn() {
        System.out.println(SELECT);
        String in = scan.nextLine();
        switch (Integer.parseInt(in)) {
            case 1:
                do {
                    try {
                        System.out.print(MESSAGE_SECOND);
                        in = scan.nextLine();
                        System.out.println(tokenizer(in));
                    } catch (NumberFormatException e) {
                        System.out.println(MESSAGE_ERROR);
                        System.exit(1);
                    }
                } while (true);
            case 2:
                try {
                    fileReader = new FileReader("/Users/wasel/IdeaProjects/untitled/src/calc/inputTask.txt");
                    fileWriter = new FileWriter("/Users/wasel/IdeaProjects/untitled/src/calc/outputResult.txt", true);
                    Scanner scanner = new Scanner(fileReader);
                    while (scanner.hasNextLine()) {
                        fileWriter.write(tokenizer(scanner.nextLine()));
                    }
                } catch (IOException e) {
                    System.out.println(FILE_NOT_FOUND);
                } finally {
                    try {
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        fileReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            case 3:
                System.exit(1);
            default:
                System.out.println(INPUT_OP_ERROR);
                dataIn();
        }
    }

    @Override
    public String dataOut(String result) {
        return MESSAGE_RESULT + result;
    }

    static Map<String, Operation> getOperations() {
        return operations;
    }

    static void setOperations(String op, Operation operation) {
        operations.put(op, operation);
    }

    private String tokenizer(String in) {
        StringTokenizer st = new StringTokenizer(in, " ");
        double a = Double.parseDouble(st.nextToken());
        String op = st.nextToken();
        double b = Double.parseDouble(st.nextToken());
        Object[] testArgs = {a, op, b, exec(a, b, myOpFactory.getOpInstance(op))};
        return dataOut(form.format(testArgs));
    }
}
