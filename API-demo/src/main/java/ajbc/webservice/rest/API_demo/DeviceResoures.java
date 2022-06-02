package ajbc.webservice.rest.API_demo;

import java.util.UUID;

import ajbc.webservice.rest.API_demo.beans.IOTThingFilterBean;
import ajbc.webservice.rest.Client.Device;
import ajbc.webservice.rest.Client.Type;
import ajbc.webservice.rest.Client.Utility;
import ajbc.webservice.rest.DB.DBService;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("device")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class DeviceResoures {
	
	private DBService dbService = new DBService();
	
	@GET
	@Path("/{id}")
	public Response getDeviceById(@PathParam("id") UUID id, @BeanParam IOTThingFilterBean filterBean) {
		Device device;
		if(filterBean.getManufacturer() == null || filterBean.getModel() == null || filterBean.getType() == null) {
			device =  dbService.getDeviceById(id);
			System.out.println(device);
			
		}
		else {
			Type type = Utility.convertStringToType(filterBean.getType());
			device = dbService.getDeviceByTypeModelManufacturerThingId(id, type, filterBean.getManufacturer(), filterBean.getModel());
			System.out.println(device);
		}
		return Response.ok().entity(device).build();
		
	}
	
	
	
	

}
