package com.nasa.api.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasa.resource.PlanetarySound;
import com.nasa.resource.PlanetarySounds;

import java.io.IOException;
import java.util.List;

/**
 * Created by Shikha on 11/20/2015.
 */
public class ResponseParser {

    public static PlanetarySounds jsonToPlanetarySounds(String json) throws JsonMappingException, JsonParseException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, PlanetarySounds.class);
    }

    public static List<PlanetarySound> jsonToPlanetarySoundList(String json) throws JsonMappingException, JsonParseException, IOException {
        return jsonToPlanetarySounds(json).getResults();
    }
}
