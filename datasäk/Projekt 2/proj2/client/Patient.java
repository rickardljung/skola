import java.util.Scanner;


public class Patient extends Account{

	public Patient(String name, String pw, int divisionID, int ID) {
		super(name, pw, divisionID, ID);
		super.degree = super.PATIENT;
	}



	@Override
	protected int op() {
		System.out.println("No options available");
		
		return REQ_LOGOUT;
		
		
	}

}
