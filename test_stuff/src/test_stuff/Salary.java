package test_stuff;

import java.util.Scanner;


public class Salary {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int wantedMoney = scan.nextInt();
        int nbrDays = calcNbrDays(wantedMoney);
        System.out.println(nbrDays);
    }
    
    public static int calcNbrDays(int wantedSum) {
        int days = 1;
        int wage = 1;
        int money = 1;
    	while (money<wantedSum) {
    	    wage += wage*2;
    	    money += wage;
    	    days += 1;
    	}
    	return days;
    }
}