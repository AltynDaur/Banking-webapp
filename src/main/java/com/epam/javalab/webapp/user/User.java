package com.epam.javalab.webapp.user;


import com.epam.javalab.webapp.account.Account;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "USERS")
@NamedQuery(name = "User.findByName", query = "SELECT u from USERS u where u.name=?1 and u.password=?2")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;
    @NotNull
    @Column(name = "NAME")
    private String name;
    @NotNull
    @Column(name = "PASSWORD")
    private String password;
    @Enumerated
    @Column(name = "ROLE")
    @NotNull
    private Role role;
    @NotNull
    @Column(name = "EMAIL")
    @Email
    private String email;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Account> bankAccounts;

    public User(String firstName, String password, String email, Role role) {

        this.name = firstName;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User() {

    }

    public int getId() {
        return id;
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

    public void setBankAccounts(List<Account> bankAccounts) {
        while (bankAccounts.iterator().hasNext()) {
            bankAccounts.iterator().next().setOwner(this);
        }
        this.bankAccounts = bankAccounts;
    }


    public List<Account> getBankAccounts() {
        return bankAccounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (role != user.role) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
