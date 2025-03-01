package com.formsapp.aop;

import com.formsapp.common.AppErrorMessage;
import com.formsapp.dto.FormDTO;
import com.formsapp.exception.FormException;
import com.formsapp.exception.Operation;
import com.formsapp.service.FormService;
import com.formsapp.service.LoggedInUserService;
import lombok.NonNull;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class AuthorizeUser {

    private final FormService formService;
    private final LoggedInUserService loggedInUserService;

    public AuthorizeUser(FormService formService, LoggedInUserService loggedInUserService) {
        this.formService = formService;
        this.loggedInUserService = loggedInUserService;
    }

    @Pointcut("@annotation(com.formsapp.annotation.FormEditPermissionAnnotation)")
    public void myCustomAnnotationPointcut() {
    }

    private void forFormEditPermissionAnnotation(@NonNull Object formId) throws FormException {
        String loggedInUserEmail = loggedInUserService.getLoggedInUserEmail();
        if (loggedInUserEmail != null) {
            FormDTO form = formService.getForm(formId.toString());
            if (form != null && !loggedInUserEmail.equalsIgnoreCase(form.getCreatedBy())) {
                throw new Operation(AppErrorMessage.NOT_HAVE_PERMISSION_TO_EDIT.getMessage());
            }
        }
    }

    @Before("myCustomAnnotationPointcut()")
    public void beforeMethod(JoinPoint joinPoint) throws FormException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // Get method arguments and parameter names
        Object[] args = joinPoint.getArgs();
        String[] paramNames = signature.getParameterNames();
        Annotation[][] paramAnnotations = method.getParameterAnnotations();

        // Extract PathVariable values
        Map<String, Object> pathVars = new HashMap<>();
        for (int i = 0; i < paramAnnotations.length; i++) {
            for (Annotation paramAnnotation : paramAnnotations[i]) {
                if (paramAnnotation instanceof org.springframework.web.bind.annotation.PathVariable) {
                    pathVars.put(paramNames[i], args[i]);
                }
            }
        }

        // authorize
        forFormEditPermissionAnnotation(pathVars.get("formId"));

    }
}

