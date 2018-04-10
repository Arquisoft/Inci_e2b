package inciDashboard.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import inciDashboard.entities.Comentario;
import inciDashboard.entities.Incidencia;

public interface CommentsRepository extends CrudRepository<Comentario, Long>{

    List<Comentario> findAllByIncidencia(Incidencia incidencia);

}
