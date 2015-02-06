package com.epam.javalab.webapp.dao.JPAImpl;

import com.epam.javalab.webapp.account.AccountType;
import com.epam.javalab.webapp.account.ExchangeRate;
import com.epam.javalab.webapp.dao.ExchangeRateDAO;
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
public class JPAExchangeRateDAO implements ExchangeRateDAO, Serializable {

    @Inject
    private EntityManager em;

    @Override
    public List<ExchangeRate> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ExchangeRate> criteriaQuery = cb.createQuery(ExchangeRate.class);
        Root<ExchangeRate> exchangeRateRoot = criteriaQuery.from(ExchangeRate.class);
        criteriaQuery.select(exchangeRateRoot).orderBy(cb.asc(exchangeRateRoot.get("id")));
        return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void add(ExchangeRate rate) {

        em.merge(rate);

    }

    @Override
    public void delete(int rateID) {

        em.remove(em.getReference(ExchangeRate.class, rateID));

    }

    @Override
    public void update(ExchangeRate rate) {

        em.merge(rate);

    }

    @Override
    public ExchangeRate findByID(int currencyID) {
        return em.find(ExchangeRate.class, currencyID);
    }

    @Override
    public ExchangeRate findByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ExchangeRate> criteriaQuery = cb.createQuery(ExchangeRate.class);
        Root<ExchangeRate> currentExchangeRate = criteriaQuery.from(ExchangeRate.class);
        criteriaQuery.select(currentExchangeRate).where(cb.equal(currentExchangeRate.get("currency"), name));
        return em.createQuery(criteriaQuery).getSingleResult();
    }
}
