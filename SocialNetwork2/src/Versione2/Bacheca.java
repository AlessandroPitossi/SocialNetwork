package Versione2;

import java.io.File;
import java.util.ArrayList;

public class Bacheca {
	
	private static final File FILE_EVENTI=Evento.FILE_EV;
	private static final String STATO_APERTA="APERTA";
	private static final String STATO_CHIUSA="CHIUSA";
	private static final String STATO_CONCLUSA="CONCLUSA";
	private static final String STATO_FALLITA="FALLITA";
	
	public Bacheca() {
		
	}
	
	public static ArrayList<String> mostraEventi(ArrayList<Integer> colonne) {
		ArrayList <String> result=new ArrayList<>();
		ArrayList<String> stati=new ArrayList<>();
		ArrayList<String> stringheEv=new ArrayList<>();
		ExcelReader.takeRowFromFile(stati, FILE_EVENTI, Evento.EVENT_STATO_ROW);
		for(int i=0; i<stati.size(); i++) {
			if(stati.get(i).equals(STATO_APERTA)) {
				colonne.add(i);
				stringheEv=ExcelReader.takeColumn(FILE_EVENTI, i);
				String nome=stringheEv.get(Evento.EVENT_NOME_ROW);
				String descr=stringheEv.get(Evento.EVENT_DESCRIZIONE_ROW);
				Evento e=Utility.getEvento(nome, i);
				result.add(e.mostraEvento());
			}
		}
		return result;
	}
	
	
	
	public static void main(String[] args) {
		//mostraEventi();
	}
}
