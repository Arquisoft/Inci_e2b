package inciDashboard.repositories;

import inciDashboard.entities.InciStatus;
import inciDashboard.entities.Incidencia;
import inciDashboard.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IncidenciasRepository extends CrudRepository<Incidencia, Long> {

    List<Incidencia> findAllByUser(User user);

    @Query("UPDATE Incidencia i SET i.estado = ?1  WHERE i.id = ?2")
    void setStatus(Enum<InciStatus> estado, Long idIncidencia);
//    @Query("SELECT c.KEY, c.VALUE FROM CAMPOS c WHERE c.INCIDENCIA_ID = ?1")
    @Query("SELECT i FROM Incidencia i WHERE i.id = ?1")
    Incidencia getDangerousValues(Long id);


    @Transactional
    @Modifying
    @Query("UPDATE Incidencia i SET i.user = ?1  WHERE i.id = ?2")
    void setUser(User u, Long idIncidencia);
}
