package it.polito.tdp.poweroutages.model;


import java.util.ArrayList;
import java.util.List;


public class Ricerca {
	private int maxOre;
	private int maxAnni;
	private List <Blackout> soluzione;
	private List <Blackout> parziale;

	
	
	public List <Blackout> ricerca (List<Blackout> blackoutDAO, int maxOre, int maxAnni){
		parziale = new ArrayList<>();
		soluzione = new ArrayList<>();
		soluzione.add(blackoutDAO.get(0));
		this.maxOre = maxOre;
		this.maxAnni = maxAnni;
		cerca(blackoutDAO, parziale, 0);
		return soluzione;
	}

	private void cerca(List <Blackout> tutti, List  <Blackout> parziale, int livello) {
		if(parziale.size() > 0  && trovaMaxDifferenzaAnni(parziale) < maxAnni && calcolaCustomers(parziale) > calcolaCustomers(soluzione)
				&& calcolaOre(parziale) <= maxOre && !puoiAggiungere(tutti, parziale)) {
				soluzione = new ArrayList<>(parziale);
				return ;
		} else {
			if(parziale.size()>0 && calcolaOre(parziale) > maxOre)
				return ;
			for(Blackout b: tutti) {
				if(!parziale.contains(b)) {
					parziale.add(b);
					cerca(tutti, parziale,livello+1);
					parziale.remove(parziale.size()-1);
				}
			}
		}
		
		
	}

	private int calcolaCustomers(List<Blackout> parziale) {
		int customers = 0;
		for(Blackout b: parziale)
			customers += b.getCustomersAffected();
		return customers;
	}
	private int calcolaOre(List <Blackout> parziale) {
		int ore = 0;
		for (Blackout b: parziale) {
			ore += b.getDuration();
		}
		return ore;
	}
	
	private int trovaMaxDifferenzaAnni(List<Blackout> soluzione) {
		int min = soluzione.get(0).getDateEventFinish().getYear();
		int max = soluzione.get(0).getDateEventFinish().getYear();
		
		for(Blackout b: soluzione) {
			if(b.getDateEventFinish().getYear() < min)
				min = b.getDateEventFinish().getYear();
			else if(b.getDateEventFinish().getYear() > max)
				max = b.getDateEventFinish().getYear();
		}
		return max-min;
	}
	
	private boolean puoiAggiungere (List<Blackout> tutti, List <Blackout> parziale) {
		List <Blackout> rimanenti = new ArrayList<>(tutti);
		List <Blackout> parz = new ArrayList<>(parziale);
		int ore = calcolaOre(parziale);
		rimanenti.removeAll(parziale);
		for(Blackout b: rimanenti) {
			parz.add(b);
			if(b.getDuration() <= (long) (maxOre-ore) && trovaMaxDifferenzaAnni(parz) < maxAnni )
				return true;
			parz.remove(parz.size()-1);
		}
		return false;
		
	}
}
