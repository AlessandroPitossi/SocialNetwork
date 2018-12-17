package Versione1;

import java.util.ArrayList;

public class PartitaDiCalcio extends Evento{

	private static final boolean OBBLIGATORIO=true;
	private static final String NOME="Partita di calcio";
	private static final String DESCRIZIONE="Organizza una partita di calcio insieme ai tuoi amici";
	private static final String GENERE="Genere";
	private static final String DESCR_GENERE="Il genere (maschile o femminile) dei giocatori";
	private static final String FASCIA_ETA="Fascia d'età";
	private static final String DESCR_FASCIA_ETA="Estremo inferiore e superiore di età ammissibile dei giocatori";
	private Campo genere=new Campo<Character>(GENERE, DESCR_GENERE, OBBLIGATORIO);
	private Campo fasciaEta=new Campo<String>(FASCIA_ETA, DESCR_FASCIA_ETA, OBBLIGATORIO);
	private int etaMin;
	private int etaMax;
	private ArrayList <Integer> eta=new ArrayList<>();
	
	public PartitaDiCalcio () {
		super(NOME, DESCRIZIONE);
		addCampo(genere);
		addCampo(fasciaEta);
	}
	
	private void calcEta() {
		String fascia=(String) fasciaEta.getValore();
		Utility.prendiEta(fascia, eta);
		setEtaMin(eta.get(0));
		setEtaMax(eta.get(1));
	}
	
	public void aggiornaEta(String valFascia) {
		setValCampo(getCampo(FASCIA_ETA), valFascia);
		calcEta();
	}

	public int getEtaMin() {
		return etaMin;
	}

	public void setEtaMin(int etaMin) {
		this.etaMin = etaMin;
	}

	public int getEtaMax() {
		return etaMax;
	}

	public void setEtaMax(int etaMax) {
		this.etaMax = etaMax;
	}
	
	
	public static void main(String[] args) {
		PartitaDiCalcio p=new PartitaDiCalcio();
		p.aggiornaEta("13-87");
		System.out.println(p.getEtaMin()+"  "+p.getEtaMax());
		p.mostra();
	}
}
