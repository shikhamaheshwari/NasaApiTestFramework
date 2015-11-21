package com.nasa.api.sound;

import com.nasa.resource.PlanetarySound;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by Shikha on 11/20/2015.
 */
public class TestSearchStringParam extends TestPlanetarySound {

    @Before
    public void testSetup() {
        super.testSetup();
        webResource = webResource.queryParam("api_key", "emzXfDHKeiwa5zDPfA3vbtW27A8QyvY1CYSzNNsZ");
    }

    @Test
    public void testDefaultSearchString() {
        List<PlanetarySound> soundList = invokeApi();
        Assert.assertTrue(soundList.size() > 0);
        Assert.assertTrue(soundList.size() <= 10);
    }

    @Test
    public void testEmptySearchString() {
        webResource = webResource.queryParam("q", "");
        List<PlanetarySound> soundList = invokeApi();
        Assert.assertTrue(soundList.size() > 0);
        Assert.assertTrue(soundList.size() <= 10);
    }

/*
    @Test
    public void testNullSearchString() {
        webResource = webResource.queryParam("q", null);
        List<PlanetarySound> soundList = invokeApi();
        Assert.assertTrue(soundList.size() > 0);
        Assert.assertTrue(soundList.size() <= 10);
    }
*/

    @Test
    public void testValidSearchString() {
        webResource = webResource.queryParam("q", "the");
        List<PlanetarySound> soundList = invokeApi();
        Assert.assertTrue(soundList.size() > 0);
        Assert.assertTrue(soundList.size() <= 10);
    }

    @Test
    public void testSpecialCharsSearchString() {
        webResource = webResource.queryParam("q", "?sd&df=as/def");
        List<PlanetarySound> soundList = invokeApi();
        Assert.assertTrue(soundList.size() > 0);
        Assert.assertTrue(soundList.size() <= 10);
    }

}
