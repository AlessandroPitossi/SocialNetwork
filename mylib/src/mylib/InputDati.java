package mylib;
import java.util.*;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;

public class InputDati 
{
	  private static Scanner lettore = creaScanner();
	  
	  private final static String ERRORE_FORMATO = "Attenzione: il dato inserito non e' nel formato corretto";
	  private final static String ERRORE_MINIMO= "Attenzione: e' richiesto un valore maggiore o uguale a ";
	  private final static String ERRORE_DATA_MIN= "Attenzione: la data inserita deve essere maggiore o uguale a ";
	  private final static String ERRORE_DATA_MAX= "Attenzione: la data inserita deve essere minore o uguale a ";
	  private final static String ERRORE_TEMPO_MIN= "Attenzione: il tempo inserito deve essere maggiore o uguale a ";
	  private final static String ERRORE_STRINGA_VUOTA= "Attenzione: non hai inserito alcun carattere";
	  private final static String ERRORE_STRINGA="Attenzione: inserire esattamente un carattere";
	  private final static String ERRORE_MASSIMO= "Attenzione: e' richiesto un valore minore o uguale a ";
	  private final static String MESSAGGIO_AMMISSIBILI= "Attenzione: i caratteri ammissibili sono: ";
	  private final static String MESSAGGIO_ORA="Inserisci l'ora:";
	  private final static String MESSAGGIO_MIN_FASCIA="Inserisci il minimo della Fascia d'età";
	  private final static String MESSAGGIO_MAX_FASCIA="Inserisci il massimo della Fascia d'età";
	  private final static String ERRORE_DURATA="Attenzione: la durata max è ";
	  private final static String MESSAGGIO_GIORNI="Inserisci il numero di giorni";
	  private final static int ORA_MIN=0;
	  private final static int ORA_MAX=23;
	  private final static String MESSAGGIO_MINUTI="Inserisci i minuti:";
	  private final static int MINUTI_MIN=0;
	  private final static int MINUTI_MAX=59;
	  private final static int SECONDI_MAX=59;
	  private final static Time DURATA_GIORNO=Time.valueOf(ORA_MAX+":"+MINUTI_MAX+":"+SECONDI_MAX);

	  private final static char RISPOSTA_SI='S';
	  private final static char RISPOSTA_NO='N';

	  
 
	  private static Scanner creaScanner ()
	  {
	   Scanner creato = new Scanner(System.in);
	   creato.useDelimiter(System.getProperty("line.separator"));
	   return creato;
	  }
	  
	  public static String leggiStringa (String messaggio)
	  {
		  System.out.print(messaggio);
		  return lettore.next();
	  }
	  
	  public static String leggiStringaNonVuota(String messaggio)
	  {
	   boolean finito=false;
	   String lettura = null;
	   do
	   {
		 lettura = leggiStringa(messaggio);
		 lettura = lettura.trim();
		 if (lettura.length() > 0)
		  finito=true;
		 else
		  System.out.println(ERRORE_STRINGA_VUOTA);
	   } while (!finito);
	   
	   return lettura;
	  }
	  
	  public static char leggiChar (String messaggio)
	  {
	   boolean finito = false;
	   char valoreLetto = '\0';
	   do
	    {
	     System.out.print(messaggio);
	     String lettura = lettore.next();
	     if (lettura.length() == 1)
	      {
	       valoreLetto = lettura.charAt(0);
	       finito = true;
	      }
	     else
	      {
	       System.out.println(ERRORE_STRINGA);
	      }
	    } while (!finito);
	   return valoreLetto;
	  }
	  
	  public static char leggiUpperChar (String messaggio, String ammissibili)
	  {
	   boolean finito = false;
	   char valoreLetto = '\0';
	   do
	   {
	    valoreLetto = leggiChar(messaggio);
	    valoreLetto = Character.toUpperCase(valoreLetto);
	    if (ammissibili.indexOf(valoreLetto) != -1)
		 finito  = true;
	    else
	     System.out.println(MESSAGGIO_AMMISSIBILI + ammissibili);
	   } while (!finito);
	   return valoreLetto;
	  }
	  
	  
	  public static int leggiIntero (String messaggio)
	  {
	   boolean finito = false;
	   int valoreLetto = 0;
	   do
	    {
	     System.out.print(messaggio);
	     try
	      {
	       valoreLetto = lettore.nextInt();
	       finito = true;
	      }
	     catch (InputMismatchException e)
	      {
	       System.out.println(ERRORE_FORMATO);
	       String daButtare = lettore.next();
	      }
	    } while (!finito);
	   return valoreLetto;
	  }

