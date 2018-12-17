package Versione1;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utente {

	private static final String USER = "User";
	private static int NUM;
	private String id;
	private String password;
	private char genere;
	private int eta;
	
	
	public Utente(String id, String password, char genere, int eta) {
		this.id = id;
		this.password = password;
		this.genere = genere;
		this.eta = eta;
	}

	public Utente(char genere, int eta) {
		NUM=ExcelReader.getNum();
		id=USER+""+(NUM++);
		password =Utility.generatePass();
		this.genere = genere;
		this.eta = eta;
	}
	
	public static Utente createUtente(char genere, int eta) {
		Utente u=new Utente(genere, eta);
		ExcelReader.addUser(u);
		return u;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public char getGenere() {
		return genere;
	}
	
	public void setGenere(char genere) {
		this.genere = genere;
	}
	
	public int getEta() {
		return eta;
	}
	
	public void setEta(int eta) {
		this.eta = eta;
	}
	
	public static void main(String[] args) {
		Utente u=createUtente('M', 18);
		Utente u1=createUtente('M', 34);
		Utente u2=createUtente('F', 54);
		ArrayList<String> ids=new ArrayList<>();
		ArrayList<String> pass=new ArrayList<>();
		ExcelReader.takeIds(ids);
		ExcelReader.takePasswords(pass);
		for(int i=0; i<ids.size(); i++) {
			System.out.print(ids.get(i)+" ");
			System.out.println(pass.get(i));
		}
	}
}
