package org.example.agroshare2.repositories;

import org.example.agroshare2.entities.Legal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegalRepository extends JpaRepository<Legal, Long> {
}
