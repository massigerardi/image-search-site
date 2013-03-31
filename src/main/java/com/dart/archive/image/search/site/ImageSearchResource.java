/**
 * 
 */
package com.dart.archive.image.search.site;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yammer.metrics.annotation.Timed;

/**
 * @author Massimiliano.Gerardi
 *
 */
@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class ImageSearchResource {

	@Autowired
	ImageSearchServiceAdapter searchService;

	@GET
	@Timed
	public Response search(@QueryParam("image") String image) {
		System.out.println("search "+image);
		try {
			ImageSearchResults results = searchService.search(new File(image));
			return Response.status(Status.OK).entity(results).build();
		} finally {
			System.out.println("search done");
		}
	}

	@PUT
	@Path("/reload")
	@Timed
	public Response reload() {
		try {
			System.out.println("reload ");
			searchService.reload();
			return Response.status(Status.OK).entity("reload done").build();
		} finally {
			System.out.println("reload done");
		}
	}
}
