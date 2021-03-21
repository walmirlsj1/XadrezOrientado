package pecas.factory;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class Factory {

    public static Object criar(Class classe, Object... param) {
        try {
            Constructor[] vetor = classe.getConstructors();
            Constructor c = Arrays.stream(vetor)
                    .filter(constructor -> constructor.getParameterCount() == param.length)
                    .findFirst().get();
            return c.newInstance(param);

        } catch (Exception e) {
            return null;
        }
    }
}
