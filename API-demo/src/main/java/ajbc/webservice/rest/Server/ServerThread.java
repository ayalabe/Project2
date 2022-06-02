package ajbc.webservice.rest.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import com.google.gson.Gson;
import ajbc.webservice.rest.Client.IOTThing;
import ajbc.webservice.rest.DB.DBService;

public class ServerThread implements Runnable {
	
	private Socket clientSocket;
	private Gson gson = new Gson();
	private Lock mutex = new ReentrantLock();
	private DBService dbService;
	
	public ServerThread(Socket clientSocket) {
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
			System.out.println("msg "+ iotThing);
			
			mutex.lock();
			
			// TODO enter to the DB
			dbService.updatData(iotThing);
			DBService.printDB();

			mutex.unlock();
			//return user to client

			// sending data
			writer.println("processing result done after ");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
public static void main(String[] args) {
	
	final int PORT = 8060;
		
		ExecutorService executorService = Executors.newCachedThreadPool();

		try (ServerSocket serverSocket = new ServerSocket(PORT);) {

			System.out.println("Server started on port " + PORT);

			while (true) {
				Socket clientSocket = serverSocket.accept();
				executorService.execute(new ServerThread(clientSocket));
			}
		} catch (IOException e) {
			System.err.println("Failed to start server on port " + PORT);
			e.printStackTrace();
		}
//		finally {
//			executorService.shutdown();
//			try {
//				executorService.awaitTermination(2, TimeUnit.SECONDS);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
}

}
