import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Nurse extends Account{
	
	public Nurse(String name, String pw, int divisonID, int ID){
		super(name, pw, divisonID, ID);
		super.degree = super.NURSE;
	}




	@Override
	protected int op() throws NumberFormatException, IOException {
		System.out.println("2: Add to record");
		System.out.println("3: Choose new patient");
		System.out.println("4: Log out");
		
		int option = Integer.parseInt(read.readLine());
		switch (option) {
		case 1:
			return REQ_VIEW;
		case 2: 
			System.out.println("Declare recordnumber: ");
			recordNbr = read.readLine();
			
			System.out.println("Text to be added (use \";\" followed by enter to add): ");
			StringBuilder sb = new StringBuilder();
			sb.append("");
			
			while(sb.indexOf(";") == -1){
				sb.append(read.readLine());
			}
			
			data = sb.toString();
			
			
			return REQ_ADD;
			
		case 3:
			
			return REQ_NEWPATIENT;
			
		case 4:
			
			return Account.REQ_LOGOUT;
			
		
		}
		
		return REQ_VIEW;
		
		
		
		
		
		
		
	}


	
	

}
