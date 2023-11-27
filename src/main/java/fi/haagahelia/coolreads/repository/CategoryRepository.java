package fi.haagahelia.coolreads.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fi.haagahelia.coolreads.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