	  public static int leggiInteroPositivo(String messaggio)
	  {
		  return leggiInteroConMinimo(messaggio,1);
	  }
	  
	  public static int leggiInteroNonNegativo(String messaggio)
	  {
		  return leggiInteroConMinimo(messaggio,0);
	  }
	  
	  
	  public static int leggiInteroConMinimo(String messaggio, int minimo)
	  {
	   boolean finito = false;
	   int valoreLetto = 0;
	   do
	    {
	     valoreLetto = leggiIntero(messaggio);
	     if (valoreLetto >= minimo)
	      finito = true;
	     else
	      System.out.println(ERRORE_MINIMO + minimo);
	    } while (!finito);
	     
	   return valoreLetto;
	  }

	  public static int leggiIntero(String messaggio, int minimo, int massimo)
	  {
	   boolean finito = false;
	   int valoreLetto = 0;
	   do
	    {
	     valoreLetto = leggiIntero(messaggio);
	     if (valoreLetto >= minimo && valoreLetto<= massimo)
	      finito = true;
	     else
	      if (valoreLetto < minimo)
	         System.out.println(ERRORE_MINIMO + minimo);
	      else
	    	 System.out.println(ERRORE_MASSIMO + massimo); 
	    } while (!finito);
	     
	   return valoreLetto;
	  }
	  
	  public static Date leggiData (String messaggio) {
		  boolean finito = false;
		  Date d;
		  System.out.println(messaggio);
		  do {
		   String s;
	       d=null;
	       //si procura la data sotto forma di una stringa nel formato SHORT
	       System.out.println("Inserisci la data [gg/mm/yyyy]: ");
	       s = lettore.next();
	       //converte la stringa della data in un oggetto di classe Date
	       try{
	           DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
	           //imposta che i calcoli di conversione della data siano rigorosi       
	           formatoData.setLenient(false);           
	           d = formatoData.parse(s);
	           finito=true;
	       } catch (ParseException e) {
	           System.out.println("Formato data non valido.");
	           String daButtare = lettore.nextLine();
	           finito=false;
	       }
	       
		  } while(!finito);
	       //visualizza la data non formattata (sfruttando implicitamente il metodo toString dell'oggetto)
	      return d;
	  }
	  
	 
	  public static String leggiFasciaEta(String messaggio, int minFascia, int maxFascia) {
		  int min=leggiIntero(MESSAGGIO_MIN_FASCIA, minFascia, maxFascia);
		  int max=leggiIntero(MESSAGGIO_MAX_FASCIA, min, maxFascia);
		  return (min+"-"+max);
	  }
	  
	  public static Date leggiDataConMinimo (String messaggio, Date min) {
		  boolean finito = false;
		  Date d;
		  do {
			  d=leggiData(messaggio);
			  finito=true;
			  if(min.compareTo(d)>0) {
				  System.out.println(ERRORE_DATA_MIN+Utility.DateToString(min));
				  finito=false;
			  }
		  } while(!finito);
	      return d;
	  }
	  
	  public static Date leggiDataConMassimoMinimo (String messaggio, Date max, Date min) {
		  boolean finito = false;
		  Date d;
		  do {
			  d=leggiData(messaggio);
			  finito=true;
			  if(max.compareTo(d)<0) {
				  System.out.println(ERRORE_DATA_MAX+Utility.DateToString(max));
				  finito=false;
			  }
			  else {
				  if(min.compareTo(d)>0) {
					  System.out.println(ERRORE_DATA_MIN+Utility.DateToString(min));
					  finito=false;
				  }
			  }
		  } while(!finito);
	      return d;
	  }
	  
