package model;

public abstract class Usuario {

	private Long id;
	private String nombre;
	private String email;
	private String codigo;
	private String password;
	
	

	public String getNombre() {
		return nombre;
	}

	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}
	
	private void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		if(password == null){
			generarPassword();
		}
		return password;
	}
	private void generarPassword() {
		StringBuffer pass = new StringBuffer();
		int low = 65;
		int top = 90;
		for (int i = 0; i < 9; i++) {
			int numAleatorio = (int) Math.floor(Math.random() * (top - low) + low);
			pass.append((char) numAleatorio);
		}
		for (int i = 0; i < 3; i++) {
			int numAleatorio = (int) Math.floor(Math.random() * (9 - 0) + 0);
			pass.append(numAleatorio);
		}
		setPassword(pass.toString());
	}

	protected void setEmail(String email) {
		this.email = email;
	}

	public String getCodigo() {
		return codigo;
	}

	protected void setCodigo(String DNI) {
		this.codigo = DNI;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Usuario other = (Usuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id =" + id + ", nombre=" + nombre + ", email=" + email + ", DNI=" + codigo + "]";
	}
}
