package org.example.agroshare2.repositories;

import org.example.agroshare2.entities.Category;
import org.example.agroshare2.entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    public Optional<Category> findByCategoryName(String title);
//    List<Category> findAllCategories();
}
