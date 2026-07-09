package com.example.epi.criteria;

import com.example.epi.dto.SearchRequestDTO;
import com.example.epi.entity.EpiRequest;
import com.example.epi.enums.RequestStatus;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class EpiRequestCriteria {

    public static Specification<EpiRequest> search(
            SearchRequestDTO dto,
            String role,
            Long employeeId,
            Long projetId
    ) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();
            if ("COLLABORATEUR".equals(role)) {


                predicates.add(
                        cb.equal(
                                root.get("employee").get("id"),
                                employeeId
                        )
                );

                if (dto.getStatus() != null) {
                    predicates.add(
                            cb.equal(
                                    root.get("status"),
                                    dto.getStatus()
                            )
                    );
                }

                if (dto.getKeyword() != null &&
                        !dto.getKeyword().trim().isEmpty()) {

                    String keyword = "%" + dto.getKeyword().toLowerCase() + "%";

                    predicates.add(
                            cb.like(
                                    cb.lower(
                                            root.get("epi")
                                                    .get("typeEPI")
                                                    .as(String.class)
                                    ),
                                    keyword
                            )
                    );
                }
            }


            if ("DIRECTEUR".equals(role)) {


                predicates.add(
                        cb.equal(
                                root.get("employee")
                                        .get("projet")
                                        .get("id"),
                                projetId
                        )
                );

                if (dto.getKeyword() != null &&
                        !dto.getKeyword().trim().isEmpty()) {

                    String keyword =
                            "%" + dto.getKeyword().toLowerCase() + "%";

                    predicates.add(
                            cb.or(
                                    cb.like(
                                            cb.lower(
                                                    root.get("employee")
                                                            .get("firstName")
                                            ),
                                            keyword
                                    ),
                                    cb.like(
                                            cb.lower(
                                                    root.get("employee")
                                                            .get("lastName")
                                            ),
                                            keyword
                                    )
                            )
                    );
                }

                if (dto.getStatus() != null) {
                    predicates.add(
                            cb.equal(
                                    root.get("status"),
                                    dto.getStatus()
                            )
                    );
                }
            }


            if ("ACHAT".equals(role)) {

                predicates.add(
                        root.get("status").in(
                                RequestStatus.VALIDEE,
                                RequestStatus.ACHAT_EFFECTUE
                        )
                );

                if (dto.getStatus() != null) {
                    predicates.add(
                            cb.equal(
                                    root.get("status"),
                                    dto.getStatus()
                            )
                    );
                }

                if (dto.getEpiType() != null) {
                    predicates.add(
                            cb.equal(
                                    root.get("epi").get("typeEPI"),
                                    dto.getEpiType()
                            )
                    );
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}