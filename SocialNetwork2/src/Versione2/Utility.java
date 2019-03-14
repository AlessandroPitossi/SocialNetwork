package Versione2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import mylib.InputDati;

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
	
	public static boolean contenuto(String[] stringhe, String nome){
		for(int i=0; i<stringhe.length; i++){
			String ispezionato=stringhe[i];
			if(nome.equals(ispezionato)) {
				return true;
			}
		}
		return false;
	}
	
	public static ArrayList<Evento> mostraCategorie(Evento p) {
		//PartitaDiCalcio p=new PartitaDiCalcio();
		ArrayList<Evento> categorie=new ArrayList<>();
		categorie.add(p);
		for(int i=0; i<categorie.size(); i++) {
			categorie.get(i).mostra();
		}
		return categorie;
	}
	
	public static boolean isEmpty(Cell c) {
		try {
			if(c.getStringCellValue().equals("")) return true;
		} catch(Exception e) {
			if((""+c.getNumericCellValue()).equals("")) return true;
		}
		return false;
	}
	
	public static Evento getEvento(String nome, int column) {
		if(nome.equals(PartitaDiCalcio.NOME)) return new PartitaDiCalcio(column);
		return null;
	}

	public static String generatePass() {
	        StringBuilder sb = new StringBuilder(LENGHT);
	        for (int i = 0; i < LENGHT; i++) {
	            sb.append(ALPHABET.charAt(rnd.nextInt(ALPHABET.length())));
	        }
	        return sb.toString();
	    }
	
	public static void main(String[] args) {
		GregorianCalendar g=new GregorianCalendar();
		
	}
}
