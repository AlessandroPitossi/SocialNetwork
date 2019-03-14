package Versione2;

import java.io.*;
import java.sql.Date;
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
	private static final int FILE_ROW=4;
	private static final int AGE_ROW=3;
	private static final int DA_LEGGERE_COLUMN=0;
	private static final int TITLE_COLUMN=1;
	private static final int DESCRIPTION_COLUMN=2;
	public static final String NO_VALUE="/";
	private static final String DA_LEGGERE="[NEW] ";
	private static final String YES="Y";
	private static final String NO="N";
	private static final String FILE_USER_PASSWORDS="d:/Progetto Zanella/prova.xml.xlsx";
	private static final String FILE_USER_PATH="d:/Progetto Zanella/FileUtenti/";
	private static final String FILE_EXTENSION=".xlsx";
	public static File FILE=new File(FILE_USER_PASSWORDS);
	
	public static void takeRowFromFile(ArrayList <String> things, File f, int row) {
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
	
	public static void takeFileNames(ArrayList<String> filenames) {
		takeRowFromFile(filenames, FILE, FILE_ROW);
	}
	
	public static Utente takeUser(String id) {
		ArrayList<String> ids=new ArrayList<>();
		ArrayList<String> passwords=new ArrayList<>();
		ArrayList<String> genders=new ArrayList<>();
		ArrayList<Integer> ages=new ArrayList<>();
		ArrayList<String> filenames=new ArrayList<>();
		takeIds(ids);
		takePasswords(passwords);
		takeGenders(genders);
		takeAges(ages);
		int i=ids.indexOf(id);
		String filename;
		try{
			filename=takeColumn(FILE, i).get(FILE_ROW);
		}
		catch(Exception e) {
				Utente u=new Utente(id, passwords.get(i), genders.get(i).charAt(0), ages.get(i), i);
				creaFile(u);
				return u;
		}
		return new Utente(id, passwords.get(i), genders.get(i).charAt(0), ages.get(i), i, filename);
	}
	
	
	
	private static void addToRow(String thing, File f, int row)  {
		FileInputStream file=null;
		XSSFWorkbook workbook=null;
		FileOutputStream outFile=null;
		 try {
	            file = new FileInputStream(f);
	            workbook = new XSSFWorkbook(file);
	            XSSFSheet sheet = workbook.getSheetAt(0);

	            //Update the value of cell
	            XSSFRow currentRow = sheet.getRow(row);
	            if(currentRow==null) currentRow=sheet.createRow(row);
	            
	            int l=currentRow.getLastCellNum();
	            if(l==-1) l=0;
	            currentRow.createCell(l).setCellValue(thing);
	            
	            outFile =new FileOutputStream(f);
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
	            file = new FileInputStream(f);
	            workbook = new XSSFWorkbook(file);
	            XSSFSheet sheet = workbook.getSheetAt(0);

	            //Update the value of cell
	            XSSFRow currentRow = sheet.getRow(row);
	            if(currentRow==null) currentRow=sheet.createRow(row);
	            
	            int l=currentRow.getLastCellNum();
	            if(l==-1) l=0;
	            currentRow.createCell(l).setCellValue(thing);
	            
	            outFile =new FileOutputStream(f);
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
	
	public static void creaFileXl(String fileName) {
		try {
			XSSFWorkbook workbook=new XSSFWorkbook();
			FileOutputStream out=new FileOutputStream(new File(fileName));
			XSSFSheet sheet=workbook.createSheet();
			workbook.write(out);
			out.close();
			}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> takeColumn(File f, int column){ 
		ArrayList<String> columndata = new ArrayList<>(); 
		FileInputStream file=null;
		XSSFWorkbook workbook=null;
		FileOutputStream outFile=null;
		try {
			file = new FileInputStream(f); 
		    workbook = new XSSFWorkbook(file); 
			XSSFSheet sheet = workbook.getSheetAt(0); 
			Iterator<Row> rowIterator = sheet.iterator(); 
			while (rowIterator.hasNext()) { 
				Row row = rowIterator.next(); 
				Iterator<Cell> cellIterator = row.cellIterator(); 
				while (cellIterator.hasNext()) { 
					Cell cell = cellIterator.next(); 
					if(row.getRowNum() >= 0){ 
						if(cell.getColumnIndex() == column){
							try {
								columndata.add(cell.getStringCellValue());			
							}
							catch(Exception e) {
								columndata.add(""+cell.getNumericCellValue());			
							}
							} 
						} 
					} 
				}
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
		finally {
			chiudiSicuro(file);
			chiudiSicuro(workbook);
			chiudiSicuro(outFile);
		 }
	 return columndata; 
	}
	
	public static void writeNotify(Evento e) {
		Notifica n=new Notifica(e);
		ArrayList<Utente> utenti=e.getUtenti();
		for(int i=0; i<utenti.size(); i++) {
			Utente u=utenti.get(i);
			if(u.getFileName()==null) {
				creaFile(u);
			}
			String fileName=u.getFileName();
			addToColumn(YES, new File(fileName), DA_LEGGERE_COLUMN);
			addToColumn(n.getTitolo(), new File(fileName), TITLE_COLUMN);
			addToColumn(n.getDescrizione(), new File(fileName), DESCRIPTION_COLUMN);
		}
	}
	
	public static void creaFile(Utente u) {
		String fileName=FILE_USER_PATH+u.getId()+FILE_EXTENSION;
		u.setFileName(fileName);
		creaFileXl(fileName);
		addToCell(fileName, FILE, u.getColumn(), FILE_ROW);
	}
	
	public static void addToCell(String s, File f, int column, int row) {
		 FileInputStream file=null;
		 XSSFWorkbook workbook=null;
		 FileOutputStream outFile=null;
		 Row currentRow = null;
		 try {
	            file = new FileInputStream(f);
	            workbook = new XSSFWorkbook(file);
	            XSSFSheet sheet = workbook.getSheetAt(0);

	            //Update the value of cell
	            Iterator<Row> rowIterator = sheet.iterator();
	            int i=0;
	            while (i<=row) {
	            	if(rowIterator.hasNext()) currentRow = rowIterator.next(); 
	            	else currentRow=sheet.createRow(i);
					i++;
	            }
	            if(currentRow==null) currentRow=sheet.createRow(i);
	            currentRow.createCell(column).setCellValue(s);	            
	            outFile =new FileOutputStream(f);
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
	
	public static void NotifyRead(Notifica n, int row, Utente u) {
		n.setNuova(false);
		addToCell(NO, new File(u.getFileName()), DA_LEGGERE_COLUMN, row);
	}
	
	public static void addToColumn(String s, File f, int column)  {
		 FileInputStream file=null;
		 XSSFWorkbook workbook=null;
		 FileOutputStream outFile=null;
		 Row currentRow = null;
		 try {
	            file = new FileInputStream(f);
	            workbook = new XSSFWorkbook(file);
	            XSSFSheet sheet = workbook.getSheetAt(0);

	            //Update the value of cell
	            Iterator<Row> rowIterator = sheet.iterator();
	            int i=0;
	            Cell cell=null;
	            while (rowIterator.hasNext()) { 
					currentRow = rowIterator.next(); 
					i++;
					cell=currentRow.getCell(column);
					if(cell==null||Utility.isEmpty(cell)) break; 
					
	            }
	            if(!rowIterator.hasNext()&&cell!=null&&!Utility.isEmpty(cell)) currentRow=sheet.createRow(i);
	            else {
	            	currentRow=sheet.getRow(i-1);
	            	if (currentRow==null) currentRow=sheet.createRow(i); 
	            }
	            if(s==null) currentRow.createCell(column).setCellValue(NO_VALUE);
	            else currentRow.createCell(column).setCellValue(s);	            
	            outFile =new FileOutputStream(f);
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
	
	public static void removeRow(File f, int row) {
		 FileInputStream file=null;
		 XSSFWorkbook workbook=null;
		 FileOutputStream outFile=null;
		 try {

	            file = new FileInputStream(f);
	            workbook = new XSSFWorkbook(file);
	            XSSFSheet sheet = workbook.getSheetAt(0);
	            int lastRowNum=sheet.getLastRowNum();
	            if(row>=0&&row<lastRowNum){
	            	sheet.shiftRows(row+1,lastRowNum, -1);
	            }
	            if(row==lastRowNum){
	            	Row removingRow=sheet.getRow(row);
	            	if(removingRow!=null){
	            		sheet.removeRow(removingRow);
	            	}
	            }
	            outFile =new FileOutputStream(f);
	            workbook.write(outFile);
	            outFile.close();
		 }catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
	}
	
	public static void addToColumn(int s, File f, int column)  {
		 FileInputStream file=null;
		 XSSFWorkbook workbook=null;
		 FileOutputStream outFile=null;
		 Row currentRow = null;
		 try {
	            file = new FileInputStream(f);
	            workbook = new XSSFWorkbook(file);
	            XSSFSheet sheet = workbook.getSheetAt(0);

	            //Update the value of cell
	            Iterator<Row> rowIterator = sheet.iterator();
	            int i=0;
	            while (rowIterator.hasNext()) { 
					currentRow = rowIterator.next(); 
					i++;
					Cell cell=currentRow.getCell(column);
					if(cell==null||Utility.isEmpty(cell)) break;
					
	            }
	            if(!rowIterator.hasNext()) currentRow=sheet.createRow(i);
	            else currentRow=sheet.getRow(i-1);
	            currentRow.createCell(column).setCellValue(s);	            
	            outFile =new FileOutputStream(f);
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
	
	public static void visualizzaNotifiche(Utente u) {
	    String fileName=u.getFileName();
		FileInputStream file=null;
		XSSFWorkbook workbook=null;
		FileOutputStream outFile=null;
		Row currentRow = null;
		try {
	           file = new FileInputStream(new File(fileName));
	           workbook = new XSSFWorkbook(file);
	           XSSFSheet sheet = workbook.getSheetAt(0);
	           Iterator<Row> rowIterator = sheet.iterator();
	           if(rowIterator.hasNext()) {
	        	   while (rowIterator.hasNext()) {
		        	   currentRow = rowIterator.next(); 
		        	   System.out.print("--");
		        	   if(currentRow.getCell(DA_LEGGERE_COLUMN).getStringCellValue().equals(YES)) {
		        		   System.out.print(DA_LEGGERE +" ");
		        	   }
		        	   System.out.println(currentRow.getCell(TITLE_COLUMN).getStringCellValue());
		           } 
	           }
	       //    else System.out.println(NO_NOTIFICHE);
		}catch (FileNotFoundException e) {
		       e.printStackTrace();
	    }catch (IOException e) {
		       e.printStackTrace();
	    }
		finally {
			   chiudiSicuro(file);
			   chiudiSicuro(workbook);
			   chiudiSicuro(outFile);
			    }
	}
	
	public static ArrayList<Notifica> visualizzaElencoNotifiche(Utente u){
		ArrayList<Notifica>notifiche=new ArrayList<>();
		String fileName=u.getFileName();
		FileInputStream file=null;
		XSSFWorkbook workbook=null;
		FileOutputStream outFile=null;
		Row currentRow = null;
		try {
	           file = new FileInputStream(new File(fileName));
	           workbook = new XSSFWorkbook(file);
	           XSSFSheet sheet = workbook.getSheetAt(0);
	           Iterator<Row> rowIterator = sheet.iterator();
	           if(rowIterator.hasNext()) {
	        	   while (rowIterator.hasNext()) {
		        	   currentRow = rowIterator.next();
		        	   String titolo=currentRow.getCell(TITLE_COLUMN).getStringCellValue();
		        	   String descrizione=currentRow.getCell(DESCRIPTION_COLUMN).getStringCellValue();
		        	   if(currentRow.getCell(DA_LEGGERE_COLUMN).getStringCellValue().equals(YES)) {
		        		   notifiche.add(0,new Notifica(titolo, descrizione, true));
		        	   }
		        	   else notifiche.add(0,new Notifica(titolo, descrizione, false));
		           } 
	           }
		}catch (FileNotFoundException e) {
		       e.printStackTrace();
	    }catch (IOException e) {
		       e.printStackTrace();
	    }
		finally {
			   chiudiSicuro(file);
			   chiudiSicuro(workbook);
			   chiudiSicuro(outFile);
			    }
		return notifiche;
	}
	
	public static void addUser(Utente u)  {
		int column=u.getColumn();
		addToColumn(u.getId(), FILE, column);
		addToColumn(u.getPassword(), FILE, column);
		addToColumn(""+u.getGenere(), FILE, column);
		addToColumn(u.getEta(),FILE, column);
	}
	
	public static void modificaStatiEventi() {
		int currentColumn=0;
		ArrayList<String>  column=takeColumn(Evento.FILE_EV, currentColumn);
		while(column.size()!=0) {
			Evento e=new Evento(currentColumn);
			e.modificaStato();
			currentColumn+=1;
			column=takeColumn(Evento.FILE_EV, currentColumn);
		}
	}
	
	public static int getNum(File f, int row) {
	XSSFWorkbook workbook=null;
	FileInputStream file=null;
	int l=-1;
	try {
		file = new FileInputStream(f);
        workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow currentRow;
        currentRow = sheet.getRow(row);
        //Update the value of cell
        if(currentRow==null) currentRow=sheet.createRow(row);
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
       /* try {
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
        
		
		Utente.createUtente('M', 18);
		Utente.createUtente('F', 21);
		Utente.createUtente('M', 65);
		*/
		/*ArrayList<String> ids=new ArrayList<>();
		ArrayList<String> pass=new ArrayList<>();
		takeIds(ids);
		takePasswords(pass);
		for(int i=0; i<ids.size(); i++) {
			System.out.print(ids.get(i)+" ");
			System.out.println(pass.get(i));
			
		 for(int i=0; i<showNotifies(0).size();i++) {
			 System.out.println(showNotifies(0).get(i));
		}
		*/
		 addToCell("12/03/2019", Evento.FILE_EV, 1, 5);
    }
}