package com.epam.javalab.webapp.exception;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class DAOException extends Exception{

    Set<ConstraintViolation<?>> errorSet;


    public DAOException() {
        super();
    }

    public DAOException(Set<ConstraintViolation<?>> violationSet) {
        this.errorSet = violationSet;
    }

    public Set<ConstraintViolation<?>> getErrorSet() {
        return errorSet;
    }

    public void setErrorSet(Set<ConstraintViolation<?>> errorSet) {
        this.errorSet = errorSet;
    }
}
