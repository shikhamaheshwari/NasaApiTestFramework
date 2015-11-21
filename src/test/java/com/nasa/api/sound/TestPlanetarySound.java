package com.nasa.api.sound;

import com.nasa.api.util.ResponseParser;
import com.nasa.resource.PlanetarySound;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import org.junit.*;

import java.util.List;

/**
 * Created by Shikha on 11/19/2015.
 */
public class TestPlanetarySound {
    protected static Client client;
    protected WebResource webResource;
    protected ClientResponse clientResponse;

    @BeforeClass
    public static void classSetup() {
        client = Client.create();
    }

    @Before
    public void testSetup() {
        webResource = client.resource("https://api.nasa.gov/planetary/sounds");
    }

    @After
    public void teardown() {
        if (clientResponse != null) {
            clientResponse.close();
        }
    }

    @AfterClass
    public static void classTeardown() {
        if (client != null) {
            client.destroy();
        }
    }

    protected List<PlanetarySound> invokeApi() {
        List<PlanetarySound> soundList = null;
        clientResponse = webResource.accept("application/json").get(ClientResponse.class);

        if (clientResponse.getStatus() != 200) {
            Assert.fail("HTTP response code: " + clientResponse.getStatus());
        }

        try {
            soundList = ResponseParser.jsonToPlanetarySoundList(clientResponse.getEntity(String.class));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        return soundList;
    }

}