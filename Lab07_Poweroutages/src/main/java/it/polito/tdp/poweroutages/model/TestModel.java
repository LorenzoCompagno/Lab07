package it.polito.tdp.poweroutages.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		System.out.println(model.getNercList());
		int totaleOre=0, totaleAffetti=0;
		for(Blackout b: model.getWorstCase(15, 100, 2)) {
			System.out.print(b.toString());
			b.toString();
			totaleOre += b.getDuration();
			totaleAffetti += b.getCustomersAffected();
		}
			
		System.out.println("Totale ore: "+totaleOre+"\nTotale clienti: "+totaleAffetti);
	}
	

}
