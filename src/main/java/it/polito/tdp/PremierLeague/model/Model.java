package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	Graph<Match, DefaultWeightedEdge> grafo;
	PremierLeagueDAO dao;
	Map<Integer, Match> idMap;
	List<Adiacenza> rr;
	List<Match> migliore;
	List<Match> matchPerMese;
	double pesoMAssimoRic;
	
	public Model() {
		dao  = new PremierLeagueDAO();
		idMap = new HashMap<>();
		
	}
	
	public List<Match> getMatchCombo(){
		return matchPerMese;
	}
	
	public String creaGrafo(int mese, int min) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		matchPerMese = dao.matchPerMese(mese, idMap);
		Graphs.addAllVertices(this.grafo, matchPerMese);
		
		rr = dao.getArchi(idMap, mese, min);
		
		for(Adiacenza a:rr) {
			Graphs.addEdge(this.grafo, a.getmID1(), a.getmID2(), a.getPeso());
		}
		
		return String.format("Grafo creato con %d vertici e %d archi\n",
				this.grafo.vertexSet().size(),
				this.grafo.edgeSet().size()) ;
	}
	
	
	public List<Adiacenza> getPartiteMigliori(){
		int pesoMassimo = 0;
		List<Adiacenza> res = new ArrayList<>();
		
		for(Adiacenza a:rr) {
			if(a.getPeso()>pesoMassimo) {
				pesoMassimo= a.getPeso();
			}
		}
		
		for(Adiacenza a:rr) {
			if(a.getPeso() == pesoMassimo) {
			res.add(a);	
			}
		}
		return res;
	}
	
	public List<Match> cerca(Match partenza, Match arrivo){
		List<Match> parziale = new ArrayList<Match>();
		parziale.add(partenza);
		pesoMAssimoRic= 0.0;
		ricorsione(parziale, partenza, arrivo, 0.0);
		
		return migliore;
	}

	private void ricorsione(List<Match> parziale, Match partenza, Match arrivo, double pesoCorrente) {
		

		Match ultimo = parziale.get(parziale.size()-1);
		if(ultimo.equals(arrivo)) {
			if(pesoCorrente>pesoMAssimoRic) {
				migliore = new ArrayList<Match>(parziale);
				pesoMAssimoRic= pesoCorrente;
			}
			return;
		}
		
		for(Match m:Graphs.neighborListOf(grafo, ultimo)) {
		/*	if(!parziale.contains(m) && !(parziale.get(parziale.size()-1).equals(partenza) && ultimo.equals(m)) || 
					!(parziale.get(parziale.size()-1).equals(m) && ultimo.equals(partenza))) {
					*/
				if( !(m.getTeamHomeID() == ultimo.getTeamHomeID() && m.getTeamAwayID() == ultimo.getTeamAwayID())
				&& !(m.getTeamHomeID() == ultimo.getTeamAwayID() && m.getTeamAwayID() == ultimo.getTeamHomeID()) 
				&& !parziale.contains(m)) {	
				pesoCorrente += grafo.getEdgeWeight(grafo.getEdge(m, ultimo));
				parziale.add(m);
				ricorsione(parziale, partenza, arrivo, pesoCorrente);
				parziale.remove(m);
				pesoCorrente -= grafo.getEdgeWeight(grafo.getEdge(m, ultimo));
			}
		}
		
		
	}
	
	

	
}
