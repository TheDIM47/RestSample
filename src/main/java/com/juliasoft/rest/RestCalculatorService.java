package com.juliasoft.rest;

import javax.inject.Inject;
import javax.inject.Scope;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * REST API for Calculator
 */
@Path("/")
public class RestCalculatorService {
    @Inject
    private MathService svc;

    @GET
    @Path("/add/{values:.+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(@PathParam("values") List<PathSegment> values) {
        final double[] v = values.stream().mapToDouble(s -> Double.parseDouble(s.getPath())).toArray();
        final Response.ResponseBuilder builder = Response.ok().entity(new RestResult(svc.add(v)));
        builder.cacheControl(CACHE_CONTROL);
        return builder.build();
    }

    @GET
    @Path("/subtract/{values:.+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response subtract(@PathParam("values") List<PathSegment> values) {
        final double[] v = values.stream().mapToDouble(s -> Double.parseDouble(s.getPath())).toArray();
        final Response.ResponseBuilder builder = Response.ok().entity(new RestResult(svc.subtract(v)));
        builder.cacheControl(CACHE_CONTROL);
        return builder.build();
    }

    @GET
    @Path("/multiply/{values:.+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response multiply(@PathParam("values") List<PathSegment> values) {
        final double[] v = values.stream().mapToDouble(s -> Double.parseDouble(s.getPath())).toArray();
        final Response.ResponseBuilder builder = Response.ok().entity(new RestResult(svc.multiply(v)));
        builder.cacheControl(CACHE_CONTROL);
        return builder.build();
    }

    @GET
    @Path("/divide/{values:.+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response divide(@PathParam("values") List<PathSegment> values) {
        final double[] v = values.stream().mapToDouble(s -> Double.parseDouble(s.getPath())).toArray();
        final Response.ResponseBuilder builder = Response.ok().entity(new RestResult(svc.divide(v)));
        builder.cacheControl(CACHE_CONTROL);
        return builder.build();
    }

    private static final int AGE = 60 * 1000; // 60 seconds
    private static final CacheControl CACHE_CONTROL = new CacheControl() {{
        setNoCache(false);
        setPrivate(false);
        setMaxAge(AGE);
    }};
}
