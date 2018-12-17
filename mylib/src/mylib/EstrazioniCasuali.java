package mylib;
import java.util.*;
/*
 * @author I docenti di informatica di UNIBS
 */
public class EstrazioniCasuali 
{	
	private static Random rand = new Random();	
	
	public static int estraiIntero(int min, int max)
	{
	 int range = max + 1 - min;
	 int casual = rand.nextInt(range);
	 return casual + min;
	}
	
	public static double estraiDouble( int max){
		double k=Math.random();
		int i=estraiIntero(1,2);
		if(i==2) k=k*(-1);
		int value=estraiIntero(1, max);
		k*=value;
		return k;
	}
	
	public static void main(String[] args){
		System.out.println(estraiDouble(5));
	}
	
}
