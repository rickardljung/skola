import java.io.IOException;
import java.util.Scanner;

public class Doctor extends Account {

	public Doctor(String name, String pw, int divisionID, int ID) {
		super(name, pw, divisionID, ID);
		super.degree = super.DOC;
	}




	@Override
	protected int op() throws IOException {
		
		StringBuilder sb = new StringBuilder();
		
		System.out.println("2: Add to record");
		System.out.println("3: Add new record");
		System.out.println("4: Choose new patient");
		System.out.println("5: Log out");

		int option = Integer.parseInt(read.readLine());
		switch (option) {
		case 2:
			System.out.println("Declare recordnumber: ");
			recordNbr = read.readLine();

			System.out.println("Text to be added (use \";\" followed by enter to add): ");
			
			sb.append("");

			while (sb.indexOf(";") == -1) {
				sb.append(read.readLine());
			}

			data = sb.toString();

			return REQ_ADD;

		case 3:
			
			
			System.out.println("Text to be added (use \";\" followed by enter to add): ");
			sb.append("");

			while (sb.indexOf(";") == -1) {
				sb.append(read.readLine());
			}
			
			System.out.println("Nurse: ");
			nurseID = read.readLine();

			data = sb.toString();

			return REQ_CREATE;

		case 4:

			return REQ_NEWPATIENT;
			
		case 5:
			
			return REQ_LOGOUT;

		}

		return REQ_VIEW;

	}

}
