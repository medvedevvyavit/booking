package ru.development.booking.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.development.booking.model.Reservation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ReservationSpecification implements Specification<Reservation> {

    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    @Override
    public Predicate toPredicate(Root<Reservation> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (startDate != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("startDate"), startDate));
        }

        if (endDate != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("endDate"), endDate));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
