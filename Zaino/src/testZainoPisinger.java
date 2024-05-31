/*********
Algoritmi 2 - Lavinia Egidi
uso:
0) sistemare il nome del package
1) scaricare l'archivio instances_01_KP.zip e decomprimerlo
2) sistemare il PATH o spostare la cartella nella directory del progetto
(la stessa directory che contiene src) 
3) In ogni test, per ogni istanza che si vuole provare
--indicare il tipo dell'istanza (nome directory): typename low-dimensional oppure large_scale 
--indicare il nome del file
**********/

//package <nome package>;

import static org.junit.Assert.*;
import it.uniupo.graphLib.InOut;

import org.junit.Test;

public class testZainoPisinger {
	
	private static final String PATH = "instances_01_KP/";

	@Test
	public void testEasypeasy() {
		String typename = "low-dimensional";
		String filename = "f10_l-d_kp_20_879";
		String[] instance = InOut.readStringArray(PATH+typename+"/"+filename);
		String[] params = instance[0].split(" ");
		int n = Integer.parseInt(params[0]);
		int cap = Integer.parseInt(params[1]);
		int[] values = new int[n];
		int[] vols = new int[n];

		for (int i = 0; i < n; i++) {
		 	String[] pieces = instance[i+1].split(" ");
		 	values[i] = Integer.parseInt(pieces[0]);
		 	vols[i] = Integer.parseInt(pieces[1]);
		}

		Zaino zai = new Zaino(values,vols, cap);
		int maxVal = Integer.parseInt(InOut.readString(PATH+typename+"-optimum/"+filename));
		assertEquals(maxVal,zai.getMaxVal());
	}
	
	@Test
	public void test1() {
		String typename = "large_scale";
		String filename = "knapPI_3_5000_1000_1";
		String[] instance = InOut.readStringArray(PATH+typename+"/"+filename);
		String[] params = instance[0].split(" ");
		int n = Integer.parseInt(params[0]);
		int cap = Integer.parseInt(params[1]);
		int[] values = new int[n];
		int[] vols = new int[n];

		for (int i = 0; i < n; i++) {
			 String[] pieces = instance[i+1].split(" ");
			 values[i] = Integer.parseInt(pieces[0]);
			 vols[i] = Integer.parseInt(pieces[1]);
		}

		Zaino zai = new Zaino(values,vols, cap);
		int maxVal = Integer.parseInt(InOut.readString(PATH+typename+"-optimum/"+filename));
		assertEquals(maxVal,zai.getMaxVal());
	}
	

}
