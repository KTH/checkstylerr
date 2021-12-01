package org.lnu.is.dao.dao.entrant;

import com.lambdista.util.Try;
import org.apache.commons.io.IOUtils;
import org.lnu.is.domain.enrolment.EntrantPlace;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EntrantPlaceDao {
    private static final String TIME_PERIOD_ID_PLACEHOLDER = "$$TIME_PERIOD_ID$$";

    @PersistenceContext
    private EntityManager entityManager;

    public List<EntrantPlace> getEntrantsPerPlace(long timePeriodId) {
        String queryTemplate = Try.apply(() ->
                IOUtils.toString(this.getClass().getResourceAsStream("/queries/entrant_per_place.sql"), "UTF-8")).get();
        String sql = queryTemplate
                .replace(TIME_PERIOD_ID_PLACEHOLDER, String.valueOf(timePeriodId));

        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> result = query.getResultList();
        return result.stream()
                .map(arr -> {
                    Double stateCount = Double.valueOf(arr[1].toString());
                    return new EntrantPlace(
                            stateCount > 0.0  ? Double.valueOf(arr[0].toString()) / stateCount : 0.0,
                            arr[2].toString(),
                            Long.parseLong(arr[3].toString()));
                })
                .collect(Collectors.toList());
    }
}
