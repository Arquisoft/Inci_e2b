package asw.dbManagement.repository;

import asw.dbManagement.model.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IncidenceRepository extends JpaRepository<Incidencia, Long> {


    List<Incidencia> findByNombreUsuario(String usuario);

}
