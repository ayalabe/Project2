package ajbc.webservice.rest.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InventoryServer {

	private ExecutorService executorService = Executors.newCachedThreadPool();
	private static final int PORT = 8060;
	private Socket clientSocket;
	
	public void run() {

	try (ServerSocket serverSocket = new ServerSocket(PORT);) {

		System.out.println("Server started on port " + PORT);

		while (true) {
			clientSocket = serverSocket.accept();
			executorService.execute(new ServerThread(clientSocket));
		}
	} catch (IOException e) {
		System.err.println("Failed to start server on port " + PORT);
		e.printStackTrace();
	}finally {
		executorService.shutdown();
		try {
			executorService.awaitTermination(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
	
	
}
