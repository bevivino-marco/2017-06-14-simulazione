package it.polito.tdp.artsmia.model;

public class Evento implements Comparable<Evento>{
	private Studente s;
	private Esposizioni e;
	private int i ;

	public Evento(Studente s, Esposizioni esposizione, int i) {
		this.s= s;
		this.e=esposizione;
		this.i=i;
	}
    public Studente getStudente () {
    	return s;
    }
    
    public Esposizioni getE() {
    	return e;
    }
	@Override
	public String toString() {
		return String.format("s=%s ,  e=%s]", s, e);
	}
	
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	@Override
	public int compareTo(Evento arg0) {
		// TODO Auto-generated method stub
		return this.i-arg0.getI();
	}
    
}
