package fi.haagahelia.coolreads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fi.haagahelia.coolreads.model.Recommendation;

@Repository
public interface ReadingRecommendationRepository extends JpaRepository<Recommendation, Long>{

}
