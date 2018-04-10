package inciDashboard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inciDashboard.entities.Coordenadas;
import inciDashboard.repositories.CoordenadasRepository;

@Service
public class CoordenadasService {
    @Autowired
    private CoordenadasRepository coordenadasRepository;

    public void addCoordenadas(Coordenadas user) {
	coordenadasRepository.save(user);
    }


}
