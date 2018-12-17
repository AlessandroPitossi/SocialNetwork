package Versione1;

import java.sql.*;
import java.util.ArrayList;

import mylib.BelleStringhe;

public class Evento {

	private static final boolean FACOLTATIVO=false;
	private static final boolean OBBLIGATORIO=true;
	private static final String TITOLO="Titolo";
	private static final String DESCR_TITOLO="Il titolo che vuoi dare al tuo evento";
	private static final String NUM_PART="Numero di partecipanti";
	private static final String DESCR_NUM_PART="Il numero di persone che possono partecipare all'evento";
	private static final String TERM_ULT_ISCR="Termine ultimo di iscrizione";
	private static final String DESCR_TERM_ULT_ISCR="Ultimo giorno utile per iscriversi all'evento";
	private static final String LUOGO="Luogo";
	private static final String DESCR_LUOGO="Indirizzo del luogo che ospiterà l'evento oppure, se l'evento è itinerante, il luogo di ritrovo";
	private static final String DATA="Data";
	private static final String DESCR_DATA="Data in cui si svolge l'evento o, nel caso l'evento duri più giorni, la data di inizio";
	private static final String ORA="Ora";
	private static final String DESCR_ORA="Orario di ritrovo";
	private static final String DURATA="Durata";
	private static final String DESCR_DURATA="Durata approssimativa in ore e minuti, per gli eventi che si esauriscono in un solo giorno, o in termini di numero esatto di giorni, per gli eventi che occupani più giorni";
	private static final String QUOTA="Quota individuale";
	private static final String DESCR_QUOTA="La spesa che ogni partecipante all'iniziativa dovrà sostenere";
	private static final String COMPRESO_QUOTA="Compreso nella quota";
	private static final String DESCR_COMPRESO_QUOTA="Tutte le voci di spesa che sono comprese nella \"Quota individuale\"";
	private static final String DATA_FIN="Data conclusiva";
	private static final String DESCR_DATA_FIN="Data in cui l'evento si conclude";
	private static final String ORA_FIN="Ora conclusiva";
	private static final String DESCR_ORA_FIN="Orario di conclusione dell'evento";
	private static final String NOTE="Note";
	private static final String DESCR_NOTE="Informazioni aggiuntive circa l'evento";
	private static final String CAMPI = "Campi:";
	private Campo titolo=new Campo<String>(TITOLO, DESCR_TITOLO, FACOLTATIVO);
	private Campo numPartecipanti=new Campo<Integer>(NUM_PART, DESCR_NUM_PART, OBBLIGATORIO);
	private Campo termIscr=new Campo<String>(TERM_ULT_ISCR, DESCR_TERM_ULT_ISCR, OBBLIGATORIO);
	private Campo luogo=new Campo<String>(LUOGO, DESCR_LUOGO, OBBLIGATORIO);
	private Campo dataIn=new Campo<Date>(DATA, DESCR_DATA, OBBLIGATORIO);
	private Campo oraIn=new Campo<Time>(ORA, DESCR_ORA, OBBLIGATORIO);
	private Campo durata=new Campo<Time>(DURATA, DESCR_DURATA, FACOLTATIVO);
	private Campo quota=new Campo<Double>(QUOTA, DESCR_QUOTA, OBBLIGATORIO);
	private Campo compresoQuota=new Campo<String>(COMPRESO_QUOTA, DESCR_COMPRESO_QUOTA, FACOLTATIVO);
	private Campo dataFin=new Campo<Date>(DATA_FIN, DESCR_DATA_FIN, FACOLTATIVO);
	private Campo oraFin=new Campo<Time>(ORA_FIN, DESCR_ORA_FIN, FACOLTATIVO);
	private Campo note=new Campo<String>(NOTE, DESCR_NOTE, FACOLTATIVO);

	private String nome; 
	private String descrizione;
	private ArrayList<Campo> campi=new ArrayList<>();
	
	public Evento(String nome, String descrizione) {
		this.nome = nome;
		this.descrizione = descrizione;
		addCampi();
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
	/*
	 * Precondizione: il nome del campo passato deve essere il nome di un campo esistente
	 */
	public Campo getCampo(String nome) {
		for(int i=0; i<campi.size(); i++) {
			if(campi.get(i).getNome()==nome) {
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
	 * Precondizioni: il campo passato deve essere un campo esistente
	 *                il valore passato deve essere del tipo corretto rispetto al campo passato
	 */
	public <T> void setValCampo(Campo c, T valore) {
		c.setValore(valore);
	}
}
