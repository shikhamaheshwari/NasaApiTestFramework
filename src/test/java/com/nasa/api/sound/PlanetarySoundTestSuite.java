package com.nasa.api.sound;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Shikha on 11/20/2015.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestPlanetarySound.class,
        TestSearchStringParam.class,
        TestLimitParam.class,
        TestApiKeyParam.class
})
public class PlanetarySoundTestSuite {
}
