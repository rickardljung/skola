
import java.util.HashMap;

public class Main {
	public static void main(String[]args){
		String host = null;
        	int port = -1;
        	for (int i = 0; i < args.length; i++) {
            	System.out.println("args[" + i + "] = " + args[i]);
        	}
        	if (args.length < 2) {
         	System.out.println("USAGE: java client host port");
            	System.exit(-1);
        	}
        	try { /* get input parameters */
            	host = args[0];
            	port = Integer.parseInt(args[1]);
        	} catch (IllegalArgumentException e) {
            	System.out.println("USAGE: java client host port");
            	System.exit(-1);
        	}
		
		HashMap<String, Account> accounts = new HashMap<String, Account>(); 
		
		Account p1 = new Patient("patient1","patient1", 0,10);
		Account p2 = new Patient("patient2","patient2", 0,20);
		Account p3 = new Patient("patient3","patient3", 0,30);
		
		Account n1 = new Nurse("nurse1","nurse1", 1,11);
		Account n2 = new Nurse("nurse2","nurse2", 2,21);
		Account n3 = new Nurse("nurse3","nurse3", 3,31);
		
		Account d1 = new Doctor("doctor1","doctor1", 1,12);
		Account d2 = new Doctor("doctor2","doctor2", 2,22);
		Account d3 = new Doctor("doctor3","doctor3", 3,32);
		
		Account ga = new Agency("agency", "agency", -1, 43);
		
		accounts.put("patient1", p1);
		accounts.put("patient2", p2);
		accounts.put("patient3", p3);
		
		accounts.put("nurse1", n1);
		accounts.put("nurse2", n2);
		accounts.put("nurse3", n3);
		
		accounts.put("doctor1", d1);
		accounts.put("doctor2", d2);
		accounts.put("doctor3", d3);
		
		accounts.put("agency", ga);
		
		Authenticator a = new Authenticator(accounts, host, port);
		
		
//		1.Ange användarnamn
//		2.Logga in
//		3.Hämta journal
//		4a. Inte authoriserad att läsa
//		4b. Visa recordsen
//		5a. Redigera
//		5b. "Stäng patienten"
//		6a. loggaut
//		6b. välj en ny pasient
	}
}
