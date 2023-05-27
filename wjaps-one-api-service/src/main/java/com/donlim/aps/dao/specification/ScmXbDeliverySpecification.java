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
        return (root, query, cb) -> cb.like(root.get("orderNo"), "%"+orderNo+"%");
    }

    public static Specification<ScmXbDelivery> materialCodeLike(String materialCode) {
        return (root, query, cb) -> cb.like(root.get("materialCode"), "%"+materialCode+"%");
    }
    public static Specification<ScmXbDelivery> materialNameLike(String materialName) {
        return (root, query, cb) -> cb.like(root.get("materialName"), "%"+materialName+"%");
    }
}
