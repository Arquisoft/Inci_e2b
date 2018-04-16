package inci.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "Usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "Tipo")
public abstract class User {

    private static final Logger LOG = LoggerFactory.getLogger(User.class);

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String codigo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Incidence> incidencias = new HashSet<Incidence>();

    public User() {
    }

    public User(String name, String email) {
	LOG.info("Creating user " + name + ". Email: " + email);
	this.name = name;
	this.email = email;
    }

    public String getName() {
	return name;
    }

    public static Logger getLog() {
	return LOG;
    }

    public String getEmail() {
	return email;
    }

    public String getPassword() {
	return password;
    }

    public Long getId() {
	return id;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Set<Incidence> getIncidencias() {
	return new HashSet<Incidence>(incidencias);
    }

    public void setIncidencias(Set<Incidence> incidencias) {
	this.incidencias = incidencias;
    }

    public String getCodigo() {
	return codigo;
    }

    public void setCodigo(String codigo) {
	this.codigo = codigo;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((email == null) ? 0 : email.hashCode());
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
	User other = (User) obj;
	if (email == null) {
	    if (other.email != null)
		return false;
	} else if (!email.equals(other.email))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
    }

}