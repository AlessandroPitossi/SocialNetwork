package mylib;

import java.util.ArrayList;
import java.util.Vector;

/*
Questa classe rappresenta un menu testuale generico a piu' voci
Si suppone che la voce per uscire sia sempre associata alla scelta 0 
e sia presentata in fondo al menu

@autor I docenti di informatica dell'UNIBS
*/
public class MyMenu
{
  final private static String CORNICE = "-------------------------------------------------";
  final private static String VOCE_USCITA = "0\tEsci";
  final private static String RICHIESTA_INSERIMENTO = "Digita il numero dell'opzione desiderata > ";

  private String titolo;
  private ArrayList <String> voci=new ArrayList<>();

	
  public MyMenu (String titolo, ArrayList <String> voci)
  {
	this.titolo = titolo;
	this.voci = voci;
  }

  
  public MyMenu(String titolo2, String[] voci2) {
	  this.titolo = titolo2;
		for(int i=0; i<voci2.length; i++) {
			voci.add(voci2[i]);
		}
}

public int scegli ()
  {
	stampaMenu();
	return InputDati.leggiIntero(RICHIESTA_INSERIMENTO, 0, voci.size());	 
  }

public int scegli (String s)
{
	stampaMenu();
	return InputDati.leggiIntero(s, 0, voci.size());	 
}
		
  public void stampaMenu ()
  {
	System.out.println(CORNICE);
	System.out.println(BelleStringhe.centrata(titolo, CORNICE.length()));
	System.out.println(CORNICE);
    for (int i=0; i<voci.size(); i++)
	 {
	  System.out.println( (i+1) + ".\t" + voci.get(i));
	 }
    System.out.println();
	System.out.println(VOCE_USCITA);
    System.out.println();
  }
  
  public int scegliSenzaEsci ()
  {
	stampaMenuSenzaEsci();
	return InputDati.leggiIntero(RICHIESTA_INSERIMENTO, 1, voci.size());	 
  }
  
  public int scegliSenzaEsci (String s)
  {
	stampaMenuSenzaEsci();
	return InputDati.leggiIntero(s, 1, voci.size());	 
  }
		
  
  public void stampaMenuSenzaEsci ()
  {
	System.out.println(CORNICE);
	System.out.println(BelleStringhe.centrata(titolo, CORNICE.length()));
	System.out.println(CORNICE);
    for (int i=0; i<voci.size(); i++)
	 {
	  System.out.println( (i+1) + ".\t" + voci.get(i));
	 }
  }
		
  public static void main(String [] args) {
	  String titolo="Scegli l'evento di cui creare l'evento, ma proprio un evento bello";
	  String [] voci= {"V1", "vbdusi"};
	  MyMenu m=new MyMenu(titolo, voci);
	  m.stampaMenu();
  }
}

