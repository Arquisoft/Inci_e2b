package inci.agents.dbManagement;

import inci.entities.Agent;

public interface UpdateInfo {
    /**
     * Permite la solicitud de cambio de contraseña
     */
    public void updatePassword(Agent agent, String password, String newPassword);

    public void updateEmail(Agent agent, String email);
}
