package com.epam.javalab.webapp.dao.JPAImpl;

import com.epam.javalab.webapp.account.AccountType;
import com.epam.javalab.webapp.dao.AccountTypeDAO;
import com.epam.javalab.webapp.dao.JPA;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Stateless
@JPA
public class JPAAccountTypeDAO implements AccountTypeDAO, Serializable {

    @Inject
    private EntityManager em;

    @Override
    public List<AccountType> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<AccountType> criteriaQuery = cb.createQuery(AccountType.class);
        Root<AccountType> currentAccountType = criteriaQuery.from(AccountType.class);
        criteriaQuery.select(currentAccountType).orderBy(cb.asc(currentAccountType.get("id")));
        return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void add(AccountType accountType) {

        em.merge(accountType);


    }

    @Override
    public void update(AccountType targetAccType) {

        em.merge(targetAccType);

    }

    @Override
    public void delete(int accountTypeID) {

        em.remove(em.getReference(AccountType.class, accountTypeID));

    }

    @Override
    public AccountType findAccTypeByID(int accTypeID) {
        return em.find(AccountType.class, accTypeID);
    }

    public AccountType findByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<AccountType> criteriaQuery = cb.createQuery(AccountType.class);
        Root<AccountType> currentAccountType = criteriaQuery.from(AccountType.class);
        criteriaQuery.select(currentAccountType).where(cb.equal(currentAccountType.get("name"), name));
        return em.createQuery(criteriaQuery).getSingleResult();
    }
}
