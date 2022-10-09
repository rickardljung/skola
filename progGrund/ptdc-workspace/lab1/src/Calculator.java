import java.util.Scanner;

public class Calculator {
	public static void main(String[] args) {
		System.out.println("Skriv två tal");
		Scanner scan = new Scanner(System.in);
		int nbr1 = scan.nextInt();
		int nbr2 = scan.nextInt();
		
		int sum = nbr1 + nbr2;
		System.out.println("Summan av talen är " + sum);
		
		sum = nbr1 - nbr2;
		System.out.println("Skillnaden mellan talen är " + sum);
		
		sum = nbr1 * nbr2;
		System.out.println("Produkten av talen är " + sum);
		
		sum = nbr1 / nbr2;
		System.out.println("Kvoten mellan talen är " + sum);
		
		sum=nbr1 % nbr2;
		
		System.out.println("Resten är " + sum);
		
	}
}
