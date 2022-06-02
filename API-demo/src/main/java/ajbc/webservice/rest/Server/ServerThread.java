package ajbc.webservice.rest.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ServerThread extends Thread {

	private ExecutorService executorService;

	public ServerThread(int port) {
		executorService = Executors.newCachedThreadPool();
	}

	@Override
	public void run() {

final int PORT = 8060;
		
		ExecutorService executorService = Executors.newCachedThreadPool();

		try (ServerSocket serverSocket = new ServerSocket(PORT);) {

			System.out.println("Server started on port " + PORT);

			while (true) {
				Socket clientSocket = serverSocket.accept();
				executorService.execute(new ServerSocketThread(clientSocket));
			}
		} catch (IOException e) {
			System.err.println("Failed to start server on port " + PORT);
			e.printStackTrace();
		}
}
	

	public void kill() {

		try {
			executorService.shutdown();
			executorService.awaitTermination(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}