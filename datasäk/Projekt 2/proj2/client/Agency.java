import java.io.IOException;
import java.util.Scanner;


public class Agency extends Account{

	public Agency(String name, String pw, int divisionID, int ID) {
		super(name, pw, divisionID, ID);
		super.degree = super.AGENCY;
	}



	@Override
	protected int op() throws NumberFormatException, IOException {
		
		
		System.out.println("2: Delete record");
		System.out.println("3: Choose new patient");
		System.out.println("4: Log out");
		
		
		int option = Integer.parseInt(read.readLine());
		switch (option) {
		case 1:
			return REQ_VIEW;
		
		

		case 2:
			System.out.println("Record to remove: ");
			recordNbr = read.readLine();
			return REQ_DEL;
			
		case 3:
			
			return REQ_NEWPATIENT;
			
		case 4:
			
			return REQ_LOGOUT;
			
		default:
			return REQ_VIEW;
			
		
		}


			
		
		}
		
		
		
		
		
		
	
	

}
