package com.juliasoft.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.juliasoft.rest.CachedCalculatorService;
import com.juliasoft.rest.MathService;
import com.juliasoft.rest.RestCalculatorService;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests for Calculator REST API
 */
public class RestServiceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(RestCalculatorService.class)
                .register(new AbstractBinder() {
                    @Override
                    protected void configure() {
                        bind(CachedCalculatorService.class).to(MathService.class);
                    }
                });
    }

    @Test
    public void testAddNone() {
        final Response response = target("/add").request().get();
        assertThat(response.getStatus(), is(404));
    }

    @Test(expected = InternalServerErrorException.class)
    public void testAddSingle() {
        final JsonNode json = target("/add/1").request().get(JsonNode.class);
        System.out.println(json);
    }

    @Test
    public void testAddDouble() {
        final Response response = target("/add/1/2").request().get();
        assertThat(response.getStatus(), is(200));
        final JsonNode json = response.readEntity(JsonNode.class);
        assertThat(json.get("data").asDouble(), is(3.0));
    }

    @Test
    public void testAddWrongValues() {
        final Response response = target("/add/1/2/xxx").request().get();
        assertThat(response.getStatus(), is(500));
    }

    @Test
    public void testAddSuccess() {
        final JsonNode json = target("/add/1/2/3").request().get(JsonNode.class);
        assertThat(json.get("data").asDouble(), is(6.0));
    }

    @Test
    public void testRepeat() {
        final String path = "/add/3/2/1";
        assertThat(target(path).request().get(JsonNode.class).get("data").asDouble(), is(6.0));
        assertThat(target(path).request().get(JsonNode.class).get("data").asDouble(), is(6.0));
        assertThat(target(path).request().get(JsonNode.class).get("data").asDouble(), is(6.0));
    }

    @Test
    public void testAddSuccessMany() {
        final int[] data = new Random().ints(20, -100, 100).toArray();
        final String p = StringUtils.join(data, '/');
        final JsonNode json = target("/add/" + p).request().get(JsonNode.class);
        final double sum = Arrays.stream(data).sum();
        assertThat(json.get("data").asDouble(), is(sum));
    }

    @Test
    public void test404() {
        final Response output = target("/qweasdzxc").request().get();
        assertThat(output.getStatus(), is(404));
    }
}
