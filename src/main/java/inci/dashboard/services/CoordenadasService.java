package inci.dashboard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inci.entities.Coordenadas;
import inci.repositories.CoordenadasRepository;

@Service
public class CoordenadasService {
    @Autowired
    private CoordenadasRepository coordenadasRepository;

    public void addCoordenadas(Coordenadas user) {
	coordenadasRepository.save(user);
    }

}
