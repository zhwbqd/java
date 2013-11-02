
package zhwb.study.javaproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogHandle implements InvocationHandler
{
    private Object stub;

    public LogHandle(final Object stub)
    {
        super();
        this.stub = stub;
    }

    public LogHandle()
    {
        super();
    }

    public Object invoke(final Object proxy, final Method method, final Object[] args)
        throws Throwable
    {
        System.out.println("Do my own job before real job.....");
        Object obj = method.invoke(stub, args);
        System.out.println("Do my own job after real job.....");
        return obj;
    }

}
