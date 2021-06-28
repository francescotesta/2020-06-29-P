package it.polito.tdp.PremierLeague.model;

public class Adiacenza {
	
	Match mID1;
	Match mID2;
	int peso;
	public Adiacenza(Match mID1, Match mID2, int peso) {
		this.mID1 = mID1;
		this.mID2 = mID2;
		this.peso = peso;
	}
	/**
	 * @return the mID1
	 */
	public Match getmID1() {
		return mID1;
	}
	/**
	 * @param mID1 the mID1 to set
	 */
	public void setmID1(Match mID1) {
		this.mID1 = mID1;
	}
	/**
	 * @return the mID2
	 */
	public Match getmID2() {
		return mID2;
	}
	/**
	 * @param mID2 the mID2 to set
	 */
	public void setmID2(Match mID2) {
		this.mID2 = mID2;
	}
	/**
	 * @return the peso
	 */
	public int getPeso() {
		return peso;
	}
	/**
	 * @param peso the peso to set
	 */
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	

}
