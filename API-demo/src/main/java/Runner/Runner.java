package Runner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import ajbc.webservice.rest.Client.Device;
import ajbc.webservice.rest.Client.InventoryReport;
import ajbc.webservice.rest.Client.Type;

public class Runner {
	private static final int NUM_CLIENTS = 1;
	

	public static void main(String[] args) {
		
		ExecutorService clientsService = Executors.newFixedThreadPool(NUM_CLIENTS);
		CountDownLatch latch = new CountDownLatch(NUM_CLIENTS);
		
		UUID uuid1 = UUID.nameUUIDFromBytes("testname".getBytes());
		UUID uuid2 = UUID.nameUUIDFromBytes("testdevice".getBytes());
		System.out.println("uuid1 "+uuid1);
		System.out.println("uuid2 "+uuid2);
		
		List<Device> devices1 = new ArrayList<Device>();
		devices1.add(new Device(uuid2, Type.CONTROLLER, "web1", "manufa1", 90.0));
		devices1.add(new Device(UUID.randomUUID(), Type.CONTROLLER, "web2", "manufa2", 76));

		while(true) {
			sleep(10000);
			clientsService.execute(new InventoryReport(uuid1, Type.ACTUATOR, "model", "manufactor", devices1, latch));
		}
	}

	private static void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
