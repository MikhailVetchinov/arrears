package com.mvetchinov.arrears.services;

import com.mvetchinov.arrears.model.Deal;
import com.mvetchinov.arrears.model.PDCL;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

@Service
public class Arrears {

    private final EntityManager entityManager;

    public Arrears(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public IndebtednessInfo getIndebtedness(Deal deal) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PDCL> builder = criteriaBuilder.createQuery(PDCL.class);
        Root<PDCL> root = builder.from(PDCL.class);
        builder.where(criteriaBuilder.equal(root.get("deal"), deal)).orderBy(criteriaBuilder.desc(root.get("date")));
        TypedQuery<PDCL> query = entityManager.createQuery(builder);
        List<PDCL> resultList = query.getResultList();
        double total = resultList.stream().mapToDouble(PDCL::getSum).sum();
        if (total <= 0.0) return new IndebtednessInfo(null, total);
        double sum = total;
        LocalDate indebtednessDate = null;
        for (PDCL pdcl : resultList) {
            if (pdcl.getSum() >= sum) {
                indebtednessDate = pdcl.getDate();
            }
            sum -= pdcl.getSum();
        }
        return new IndebtednessInfo(indebtednessDate, total);
    }

    public static class IndebtednessInfo {
        private final LocalDate date;
        private final Double value;

        IndebtednessInfo(LocalDate date, Double value) {
            this.date = date;
            this.value = value;
        }

        public LocalDate getDate() {
            return date;
        }

        public Double getValue() {
            return value;
        }
    }

}
