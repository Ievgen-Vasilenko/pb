package IOtask;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class ReadWriteReflection {

    private FileWriter fileWriter;
    private FileReader fileReader;
    private String inputFile;
    private String outputFile;

    private Logger logger = Logger.getLogger(ReadWriteReflection.class.getName());

    ReadWriteReflection(String inputFile, String outputFile) {
        logger.log(Level.INFO, "Start...");
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    void go() {

        try {
            fileReader = new FileReader(inputFile);
            fileWriter = new FileWriter(outputFile, true);
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                logger.log(Level.INFO, "Start scan new line.");
                String str = scanner.nextLine();
                String delimeter = " ";
                String[] subStr = str.split(delimeter);
                String className = subStr[0];
                String methodName = subStr[1];
                logger.log(Level.INFO, "Stop scan new line. LINE - " + str);
                String[] params = new String[subStr.length - 2];
                System.arraycopy(subStr, 2, params, 0, subStr.length - 2);
                try {
                    Class c = Class.forName(className);
                    Method[] methods = c.getDeclaredMethods();
                    for (Method method : methods) {
                        Class[] paramTypes = method.getParameterTypes();
                        Object object = c.newInstance();
                        method = c.getDeclaredMethod(methodName, paramTypes);
                        method.setAccessible(true);
                        logger.log(Level.INFO, "Class name - " + c.getName() + ", method name - " + method.getName() + ", params - " + Arrays.toString(params));
                        String result = String.valueOf(method.invoke(object, (Object[]) params));
                        fileWriter.write(result);
                        logger.log(Level.INFO, "Result - " + result);
                    }

                } catch (ClassNotFoundException e) {
                    logger.log(Level.WARNING, str + " - CLASS NOT FOUND! " + e);
                } catch (NoSuchMethodException e) {
                    logger.log(Level.WARNING, str + " - METHOD NOT FOUND! " + e);
                } catch (IllegalArgumentException e) {
                    logger.log(Level.WARNING, str + " - WRONG NUMBER OF ARGUMENTS! " + e);
                } catch (InstantiationException e) {
                    logger.log(Level.WARNING, str + " - OBJECT NOT CREATED! " + e);
                } catch (IllegalAccessException e) {
                    logger.log(Level.WARNING, str + " - ILLEGAL METHOD ACCESS! " + e);
                } catch (InvocationTargetException e) {
                    logger.log(Level.WARNING, str + " - INVOKE PROBLEM! " + e);
                }
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "FILE NOT FOUND! " + e);
        } finally {
            try {
                assert fileReader != null;
                fileReader.close();
                logger.log(Level.INFO, "FILE READER IS CLOSED!");
                fileWriter.close();
                logger.log(Level.INFO, "FILE WRITER IS CLOSED!");
            } catch (IOException e) {
                logger.log(Level.WARNING, "FILE READER/WRITER NOT FOUND! " + e);
            }
        }
    }

}
