import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;
import javax.security.cert.X509Certificate;

public class Server implements Runnable {
	private ServerSocket serverSocket = null;
	private static int numConnectedClients = 0;
	protected ArrayList<Journal> journals = new ArrayList<Journal>();

	public Server(ServerSocket ss) throws IOException {
		serverSocket = ss;
		newListener();
	}

	public void run() {
		try {
			SSLSocket socket = (SSLSocket) serverSocket.accept();
			newListener();
			SSLSession session = socket.getSession();
			X509Certificate cert = (X509Certificate) session
					.getPeerCertificateChain()[0];
			String subject = cert.getSubjectDN().getName();
			numConnectedClients++;
			System.out.println("client connected");
			System.out.println("client name (cert subject DN field): "
					+ subject);
			System.out.println(numConnectedClients
					+ " concurrent connection(s)\n");

			PrintWriter out = null;
			BufferedReader in = null;
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String clientMsg = null;
			readFile();
			while ((clientMsg = in.readLine()) != null) {
				System.out.println(clientMsg);
				String ret = readMessage(clientMsg);
				System.out.println(ret);
				if(ret != null){				
				out.println(ret);
				out.flush();
				}
				System.out.println("done\n");
			}
			in.close();
			out.close();
			socket.close();
			numConnectedClients--;
			System.out.println("client disconnected");
			System.out.println(numConnectedClients
					+ " concurrent connection(s)\n");
		} catch (IOException e) {
			System.out.println("Client died: " + e.getMessage());
			e.printStackTrace();
			return;
		}
	}
	private static final int FAILEDLOGGIN = -1;
	private static final int LOGGIN = 0;
	private static final int VIEW = 1;
	private static final int CREATE = 2;
	private static final int ADD = 3;
	private static final int DELETE = 4;
	private static final int LOGOUT = 5;

	protected String readMessage(String clientMsg) {
		String ID = null;
		String div = null;
		String pid = null;
		String nurseID = null;
		String data = null;
		String recordNumber = null;
		String action = "Denied";
		Journal journal = null;
		List<Record> recordList;
		// Delar upp och sparar undan info från in-strängen
		String[] info = clientMsg.split("#%");
		ID = info[1];
		div = info[2];
		pid = info[3];
		recordNumber = info[4];
		nurseID = info[5];
		data = info[6];
		
		StringBuilder sb = new StringBuilder();
		// Om någon vill endast läsa
		if (Integer.parseInt(info[0]) == LOGOUT) {
			action = "Granted";
		//	sb.append("Loggout");
			try {
				audit(ID, pid, action, "Loggout");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Logg out!");
			}
			return null;

		} else 	if (Integer.parseInt(info[0]) == FAILEDLOGGIN) {
			action = "Denied";
			sb.append("Failed loggin");
			try {
				audit(ID, pid, action, "Log in ");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Logging failed!");
			}
			return null;

		} else if (Integer.parseInt(info[0]) == LOGGIN) {
			action ="Granted";
		//	sb.append("Loggin successful");
			
			
			try {
				audit(ID, pid, action, "Log in ");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Logging failed!");
			}
			return null;

		}else if (Integer.parseInt(info[0]) == VIEW) {

			for (int i = 0; i < journals.size(); i++) {
				if (pid.compareTo(journals.get(i).getPatient()) == 0) {
					journal = journals.get(i);
					break;
				}
			}
			// Kolla ifall hittat journal
			if (journal == null) {
				sb.append("No such Journal");
				// Hitta Journal
			} else {

				recordList = journal.getList();
				// om det är patienten som vill kolla
				if (ID.compareTo(pid) == 0) {
					for (Record r : recordList) {
						sb.append("Record nbr; " + r.getRecordNbr() + "  ");
						sb.append("Doctor; " + r.getDoctor() + "  ");
						sb.append("Nurse; " + r.getNurse()  + "  ");
						sb.append("Data; " + r.getData()  + "  ");
						sb.append("#%");
						action = "Granted";
					}
					// Om någon annan vill kolla
				} else {
					for (int i = 0; i < recordList.size(); i++) {
						Record currentRecord = recordList.get(i);
						if (currentRecord.getNurse().compareTo(ID) == 0
								|| currentRecord.getDoctor().compareTo(ID) == 0) {
							sb.append("Record nbr; "
									+ currentRecord.getRecordNbr() + "  ");
							sb.append("Doctor; " + currentRecord.getDoctor()+ "  ");
							sb.append("Nurse; " + currentRecord.getNurse()+ "  ");
							sb.append("Data; " + currentRecord.getData()+ "  ");
							sb.append("#%");
							action = "Granted";
						} else if (currentRecord.getDivision().compareTo(div) == 0
								|| Integer.parseInt(div) == -1) {
							sb.append("Record nbr; "
									+ currentRecord.getRecordNbr()+ "  ");
							sb.append("Doctor; " + currentRecord.getDoctor()+ "  ");
							sb.append("Nurse; " + currentRecord.getNurse()+ "  ");
							sb.append("Data; " + currentRecord.getData()+ "  ");
							sb.append("#%");
							action = "Granted";
						}
					}
				}
				if (sb.length() == 0) {
					sb.append("No such record");
				}
			}

			// Skriv i loggen
			try {
				audit(ID, pid, action, "View of ");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Logging failed!");
			}
			saveToFile();
			return sb.toString();

			// Någon vill lägga till record i Journal
		} else if (Integer.parseInt(info[0]) == CREATE) {
			Journal currentJournal = null;
			action = "Denied";
			// Kolla om patient har journal sedan tidigare
			for (Journal j : journals) {
				if (pid.compareTo(j.getPatient()) == 0) {
					currentJournal = j;
					j.addRecord(nurseID, ID, div, data);
					action = "Granted";
					sb.append("Created new record");
					break;
				}

			}
			// Finns ingen tidigare journal, skapa ny journal och lägg till
			// record
			if (currentJournal == null) {
				currentJournal = new Journal(pid);
				currentJournal.addRecord(nurseID, ID, div, data);
				journals.add(currentJournal);
				action = "Granted";
				sb.append("Created new Journal and record");
			}
			if (sb.length() == 0) {
				sb.append("Couldn't create new record");
			}
			try {
				audit(ID, pid, action, "create");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Logging failed!");
			}
			saveToFile();
			return sb.toString();

			// Lägga till mer data i Journal
		} else if (Integer.parseInt(info[0]) == ADD) {
			action = "Denied";
			Journal tempJournal = null;
			for (Journal j : journals) {
				if (pid.compareTo(j.getPatient()) == 0) {

					tempJournal = j;
					break;
				}
			}
			// Kollar att journal finns och att det är doctor eller nurse som
			// har hand om patient som vill lägga till
			if (tempJournal != null) {
				Record tempRecord = tempJournal.removeRecord(recordNumber);
				if (tempRecord.getDoctor().compareTo(ID) == 0
						|| tempRecord.getNurse().compareTo(ID) == 0
						|| tempRecord != null) {
					tempRecord.addData(data);
					tempJournal.addRecord(tempRecord);
					action = "Granted";
					sb.append("Added new data to record");
				} else {
					tempJournal.addRecord(tempRecord);
					sb.append("Couldn't add new data to record");
				}

			}
			if (sb.length() == 0) {
				sb.append("Couldn't add new data to record");
			}
			try {
				audit(ID, pid, action, "add to record ");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Logging failed!");
			}
			saveToFile();
			return sb.toString();

		} else if (Integer.parseInt(info[0]) == DELETE) {
			action = "Denied";
			
			if (Integer.valueOf(div) == -1) {

				for (int i = 0; i< journals.size(); i++) {
					if (pid.compareTo(journals.get(i).getPatient()) == 0) {
						journal=journals.remove(i);
						break;
					}
				}
				journal.removeRecord(recordNumber);
				journals.add(journal);
				action = "Granted";
				sb.append("Record deleted");
			}
			if (sb.length() == 0) {
				sb.append("No record deleted");
			}

			try {
				audit(ID, pid, action, "delete ");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Logging failed!");
			}
			saveToFile();
			return sb.toString();
		}
		return "Undefined operation";
	}

