/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csk.mobilewebshop.interceptor;

import csk.mobilewebshop.annotation.Validate;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;

@Interceptor
@BeanValidation
public class ValidatorInterceptor {

    @Inject
    private Validator validator;

    public Object logMethod(InvocationContext ic) throws Exception {
        validateParams(ic.getParameters());
        return ic.proceed();
    }

    private void validateParams(Object[] parameters) {
        for (Object o : parameters) {
            if (o.getClass().isAnnotationPresent(Validate.class)) {
                validateBean(o);
            }
        }
    }

    private void validateBean(Object o) {
        Set<ConstraintViolation<Object>> violations = validator.validate(o);
        Optional<String> errorMessage = violations.stream().map(e -> "Validation error: " + e.getMessage() + " - property: " + e.getPropertyPath().toString() + " . ").reduce(String::concat);
        if (errorMessage.isPresent()) {
            throw new ValidationException(errorMessage.get());
        }
    }
}
