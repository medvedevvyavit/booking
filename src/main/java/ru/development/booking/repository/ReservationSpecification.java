package ru.development.booking.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.development.booking.dto.ReservationFilterDto;
import ru.development.booking.model.Reservation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ReservationSpecification implements Specification<Reservation> {

    private final ReservationFilterDto filter;

    @Override
    public Predicate toPredicate(Root<Reservation> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getStartDate() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("startDate"), filter.getStartDate()));
        }

        if (filter.getEndDate() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("endDate"), filter.getEndDate()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
