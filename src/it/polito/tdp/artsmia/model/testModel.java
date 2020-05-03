package it.polito.tdp.artsmia.model;

public class testModel {

	public static void main(String[] args) {
		Model m = new Model();
		m.creaGrafo(2006);
		System.out.println(m.simula(4).toString());
	}

}
