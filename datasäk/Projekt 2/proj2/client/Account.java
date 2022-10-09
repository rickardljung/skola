import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public abstract class Account {

	private String name, passwd;
	private int divisionID;
	protected int ID;
	protected int degree;
	protected String patientID;
	protected int request;
	protected String recordNbr;
	protected String nurseID;
	protected String data;

	protected BufferedReader read;
		
	protected final int PATIENT = 0;
	protected final  int NURSE = 1;
	protected final int DOC = 2;
	protected final int AGENCY = 3;
	
	public static final int REQ_LOGIN_FAIL = -1;
	protected final int REQ_LOGIN = 0;
	protected final int REQ_VIEW = 1;
	protected final int REQ_CREATE = 2;
	protected final int REQ_ADD = 3;
	protected final int REQ_DEL = 4;
	
	protected static final int REQ_LOGOUT = 5;
	protected static int REQ_NEWPATIENT = 6;
	
		
	
	public Account(String name, String pw, int divisionID, int ID){
		this.name = name;
		this.divisionID = divisionID;
		this.ID = ID;
		this.degree = degree;
		passwd = pw;
		recordNbr = "0";
		nurseID = "0";
		request = 0;
		data = "0";
		read = new BufferedReader(new InputStreamReader(System.in));

		
		// maybe print to file
	}
	
	public String sendInfo(){
		//System.out.println("THIS IS SPARTA: " + request);
		return request + "#%"+ ID +"#%" + divisionID + "#%" + patientID + "#%" + recordNbr + "#%" + nurseID + "#%" + data; 
	}
	
	public void choosePatient() throws IOException {
		System.out.println("Patient ID: ");
		patientID = read.readLine();
		request = REQ_VIEW;	
		
	}
	
	public int showOpt() throws NumberFormatException, IOException {
		
		System.out.println("--- OPTIONS ---");
		System.out.println("1. View patient record");

		request = op();
		
		if(request == REQ_NEWPATIENT) {
			return REQ_NEWPATIENT;
		}
		else if (request == REQ_LOGOUT) {
			return REQ_LOGOUT;
		}
		
		
		return 0;
		
	
		
		
	}
	protected abstract int op() throws NumberFormatException, IOException;

	/**
	 * 
	 * @param password
	 * @return account if password is authenticated, if not null.
	 */
	public Account authenticate(String password) {
		if(passwd.equals(password))
			return this;
		
		return null;
	}
	public String logout(){
		request = REQ_LOGOUT;
		return sendInfo();
	}
	public String login(){
		request = REQ_LOGIN;
		return sendInfo();
	}
}
