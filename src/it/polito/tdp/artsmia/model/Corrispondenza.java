package it.polito.tdp.artsmia.model;

public class Corrispondenza {
	private int a1;
	private int a2;
	public Corrispondenza(int a1, int a2) {
		super();
		this.a1 = a1;
		this.a2 = a2;
	}
	public int getA1() {
		return a1;
	}
	public void setA1(int a1) {
		this.a1 = a1;
	}
	public int getA2() {
		return a2;
	}
	public void setA2(int a2) {
		this.a2 = a2;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a1;
		result = prime * result + a2;
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
		Corrispondenza other = (Corrispondenza) obj;
		if (a1 != other.a1)
			return false;
		if (a2 != other.a2)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return String.format("a1=%s , a2=%s\n", a1, a2);
	}
	
 

}
