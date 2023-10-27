package it.polito.tdp.libretto.model;

import java.util.*;

public class Libretto {
	private List<Voto> voti;

	public Libretto() {  //inizializzo le variabili
		this.voti = new ArrayList<Voto>();
	}
	
	/**
	 * Aggiungi un nuovo voto al libretto
	 * (per ora non fa nessun controllo)
	 * @param v voto da aggiungere
	 * @return true
	 */
	public boolean add(Voto v) { //se metto Boolean si può ritornare null, ma non è del tutto corretto
		if(this.esisteVotoConflitto(v) || this.esisteVotoDuplicato(v)) {
			throw new IllegalArgumentException("Voto errato: "+v); //opzione per comunicare al chiamante che è andato male
		}
		return this.voti.add(v); //delega, delego alla lista di fare le cose che voglio
	}
	
	public void stampa() {
		for(Voto v : this.voti) {
			System.out.println(v);
		}
	}
	
	public String toString() {
		String txt = "";
		for(Voto v : this.voti) {
			txt = txt+ v.toString()+ "\n";
		}
		
		return txt;
	}
	
	public void stampaPuntiUguali(int votoDesiderato) {
		for(Voto v : this.voti) {
			if(v.getPunti()== votoDesiderato)
				System.out.println(v);
		}
	}
	
	public Voto cercaVotoPerNome(String nomeCorso) {
		for(Voto vo: this.voti) {
			if(vo.getCorso().equals(nomeCorso)) {
				return vo;
			}
		}
		return null;
		
		//throw new RuntimeException("Voto non trovato"); si usa in casi non prevedibili, es. se qualcosa 
		// è andato storto nul programma;
	}
	
	public boolean esisteVotoDuplicato(Voto nuovo) {
		for(Voto v: this.voti) {
			if(v.isDuplicato(nuovo)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean esisteVotoConflitto(Voto nuovo) {
		for(Voto v: this.voti) {
			if(v.isConflitto(nuovo)) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Metodo 'factory' per creare un nuovo libretto
	 * con voti migliorati.
	 * @return
	 */
	public Libretto librettoMigliorato() {
		Libretto migliore = new Libretto();
		migliore.voti = new ArrayList<>(); //(this.voti)nuovo oggetto lista, le due liste sono indipendenti
		//ho clonato i voti, gli oggetti voto sono condivisi da entrami i libretti; se aggiungo un nuovo voto ad uno
		//non lo aggiungo anche nell'altro
		for(Voto v : this.voti) {
			migliore.voti.add(v.clone()); //per fare una copia indipendente bisogna clonare tutti gli oggetti
			//fino ad oggetti primitivi o immutabili (es. stringhe)
			//migliore.voti.add(new Voto(v));
		}
		
		for(Voto v : migliore.voti) {
			v.setPunti(v.getPunti()+2);
		}
		
		return migliore;
		
	}
	
	public void cancellaVotiInferiori(int punti) {
		List<Voto> daCancellare = new ArrayList<Voto>();
		for(Voto v : this.voti) { //non si modifica la lista su cui si sta iterando;
			if(v.getPunti()<punti) {
				daCancellare.add(v);
			}
		}
		
		for(Voto v1: daCancellare) {
			this.voti.remove(v1);
		}
		
		//Meglio: this.voti.removeAll(daCancellare);  alternativa al secondo ciclo
	/*
		for(int i = 0; i<this.voti.size(); i++) {
			if(this.voti.get(i).getPunti()<punti) {
				this.voti.remove(i);
			}
		}
	*/
	}
	
	public Libretto librettoOrdinatoAlfa() {
		Libretto ordinato = new Libretto();
		ordinato.voti = new ArrayList<>(this.voti);
		
		Collections.sort(ordinato.voti, new ComparatoreByName());
		
		return ordinato;
	}
	
	public Libretto librettoOrdinatoPerVoto() {
		Libretto ordinato = new Libretto();
		ordinato.voti = new ArrayList<>(this.voti);
		
		ordinato.voti.sort(new Comparator<Voto>() {

			@Override
			public int compare(Voto o1, Voto o2) {
				return o2.getPunti()-o1.getPunti(); //ordinamento decrescente
			} 
			//creo un'altra classe del costruttore, la implemento detro le graffe
			
		});
		return ordinato;
		
	}
	
}
