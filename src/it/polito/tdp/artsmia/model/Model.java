package it.polito.tdp.artsmia.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	private ArtsmiaDAO dao;
	private SimpleDirectedGraph<Integer, DefaultEdge> grafo;
	private List <Corrispondenza> listaC;
	Map <Integer, Esposizioni> opere;
	public Model() {
		dao = new ArtsmiaDAO ();
	}
	
	
	public void creaGrafo(int anno) {
		grafo = new SimpleDirectedGraph<Integer,DefaultEdge> (DefaultEdge.class);
		listaC = new LinkedList<Corrispondenza>(dao.getCorrispondenze(anno));
		for (Corrispondenza c : listaC) {
			if (!grafo.containsVertex(c.getA1())) {
				grafo.addVertex(c.getA1());
			}
			if (!grafo.containsVertex(c.getA2())) {
				grafo.addVertex(c.getA2());
			}
			if (!grafo.containsEdge(c.getA1(),c.getA2())) {
				grafo.addEdge(c.getA1(), c.getA2());
			}
		}
		System.out.println("N. vertici : "+grafo.vertexSet().size());
		System.out.println("N. archi : "+grafo.edgeSet().size());
		System.out.println(this.analizzaGrafo());
		System.out.println(this.opereMax(anno));
        
		
	}
	public String opereMax(int anno) {
		int Max =0;
		int id_Max =0;
		opere = new HashMap <Integer, Esposizioni>(dao.getNOpere(anno));
		
		for (Esposizioni e : opere.values()) {
			int cont =  e.getNum();
			if (cont > Max) {
				Max = cont;
				id_Max = e.getId();
			}
		}
		return "la mostra con il numero massimo di opere � :"+id_Max+"con : "+Max+" opere!";
	}
	public String analizzaGrafo() {
		boolean connesso= true;
		for (Integer a1 : grafo.vertexSet()) {
			for (Integer a2 : grafo.vertexSet()) {
				if (!a1.equals(a2) && a1<a2) {
					DijkstraShortestPath <Integer, DefaultEdge> dijkstra = new DijkstraShortestPath<>(this.grafo);
					GraphPath <Integer, DefaultEdge>path = dijkstra.getPath(a1	, a2);
					if (path == null) {
						return "il grafo � fortemente connesso?"+false;
					}
				}
			}
		}
		
		return "il grafo � fortemente connesso?"+connesso;
		
	}

	public List<Integer> getAnni() {
		
		return dao.getAnni();
	}
	public List <Studente> simula (int n){
		Simulazione s = new Simulazione();
		List <Esposizioni> esp = new LinkedList<>(opere.values());
		s.init(grafo, esp, n);
		s.run();
		return s.getS();
		
	}

}
