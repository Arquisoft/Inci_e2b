package model;

import java.io.Serializable;

import model.types.Location;


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