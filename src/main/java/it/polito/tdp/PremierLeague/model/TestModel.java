package it.polito.tdp.PremierLeague.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		Model m = new Model();
		
		System.out.println(m.creaGrafo(2, 85));
		
		List<Adiacenza> r = m.getPartiteMigliori();
		
		for(Adiacenza a:r) {
			System.out.println(a.getmID1().getTeamAwayNAME()+"-"+a.getmID1().getTeamHomeNAME()+"-"+a.getmID2().getTeamAwayNAME()+"-"+a.getmID2().getTeamHomeNAME()+a.getPeso());
		}

		
	}

}
