package mylib;

import java.util.Vector;
/*
 * Calsse di metodi di utilità generale
 */
public class Utility {	

	private final static String WARNING="ATTENZIONE!!! ";
	private final static String FINITO="Premi 1 se vuoi terminare, 2 per ripetere l'operazione";
	
	/*
	 * Questo metodo mette in ordine alfabetico un vettore di stringhe
	 * 
	 * @param stringhe il vettore di stringhe da ordinare
	 * @return il vettore di stringhe ordinato
	 */
	public static Vector<String> OrdineAlfabetico(Vector<String> stringhe){
		Vector<String> inOrdine=new Vector<>();
		do{
			int m=0;
			String prima=stringhe.get(0);
			for(int i=1; i<stringhe.size(); i++){
				String ispezionata=stringhe.get(i);
				if(ispezionata.compareToIgnoreCase(prima)<0)
					prima=ispezionata;
					m=i;
		}
			inOrdine.add(prima);
			stringhe.remove(m);
		}while(stringhe.size()>0);
		return inOrdine;
	}
	
	/*
	 * Questo metodo visualizza un elenco di stringhe, numerando ogni stringa con la rispettiva posizione
	 * all'interno del vettore
	 * 
	 * @param elenco il vettore di stringhe da visualizzare
	 * @param messaggio il messaggio di errore in caso l'elenco sia vuoto
	 */
	public static void visualizzaElenco(Vector<String> elenco, String messaggio){
		if(elenco.size()>0){
			for(int i=0; i<elenco.size(); i++){
				String cercato=elenco.get(i);
				System.out.print((i+1)+". ");
				System.out.println(cercato+"\n\n");
			}
		}
		else{
			System.out.println(WARNING+messaggio);
		}
	}
	
	/*
	 * Questo metodo controlla se una data stringa è accettabile, cioè se essa non è presente all'interno di
	 * un vettore di stringhe precedentemente creato
	 * 
	 * @param stringhe il vettore di stringhe in cui voglio controllare se la parola è presente
	 * @param nome la stringa che voglio controllare se presente all'interno del vettore
	 * @param msgDoppione il messaggio da visualizzare in caso la stringa sia già presente all'interno del vettore
	 * 
	 * @return false se la parolaè già presente all'interno del vettore, false altrimenti
	 */
	public static boolean accettabile(Vector<String> stringhe, String nome, String msgDoppione){
		for(int i=0; i<stringhe.size(); i++){
			String ispezionato=stringhe.get(i);
			if(nome.equals(ispezionato)) {
				System.out.println(msgDoppione);
				return false;
			}
		}
		return true;
	}
	
	  
	  public static boolean isFinish(){
	  	boolean finito=false;
		int i=InputDati.leggiIntero(FINITO, 1, 2);
		  switch(i){
		  		case 1: finito=true;
		  				break;
		  		case 2: finito=false;
		  				break;
		  			} 
		  return finito;
	  }
	
}
