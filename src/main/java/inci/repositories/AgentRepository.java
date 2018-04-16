package inci.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import inci.entities.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

    /**
     * Método que devuelve el agente el cual es buscado por email en la base de
     * datos
     * 
     * @param email
     *            del Partipante
     * @return El agente con dicho email
     */
    public Agent findByEmail(String email);

}
