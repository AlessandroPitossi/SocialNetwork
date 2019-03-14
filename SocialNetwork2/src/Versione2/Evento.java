package Versione2;

import java.io.File;
import java.sql.Time;
import java.util.*;
import java.util.GregorianCalendar;

import mylib.BelleStringhe;
import mylib.InputDati;

public class Evento {

	private static final boolean FACOLTATIVO=false;
	private static final boolean OBBLIGATORIO=true;
	private static final String PART_ATT="Partecipanti attuali:";
	private static final String GIORNI=" giorni";
	private static final String ORE=" ore";
	public static final String TITOLO="Titolo";
	private static final String DESCR_TITOLO="Il titolo che vuoi dare al tuo evento";
	public static final String NUM_PART="Numero di partecipanti";
	private static final String DESCR_NUM_PART="Il numero di persone che possono partecipare all'evento";
	public static final String TERM_ULT_ISCR="Termine ultimo di iscrizione";
	private static final String DESCR_TERM_ULT_ISCR="Ultimo giorno utile per iscriversi all'evento";
	public static final String LUOGO="Luogo";
	private static final String DESCR_LUOGO="Indirizzo del luogo che ospiterà l'evento oppure, se l'evento è itinerante, il luogo di ritrovo";
	public static final String DATA="Data";
	private static final String DESCR_DATA="Data in cui si svolge l'evento o, nel caso l'evento duri più giorni, la data di inizio";
	public static final String ORA="Ora";
	private static final String DESCR_ORA="Orario di ritrovo";
	public static final String DURATA="Durata";
	private static final String DESCR_DURATA="Durata approssimativa in ore e minuti, per gli eventi che si esauriscono in un solo giorno, o in termini di numero esatto di giorni, per gli eventi che occupani più giorni";
	public static final String QUOTA="Quota individuale";
	private static final String DESCR_QUOTA="La spesa che ogni partecipante all'iniziativa dovrà sostenere";
	public static final String COMPRESO_QUOTA="Compreso nella quota";
	private static final String DESCR_COMPRESO_QUOTA="Tutte le voci di spesa che sono comprese nella \"Quota individuale\"";
	public static final String DATA_FIN="Data conclusiva";
	private static final String DESCR_DATA_FIN="Data in cui l'evento si conclude";
	public static final String ORA_FIN="Ora conclusiva";
	private static final String DESCR_ORA_FIN="Orario di conclusione dell'evento";
	public static final String NOTE="Note";
	private static final String DESCR_NOTE="Informazioni aggiuntive circa l'evento";
	private static final String CAMPI = "Campi:";
	private static final String MESSAGGIO_FACOLTATIVO="\nVuoi inserire il valore del campo ";
	public static final String MESSAGGIO_CAMPO="\nInserisci il valore del campo \"";
	public static final String FINE_MESSAGGIO_CAMPO="\": ";
	private final static String MESSAGGIO_DURATA="\nL'evento si conclude nell'arco di una giornata?";
	private static final String MESSAGGIO_EVENTO="\nVuoi pubblicare l'evento in bacheca?";
	private static final String EVENTO_PUBBLICATO="\nEvento pubblicato\n";
	private static final String EVENTO_NON_PUBBLICATO="\nEvento non pubblicato\n";
	private static final int EV_ROW=0;
	private static final int MIN_QUOTA=0;
	private static final int MIN_PARTECIPANTI=2;
	private static final String[] CAMPI_STR= {TITOLO, LUOGO, COMPRESO_QUOTA, NOTE};
	private static final String[] CAMPI_INT= {NUM_PART};
	private static final String[] CAMPI_DOUBLE= {QUOTA};
	private static final String[] CAMPI_DATE= {TERM_ULT_ISCR, DATA, DATA_FIN};
	private static final String[] CAMPI_TIME= {ORA, DURATA, ORA_FIN};
	private static final String FILE_EVENT="d:/Progetto Zanella/FileEventi/FileEventi.xlsx";
	private static final String FILE_UTENTI_EVENTI="d:/Progetto Zanella/FileEventi/FileUtentiEventi.xlsx";
	public static final int EVENT_NOME_ROW=0;
	public static final int EVENT_DESCRIZIONE_ROW=1;
	public static final int EVENT_STATO_ROW=2;
	public static final int EVENT_TITLE_ROW=3;
	public static final int EVENT_NUM_PART_ROW=4;
	public static final int EVENT_TERM_ISCR_ROW=5;
	public static final int EVENT_LUOGO_ROW=6;
	public static final int EVENT_DATA_IN_ROW=7;
	public static final int EVENT_ORA_IN_ROW=8;
	public static final int EVENT_DURATA_ROW=9;
	public static final int EVENT_QUOTA_ROW=10;
	public static final int EVENT_COMPRESO_QUOTA_ROW=11;
	public static final int EVENT_DATA_FIN_ROW=12;
	public static final int EVENT_ORA_FIN_ROW=13;
	public static final int EVENT_NOTE_ROW=14;
	public static File FILE_UT_EV=new File(FILE_UTENTI_EVENTI);
	public static File FILE_EV=new File(FILE_EVENT);
	private Campo <String> titolo=new Campo<>(TITOLO, DESCR_TITOLO, FACOLTATIVO);
	private Campo <Integer> numPartecipanti=new Campo<>(NUM_PART, DESCR_NUM_PART, OBBLIGATORIO);
	private Campo <Date> termIscr=new Campo<>(TERM_ULT_ISCR, DESCR_TERM_ULT_ISCR, OBBLIGATORIO);
	private Campo <String> luogo=new Campo<>(LUOGO, DESCR_LUOGO, OBBLIGATORIO);
	private Campo <Date> dataIn=new Campo<>(DATA, DESCR_DATA, OBBLIGATORIO);
	private Campo <Time> oraIn=new Campo<>(ORA, DESCR_ORA, OBBLIGATORIO);
	private Campo durata=new Campo<>(DURATA, DESCR_DURATA, FACOLTATIVO);
	private Campo <Double> quota=new Campo<>(QUOTA, DESCR_QUOTA, OBBLIGATORIO);
	private Campo <String> compresoQuota=new Campo<>(COMPRESO_QUOTA, DESCR_COMPRESO_QUOTA, FACOLTATIVO);
	private Campo <Date> dataFin=new Campo<>(DATA_FIN, DESCR_DATA_FIN, FACOLTATIVO);
	private Campo <Time> oraFin=new Campo<>(ORA_FIN, DESCR_ORA_FIN, FACOLTATIVO);
	private Campo <String> note=new Campo<>(NOTE, DESCR_NOTE, FACOLTATIVO);
	private int column;
	