	  public static double leggiDouble (String messaggio)
	  {
	   boolean finito = false;
	   double valoreLetto = 0;
	   do
	    {
	     System.out.print(messaggio);
	     try
	      {
	       valoreLetto = lettore.nextDouble();
	       finito = true;
	      }
	     catch (InputMismatchException e)
	      {
	       System.out.println(ERRORE_FORMATO);
	       String daButtare = lettore.next();
	      }
	    } while (!finito);
	   return valoreLetto;
	  }
	 
	  public static double leggiDoubleConMinimo (String messaggio, double minimo)
	  {
	   boolean finito = false;
	   double valoreLetto = 0;
	   do
	    {
	     valoreLetto = leggiDouble(messaggio);
	     if (valoreLetto >= minimo)
	      finito = true;
	     else
	      System.out.println(ERRORE_MINIMO + minimo);
	    } while (!finito);
	     
	   return valoreLetto;
	  }

	  public static Time leggiTempo(String messaggio) {
		  boolean finito = false;
		  int ora;
		  int minuti;
		  Time t=null;
		   do
		    {
		     System.out.println(messaggio);
		     try
		      {
		       ora= leggiIntero(MESSAGGIO_ORA, ORA_MIN, ORA_MAX);
		       minuti= leggiIntero(MESSAGGIO_MINUTI, MINUTI_MIN, MINUTI_MAX);
		       t=Time.valueOf(ora+":"+minuti+":00");
		       finito = true;
		      }
		     catch (IllegalArgumentException e)
		      {
		       System.out.println(ERRORE_FORMATO);
		       String daButtare = lettore.next();
		       finito=false;
		      }
		    } while (!finito);
		   return t;
	  }
	  
	  public static Time leggiTempoConMinimo(String messaggio, Time min) {
		  boolean finito = false;
		  Time t;
		  do {
			  t=leggiTempo(messaggio);
			  finito=true;
			  if(min.compareTo(t)>0) {
				  System.out.println(ERRORE_TEMPO_MIN+t);
				  finito=false;
			  }
		  } while(!finito);
	      return t;
	  }
	  	  
	  public static Time leggiDurata(String messaggio, Time time) {
		  boolean finito = false;
		  int ora;
		  int minuti;
		  Time t=null;
		  int oraMax=Integer.parseInt(DURATA_GIORNO.toString().substring(0, 2))-Integer.parseInt(time.toString().substring(0, 2));
		  int minutiMax=Integer.parseInt(DURATA_GIORNO.toString().substring(3, 5))-Integer.parseInt(time.toString().substring(3, 5));
		  Time durataMax=Time.valueOf(oraMax+":"+minutiMax+":"+SECONDI_MAX);
		  do {
		     System.out.println(messaggio);
		     try
		      {
		       ora= leggiIntero(MESSAGGIO_ORA, ORA_MIN, ORA_MAX);
		       minuti= leggiIntero(MESSAGGIO_MINUTI, MINUTI_MIN, MINUTI_MAX);
		       t=Time.valueOf(ora+":"+minuti+":00");
		       if(t.compareTo(durataMax)>0) {
		    	   System.out.println(ERRORE_DURATA+durataMax);
		       }
		       else finito = true;
		      }
		     catch (IllegalArgumentException e)
		      {
		       System.out.println(ERRORE_FORMATO);
		       String daButtare = lettore.next();
		       finito=false;
		      }
		    } while (!finito);
	  return t;	
	  }
	  
	  public static int leggiDurataDay(String messaggio) {
		  int giorni;
		  System.out.println(messaggio);
		  giorni= leggiInteroPositivo(MESSAGGIO_GIORNI);
		 
		  return giorni; 
		 
		  
	  }
	  
	  public static boolean yesOrNo(String messaggio)
	  {
		  String mioMessaggio = messaggio + "("+RISPOSTA_SI+"/"+RISPOSTA_NO+")";
		  char valoreLetto = leggiUpperChar(mioMessaggio,String.valueOf(RISPOSTA_SI)+String.valueOf(RISPOSTA_NO));
		  
		  if (valoreLetto == RISPOSTA_SI)
			return true;
		  else
			return false;
	  }
}
