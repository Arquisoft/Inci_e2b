package inciDashboard.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inciDashboard.entities.Comentario;
import inciDashboard.entities.Incidencia;
import inciDashboard.repositories.CommentsRepository;

@Service
public class CommentsService {
    @Autowired
    private CommentsRepository commentsRepository;

    public List<Comentario> getComentarios() {
	List<Comentario> comentarios = new ArrayList<Comentario>();
	commentsRepository.findAll().forEach(comentarios::add);
	return comentarios;
    }

    public Comentario getComentarios(Long id) {
	return commentsRepository.findOne(id);
    }

    public void addComentario(Comentario comentario) {
	commentsRepository.save(comentario);
    }

    public void deleteComentarios(Long id) {
	commentsRepository.delete(id);
    }

    public List<Comentario> getCommentsByIncidencia(Incidencia incidencia) {
	return commentsRepository.findAllByIncidencia(incidencia);
    }
}
