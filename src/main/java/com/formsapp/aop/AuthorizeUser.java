package com.formsapp.aop;

import com.formsapp.common.AppConstant;
import com.formsapp.common.AppErrorMessage;
import com.formsapp.exception.FormException;
import com.formsapp.exception.Operation;
import com.formsapp.service.FormService;
import com.formsapp.service.LoggedInUserService;
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
    public void formEditPermissionAnnotationPointcut() {
    }

    /**
     * This method to take jointPoint and return map of path variable and its values from methods.
     *
     * @param joinPoint
     * */
    private Map<String, Object> getPathVariableAndValue(JoinPoint joinPoint) {
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
        return pathVars;
    }

    /**
     * This Method check form is created by same as logged-in user, if not same then throw exception.
     *
     * @param joinPoint
     * */
    @Before("formEditPermissionAnnotationPointcut()")
    public void beforeMethod(JoinPoint joinPoint) throws FormException {
        Map<String, Object> pathVars = getPathVariableAndValue(joinPoint);

        // authorize
        String loggedInUserEmail = loggedInUserService.getLoggedInUserEmail();

        if (!formService.isFormCreatedByEmail(pathVars.get(AppConstant.FIELD_FORM_ID).toString(), loggedInUserEmail)) {
            throw new Operation(AppErrorMessage.USER_NOT_HAVE_PERMISSION_TO_EDIT.getMessage());
        }
    }
}

