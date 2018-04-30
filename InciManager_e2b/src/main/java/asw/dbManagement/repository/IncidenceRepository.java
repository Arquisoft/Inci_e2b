package asw.dbManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import asw.dbManagement.model.Incidence;

@Repository
public interface IncidenceRepository extends JpaRepository<Incidence, Long> {

}
