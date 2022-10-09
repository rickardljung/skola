
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.KeyStore;
import java.util.HashMap;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.security.cert.X509Certificate;


public class Authenticator {
	private Account account;
	private String host;
	private int port;

	public Authenticator(HashMap<String, Account> acc, String host, int port) {
		this.host = host;
		this.port = port;
		String username = null;
		String password = null;
		try {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Username: ");
		username = reader.readLine();
		System.out.println("Password: ");
		password = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		account = acc.get(username);
		if (account != null) {
			account = account.authenticate(password);
			if (account == null)
				System.out.println("Could not find user");
			else
				System.out.println("User authenticated");
		}
		else {
			System.out.println("Could not find user");
		}
		setUpConnection(username, password);
		
		
	}
	
	public void setUpConnection(String username, String password) {
			
		try { /* set up a key manager for client authentication */
            SSLSocketFactory factory = null;
            try {
                char[] pw = password.toCharArray();
                KeyStore ks = KeyStore.getInstance("JKS");
                KeyStore ts = KeyStore.getInstance("JKS");
                KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
                TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
                SSLContext ctx = SSLContext.getInstance("TLS");
                
                String userKeystore = "eit060/"+ username + "_keystore"   ;//"truststores/" + username + "_keystore";
                String userTruststore = "eit060/" + username + "_truststore";//"truststores/" + username + "_truststore";
                
                ks.load(new FileInputStream(userKeystore), pw);  // keystore password (storepass)
				ts.load(new FileInputStream(userTruststore), pw); // truststore password (storepass);
				kmf.init(ks, pw); // user password (keypass)
				tmf.init(ts); // keystore can be used as truststore here
				ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
                factory = ctx.getSocketFactory();
            } catch (Exception e) {
                throw new IOException(e.getMessage());
            }
            SSLSocket socket = (SSLSocket)factory.createSocket(host, port);
            System.out.println("\nsocket before handshake:\n" + socket + "\n");

            /*
             * send http request
             *
             * See SSLSocketClient.java for more information about why
             * there is a forced handshake here when using PrintWriters.
             */
            socket.startHandshake();

            SSLSession session = socket.getSession();
            X509Certificate cert = (X509Certificate)session.getPeerCertificateChain()[0];
            String subject = cert.getSubjectDN().getName();
            System.out.println("certificate name (subject DN field) on certificate received from server:\n" + subject + "\n");
            System.out.println("socket after handshake:\n" + socket + "\n");
            System.out.println("secure connection established\n\n");
            
            Session s = new Session(account, socket);
            s.startSession();
            s.closeSession();

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
