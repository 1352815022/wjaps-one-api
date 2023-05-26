package com.donlim.aps.dao.specification;

import com.donlim.aps.entity.ScmXbDelivery;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.xml.soap.SAAJResult;
import java.time.LocalDate;

public class ScmXbDeliverySpecification {

    public static Specification<ScmXbDelivery> DeliveryStartDateBetween(LocalDate start, LocalDate end) {
        return (root, query, cb) ->{
            Predicate predicate = cb.conjunction();
            predicate=cb.and(cb.between(root.get("deliveryStartDate"), start, end),
                    cb.or(cb.equal(root.get("changeDateFlag"), true), cb.equal(root.get("changeDateFlag"), true)));
            query.orderBy(cb.asc(root.get("deliveryStartDate")));
            return predicate;
        };


    }

    public static Specification<ScmXbDelivery> orderNoLike(String orderNo) {
        return (root, query, cb) -> cb.like(root.get("orderNo"), orderNo);
    }

    public static Specification<ScmXbDelivery> orderBy() {
        return (root, query, cb) ->{
            Predicate predicate = cb.conjunction();
            Order order=cb.asc(root.get("deliveryStartDate"));
            query.orderBy(cb.desc(root.get("createdAt")));;
            return predicate;
        };
    }
   // Order order = sortOrder.equalsIgnoreCase("desc") ? cb.desc(root.get(sortField)) : cb.asc(root.get(sortField));
        //    query.orderBy(order);
}
