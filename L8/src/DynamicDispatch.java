import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by cfrederick on 7/9/17.
 */
public class DynamicDispatch {
    private static boolean log_to_err = false;

    public static void log_to_err(boolean should_log) {
        log_to_err = should_log;
    }

    private static void log(String s) {
        if (log_to_err)
            System.err.println(s);
    }

    public static void main(String[] args) throws NoSuchMethodException {

    }

    static Object dynamicallyCallMethodOnObject(String method_name, Object o, Object... arguments)
            throws NoSuchMethodException {
        return callMethodFromClassElseFromSuper(o.getClass(), method_name, o, arguments);
    }

    private static Object callMethodFromClassElseFromSuper(Class<?> c, String method_name, Object o, Object[] arguments)
            throws NoSuchMethodException {
    	if (c == null) {

    	String message = String.format("Failed to find a method for %s.%s%s", o.getClass().getName(), method_name, arrayToStringWithParens(arguments));
    	throw new NoSuchMethodException(message);
    	
    	}
    	log(String.format("Trying to find method [%s] on class [%s]..", method_name, c.getName()));
    	
    	for (Method m : c.getDeclaredMethods()) {
			if(m.getName().equals(method_name)) {
				
				if(parameterTypesMatchArguments(m.getParameterTypes(), arguments)) {
					log(String.format("Found in class %s! Invoking with args %s to produce a %s.\n", c.getName(), Arrays.toString(arguments), m.getReturnType().getName()));
					try {
						return m.invoke(o, arguments);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block

						String message = String.format("Method %s.%s%s threw:", c.getName(), method_name, arrayToStringWithParens(arguments));
						throw new RuntimeException(message, e.getCause());
						//e.getCause();
					} catch (IllegalArgumentException e) {

						String message = String.format("Method %s.%s%s threw:", c.getName(), method_name, arrayToStringWithParens(arguments));
						throw new RuntimeException(message,e.getCause());
						//e.getCause();
					} catch (InvocationTargetException e) {

						String message = String.format("Method %s.%s%s threw:", c.getName(), method_name, arrayToStringWithParens(arguments));
						throw new RuntimeException(message,e.getCause());
						//e.getCause();
					}
				}
			}
		}
    	return callMethodFromClassElseFromSuper(c.getSuperclass(),method_name,  o, arguments);
    	
    }
    



    private static String arrayToStringWithParens(Object[] arr) {
        String args = Arrays.toString(arr);
        return args.replace('[', '(').replace(']', ')');
    }

    private static boolean parameterTypesMatchArguments(Class<?>[] parameterTypes, Object[] arguments) {
        if (parameterTypes.length != arguments.length) return false;
        for (int i = 0; i < arguments.length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                if (!willUnwrapToPrimitive(arguments[i], parameterTypes[i]))
                    return false;
            } else {
                if (!parameterTypes[i].isInstance(arguments[i]))
                    return false;
            }
        }
        return true;
    }

    private static boolean willUnwrapToPrimitive(Object x, Class<?> primitiveType) {
        final String name = primitiveType.toString();
        switch (name) {
            case "double":
                if (x instanceof Double) return true;
            case "float":
                if (x instanceof Float) return true;
            case "long":
                if (x instanceof Long) return true;
            case "int":
                if (x instanceof Integer) return true;
            case "char":
                if (x instanceof Character) return true;
                if (name.equals("char")) break;
            case "short":
                if (x instanceof Short) return true;
            case "byte":
                if (x instanceof Byte) return true;
                break;
            case "boolean":
                if (x instanceof Boolean) return true;
                break;
        }
        return false;
    }

    public interface Dispatch_with_Method_and_Receiver {
        Object withArgs(Object... arguments) throws NoSuchMethodException;
    }

    public interface Dispatch_with_Method extends Dispatch_with_Method_and_Receiver {
        Dispatch_with_Method_and_Receiver on(Object receiver);
    }

    public interface Dispatch_with_Receiver {
        Dispatch_with_Method_and_Receiver call(String method_name);
    }

    public static Dispatch_with_Receiver On(Object receiver) {
        return new DispatchRequest(null, receiver);
    }

    public static Dispatch_with_Method Call(String method_name) {
        return new DispatchRequest(method_name, null);
    }

    private static class DispatchRequest implements Dispatch_with_Method, Dispatch_with_Receiver {
        private final String method_name;
        private final Object receiver;

        private DispatchRequest(String method_name, Object receiver) {
            this.method_name = method_name;
            this.receiver = receiver;
        }

        @Override
        public Object withArgs(Object... arguments) throws NoSuchMethodException {
            assert method_name != null;
            if (receiver != null)
                return dynamicallyCallMethodOnObject(method_name, receiver, arguments);
            assert arguments.length > 0;
            DispatchRequest withReceiver = new DispatchRequest(method_name, arguments[0]);
            Object[] remainingArguments = Arrays.copyOfRange(arguments, 1, arguments.length);
            assert remainingArguments.length == arguments.length - 1;
            return withReceiver.withArgs(remainingArguments);
        }

        @Override
        public Dispatch_with_Method_and_Receiver on(Object receiver) {
            return new DispatchRequest(method_name, receiver);
        }

        @Override
        public Dispatch_with_Method_and_Receiver call(String method_name) {
            return new DispatchRequest(method_name, receiver);
        }
    }
}
