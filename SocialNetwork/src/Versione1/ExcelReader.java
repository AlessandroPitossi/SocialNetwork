package Versione1;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader{
	
	private static final int USER_ROW=0;
	private static final int PASS_ROW=1;
	private static final int GENDER_ROW=2;
	private static final int AGE_ROW=3;
	private static final String FILE_USER_PASSWORDS="prova.xml.xlsx";
	private static File FILE=new File(FILE_USER_PASSWORDS);
	
	private static void takeRowFromFile(ArrayList <String> things, File f, int row) {
		FileInputStream excelFile=null;
		Workbook workbook=null;
		 try {
			 excelFile= new FileInputStream(f);
			 workbook = new XSSFWorkbook(excelFile);
		     Sheet datatypeSheet = workbook.getSheetAt(0);
		     Row currentRow = datatypeSheet.getRow(row);
		     if(currentRow!=null) {
		    	 Iterator<Cell> cellIterator = currentRow.cellIterator();
		    	 while (cellIterator.hasNext()) {
			          Cell currentCell =cellIterator.next();
			          things.add(currentCell.getStringCellValue());
			     }
		     }
		 	 }catch (FileNotFoundException ex) {
		 		  System.out.println(ex.getMessage());
		     }catch (IOException ex) {
		          System.out.println(ex.getMessage());
		     }	
		 finally {
			 chiudiSicuro(excelFile);
			 chiudiSicuro(workbook);
		 }
	}
	
	private static void takeIntRowFromFile(ArrayList <Integer> things, File f, int row) {
		FileInputStream excelFile=null;
		Workbook workbook=null;
		 try {
			 excelFile= new FileInputStream(f);
			 workbook = new XSSFWorkbook(excelFile);
		     Sheet datatypeSheet = workbook.getSheetAt(0);
		     Row currentRow = datatypeSheet.getRow(row);
		     if(currentRow!=null) {
		    	 Iterator<Cell> cellIterator = currentRow.cellIterator();
		    	 while (cellIterator.hasNext()) {
			          Cell currentCell =cellIterator.next();
			          things.add((int) currentCell.getNumericCellValue());
			     }
		     }
		 	 }catch (FileNotFoundException ex) {
		 		  System.out.println(ex.getMessage());
		     }catch (IOException ex) {
		          System.out.println(ex.getMessage());
		     }	
		 finally {
			 chiudiSicuro(excelFile);
			 chiudiSicuro(workbook);
		 }
	}
	
	public static void takeIds(ArrayList <String> ids) {
		takeRowFromFile(ids, FILE, USER_ROW);
	}
	
	public static void takePasswords(ArrayList <String> passwords) {
		takeRowFromFile(passwords, FILE, PASS_ROW);
	}
	
	public static void takeGenders(ArrayList <String> genders) {
		takeRowFromFile(genders, FILE, GENDER_ROW);
	}
	
	public static void takeAges(ArrayList <Integer> ages) {
		takeIntRowFromFile(ages, FILE, AGE_ROW);
	}
	
	public static Utente takeUser(String id) {
		ArrayList<String> ids=new ArrayList<>();
		ArrayList<String> passwords=new ArrayList<>();
		ArrayList<String> genders=new ArrayList<>();
		ArrayList<Integer> ages=new ArrayList<>();
		takeIds(ids);
		takePasswords(passwords);
		takeGenders(genders);
		takeAges(ages);
		int i=ids.indexOf(id);
		return new Utente(id, passwords.get(i), genders.get(i).charAt(0), ages.get(i));
	}
	
	
	private static void addToRow(String thing, File f, int row)  {
		FileInputStream file=null;
		XSSFWorkbook workbook=null;
		FileOutputStream outFile=null;
		 try {
	            file = new FileInputStream(FILE);
	            workbook = new XSSFWorkbook(file);
	            XSSFSheet sheet = workbook.getSheetAt(0);

	            //Update the value of cell
	            XSSFRow currentRow = sheet.getRow(row);
	            if(currentRow==null) currentRow=sheet.createRow(row);
	            
	            int l=currentRow.getLastCellNum();
	            if(l==-1) l=0;
	            currentRow.createCell(l).setCellValue(thing);

	            file.close();
	            
	            outFile =new FileOutputStream(FILE);
	            workbook.write(outFile);
	            outFile.close();

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 finally {
			chiudiSicuro(file);
			chiudiSicuro(workbook);
			chiudiSicuro(outFile);
		 }
	}
	

	private static void addToRow(int thing, File f, int row)  {
		FileInputStream file=null;
		XSSFWorkbook workbook=null;
		FileOutputStream outFile=null;
		 try {
	            file = new FileInputStream(FILE);
	            workbook = new XSSFWorkbook(file);
	            XSSFSheet sheet = workbook.getSheetAt(0);

	            //Update the value of cell
	            XSSFRow currentRow = sheet.getRow(row);
	            if(currentRow==null) currentRow=sheet.createRow(row);
	            
	            int l=currentRow.getLastCellNum();
	            if(l==-1) l=0;
	            currentRow.createCell(l).setCellValue(thing);

	            file.close();
	            
	            outFile =new FileOutputStream(FILE);
	            workbook.write(outFile);
	            outFile.close();

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 finally {
			chiudiSicuro(file);
			chiudiSicuro(workbook);
			chiudiSicuro(outFile);
		 }
	}
	
	public static void addUser(Utente u)  {
		ExcelReader.addToRow(u.getId(), FILE, USER_ROW);
		ExcelReader.addToRow(u.getPassword(), FILE, PASS_ROW);
		ExcelReader.addToRow(""+u.getGenere(), FILE, GENDER_ROW);
		ExcelReader.addToRow(u.getEta(), FILE, AGE_ROW);
		
	}
	
	public static int getNum() {
	XSSFWorkbook workbook=null;
	FileInputStream file=null;
	int l=-1;
	try {
		file = new FileInputStream(FILE);
        workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow currentRow;
        currentRow = sheet.getRow(USER_ROW);
        //Update the value of cell
        if(currentRow==null) currentRow=sheet.createRow(USER_ROW);
        l=currentRow.getLastCellNum();
        if(l==-1) l=0;
	  } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
	 finally {
		chiudiSicuro(file);
		chiudiSicuro(workbook);
	 }
	return l;
	}
	
	
	
	
	
	public static void chiudiSicuro(OutputStream is) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
 
	public static void chiudiSicuro(InputStream is) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void chiudiSicuro(Workbook is) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/*
	 * metti user su riga e pass su altra riga, itera su entrambi e mettili in arrayList
	 */
	public static void main(String[] args) {
      /*  try {
            String file = "test.xlsx";
            FileInputStream excelFile = new FileInputStream(new File(file));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator iterator = datatypeSheet.iterator();
            while (iterator.hasNext()) {
                Row currentRow = (Row) iterator.next();
                Iterator cellIterator = currentRow.iterator();
                while (cellIterator.hasNext()) {
                    Cell currentCell = (Cell) cellIterator.next();
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        System.out.print(currentCell.getStringCellValue() + "--");
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        System.out.print(currentCell.getNumericCellValue() + "--");
                    }
                }
                System.out.println();
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        */
		Utente u=new Utente('M', 18);
		Utente u1=new Utente('F', 21);
		Utente u2=new Utente('M', 56);
	//	addUser(u);
		//addUser(u1);
		//addUser(u2);
		ArrayList<String> ids=new ArrayList<>();
		ArrayList<String> pass=new ArrayList<>();
		takeIds(ids);
		takePasswords(pass);
		for(int i=0; i<ids.size(); i++) {
			System.out.print(ids.get(i)+" ");
			System.out.println(pass.get(i));
		}
    }
}