package com.epam.javalab.webapp.user;

import javax.persistence.Entity;


public class Admin extends User{

    public Admin(String firstName, String password, String email, Role role) {
        super(firstName, password, email, role);
    }
    public Admin(){
        super();
    }

}
