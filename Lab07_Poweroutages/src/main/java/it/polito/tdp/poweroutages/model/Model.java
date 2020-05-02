package it.polito.tdp.poweroutages.model;

import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	Ricerca ricerca ;
	
	public Model() {
		podao = new PowerOutageDAO();
		ricerca = new Ricerca();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<Blackout> getWorstCase(int NERCID, int maxOre, int maxAnni){
		return ricerca.ricerca(podao.getListBlackout(NERCID), maxOre, maxAnni);
	}

}
