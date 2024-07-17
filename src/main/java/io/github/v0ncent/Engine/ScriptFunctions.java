package io.github.v0ncent.Engine;

import io.github.v0ncent.Exceptions.NoFunctionFound;
import io.github.v0ncent.Exceptions.ScriptNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public final class ScriptFunctions {
    public static final Logger LOGGER = LoggerFactory.getLogger(JDateEngine.class);

    public static HashMap<String, Method> getMethods() {
        HashMap<String, Method> methods = new HashMap<>();

        for (Method method : ScriptFunctions.class.getDeclaredMethods()) {
            methods.put(method.getName(), method);
        }

        return methods;
    }

    public void log(ArrayList<String> args) {
        LOGGER.info(args.getFirst());
    }

    public void go(ArrayList<String> args) throws NoFunctionFound, IOException, InvocationTargetException, IllegalAccessException, ScriptNotFound {
        JDateInterpreter interpreter = JDateInterpreter.getInstance();
        File script = JDateEngine.getProject().getScriptMap().get(args.getFirst());

        if (script == null) {
            throw new ScriptNotFound(String.format("Cannot find Script: %s", args.getFirst()));
        }

        System.out.println("Running interpreter from go function");
        interpreter.interpret(script);
    }

}
