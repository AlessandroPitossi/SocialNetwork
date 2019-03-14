package mylib;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Vector;
/*
 * Calsse di metodi di utilità generale
 */
public class Utility {	

	
	private final static String JANUARY="JAN";
	private final static String	FEBRUARY="FEB";
	private final static String	MARCH="MAR";
	private final static String	APRIL="APR";
	private final static String	MAY="MAY";
	private final static String	JUNE="JUN";
	private final static String	JULY="JUL";
	private final static String	AUGUST="AUG";
	private final static String	SEPTEMBER="SEP";
	private final static String	OCTOBER="OCT";
	private final static String	NOVEMBER="NOV";
	private final static String	DECEMBER="DEC";
	private final static String WARNING="ATTENZIONE!!! ";
	private final static String FINITO="Premi 1 se vuoi terminare, 2 per ripetere l'operazione";
	private static final int DAY_FIELD=5;
	private static final int MILLIS_TO_DAYS=86400000;
	
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
	public static boolean accettabile(ArrayList<String> stringhe, String nome, String msgDoppione){
		for(int i=0; i<stringhe.size(); i++){
			String ispezionato=stringhe.get(i);
			if(nome.equals(ispezionato)) {
				System.out.println(msgDoppione);
				return false;
			}
		}
		return true;
	}
	
	public static String getSumTime(Time t1, Time t2) {
		int h1=Integer.parseInt(t1.toString().substring(0, 2));
		int m1=Integer.parseInt(t1.toString().substring(3, 5));
		int h2=Integer.parseInt(t2.toString().substring(0, 2));
		int m2=Integer.parseInt(t2.toString().substring(3, 5));
		int h;
		int m;
		if(m1+m2<60) {
			m=m1+m2;
			h=h1+h2;
		}
		else {
			m=m1+m2-60;
			h=h1+h2+1;
		}
		String s=String.format("%02d:%02d:00", h, m);
		return s;
	}
	
	
	public static Date getTodayDate() {
		Date today=null;
		try {
		Date d=new Date();
		DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY); 
        formatoData.setLenient(false); 
        String s=DateToString(d);
		today = formatoData.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return today;
	}	
	
	public static Date getTomorrowDate() {
		GregorianCalendar g=new GregorianCalendar();
		g.setTime(getTodayDate());
		return dateAfterDays(g,1);
	}
	
	//Precondizione:la data d1 deve essere<=alla data d2
	public static int getDifferenceDays(Date d1, Date d2) {
		Date date1=StringToDate(DateToString(d1));
		Date date2=StringToDate(DateToString(d2));
		long t2=date2.getTime();
		long t1=date1.getTime();
		long durataMilliseconds=t2-t1;
		long result=durataMilliseconds/MILLIS_TO_DAYS;
		return (int)result;
	}
	
	public static Date dateAfterDays(GregorianCalendar d, int giorni) {
		d.add(DAY_FIELD, giorni);
		return d.getTime();
	}
	
	//Precondizione:il tempo t1 deve essere maggiore del tempo t2
	public static String getDifferenceTime(Time t1, Time t2) {
		int h1=Integer.parseInt(t1.toString().substring(0, 2));
		int m1=Integer.parseInt(t1.toString().substring(3, 5));
		int h2=Integer.parseInt(t2.toString().substring(0, 2));
		int m2=Integer.parseInt(t2.toString().substring(3, 5));
		int h;
		int m;
		if(m1-m2>=0) {
			m=m1-m2;
			h=h1-h2;
		}
		else {
			m=60+m1-m2;
			h=h1-h2-1;
		}
		String s=String.format("%02d:%02d:00", h, m);
		return s;
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
	  
	  //La data deve essere passata nel formato gg/mm/aaa
	  public static Date StringToDate(String s) {
		DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
        formatoData.setLenient(false);           
        Date d=null;
		try {
			d = formatoData.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
         return d;
	  }
	
	  public static String DateToString(Date d) {
			String s="";
			s+=d.toString().substring(8, 10)+"/";
			String month=d.toString().substring(4, 7).toUpperCase();
			switch(month) {
				case(JANUARY): 	s+="01/";
								break;
				case(FEBRUARY): s+="02/";
								break;
				case(MARCH): 	s+="03/";
								break;
				case(APRIL): 	s+="04/";
								break;
				case(MAY): 		s+="05/";
								break;
				case(JUNE): 	s+="06/";
								break;
				case(JULY): 	s+="07/";
								break;
				case(AUGUST): 	s+="08/";
								break;
				case(SEPTEMBER):s+="09/";
								break;
				case(OCTOBER): 	s+="10/";
								break;
				case(NOVEMBER):	s+="11/";
								break;
				case(DECEMBER): s+="12/";
								break;
			}
			s+=d.toString().substring(24);
			return s;
		}
	  
	  public static void main(String[] args) {
		  Date d1=StringToDate("12/02/2020");
		  Date d2=StringToDate("15/02/2021");
		  System.out.println(d1.getTime());
		  System.out.println(d2.getTime());
		  System.out.println(getDifferenceDays(d1,d2));
	  }
}