	public enum Stato {
		APERTA,
		CHIUSA,
		CONCLUSA,
		FALLITA;
		}
	
	private Stato stato;

	private String nome; 
	private String descrizione;
	private ArrayList<Campo> campi=new ArrayList<>();
	
	public Evento(int column) {
		this.column=column;
		nome=ExcelReader.takeColumn(FILE_EV, column).get(EVENT_NOME_ROW);
		descrizione=ExcelReader.takeColumn(FILE_EV, column).get(EVENT_DESCRIZIONE_ROW);
		stato=Stato.valueOf(ExcelReader.takeColumn(FILE_EV, column).get(EVENT_STATO_ROW));
		addCampi();
		inizializzaCampiDaFile();
	}
	
	public Evento(String nome, String descrizione) {
		this.nome = nome;
		this.descrizione = descrizione;
		stato=Stato.APERTA;
		addCampi();
		column=ExcelReader.getNum(FILE_EV, EV_ROW);
	}
	
	public String mostraEvento() {
		//ArrayList<String> colonna=ExcelReader.takeColumn(FILE_EV, column);
		String s="";
		s+=nome+"\n"+descrizione+"\n\n";
		if(titolo.getValore()!=null) s+=TITOLO+": "+titolo.getValore()+"\n";
		s+=NUM_PART+": "+numPartecipanti.getValore()+"        "+PART_ATT+getPartAtt(column)+"\n";
		s+=LUOGO+": "+luogo.getValore()+"\n";
		s+=DATA+": "+mylib.Utility.DateToString(dataIn.getValore())+"\n";
		s+=TERM_ULT_ISCR+": "+mylib.Utility.DateToString(termIscr.getValore())+"\n";
		if(durata.getValore()!=null) {
			s+=DURATA+": "+durata.getValore().toString();
			if(durata.getValore().toString().indexOf(":")==-1) s+=GIORNI+"\n";
			else s+=ORE+"\n";
		}
		if(dataFin.getValore()!=null) {
			s+=DATA_FIN+": "+mylib.Utility.DateToString(dataFin.getValore())+"\n";
		}
		s+=ORA+": "+oraIn.getValore().toString()+"\n";
		if(oraFin.getValore()!=null) {
			s+=ORA_FIN+": "+oraFin.getValore().toString()+"\n";
		}
		s+=QUOTA+": "+quota.getValore()+"\n";
		if(compresoQuota.getValore()!=null) {
			s+=COMPRESO_QUOTA+": "+compresoQuota.getValore()+"\n";
		}
		if(note.getValore()!=null) {
			s+=NOTE+": "+note.getValore()+"\n";
		}
		return s;
	}
	
