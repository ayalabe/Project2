package Runner;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import ajbc.webservice.rest.Client.Device;
import ajbc.webservice.rest.Client.InventoryReport;
import ajbc.webservice.rest.Client.Type;
import ajbc.webservice.rest.DB.DBMock;
import ajbc.webservice.rest.DB.DBService;
import ajbc.webservice.rest.Server.ServerThread;

public class Runner {
	private static final int PORT = 8060, NUM_CLIENTS = 1;
	

	public static void main(String[] args) {
		
		ExecutorService clientsService = Executors.newFixedThreadPool(NUM_CLIENTS);
		CountDownLatch latch = new CountDownLatch(NUM_CLIENTS);
		
		UUID uuid1 = UUID.nameUUIDFromBytes("testname".getBytes());
		UUID uuid2 = UUID.nameUUIDFromBytes("testdevice".getBytes());
		UUID uuid3 = UUID.nameUUIDFromBytes("testname1".getBytes());
		UUID uuid4 = UUID.nameUUIDFromBytes("testdevice1".getBytes());
		
		System.out.println("uuid1 "+uuid1);
		System.out.println("uuid2 "+uuid2);
		
		List<Device> devices1 = new ArrayList<Device>();
		devices1.add(new Device(uuid2, Type.CONTROLLER, "web1", "manufa1", 90.0));
		devices1.add(new Device(UUID.randomUUID(), Type.CONTROLLER, "web2", "manufa2", 76));
//		devices1.add(new Device(UUID.randomUUID(), Type.CONTROLLER, "web3", "manufa3", 58.9));
//		devices1.add(new Device(UUID.randomUUID(), Type.CONTROLLER, "web4", "manufa4", 4.9));
//		devices1.add(new Device(UUID.nameUUIDFromBytes("ayala".getBytes()), Type.CONTROLLER, "web", "manufa", 90.0));
		
		
//		List<Device> devices2 = new ArrayList<Device>();
//		devices2.add(new Device(uuid2, Type.CONTROLLER, "serv1", "manufa1", 60.0));
//		devices2.add(new Device(UUID.randomUUID(), Type.CONTROLLER, "serv2", "manufa2", 15));
//		devices2.add(new Device(UUID.randomUUID(), Type.CONTROLLER, "serv3", "manufa3", 190.9));
//		devices2.add(new Device(UUID.randomUUID(), Type.CONTROLLER, "serv4", "manufa4", 56.9));
//		devices2.add(new Device(UUID.nameUUIDFromBytes("a".getBytes()), Type.SENSOR, "sensor", "manufactorSensor", 6.0));
//		
//		
//		List<Device> devices3 = new ArrayList<Device>();
//		devices3.add(new Device(uuid4, Type.SENSOR, "serv1", "manufa1", 7.0));
//		devices3.add(new Device(UUID.randomUUID(), Type.SENSOR, "serv2", "manufa2", 165));
//		devices3.add(new Device(UUID.randomUUID(), Type.CONTROLLER, "serv3", "manufa3", 120.9));
		
		
		
//		List<InventoryReport> inventoryReports = new ArrayList();
//		inventoryReports.add(new InventoryReport(uuid1, Type.ACTUATOR, "model", "manufactor", devices1, latch));
//		inventoryReports.add(new InventoryReport(UUID.nameUUIDFromBytes("b".getBytes()), Type.SENSOR, "sensor", "manufactorSensor", devices2, latch));
//		inventoryReports.add(new InventoryReport(UUID.nameUUIDFromBytes("c".getBytes()), Type.SENSOR, "sensor", "manufactorSensor", devices2, latch));
		
//		for (InventoryReport inventoryReport : inventoryReports) {
//			clientsService.execute(inventoryReport);
//		}
		
//		clientsService.execute(new InventoryReport(UUID.randomUUID(), Type.ACTUATOR, "model", "manufactor", devices1, latch));
//		DBService.printDB();
		

		while(true) {
			sleep(10000);
			clientsService.execute(new InventoryReport(uuid1, Type.ACTUATOR, "model", "manufactor", devices1, latch));
		}





	}


	private static void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
