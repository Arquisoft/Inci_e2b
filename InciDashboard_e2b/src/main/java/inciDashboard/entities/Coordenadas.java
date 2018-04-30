package inciDashboard.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Coordenadas {
    @Id
    @GeneratedValue
    private Long id;
    private double latitud;
    private double longitud;

    @OneToOne(mappedBy = "coordenadas", cascade = CascadeType.ALL)
    private Incidencia incidencia;

    public Coordenadas() {

    }

    public Coordenadas(double latitud, double longitud) {
	super();
	this.latitud = latitud;
	this.longitud = longitud;
    }

    public double getLatitud() {
	return latitud;
    }

    public void setLatitud(double latitud) {
	this.latitud = latitud;
    }

    public double getLongitud() {
	return longitud;
    }

    public void setLongitud(double longitud) {
	this.longitud = longitud;
    }

    @Override
    public String toString() {
	return "Coordenadas [id=" + id + ", latitud=" + latitud + ", longitud=" + longitud + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((incidencia == null) ? 0 : incidencia.hashCode());
	long temp;
	temp = Double.doubleToLongBits(latitud);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	temp = Double.doubleToLongBits(longitud);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Coordenadas other = (Coordenadas) obj;
	if (incidencia == null) {
	    if (other.incidencia != null)
		return false;
	} else if (!incidencia.equals(other.incidencia))
	    return false;
	if (Double.doubleToLongBits(latitud) != Double.doubleToLongBits(other.latitud))
	    return false;
	if (Double.doubleToLongBits(longitud) != Double.doubleToLongBits(other.longitud))
	    return false;
	return true;
    }

}