	private void audit(String ID, String pid, String action, String preformed)
			throws IOException {
		Date date = new Date();
		FileWriter fw = new FileWriter("log.txt", true);
		fw.append(ID + " requested " + " " + preformed + " " + pid
				+ "'s journal. " + date.toString() + " --> " + action + "\n");
		fw.flush();
		fw.close();

	}

	protected ArrayList<Journal> getAllJournals() {
		return journals;
	}

	protected void addJournal(Journal journal) {
		journals.add(journal);
	}

	private void newListener() {
		(new Thread(this)).start();
	} // calls run()

	public static void main(String args[]) {
		
		int port = -1;
        	if (args.length >= 1) {
            	port = Integer.parseInt(args[0]);
        	}

		System.out.println("\nServer Started\n");
		String type = "TLS";
		try {
			ServerSocketFactory ssf = getServerSocketFactory(type);
			ServerSocket ss = ssf.createServerSocket(port);
			((SSLServerSocket) ss).setNeedClientAuth(true); // enables client
															// authentication
			new Server(ss);
		} catch (IOException e) {
			System.out.println("Unable to start Server: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static ServerSocketFactory getServerSocketFactory(String type) {
		if (type.equals("TLS")) {
			SSLServerSocketFactory ssf = null;
			try { // set up key manager to perform server authentication
				SSLContext ctx = SSLContext.getInstance("TLS");
				KeyManagerFactory kmf = KeyManagerFactory
						.getInstance("SunX509");
				TrustManagerFactory tmf = TrustManagerFactory
						.getInstance("SunX509");
				KeyStore ks = KeyStore.getInstance("JKS");
				KeyStore ts = KeyStore.getInstance("JKS");
				char[] password = "password".toCharArray();

				ks.load(new FileInputStream("serverkeystore"), password); // keystore
																			// password
																			// (storepass)
				ts.load(new FileInputStream("servertruststore"), password); // truststore
																			// password
																			// (storepass)
				kmf.init(ks, password); // certificate password (keypass)
				tmf.init(ts); // possible to use keystore as truststore here
				ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
				ssf = ctx.getServerSocketFactory();
				return ssf;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return ServerSocketFactory.getDefault();
		}
		return null;
	}

	private void readFile() {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream("fileout.detspelaringenroll");
			in = new ObjectInputStream(fis);
			int i = (Integer) in.readObject();
			for (int j = 0; j < i; j++) {
				Journal inJ = (Journal) in.readObject();
				addJournal(inJ);
			}
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void saveToFile() {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream("fileout.detspelaringenroll");
			out = new ObjectOutputStream(fos);
			ArrayList<Journal> allJournals = getAllJournals();
			int size = allJournals.size();
			out.writeObject(size);
			for (Journal j : allJournals) {
				out.writeObject(j);
			}
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
