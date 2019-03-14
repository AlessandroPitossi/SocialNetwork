package Versione2;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utente {

	private static final String USER = "User";
	private static final int USER_ROW= 0;
	private static int NUM;
	private String id;
	private String password;
	private char genere;
	private int eta;
	private int column;
	private String fileName;
	
	public Utente(String id, String password, char genere, int eta, int column) {
		this.id = id;
		this.password = password;
		this.genere = genere;
		this.eta = eta;
		this.column = column;
	}
	
	public Utente(String id, String password, char genere, int eta, int column, String fileName) {
		this.id = id;
		this.password = password;
		this.genere = genere;
		this.eta = eta;
		this.column = column;
		this.fileName=fileName;
	}

	public Utente(char genere, int eta) {
		NUM=ExcelReader.getNum(ExcelReader.FILE, USER_ROW);
		column=NUM;
		id=USER+""+(NUM++);
		password =Utility.generatePass();
		ExcelReader.creaFile(this);
		this.genere = genere;
		this.eta = eta;
	}
	
	public static Utente createUtente(char genere, int eta) {
		Utente u=new Utente(genere, eta);
		ExcelReader.addUser(u);
		return u;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setColumn(int i) {
		column=i;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String s) {
		fileName=s;
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
}
