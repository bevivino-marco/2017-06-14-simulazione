package it.polito.tdp.artsmia.model;

import java.util.*;

public class Studente {
    private int id;
    private List <Esposizioni> lista;
    private int cont;
	public Studente(int i) {
         this.id=i;
         lista = new LinkedList<>();
         cont =0;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean visita (Esposizioni e) {
		if (!lista.contains(e)) {
			lista.add(e);
			cont+=e.getNum();
			return true;
		}return false;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Studente other = (Studente) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

}
