package inci.agents.dbManagement.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inci.agents.dbManagement.GetAgent;
import inci.entities.Agent;
import inci.repositories.AgentRepository;

@Service
public class GetAgentImpl implements GetAgent {

    @Autowired
    private AgentRepository repository;

    /**
     * Método que devuelve el Agente buscado por email Hace uso del método
     * findByEmail (mapeador)
     */
    @Override
    public Agent getAgent(String email) {

	return this.repository.findByEmail(email);
    }

}