	private static int getPartAtt(int column) {
		return ExcelReader.takeColumn(FILE_UT_EV, column).size();
	}
	
	public void mostra() {
		System.out.println();
		System.out.println(nome.toUpperCase());
		System.out.println(descrizione+"\n");
		System.out.println(BelleStringhe.incornicia(CAMPI));
		for(int i=0; i<campi.size(); i++) {
			campi.get(i).mostra();
		}
		System.out.println();
	}

	private void inizializzaCampiDaFile() {
		ArrayList<String> colonna=ExcelReader.takeColumn(FILE_EV, column);
		if(!colonna.get(EVENT_TITLE_ROW).equals(ExcelReader.NO_VALUE)) setValCampo(TITOLO, colonna.get(EVENT_TITLE_ROW));
		setValCampo(NUM_PART, Integer.parseInt(colonna.get(EVENT_NUM_PART_ROW)));
		setValCampo(TERM_ULT_ISCR, mylib.Utility.StringToDate(colonna.get(EVENT_TERM_ISCR_ROW)));
		setValCampo(LUOGO, colonna.get(EVENT_LUOGO_ROW));
		setValCampo(DATA, mylib.Utility.StringToDate(colonna.get(EVENT_DATA_IN_ROW)));
		setValCampo(ORA, Time.valueOf(colonna.get(EVENT_ORA_IN_ROW)));
		if(!colonna.get(EVENT_DURATA_ROW).equals(ExcelReader.NO_VALUE)) {
			if(colonna.get(EVENT_DURATA_ROW).indexOf(":")==-1) {
				setValCampo(DURATA, Integer.parseInt(colonna.get(EVENT_DURATA_ROW)));
			}
			else setValCampo(DURATA, Time.valueOf(colonna.get(EVENT_DURATA_ROW)));
		}
		setValCampo(QUOTA, Double.parseDouble(colonna.get(EVENT_QUOTA_ROW)));
		if(!colonna.get(EVENT_COMPRESO_QUOTA_ROW).equals(ExcelReader.NO_VALUE))setValCampo(COMPRESO_QUOTA, colonna.get(EVENT_COMPRESO_QUOTA_ROW));
		if(!colonna.get(EVENT_DATA_FIN_ROW).equals(ExcelReader.NO_VALUE)) setValCampo(DATA_FIN, mylib.Utility.StringToDate(colonna.get(EVENT_DATA_FIN_ROW)));
		if(!colonna.get(EVENT_ORA_FIN_ROW).equals(ExcelReader.NO_VALUE)) setValCampo(ORA_FIN, Time.valueOf(colonna.get(EVENT_ORA_FIN_ROW)));
		if(!colonna.get(EVENT_NOTE_ROW).equals(ExcelReader.NO_VALUE)) setValCampo(NOTE, colonna.get(EVENT_NOTE_ROW));
	}
	
	private void addCampi() {
		addCampo(titolo);
		addCampo(numPartecipanti);
		addCampo(termIscr);
		addCampo(luogo);
		addCampo(dataIn);
		addCampo(oraIn);
		addCampo(durata);
		addCampo(quota);
		addCampo(compresoQuota);
		addCampo(dataFin);
		addCampo(oraFin);
		addCampo(note);
	}

