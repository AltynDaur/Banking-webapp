package com.epam.javalab.webapp.user;

import javax.persistence.Entity;

@Entity
public class Admin extends User{

    public Admin(String firstName, String password) {
        super(firstName, password);
    }
    public Admin(){
        super();
    }

}
