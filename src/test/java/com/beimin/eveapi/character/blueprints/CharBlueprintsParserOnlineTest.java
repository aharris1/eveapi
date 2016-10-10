package com.beimin.eveapi.character.blueprints;

import org.junit.Test;

import com.beimin.eveapi.AbstractOnlineTest;
import com.beimin.eveapi.parser.pilot.CharBlueprintsParser;
import com.beimin.eveapi.response.shared.BlueprintsResponse;

public class CharBlueprintsParserOnlineTest extends AbstractOnlineTest {

    @Test
    public void blueprintsParser() throws Exception {
        final CharBlueprintsParser parser = new CharBlueprintsParser();
        final BlueprintsResponse response = parser.getResponse(getPilot());
        testResponse(response);
    }

}