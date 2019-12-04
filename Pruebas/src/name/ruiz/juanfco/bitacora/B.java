package name.ruiz.juanfco;

public class B extends A {
	private String apellido1;

	/**
	 * 
	 */
	public B() {
		super();
	}

	/**
	 * @param apellido1
	 */
	public B(String apellido1) {
		super();
		this.apellido1 = apellido1;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((apellido1 == null) ? 0 : apellido1.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		B other = (B) obj;
		if (apellido1 == null) {
			if (other.apellido1 != null)
				return false;
		} else if (!apellido1.equals(other.apellido1))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("B [apellido1=").append(apellido1).append("]");
		return builder.toString();
	}
	
	

}
