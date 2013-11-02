
package zhwb.aop.annotation;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SimpleAspect
{
    @Pointcut("execution(* zhwb.aop.mockbusiness.DAOImpl.*(..))")
    public void simpleDaoPointcut()
    {
        //一个空的方法只是去声明切入点 是哪个方法
    }

    //后置通知
    @AfterReturning(returning = "retVal", pointcut = "simpleDaoPointcut()")
    public void simpleAdviceAfterReturning(final Object retVal)
    {
        if (!retVal.equals(false))
        System.out.println("get the retVAL is " + retVal + " I will commit into database!");
    }

    //最终通知 注意:如果出现异常则前置通知和最终通知执行，后置通知则不执行  
    @After(value = "simpleDaoPointcut()")
    public void simpleAdviceAfter()
    {
        System.out.println("After Metod Returned, I will close the connection!");

    }

    //前置通知
    @Before(value = "simpleDaoPointcut() &&" + "args(obj,..)")
    public void simpleAdviceBefore(final Object obj)
        throws Exception
    {
        if (obj != null)
            System.out.println("I will check the data integrity! input Param: " + obj.toString());
    }

    @AfterThrowing(pointcut = "simpleDaoPointcut()", throwing = "ex")
    //定义异常通知  
    public void doExceptionAction(final Exception ex)
    {
        System.out.println("When Exception throw, it will log the data" + ex.getMessage());
    }
}
