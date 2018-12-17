package Versione1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Utility {
	
	 private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	 private static Random rnd = new Random(System.currentTimeMillis());
	 private static final int LENGHT = 8;
	 
	/*
	 * Precondizioni: fascia deve essere una stringa rappresentata come "int - int" (es:9-18)
	 *                eta deve essere un arrayList vuoto 
	 */
	public static void prendiEta (String fascia, ArrayList<Integer> eta) {
		int i=fascia.indexOf('-');
		int etaMin=Integer.parseInt(fascia.substring(0, i).trim());
		int etaMax=Integer.parseInt(fascia.substring(i+1).trim());
		eta.add(0, etaMin);
		eta.add(1, etaMax);
	}
	 
	public static boolean contenuto(ArrayList<String> stringhe, String nome){
		for(int i=0; i<stringhe.size(); i++){
			String ispezionato=stringhe.get(i);
			if(nome.equals(ispezionato)) {
				return true;
			}
		}
		return false;
	}
	
	public static ArrayList<Evento> mostraCategorie() {
		PartitaDiCalcio p=new PartitaDiCalcio();
		ArrayList<Evento> categorie=new ArrayList<>();
		categorie.add(p);
		for(int i=0; i<categorie.size(); i++) {
			categorie.get(i).mostra();
		}
		return categorie;
	}
	 
	public static String generatePass() {
	        StringBuilder sb = new StringBuilder(LENGHT);
	        for (int i = 0; i < LENGHT; i++) {
	            sb.append(ALPHABET.charAt(rnd.nextInt(ALPHABET.length())));
	        }
	        return sb.toString();
	    }
	
	public static void main(String [] args) {
		System.out.println(generatePass());
	}
}