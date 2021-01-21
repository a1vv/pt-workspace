package rekrytering;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

	/**
	 * Returnerar max nbrOfRows rader från filen som en vektor av Applicant-objekt.
	 * Läser i filen tills det inte finns fler rader eller tills man läst nbrOfRows
	 * rader (det som inträffar först). 
	 * Returnerar null om filen inte finns.
	 */
	public static Applicant[] readFromFile(String fileName, int nbrOfRows) {
		Scanner scan;
		try {
			scan = new Scanner(new File(fileName), "utf-8");
		} catch (FileNotFoundException e) {
			System.err.println("File not found" + fileName);
			e.printStackTrace();
			return null;
		}
		//Här kan du använda Scannern för att läsa från filen fileName.
		//Varje rad motsvarar en Applicant. Kontrollera vad Applicants konstruktor kräver
		//Alla Applicant-objekt (max nbrOfRows stycken) ska lagras i en Applicant-vektor och returneras på slutet
		//Byt ut denna rad mot hela lösningen
		int i = 0;
		Applicant[] applicants = new Applicant[nbrOfRows];
		while(scan.hasNextLine() && i < nbrOfRows) {
			// dela först upp med " ", 0-förnamn, 1-efternamn, 2-betyg
			// skicka in 0,1 som namn och 2 som betyg till ett nytt Applicant objekt som ska läggas i en vektor
			String next = scan.nextLine();
			String args[] = next.split(" ");
			if(args.length < 3) {
				continue;
			}
			if(args[2] != null) {
				args[2] = filterInput(args[2]);
			}
			applicants[i] = new Applicant(args[0] + " " + args[1],args[2]);
			i++;
		}
		return applicants;
	}
	
	// gå igenom betygen och filtrera bort alla siffror som inte är mellan 0-5.
	// om det inte finns några siffror returneras 0.
	private static String filterInput(String input) {
		StringBuilder output = new StringBuilder();
		String[] grades = input.split(",");
		for(int s = 0 ; s < grades.length ; s++) {
			try {
				int num = Integer.parseInt(grades[s]);
				if(num >= 0 && num <= 5) {
		            output.append(num);
		            output.append(",");
				}
	        }
	        catch (NumberFormatException ex){
	        
	        }
		}
		int len = output.length();
		if(len > 1) {
			output.delete(len-1,len);
		} else {
			output.replace(0, len, "0");
		}
		return output.toString();
	}
}
