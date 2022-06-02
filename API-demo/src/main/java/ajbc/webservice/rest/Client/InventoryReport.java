package ajbc.webservice.rest.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import com.google.gson.Gson;

public class InventoryReport implements Runnable {

	private Random rand = new Random();
	private Socket clientSocket = null;
	private PrintWriter writer = null;
	private BufferedReader bufferReader = null;
	private CountDownLatch latch ;
	private IOTThing iotThing;
	private final String[] models = {"big", "small", "strong"};
	private final String[] manufacturers = {"Yapan", "Israel", "China"};

	private final static String SERVER_NAME = "localhost";
	private final static int SERVER_PORT = 8060;
	private static final int OPTION = 2;
	private static final int MAX_READING = 80;
	public static Gson gson = new Gson();


	public InventoryReport(UUID id, Type type, String model, String manufacturer, List<Device> devices, CountDownLatch latch) {
		iotThing = new IOTThing(id, type, model, manufacturer, devices);
		this.latch = latch;
	}
	
	public InventoryReport() {}

	@Override
	public void run() {
		simulateInventoryChange();

		try {
			latch.countDown();
			latch.await();
			clientSocket = new Socket(SERVER_NAME, SERVER_PORT);
			System.out.println("Connected to server");

			// sending data
			String jsonIOTThing = gson.toJson(iotThing);
			writer = new PrintWriter(clientSocket.getOutputStream(), true);
			writer.println(jsonIOTThing);

			// create reader
			bufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			// reading data
			String line = bufferReader.readLine();
			System.out.println("server says: " + line );
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void simulateInventoryChange(){
		
		int rundInt1 = rand.nextInt(OPTION);
		switch(rundInt1) {
		case 0:
			removeDevice();
			break;
		case 1:
			iotThing.getDevices().add(createDevice());
			break;
		}
		
		
	}
	
	private void removeDevice() {
		if(iotThing.getDevices().size()>0) {
			int rundInt2 = rand.nextInt(0,iotThing.getDevices().size());
			iotThing.getDevices().remove(rundInt2);
			iotThing.setDevices(iotThing.getDevices());
			}
	}

	private Device createDevice() {
		Type[] types = Type.values();
		int rundInt = rand.nextInt(0,types.length);
		int rundReading = rand.nextInt(0,MAX_READING);
		return new Device(UUID.randomUUID(), types[rundInt], models[rundInt], manufacturers[rundInt], rundReading);
	}


}
