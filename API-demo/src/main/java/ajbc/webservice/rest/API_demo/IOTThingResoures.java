package ajbc.webservice.rest.API_demo;

import java.util.List;
import java.util.UUID;

import ajbc.webservice.rest.API_demo.beans.IOTThingFilterBean;
import ajbc.webservice.rest.Client.IOTThing;
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


@Path("thing")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class IOTThingResoures {
	
	private DBService dbService = new DBService();
	
	@GET
	@Path("/{id}")
	public Response getIotThingById(@PathParam("id") UUID id) {
		
		IOTThing iotThing =  dbService.getIotThingById(id);
		return Response.ok().entity(iotThing).build();
	}
	
	@GET
	public Response getIotThingByTypemodelmanufacturer(@BeanParam IOTThingFilterBean filterBean) {
		if(filterBean.getManufacturer() == null || filterBean.getModel() == null || filterBean.getType() == null)
			return Response.ok().entity(dbService.getAllIOTThing()).build();
		
		Type type = Utility.convertStringToType(filterBean.getType());
		List<IOTThing> iotThings = dbService.getIotThingByTypemodelmanufacturer(type, filterBean.getManufacturer(), filterBean.getModel());

		return Response.ok().entity(iotThings).build();
	}
	
	

}
