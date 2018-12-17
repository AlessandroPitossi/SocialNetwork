package mylib;
/*
 * @author I docenti di informatica di UNIBS
 */
public class BelleStringhe
{

 private final static String SPAZIO = " ";
 private final static String CORNICE = "-";
 private final static String ACAPO = "\n";

 public static String incornicia (String s)
	{ 
	 StringBuffer res = new StringBuffer();
	 int lenght=s.length();
	 for(int i=0; i<s.length(); i++) {
		 res.append(CORNICE);
	 }
	 res.append(ACAPO);
	 res.append(s+ACAPO);
	 for(int i=0; i<s.length(); i++) {
		 res.append(CORNICE);
	 }
	 res.append(ACAPO);

 	 return res.toString();

  }

 /*
  * Precondizioni: la stringa passata non deve avere parole più lunghe del valore passato come larghezza
  */
 public static String aCapo (String s, int larghezza) {
	 StringBuffer res = new StringBuffer(larghezza);
	 int charStam=0;
	 int end=0;
	 do {
		 int daStam=s.length()-charStam;
		 int numCharDaStampare=0; 
		 int larg=larghezza;
		 boolean nok=true;
		 if(larghezza<daStam) {
			 do {
				 if (s.charAt(charStam+larg-1)==' ') {
					 numCharDaStampare=larg;
					 nok=false;
				 }
				 else {
					 larg=larg-1;
				 }
			 }while(nok);
		 }
		 else {
			 numCharDaStampare=s.length();
			 }
		 end=Math.min(end+=numCharDaStampare,s.length());
		 res.append(s.substring(charStam, end)+ACAPO);
		 charStam=end;
	 }while(charStam<s.length());
	 return res.toString();
 }
 
 public static String incolonna (String s, int larghezza)
	{
	 StringBuffer res = new StringBuffer(larghezza);
	 int numCharDaStampare = Math.min(larghezza,s.length());
	 res.append(s.substring(0, numCharDaStampare));
	 for (int i=s.length()+1; i<=larghezza; i++)
		res.append(SPAZIO);
	 return res.toString();
	}
	
 public static String centrata (String s, int larghezza)
	{
	 StringBuffer res = new StringBuffer(larghezza);
	 try{
		 if (larghezza <= s.length())
			 res.append(aCapo(s, larghezza));
		 else
		 {
			int spaziPrima = (larghezza - s.length())/2;
		 	int spaziDopo = larghezza - spaziPrima - s.length();
		 	for (int i=1; i<=spaziPrima; i++)
		 		res.append(SPAZIO);
			
		 	res.append(s);
		
		 	for (int i=1; i<=spaziDopo; i++)
		 		res.append(SPAZIO);
		 }
	 }catch(Exception e) {
		 res.append(s);
	 }
	 	 return res.toString();

	}

	public static String ripetiChar (char elemento, int larghezza)
	 {
		 StringBuffer result = new StringBuffer(larghezza);
		 for (int i = 0; i < larghezza; i++)
			{
			 result.append(elemento);
			}
		 return result.toString();

	 }

	public static String rigaIsolata(String daIsolare)
	 {
		 StringBuffer result = new StringBuffer();
		 result.append(ACAPO);
		 result.append(daIsolare);
		 result.append(ACAPO);
		 return result.toString();
	 }
 
}

