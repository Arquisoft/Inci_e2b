package inci.agents.dbManagement;

import inci.entities.Agent;

public interface GetAgent {
    /**
     * Permite la solicitud la de información para el usuario.
     */
    public Agent getAgent(String email);

}
