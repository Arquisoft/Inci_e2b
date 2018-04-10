package inciDashboard.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comentario {
    @Id
    @GeneratedValue
    private Long id;
    private String texto;
    
    @ManyToOne
    @JoinColumn(name = "incidencia_id")
    private Incidencia incidencia;

    public Comentario() {

    }
    
    public Comentario(String texto) {
    	super();
		this.texto = texto;
	}

    public Comentario(String texto, Incidencia incidencia) {
	this(texto);
	this.incidencia = incidencia;
    }
    

	public String getTexto() {
	return texto;
    }

    public void setTexto(String texto) {
	this.texto = texto;
    }

    public Incidencia getIncidencia() {
	return incidencia;
    }

    public void setIncidencia(Incidencia incidencia) {
	this.incidencia = incidencia;
    }

    @Override
    public String toString() {
	return "Comentario [id=" + id + ", texto=" + texto + ", incidencia=" + incidencia + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((incidencia == null) ? 0 : incidencia.hashCode());
	result = prime * result + ((texto == null) ? 0 : texto.hashCode());
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
	Comentario other = (Comentario) obj;
	if (incidencia == null) {
	    if (other.incidencia != null)
		return false;
	} else if (!incidencia.equals(other.incidencia))
	    return false;
	if (texto == null) {
	    if (other.texto != null)
		return false;
	} else if (!texto.equals(other.texto))
	    return false;
	return true;
    }

}
