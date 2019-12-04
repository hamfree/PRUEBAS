package name.ruiz.juanfco;

public class C extends A {
	private String apellido2;

	
	
	/**
	 * 
	 */
	public C() {
		super();
	}

	/**
	 * @param apellido2
	 */
	public C(String apellido2) {
		super();
		this.apellido2 = apellido2;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido2 == null) ? 0 : apellido2.hashCode());
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
		C other = (C) obj;
		if (apellido2 == null) {
			if (other.apellido2 != null)
				return false;
		} else if (!apellido2.equals(other.apellido2))
			return false;
		return true;
	}
	
	
}
