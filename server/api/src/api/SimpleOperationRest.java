package api;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Operation;
 
@Path("/operationAPI")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SimpleOperationRest {
	
	@POST
	public Response postOperationResult(String json) {
		Operation operation;
		Response response;
		String jsonResponse;
		
		json = json.replaceAll("\r?\n", "");
		Jsonb jsonb = JsonbBuilder.create();
		
		try {
			//De-serializing JSON
			operation = jsonb.fromJson(json,Operation.class);
		}catch(Exception e) {
			//if JSON received is not correct returns bad request
			return Response.status(400).build();
		}
		
		if(operation == null) {
			response =Response.status(400).build(); //returns just bad request
		}else {
			jsonResponse = jsonb.toJson(operation);
			
			if(operation.getError() != null) {
				//returns bad request and the json (with info about if is complete or and error)
				response = Response.status(400).entity(jsonResponse).type(MediaType.APPLICATION_JSON).build();
			}else {
				//returns json with values
				response = Response.ok(jsonResponse,MediaType.APPLICATION_JSON).build();
			}
		}
		
		return response;
	 }
}