	public int getColumn() {
		return column;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public ArrayList<Campo> getCampi(){
		return campi;
	}
	
	private void cambiaStato(Stato s) {
		stato=s;
		ExcelReader.addToCell(s.name(), FILE_EV, column, EVENT_STATO_ROW);
	}
	
	private static void inizializzaNumeroReale(Campo <Double> c, int min, String s) {
		double valore=InputDati.leggiDoubleConMinimo(s, min);
		c.setValore(valore);
	}
	
	private static void inizializzaNumeroIntero(Campo <Integer> c, int min, String s) {
		int valore=InputDati.leggiInteroConMinimo(s, min);
		c.setValore(valore);
	}
	
	private static void inizializzaStringa(Campo <String> c, String s) {
		String str=InputDati.leggiStringaNonVuota(s);
		c.setValore(str);
	}
	
	private static void inizializzaData(Campo <Date> c, String s) {
		Date d=InputDati.leggiData(s);
		c.setValore(d);
	}
	
	private static void inizializzaDataConMinimo(Campo <Date> c, String s, Date min) {
		Date d=InputDati.leggiDataConMinimo(s, min);
		c.setValore(d);
	}
	
	private static void inizializzaOra(Campo <Time> c, String s) {
		Time t=InputDati.leggiTempo(s);
		c.setValore(t);
	}
	
	private static void inizializzaOraConMinimo(Campo <Time> c, String s, Time min) {
		Time t=InputDati.leggiTempoConMinimo(s, min);
		c.setValore(t);
	}
	
	private static void inizializzaDurata(Campo  c, String s, Time t, boolean oneDay) {
		Time durata;
		int durataGiorni;
		if(oneDay) {
			durata=InputDati.leggiDurata(s, t);
			c.setValore(durata);
		}
		else {
			durataGiorni=InputDati.leggiDurataDay(s);
			c.setValore(durataGiorni);
		}
	}
	
	public void inizializza(Campo c) {
		Date tomorrow=mylib.Utility.getTomorrowDate();
		boolean ok=true;
		if(!c.isObbligatorio()) {
			if(c.getValore()!=null) {
				ok=false;
			}
			else{
				if(c.getValore()==null) ok=InputDati.yesOrNo(MESSAGGIO_FACOLTATIVO+c.getNome()+"?");
				else ok=false;
			}
		}
		if(ok) {
			if(Utility.contenuto(CAMPI_STR, c.getNome())) inizializzaStringa(c, MESSAGGIO_CAMPO+c.getNome()+FINE_MESSAGGIO_CAMPO);
			else if(Utility.contenuto(CAMPI_INT, c.getNome())) inizializzaNumeroIntero(c, MIN_PARTECIPANTI, MESSAGGIO_CAMPO+c.getNome()+FINE_MESSAGGIO_CAMPO);
			else if(Utility.contenuto(CAMPI_DOUBLE, c.getNome())) inizializzaNumeroReale(c, MIN_QUOTA, MESSAGGIO_CAMPO+c.getNome()+FINE_MESSAGGIO_CAMPO);
			else if(Utility.contenuto(CAMPI_DATE, c.getNome())) {
				if(c.getNome().equals(DATA_FIN)&&durata.getValore()==null) {
					inizializzaDataConMinimo(c, MESSAGGIO_CAMPO+c.getNome()+FINE_MESSAGGIO_CAMPO, dataIn.getValore());
					durata.setValore(mylib.Utility.getDifferenceDays(dataIn.getValore(), dataFin.getValore()));
				}
				else if(c.getNome().equals(DATA)) inizializzaDataConMinimo(c, MESSAGGIO_CAMPO+c.getNome()+FINE_MESSAGGIO_CAMPO, termIscr.getValore());
				else inizializzaDataConMinimo(c, MESSAGGIO_CAMPO+c.getNome()+FINE_MESSAGGIO_CAMPO, tomorrow);
			}
			else if(Utility.contenuto(CAMPI_TIME, c.getNome())) {
				if(c.getNome().equals(ORA_FIN)) {
						Date d=dataFin.getValore();
						if(d==null||d.compareTo(dataIn.getValore())==0) {
							inizializzaOraConMinimo(c, MESSAGGIO_CAMPO+c.getNome()+FINE_MESSAGGIO_CAMPO, oraIn.getValore());
							durata.setValore(Time.valueOf(mylib.Utility.getDifferenceTime(oraFin.getValore(), oraIn.getValore())));
						}
						else inizializzaOra(c, MESSAGGIO_CAMPO+c.getNome()+FINE_MESSAGGIO_CAMPO);		
				}
				else if(c.getNome().equals(ORA)) {
					inizializzaOra(c, MESSAGGIO_CAMPO+c.getNome()+FINE_MESSAGGIO_CAMPO);
				}
				else {
					boolean oneDay=InputDati.yesOrNo(MESSAGGIO_DURATA);
					inizializzaDurata(c, MESSAGGIO_CAMPO+c.getNome()+FINE_MESSAGGIO_CAMPO, oraIn.getValore(), oneDay);
					if(oneDay) {
						oraFin.setValore(Time.valueOf(mylib.Utility.getSumTime(oraIn.getValore(), (Time) durata.getValore())));
						dataFin.setValore(dataIn.getValore());
					}
					else {
						GregorianCalendar g=new GregorianCalendar();
						g.setTime(dataIn.getValore());
						dataFin.setValore(mylib.Utility.dateAfterDays(g, (int)durata.getValore()));
					}
				}
			}
		}
	}
	
	public void inizializzaEvento(Utente u) {
		for(int i=0; i<campi.size(); i++) {
			inizializza(campi.get(i));
		}
	}
	
	public void writeEvent() {
		File f=FILE_EV;
		ExcelReader.addToColumn(nome, f, column);
		ExcelReader.addToColumn(descrizione, f, column);
		ExcelReader.addToColumn(stato.toString(), f, column);
		if(titolo.getValore()!=null) ExcelReader.addToColumn(titolo.getValore(), f, column);
		else ExcelReader.addToColumn(null, f, column);
		ExcelReader.addToColumn(""+numPartecipanti.getValore(), f, column);
		ExcelReader.addToColumn(mylib.Utility.DateToString(termIscr.getValore()), f, column);
		ExcelReader.addToColumn(luogo.getValore(), f, column);
		ExcelReader.addToColumn(mylib.Utility.DateToString(dataIn.getValore()), f, column);
		ExcelReader.addToColumn(oraIn.getValore().toString(), f, column);
		if(durata.getValore()!=null) ExcelReader.addToColumn(durata.getValore().toString(), f, column);
		else ExcelReader.addToColumn(null, f, column);
		ExcelReader.addToColumn(""+quota.getValore(), f, column);
		if(compresoQuota.getValore()!=null) ExcelReader.addToColumn(compresoQuota.getValore(), f, column);
		else ExcelReader.addToColumn(null, f, column);
		if(dataFin.getValore()!=null) ExcelReader.addToColumn(mylib.Utility.DateToString(dataFin.getValore()), f, column);
		else ExcelReader.addToColumn(null, f, column);
		if(oraFin.getValore()!=null) ExcelReader.addToColumn(oraFin.getValore().toString(), f, column);
		else ExcelReader.addToColumn(null, f, column);
		if(note.getValore()!=null)ExcelReader.addToColumn(note.getValore(), f, column);
		else ExcelReader.addToColumn(null, f, column);
		ArrayList<Utente> utenti=getUtenti();
		for(int i=0; i<utenti.size(); i++) {
			ExcelReader.addToColumn(utenti.get(i).getId(), FILE_UT_EV, column);
		}
	}
	
	public static void createEvento(Evento e, Utente u) {
		e.inizializzaEvento(u);
		boolean sure=InputDati.yesOrNo(MESSAGGIO_EVENTO);
		if(sure) {
			e.writeEvent();
			e.addUtente(u);
			System.out.println("\n"+EVENTO_PUBBLICATO);
		}
		else System.out.println("\n"+EVENTO_NON_PUBBLICATO);
	}
	
	/*
	 * Precondizione: il nome del campo passato deve essere il nome di un campo esistente
	 */
	public Campo getCampo(String nome) {
		for(int i=0; i<campi.size(); i++) {
			if(campi.get(i).getNome().equals(nome)) {
				return campi.get(i);
			}
		}
		System.out.println("Campo inesistente");
		return null;
	}
	
	public void addCampo(Campo c) {
		campi.add(c);
	}
	
	/*
	 * Precondizioni: il nome del campo passato deve essere un campo esistente
	 *                il valore passato deve essere del tipo corretto rispetto al campo passato
	*/
	public <T> void setValCampo(String c, T valore) {
		getCampo(c).setValore(valore);
	}
	
	public Stato getStato() {
		return stato;
	}
	
	public int getNumPart() {
		String num=ExcelReader.takeColumn(FILE_EV, column).get(EVENT_NUM_PART_ROW);
		return Integer.parseInt(num);
	}
	
	public void modificaStato() {
		if(stato.equals(Stato.APERTA)) {
			ArrayList<Utente> utenti=getUtenti();
			if(utenti.size()==getNumPart()) {
				cambiaStato(Stato.CHIUSA);
				ExcelReader.writeNotify(this);
			}
			else {
				if(mylib.Utility.getTodayDate().compareTo(termIscr.getValore())>=0){
					cambiaStato(Stato.FALLITA);
					ExcelReader.writeNotify(this);
				}
			}
		}
		else {
			if(stato.equals(Stato.CHIUSA)&&mylib.Utility.getTodayDate().after(dataFin.getValore())){
				cambiaStato(Stato.CONCLUSA);
			}
		}
		
	}
	
	/*
	private void notificaUtenti() {
		Notifica.notifica(this);
	}
	*/
	
	public void addUtente(Utente u) {
		ExcelReader.addToColumn(u.getId(), FILE_UT_EV, column);
	}
	
	public ArrayList<Utente> getUtenti() {
		ArrayList<String> strUtenti=ExcelReader.takeColumn(FILE_UT_EV, column);
		ArrayList<Utente> utenti=new ArrayList<>();
		for(int i=0; i<strUtenti.size(); i++) {
			utenti.add(ExcelReader.takeUser(strUtenti.get(i)));
		}
		return utenti;
	}

	public static void main(String[] args) {
	/*	Utente u=Utente.createUtente('M', 18);
		Utente u1=Utente.createUtente('M', 34);
		Utente u2=Utente.createUtente('F', 54);
		ArrayList<String> ids=new ArrayList<>();
		ArrayList<String> pass=new ArrayList<>();
		ExcelReader.takeIds(ids);
		ExcelReader.takePasswords(pass);
		for(int i=0; i<ids.size(); i++) {
			System.out.print(ids.get(i)+" ");
			System.out.println(pass.get(i));
		}
		Evento e=new Evento("Calcio", "Divertente");
		Date d=new Date(100000000);
		e.dataIn.setValore(d);
		e.luogo.setValore("Poggibonzi");
		e.quota.setValore(178.00);
		e.addUtente(u);
		e.addUtente(u2);
		ExcelReader.writeNoify(e);
		
		s+=colonna.get(EVENT_NOME_ROW)+"\n"+colonna.get(EVENT_DESCRIZIONE_ROW)+"\n\n";
		if(!colonna.get(EVENT_TITLE_ROW).equals(ExcelReader.NO_VALUE)) s+=TITOLO+": "+colonna.get(EVENT_TITLE_ROW)+"\n";
		s+=NUM_PART+": "+colonna.get(EVENT_NUM_PART_ROW)+"        "+PART_ATT+getPartAtt(column)+"\n";
		s+=LUOGO+": "+colonna.get(EVENT_LUOGO_ROW)+"\n";
		s+=DATA+": "+colonna.get(EVENT_DATA_IN_ROW)+"\n";
		s+=TERM_ULT_ISCR+": "+colonna.get(EVENT_TERM_ISCR_ROW)+"\n";
		if(!colonna.get(EVENT_DURATA_ROW).equals(ExcelReader.NO_VALUE)) {
			s+=DURATA+": "+colonna.get(EVENT_DURATA_ROW);
			if(colonna.get(EVENT_DURATA_ROW).indexOf(":")==-1) s+=GIORNI+"\n";
			else s+=ORE+"\n";
		}
		if(!colonna.get(EVENT_DATA_FIN_ROW).equals(ExcelReader.NO_VALUE)) {
			s+=DATA_FIN+": "+colonna.get(EVENT_DATA_FIN_ROW)+"\n";
		}
		s+=ORA+": "+colonna.get(EVENT_ORA_IN_ROW)+"\n";
		if(!colonna.get(EVENT_ORA_FIN_ROW).equals(ExcelReader.NO_VALUE)) {
			s+=ORA_FIN+": "+colonna.get(EVENT_ORA_FIN_ROW)+"\n";
		}
		s+=QUOTA+": "+colonna.get(EVENT_QUOTA_ROW)+"\n";
		if(!colonna.get(EVENT_COMPRESO_QUOTA_ROW).equals(ExcelReader.NO_VALUE)) {
			s+=COMPRESO_QUOTA+": "+colonna.get(EVENT_COMPRESO_QUOTA_ROW)+"\n";
		}
		if(!colonna.get(EVENT_NOTE_ROW).equals(ExcelReader.NO_VALUE)) {
			s+=NOTE+": "+colonna.get(EVENT_NOTE_ROW)+"\n";
		
		*
		*
		*/
		
		PartitaDiCalcio p=new PartitaDiCalcio(0);
		System.out.println(p.mostraEvento());
	}
}
