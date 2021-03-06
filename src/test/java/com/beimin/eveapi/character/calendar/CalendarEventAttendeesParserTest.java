package com.beimin.eveapi.character.calendar;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.beimin.eveapi.exception.ApiException;
import com.beimin.eveapi.model.character.CalendarEventAttendee;
import com.beimin.eveapi.model.character.CalendarEventStatus;
import com.beimin.eveapi.parser.ApiPage;
import com.beimin.eveapi.parser.ApiPath;
import com.beimin.eveapi.parser.character.CalendarEventAttendeesParser;
import com.beimin.eveapi.response.character.CalendarEventAttendeesResponse;
import com.beimin.eveapi.utils.FullAuthParserTest;

public class CalendarEventAttendeesParserTest extends FullAuthParserTest {
    public CalendarEventAttendeesParserTest() {
        super(ApiPath.CHARACTER, ApiPage.CALENDAR_EVENT_ATTENDEES);
    }

    @Test
    public void getResponse() throws ApiException {
        final CalendarEventAttendeesParser parser = new CalendarEventAttendeesParser();
        final CalendarEventAttendeesResponse response = parser.getResponse(auth, 133918L);
        final List<CalendarEventAttendee> attendees = response.getAll();
        assertEquals(1, attendees.size());
        final CalendarEventAttendee event = attendees.iterator().next();
        assertEquals(133918L, event.getEventID());
        assertEquals(1380128241L, event.getCharacterID());
        assertEquals("Zy'or Tealon", event.getCharacterName());
        assertEquals(CalendarEventStatus.ACCEPTED, event.getResponse());
    }

    @Override
    public void extraAsserts(final Map<String, String> req) {
        super.extraAsserts(req);
        assertEquals("133918", req.get("eventIDs"));
    }
}
