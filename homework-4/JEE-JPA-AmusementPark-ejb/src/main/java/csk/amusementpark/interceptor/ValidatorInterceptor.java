package csk.amusementpark.interceptor;

import csk.amusementpark.annotation.Validate;
import csk.amusementpark.exception.ValidationException;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

@Interceptor
@Validation
public class ValidatorInterceptor {

    @Inject
    private Validator validator;

    @AroundInvoke
    public Object invoke(InvocationContext invocationContext) throws Exception {
        validateParameters(invocationContext.getParameters());
        return invocationContext.proceed();
    }

    private void validateParameters(Object[] params) {
        for (Object o : params) {
            if (o.getClass().isAnnotationPresent(Validate.class)) {
                validate(o);
            }
        }
    }

    private void validate(Object o) {
        Set<ConstraintViolation<Object>> violations = validator.validate(o);
        Optional<String> errorMessage = violations.stream().map(e -> "Validation error: "
                + e.getMessage()
                + " - property: "
                + e.getPropertyPath().toString()
                + " . ").reduce(String::concat);
        if (errorMessage.isPresent()) {
            throw new ValidationException(errorMessage.get());
        }
    }

}