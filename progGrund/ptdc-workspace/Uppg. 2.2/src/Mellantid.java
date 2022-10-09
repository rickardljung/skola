
import java.util.Scanner;

public class Mellantid {


	public static void main(String[] args) {
		Scanner Scan = new Scanner(System.in);
	
		System.out.println("Skriv in starttiden (timmar minuter)");
		
		int starttim=Scan.nextInt();
		int startmin=Scan.nextInt();
		
		System.out.println("Skriv in sluttiden (timmar minuter)");
		
		int sluttim=Scan.nextInt();
		int slutmin=Scan.nextInt();
		
		int starttid = starttim * 60 + startmin;
		int sluttid = sluttim*60 + slutmin;
		
		int mellantid = sluttid-starttid;
		
		System.out.println("Mellantiden är: " +mellantid + " minuter");
		
		int mellanTimmar = mellantid/60;
		
		int mellanmin = mellantid % 60;
		
		System.out.println("Mellantiden är: " +mellanTimmar + " timmar och " +mellanmin+ " minuter");

	}

}
