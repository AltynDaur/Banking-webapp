package com.epam.javalab.webapp.dao.JPAImpl;


import com.epam.javalab.webapp.account.TransactionHistoryRecord;
import com.epam.javalab.webapp.dao.JPA;
import com.epam.javalab.webapp.dao.TransactionHistoryDAO;
import com.epam.javalab.webapp.exception.DAOException;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
@JPA
public class JPATransactionHistoryDAO implements TransactionHistoryDAO {

    @Inject
    private EntityManager em;

    @Override
    public void add(TransactionHistoryRecord record) {

        em.merge(record);

    }

    @Override
    public List<TransactionHistoryRecord> findByUserID(int id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TransactionHistoryRecord> criteriaQuery = cb.createQuery(TransactionHistoryRecord.class);

        Root<TransactionHistoryRecord> transactionHistoryRecordRoot = criteriaQuery.from(TransactionHistoryRecord.class);
        criteriaQuery.select(transactionHistoryRecordRoot)
                .where(cb.or(cb.equal(transactionHistoryRecordRoot.get("startAcc").get("client").get("id"), id), cb.equal(transactionHistoryRecordRoot.get("finalAcc").get("client").get("id"), id)));

        return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<TransactionHistoryRecord> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TransactionHistoryRecord> criteriaQuery = cb.createQuery(TransactionHistoryRecord.class);
        Root<TransactionHistoryRecord> transactionHistoryRecordRoot = criteriaQuery.from(TransactionHistoryRecord.class);
        criteriaQuery.select(transactionHistoryRecordRoot).orderBy(cb.asc(transactionHistoryRecordRoot.get("id")));
        return em.createQuery(criteriaQuery).getResultList();
    }
}
