package com.nasa.api.sound;

import com.nasa.resource.PlanetarySound;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by Shikha on 11/20/2015.
 */
public class TestLimitParam extends TestPlanetarySound {

    @Before
    public void testSetup() {
        super.testSetup();
        webResource = webResource.queryParam("api_key", "emzXfDHKeiwa5zDPfA3vbtW27A8QyvY1CYSzNNsZ");
    }

    @Test
    public void testDefaultLimit() {
        List<PlanetarySound> soundList = invokeApi();
        Assert.assertTrue(soundList.size() > 0);
        Assert.assertTrue(soundList.size() <= 10);
    }

    @Test
    public void testLimitSetToZero() {
        webResource = webResource.queryParam("limit", "0");
        List<PlanetarySound> soundList = invokeApi();
        Assert.assertTrue(soundList.size() == 0);
    }

    @Test
    public void testLimitSetToOne() {
        webResource = webResource.queryParam("limit", "1");
        List<PlanetarySound> soundList = invokeApi();
        Assert.assertTrue(soundList.size() == 1);
    }

    @Test
    public void testLimitSetToNegativeInteger() {
        webResource = webResource.queryParam("limit", "-14");
        List<PlanetarySound> soundList = invokeApi();
        Assert.assertTrue(soundList.size() > 0);
        Assert.assertTrue(soundList.size() <= 10);
    }

    @Test
    public void testLimitSetToHighestInteger() {
        webResource = webResource.queryParam("limit", Integer.MAX_VALUE + "");
        List<PlanetarySound> soundList = invokeApi();
        Assert.assertTrue(soundList.size() <= Integer.MAX_VALUE);
    }

    @Test
    public void testLimitSetToLowestInteger() {
        webResource = webResource.queryParam("limit", Integer.MIN_VALUE + "");
        List<PlanetarySound> soundList = invokeApi();
        Assert.assertTrue(soundList.size() > 0);
        Assert.assertTrue(soundList.size() <= 10);
    }

    @Test
    public void testLimitSetToEmpty() {
        webResource = webResource.queryParam("limit", "");
        List<PlanetarySound> soundList = invokeApi();
        Assert.assertTrue(soundList.size() > 0);
        Assert.assertTrue(soundList.size() <= 10);
    }

    @Test
    public void testLimitSetToString() {
        webResource = webResource.queryParam("limit", "string");
        List<PlanetarySound> soundList = invokeApi();
        Assert.assertTrue(soundList.size() > 0);
        Assert.assertTrue(soundList.size() <= 10);
    }

}
