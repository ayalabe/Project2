package ajbc.webservice.rest.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import com.google.gson.Gson;
import ajbc.webservice.rest.Client.IOTThing;
import ajbc.webservice.rest.DB.DBService;

public class ServerSocketThread implements Runnable {
	
	private Socket clientSocket;
	private Gson gson = new Gson();
	private Lock mutex = new ReentrantLock();
	private DBService dbService;
	
	public ServerSocketThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
		dbService = new DBService();
	}

	@Override
	public void run() {
		try (BufferedReader bufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);) {

			System.out.println(
					"Server is connected " + clientSocket.getInetAddress() + " port " + clientSocket.getPort());
			// reading data
			String msg = bufferReader.readLine();
			IOTThing iotThing = gson.fromJson(msg, IOTThing.class);
			
			mutex.lock();
			
			dbService.updatData(iotThing);

			mutex.unlock();

			// sending data
			writer.println("Data save in DB ");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
