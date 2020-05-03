package it.polito.tdp.artsmia.model;

import java.util.*;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	ArtsmiaDAO dao;
	public Model() {
		dao = new ArtsmiaDAO ();
	}

	public List<Integer> getAnni() {
		
		return dao.getAnni();
	}

}
