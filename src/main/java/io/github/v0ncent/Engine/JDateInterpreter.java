package io.github.v0ncent.Engine;

import io.github.v0ncent.Constants;
import io.github.v0ncent.Exceptions.NoFunctionFound;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

public class JDateInterpreter {
    private static JDateInterpreter JDateInterpreter;

    private final HashMap<String, Method> methods;
    private BufferedReader reader;

    private JDateInterpreter() {
        this.methods = ScriptFunctions.getMethods();
    }

    public void interpret(File jdateScript) throws IOException, InvocationTargetException, IllegalAccessException, NoFunctionFound {
        System.gc();

        if (reader != null) {
            reader.close();
        }

        reader = new BufferedReader(new FileReader(jdateScript));

        String line;
        Function function;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith(Constants.ScriptKeyWords.COMMENT) || line.isEmpty()) continue;

            // CHANGE ME LATER
            line = line.trim();
            line = line.replaceAll(" ", "");

            final String[] parsedLine = line.split(Constants.ScriptKeyWords.DELIMINATOR);

            final String[] arguments = parsedLine[1].split(Constants.ScriptKeyWords.DELIMINATOR);

            function = new Function(parsedLine[0], arguments);

            // temp fix
            if (parsedLine[0].equals(Constants.ScriptKeyWords.END_OF_FILE_FUNCTION)) {
                executeLine(function);
                break;
            } else {
                executeLine(function);
            }
        }

        reader.close();
        System.gc();
    }

    private void executeLine(Function functionNameAndArgs) throws InvocationTargetException, IllegalAccessException, NoFunctionFound {
        Method method = methods.get(functionNameAndArgs.functionName);

        if (method == null) {
            throw new NoFunctionFound(String.format("No function of name %s found!", functionNameAndArgs.functionName));
        }

        method.invoke(new ScriptFunctions(), (Object) functionNameAndArgs.args);
    }

    public static JDateInterpreter getInstance() {
        if (JDateInterpreter == null) {
            JDateInterpreter = new JDateInterpreter();
        }
        return JDateInterpreter;
    }

    private static class Function {
        public String functionName;
            public String[] args;

        public Function(String functionName, String[] args) {
            this.functionName = functionName;
            this.args = args;
        }

        @Override
        public String toString() {
            return "Function { functionName= " + functionName + "\nparams= " + Arrays.toString(args) + "\n}";
        }
    }
}
