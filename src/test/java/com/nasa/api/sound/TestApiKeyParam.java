package com.nasa.api.sound;

import com.nasa.resource.PlanetarySound;
import com.sun.jersey.api.client.ClientResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * Created by Shikha on 11/20/2015.
 */
public class TestApiKeyParam extends TestPlanetarySound {

    @Before
    public void testSetup() {
        super.testSetup();
    }

    @Test
    public void testDefaultApiKey() {
        List<PlanetarySound> soundList = invokeApi();
        Assert.assertTrue(soundList.size() > 0);
        Assert.assertTrue(soundList.size() <= 10);
    }

    @Test
    public void testEmptyApiKey() {
        webResource = webResource.queryParam("api_key", "");
        invokeApiWithInvalidApiKey();
    }

    @Test
    public void testValidApiKey() {
        webResource = webResource.queryParam("api_key", "emzXfDHKeiwa5zDPfA3vbtW27A8QyvY1CYSzNNsZ");
        List<PlanetarySound> soundList = invokeApi();
        Assert.assertTrue(soundList.size() > 0);
        Assert.assertTrue(soundList.size() <= 10);
    }

    @Test
    public void testInvalidApiKey() {
        webResource = webResource.queryParam("api_key", "11111emzXfDHKeiwa5zDPfA3vbtW27A8QyvY1CYSzNNsZ");
        invokeApiWithInvalidApiKey();
    }

    @Test
    public void testDemoApiKey() {
        webResource = webResource.queryParam("api_key", "DEMO_KEY");
        List<PlanetarySound> soundList = invokeApi();
        Assert.assertTrue(soundList.size() > 0);
        Assert.assertTrue(soundList.size() <= 10);
    }

    @Test
    @Ignore
    public void testDemoApiKeyHourlyLimit() {
        webResource = webResource.queryParam("api_key", "DEMO_KEY");
        for (int i=0 ; i<=30 ; i++) {
            System.out.println("i = " + i);
            if (i == 30) {
                System.out.println("inside i = 30 if-block, i is " + i);
                clientResponse = webResource.accept("application/json").get(ClientResponse.class);

                if (clientResponse.getStatus() != 403) {
                    Assert.fail("HTTP response code: " + clientResponse.getStatus());
                }
            } else {
                List<PlanetarySound> soundList = invokeApi();
                Assert.assertTrue(soundList.size() > 0);
                Assert.assertTrue(soundList.size() <= 10);
            }
        }
    }

    @Test
    @Ignore
    public void testValidApiKeyHourlyLimit() {
        webResource = webResource.queryParam("api_key", "emzXfDHKeiwa5zDPfA3vbtW27A8QyvY1CYSzNNsZ");
        for (int i=0 ; i<=1000 ; i++) {
            System.out.println("i = " + i);
            if (i == 1000) {
                System.out.println("inside i = 1000 if-block, i is " + i);
                clientResponse = webResource.accept("application/json").get(ClientResponse.class);

                if (clientResponse.getStatus() != 403) {
                    Assert.fail("HTTP response code: " + clientResponse.getStatus());
                }
            } else {
                List<PlanetarySound> soundList = invokeApi();
                Assert.assertTrue(soundList.size() > 0);
                Assert.assertTrue(soundList.size() <= 10);
            }
        }
    }

    private void invokeApiWithInvalidApiKey() {
        clientResponse = webResource.accept("application/json").get(ClientResponse.class);

        if (clientResponse.getStatus() == 200) {
            Assert.fail("HTTP response code: " + clientResponse.getStatus());
        }
    }
}
