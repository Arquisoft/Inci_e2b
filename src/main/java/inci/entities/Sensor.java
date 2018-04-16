package inci.entities;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Entity")
@DiscriminatorValue("Entity")
public class Sensor extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Coordenadas coordenadas;

    Sensor() {
    }

    public Sensor(String name, Coordenadas coordenadas, String email, String code) {
	setName(name);
	this.coordenadas = coordenadas;
	setEmail(email);
	setCodigo(code);
    }

    public Coordenadas getCoordenadas() {
	return coordenadas;
    }
}