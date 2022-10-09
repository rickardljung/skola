import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Session {

	private BufferedReader in;
	private PrintWriter out;
	private Socket socket;
	private Account account;

	public Session(Account account, Socket socket) {
		this.account = account;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);

			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void startSession() {
		out.println(account.login());
		out.flush();
		String msg = null;
		String serverMsg = null;

		try {
			int loop = Account.REQ_NEWPATIENT;
			
			while (loop == Account.REQ_NEWPATIENT) {
				
				account.choosePatient();
				loop = 0;

				while (!(loop == Account.REQ_NEWPATIENT || loop == Account.REQ_LOGOUT)) {

					msg = account.sendInfo();

					out.println(msg);

					out.flush();
					//System.out.println("Vi har skickat nu! ");
					
					
						serverMsg = in.readLine();
						interpretMessage(serverMsg);
					
					
					
					loop = account.showOpt();

				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println(account.logout());
		out.flush();

	}

	private void interpretMessage(String serverMsg) {
		String[] tempString = serverMsg.split("#%");
		for (int i = 0; i < tempString.length; i++) {
			System.out.println(tempString[i]);

		}

	}

	public void closeSession() {
		try {

			in.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
