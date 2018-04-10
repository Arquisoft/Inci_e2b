package asw.dbManagement.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Incidence {
	
	public Incidence() {}
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String usuario;
	private String password;
	
	private String nombre;
	private String descripcion;
	private String localizacion;
	
	private String etiquetas;
	
	private String extra;
	
	@Transient
	private List<String> campos;

	
	
	public Incidence(String usuario) {
		super();
		this.usuario = usuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public String getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(String etiquetas) {
		this.etiquetas = etiquetas;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public List<String> getCampos() {
		return campos;
	}

	public void setCampos(List<String> campos) {
		this.campos = campos;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Incidence [id=" + id + ", usuario=" + usuario + ", password=" + password + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + ", localizacion=" + localizacion + ", etiquetas=" + etiquetas
				+ ", extra=" + extra + "]";
	}
	

}
