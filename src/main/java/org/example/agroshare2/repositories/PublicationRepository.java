package org.example.agroshare2.repositories;

import org.example.agroshare2.entities.Publication;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    public List<Publication> findByTitle(String title);

    @Query(value = "SELECT * FROM public.publication p ORDER BY id DESC LIMIT 10", nativeQuery = true)
    public List<Publication> findLast10();

    List<Publication> findByUserId(Long id);
}
