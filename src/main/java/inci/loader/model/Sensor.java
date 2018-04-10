package inci.loader.model;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import inci.loader.model.types.Location;

@Entity
@Table(name = "Entity")
@DiscriminatorValue("Entity")
public class Sensor extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private Location location;

    Sensor(){}

    public Sensor(String name, Location location, String email, String code){
        setNombre(name);
        this.location = location;
        setEmail(email);
        setCodigo(code);
    }

    public Location getLocation() {
        return location;
    }
}