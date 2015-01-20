package com.epam.javalab.webapp.exception;

import com.epam.javalab.webapp.user.User;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class DAOException extends Exception{

    Set<ConstraintViolation<User>> errorSet;


    public DAOException() {
        super();
    }

    public DAOException(Set<ConstraintViolation<User>> violationSet) {
        this.errorSet = violationSet;
    }

    public Set<ConstraintViolation<User>> getErrorSet() {
        return errorSet;
    }

    public void setErrorSet(Set<ConstraintViolation<User>> errorSet) {
        this.errorSet = errorSet;
    }
}
