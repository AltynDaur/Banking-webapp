package com.epam.javalab.webapp.dao.JPAImpl;

import com.epam.javalab.webapp.account.Account;
import com.epam.javalab.webapp.dao.AccountDAO;
import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.exception.DAOException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


@Stateless
@JPA
public class JPAAccountDAO implements AccountDAO, Serializable {

    @Inject
    private EntityManager em;

    @Resource
    Validator validator;

    Set<ConstraintViolation<?>> errorSet;

    @Override
    public List<Account> findAllByUserID(int id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Account> criteriaQuery = cb.createQuery(Account.class);
        Root<Account> currentAccount = criteriaQuery.from(Account.class);
        criteriaQuery.select(currentAccount).where(cb.equal(currentAccount.get("client").get("id"), id)).orderBy(cb.asc(currentAccount.get("id")));
        return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Account> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Account> criteriaQuery = cb.createQuery(Account.class);
        Root<Account> currentAccount = criteriaQuery.from(Account.class);
        criteriaQuery.select(currentAccount).orderBy(cb.asc(currentAccount.get("id")));
        return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Account findByID(int id) {
        return em.find(Account.class, id);
    }

    @Override
    public void update(Account account) {

        em.merge(account);

    }

    @Override
    public void delete(int accountID) {

        em.remove(em.getReference(Account.class, accountID));

    }

    @Override
    public void add(Account account) {

        em.merge(account);


    }

    @Override
    public boolean transaction(Account currentAccount, Account targetAccount, long amount) {
        try {
            double coeffToTenge = currentAccount.getAcctype().getExchangeRate().getValue();
            currentAccount.setAmount((long) ((currentAccount.getAmount() * coeffToTenge - amount) / coeffToTenge));
            em.merge(currentAccount);
            coeffToTenge = targetAccount.getAcctype().getExchangeRate().getValue();
            targetAccount.setAmount((long) ((targetAccount.getAmount() * coeffToTenge + amount) / coeffToTenge));
            em.merge(targetAccount);

        } catch (RollbackException e) {

            return false;
        }
        return true;
    }
}
