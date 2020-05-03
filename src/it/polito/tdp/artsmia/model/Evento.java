package it.polito.tdp.artsmia.model;

public class Evento {
	private Studente s;
	private Esposizioni e;

	public Evento(Studente s, Esposizioni esposizione) {
		this.s= s;
		this.e=esposizione;
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
    
}
