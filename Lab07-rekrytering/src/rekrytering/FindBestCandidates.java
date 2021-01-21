package rekrytering;

import java.util.Arrays;

public class FindBestCandidates {
	private static final double MIN_AVG_GRADE = 3.0;

	public static void main(String[] args) {
		// Läs från fil (Börja med "applications_small.txt), spara resultatet i en
		// vektor

		// Skicka in Applicant-vektorn (som du fick i föregående steg) till metoden
		// findBestCandidiates (nedan)
		// Spara resultatet i en vektor

		// Printa resultatet från findBestCandidates
		Applicant[] applicants = FileReader.readFromFile("applications_all.txt",300);
		applicants = findBestCandidates(applicants);
		Arrays.sort(applicants);
		for(Applicant a : applicants) {
			System.out.println(a);
		}
		
	}

	public static Applicant[] findBestCandidates(Applicant[] applicants) {
		// Hitta alla kandidater som har medelbetyg över MIN_AVG_GRADE
		// Lagra alla dessa kandidater i en vektor, returnera vektorn
		
		int length = 0;
		for(Applicant a : applicants) {
			if(a != null) {
				if(a.getAvgGrade() >= MIN_AVG_GRADE) {
					length++;
				}
			}
		}
		Applicant[] candy = new Applicant[length];
		int i = 0;
		for(Applicant a : applicants) {
			if(a != null) {
				if(a.getAvgGrade() >= MIN_AVG_GRADE) {
					candy[i] = a;
					i++;
				}
			}		
		}
		return candy;
	}
}
