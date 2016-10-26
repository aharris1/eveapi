package com.beimin.eveapi.character.mail;

import org.junit.Test;

import com.beimin.eveapi.AbstractOnlineTest;
import com.beimin.eveapi.parser.pilot.MailMessagesParser;
import com.beimin.eveapi.response.pilot.MailMessagesResponse;

public class MailMessagesParserOnlineTest extends AbstractOnlineTest {

    @Test
    public void getResponse() throws Exception {
        addIgnoreElement("row");
        addNullOk("getCharacterIDs"); //not to character
        addEmptyOK("getToCharacterIDs"); //not to character
        addEmptyOK("getToListIDs"); //not to list
        addNullOk("getListIDs"); //not to list
        addNullOk("getToCorpOrAllianceID"); //not to corp or alliance
        
        final MailMessagesParser parser = new MailMessagesParser();
        prepareParser(parser);

        final MailMessagesResponse response = parser.getResponse(getCharacter());

        testResponse(response);
    }

}
