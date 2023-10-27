package it.polito.tdp.libretto.model;

import java.time.LocalDate;

public class TestLibretto {

	public static void main(String[] args) {
		
		Libretto lib = new Libretto();
				
		lib.add(new Voto("Analisi I", 29, LocalDate.of(2021, 2, 15)));
		lib.add(new Voto("Fisica I", 22, LocalDate.of(2022, 2, 14)));
		lib.add(new Voto("Informatica", 25, LocalDate.of(2022, 7, 10)));
		lib.add(new Voto("Fisica II", 28, LocalDate.of(2021, 1, 20)));
		
		
		//lib.stampa();
		
		lib.stampaPuntiUguali(22);
		
		Voto v = lib.cercaVotoPerNome("Analisi I");
		System.out.println(v);
		
		Voto a1bis = new Voto("Analisi I", 29, LocalDate.of(2021, 2, 15));
		Voto a1bis1 = new Voto("Analisi II", 30, LocalDate.of(2025, 2, 15));
		
		System.out.println(a1bis+" è duplicato: " +lib.esisteVotoDuplicato(v));
		System.out.println(a1bis1+" è duplicato: " +lib.esisteVotoDuplicato(v));
		
		
		try { //per non rischiare di bloccare il codice
			lib.add(new Voto("Informatica", 25, LocalDate.of(2023, 7, 10)));
		}catch(IllegalArgumentException e) {
			System.out.println("Errore nell'inserimento voto.");
			System.out.println(e.getMessage());
		}
		
		lib.stampa();
		Libretto migliore = lib.librettoMigliorato();
		
		System.out.println("LIBRETTO MIGLIORATO");
		migliore.stampa();
		
/*		
		lib.cancellaVotiInferiori(24);
		System.out.println("LIBRETTO");
		lib.stampa();
		*/
		System.out.println("LIBRETTO IN ORDINE ALFABETICO");
		lib.librettoOrdinatoAlfa().stampa();
		
		System.out.println("LIBRETTO IN ORDINE DI VOTO");
		lib.librettoOrdinatoPerVoto().stampa();
	}

}
