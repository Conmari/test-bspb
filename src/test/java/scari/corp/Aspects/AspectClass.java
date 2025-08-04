package scari.corp.Aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Aspect
public class AspectClass {

    private static final Logger logger = LoggerFactory.getLogger(AspectClass.class);

    // Точка среза: все публичные методы в пакете scari.corp.jsonPractic
    // @Pointcut("execution(public * scari.corp.jsonPractic..*.*(..))")
    @Pointcut("execution(* scari.corp.jsonPractic.EqualsCollectionTests.checkResponseOfficeRateContains())")
    public void greeting() {
    }

    //запускаются перед выполнением целевых методов
//    @Before("greeting()")
//    public void beforeAdvice(JoinPoint joinPoint) {
//        logger.info("запускаются перед выполнением целевых методов!");
//        logger.info("Запуск метода: {}", joinPoint.getSignature().toShortString());
//    }
//
//    //выполняются после завершения выполнения методов
//    @After("greeting()")
//    public void afterAdvice(JoinPoint joinPoint) {
//        logger.info("выполняются после завершения выполнения методов!");
//        logger.info("Завершение метода: {}", joinPoint.getSignature().toShortString());
//    }

    //выполняются только в том случае, когда целевой метод отрабатывает нормально, без ошибок
    @AfterReturning("greeting()")
    public void afterAdviceReturning() {
        logger.info("выполняются только в том случае, когда целевой метод отрабатывает нормально, без ошибок!");
    }

    //предназначен для тех случаев, когда метод, то есть точка соединения выдает исключение
    @AfterThrowing(value = "greeting()", throwing = "exception")
    public void afterAdviceThrowing(JoinPoint joinPoint, Exception exception) {
        logger.error("Метод - {}, класса - {}, был аварийно завершен с исключением - {}",
                joinPoint.getSignature().getName(),
                joinPoint.getSourceLocation().getWithinType().getName(),
                exception.toString());
    }

    //Который окружает метод, то есть — точку соединения,
    //с помощью которого мы можем, к примеру, выбрать, выполнять данный метод точки соединения или нет.
    //Можно написать код совета, который будет выполняться до и после выполнения метода точки соединения
    @Around(value = "greeting()")
    public void beforeAdvice(ProceedingJoinPoint joinPoint) {
        logger.info("Это перед методом...");
        try {
            joinPoint.proceed();
            logger.info("Это после метода....");
        }
        catch (Throwable throwable) {
            logger.error("Тут ошибка...");
        }
    }
}

