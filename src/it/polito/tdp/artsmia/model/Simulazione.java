package it.polito.tdp.artsmia.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;



public class Simulazione {
	//Modello -> Stato del sistema ad ogni passo
	private SimpleDirectedGraph<Integer, DefaultEdge> grafo;
	//Tipi di evento/coda prioritaria
		// 1 solo evento
		private PriorityQueue<Evento> queue;
		
    ////Parametri della simulazione
		private List <Esposizioni> esposizioni;
		private int N_STDENTI ;
		//Valori in output
		private List <Studente> listaS;
		
	public void init(SimpleDirectedGraph<Integer, DefaultEdge> grafo, List <Esposizioni> esposizioni, int n) {
			//ricevo i parametri
		    this.esposizioni=esposizioni;
			this.grafo = grafo;
			this.N_STDENTI=n;
			listaS = new LinkedList <>();
			
			//impostazione dello stato iniziale
			queue = new PriorityQueue<Evento>();
			for (int i = 0; i<this.N_STDENTI;i++) {
				Studente s = new Studente (i);
				listaS.add(s);
				Evento e =new Evento(s, esposizioni.get(0));
				queue.add(e);
				
			}
			
		
		
		}
	public void run () {
		//Estraggo un evento per volta dalla coda e lo eseguo,
				//finchè la coda non si svuota
		Evento e;
		while((e = queue.poll()) != null){
			Studente s = e.getStudente();
			Esposizioni esp = e.getE();
			s.visita(esp);
			List<Integer> l = Graphs.successorListOf(this.grafo, e.getStudente().getId());
			if (l.size()>0) {
			Esposizioni daVisitare = null;
			/**/
			int dv = l.get((int)(Math.random()*10));
			for (Esposizioni es: esposizioni) {
				if (es.getId()==dv) {
					daVisitare= es;
				}
			}
			this.queue.add(new Evento(s,daVisitare));
			
		}
	      
	
	}
	}
	public List <Studente> getS (){
		return listaS;
	}

}
