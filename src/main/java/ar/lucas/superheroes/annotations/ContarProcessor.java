package ar.lucas.superheroes.annotations;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Lucas Mussi
 * date 14/4/2021
 */

@Aspect
@Component
@Slf4j
public class ContarProcessor {

    @Around("@annotation(ar.lucas.superheroes.annotations.Contar)")
    public Object contarEjecucion(ProceedingJoinPoint joint) throws Throwable {

        long inicio = System.currentTimeMillis();
        Object ejecutar = joint.proceed();
        long tiempoEjecucion = System.currentTimeMillis() - inicio;
        log.trace("Se ejecutó " + joint.getSignature() + " en " + tiempoEjecucion +"ms");
        return ejecutar;
    }
}
