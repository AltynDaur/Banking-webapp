package com.epam.javalab.webapp.user;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "USERS")
public abstract class User {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;
    @NotNull
    @Column(name = "NAME")
    private String firstName;
    @NotNull
    @Column(name = "PASSWORD")
    private String password;
    @Enumerated
    @Column(name = "ROLE")
    private Role role;
    @NotNull
    @Column(name = "EMAIL")
    private String email;

    public User(String firstName, String password) {

        this.firstName = firstName;
        this.password = password;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = Role.valueOf(role.toUpperCase());
    }
    public void setRole(int roleId){
        if(roleId == 1){
            this.role = Role.ADMIN;
        } else if (roleId == 2){
            this.role = Role.CLIENT;
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (role != user.role) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
