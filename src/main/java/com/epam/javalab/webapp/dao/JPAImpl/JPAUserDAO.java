package com.epam.javalab.webapp.dao.JPAImpl;

import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.dao.UserDAO;
import com.epam.javalab.webapp.exception.DAOException;
import com.epam.javalab.webapp.user.User;

import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Stateless
@JPA
public class JPAUserDAO implements UserDAO, Serializable {


    @Inject
    private EntityManager em;

    @Resource
    Validator validator;

    Set<ConstraintViolation<User>> errorSet;

    @Override
    public List<User> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> currentUser = criteriaQuery.from(User.class);
        criteriaQuery.select(currentUser).orderBy(cb.asc(currentUser.get("name")));
        return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void add(User user) {
        em.persist(user);
    }

    @Override
    public void delete(int userID) {
        em.remove(em.getReference(User.class, userID));
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }


    @Override
    public User findByNameAndPass(String name, String password) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> currentUser = criteriaQuery.from(User.class);
        criteriaQuery.select(currentUser).where(cb.equal(currentUser.get("name"), name)).where(cb.equal(currentUser.get("password"), password));
        return em.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public User findUserByID(int userID) {
        return em.find(User.class, userID);
    }

    @Override
    public User findByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> currentUser = criteriaQuery.from(User.class);
        criteriaQuery.select(currentUser).where(cb.equal(currentUser.get("name"), name));
        return em.createQuery(criteriaQuery).getSingleResult();
    }
}
