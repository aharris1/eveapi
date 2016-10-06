package com.beimin.eveapi.handler.eve;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.beimin.eveapi.handler.AbstractContentHandler;
import com.beimin.eveapi.model.eve.CharacterKills;
import com.beimin.eveapi.model.eve.CharacterStat;
import com.beimin.eveapi.model.eve.CharacterVictoryPoints;
import com.beimin.eveapi.model.eve.CorporationKills;
import com.beimin.eveapi.model.eve.CorporationStat;
import com.beimin.eveapi.model.eve.CorporationVictoryPoints;
import com.beimin.eveapi.model.eve.FactionKills;
import com.beimin.eveapi.model.eve.FactionStat;
import com.beimin.eveapi.model.eve.FactionVictoryPoints;
import com.beimin.eveapi.model.eve.KillStat;
import com.beimin.eveapi.model.eve.VictoryPointsStat;
import com.beimin.eveapi.response.ApiResponse;
import com.beimin.eveapi.response.eve.FacWarTopStatsResponse;

public class FacWarTopStatsHandler extends AbstractContentHandler {
    private FacWarTopStatsResponse response;
    private boolean characters;
    private boolean corporations;
    private boolean factions;
    private boolean killsYesterday;
    private boolean killsLastWeek;
    private boolean killsTotal;
    private boolean victoryPointsYesterday;
    private boolean victoryPointsLastWeek;
    private boolean victoryPointsTotal;

    @Override
    public void startDocument() throws SAXException {
        response = new FacWarTopStatsResponse();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
        if ("characters".equals(qName))
            characters = true;
        else if ("corporations".equals(qName))
            corporations = true;
        else if ("factions".equals(qName))
            factions = true;
        else if (ELEMENT_ROWSET.equals(qName)) {
            String name = getString(attrs, "name");
            killsYesterday = name.equals("KillsYesterday");
            killsLastWeek = name.equals("KillsLastWeek");
            killsTotal = name.equals("KillsTotal");
            victoryPointsYesterday = name.equals("VictoryPointsYesterday");
            victoryPointsLastWeek = name.equals("VictoryPointsLastWeek");
            victoryPointsTotal = name.equals("VictoryPointsTotal");
        } else if (ELEMENT_ROW.equals(qName)) {
            if (characters) {
                if (killsYesterday)
                    response.addYesterday(getCharKill(attrs));
                else if (killsLastWeek)
                    response.addLastWeek(getCharKill(attrs));
                else if (killsTotal)
                    response.addTotal(getCharKill(attrs));
                else if (victoryPointsYesterday)
                    response.addYesterday(getCharVP(attrs));
                else if (victoryPointsLastWeek)
                    response.addLastWeek(getCharVP(attrs));
                else if (victoryPointsTotal)
                    response.addTotal(getCharVP(attrs));
            } else if (corporations) {
                if (killsYesterday)
                    response.addYesterday(getCorpKill(attrs));
                else if (killsLastWeek)
                    response.addLastWeek(getCorpKill(attrs));
                else if (killsTotal)
                    response.addTotal(getCorpKill(attrs));
                else if (victoryPointsYesterday)
                    response.addYesterday(getCorpVP(attrs));
                else if (victoryPointsLastWeek)
                    response.addLastWeek(getCorpVP(attrs));
                else if (victoryPointsTotal)
                    response.addTotal(getCorpVP(attrs));
            } else if (factions) {
                if (killsYesterday)
                    response.addYesterday(getFactionKill(attrs));
                else if (killsLastWeek)
                    response.addLastWeek(getFactionKill(attrs));
                else if (killsTotal)
                    response.addTotal(getFactionKill(attrs));
                else if (victoryPointsYesterday)
                    response.addYesterday(getFactionVP(attrs));
                else if (victoryPointsLastWeek)
                    response.addLastWeek(getFactionVP(attrs));
                else if (victoryPointsTotal)
                    response.addTotal(getFactionVP(attrs));
            }
        } else
            super.startElement(uri, localName, qName, attrs);
    }

    private void setCharacterDetails(Attributes attrs, CharacterStat stat) {
        stat.setCharacterID(getLong(attrs, "characterID"));
        stat.setCharacterName(getString(attrs, "characterName"));
    }

    private void setCorporationDetails(Attributes attrs, CorporationStat stat) {
        stat.setCorporationID(getLong(attrs, "corporationID"));
        stat.setCorporationName(getString(attrs, "corporationName"));
    }

    private void setFactionDetails(Attributes attrs, FactionStat stat) {
        stat.setFactionID(getInt(attrs, "factionID"));
        stat.setFactionName(getString(attrs, "factionName"));
    }

    private void setKills(Attributes attrs, KillStat stat) {
        stat.setKills(getInt(attrs, "kills"));
    }

    private void setVictoryPoint(Attributes attrs, VictoryPointsStat stat) {
        stat.setVictoryPoints(getInt(attrs, "victoryPoints"));
    }

    private CharacterKills getCharKill(Attributes attrs) {
        CharacterKills stat = new CharacterKills();
        setCharacterDetails(attrs, stat);
        setKills(attrs, stat);
        return stat;
    }

    private CharacterVictoryPoints getCharVP(Attributes attrs) {
        CharacterVictoryPoints stat = new CharacterVictoryPoints();
        setCharacterDetails(attrs, stat);
        setVictoryPoint(attrs, stat);
        return stat;
    }

    private CorporationKills getCorpKill(Attributes attrs) {
        CorporationKills stat = new CorporationKills();
        setCorporationDetails(attrs, stat);
        setKills(attrs, stat);
        return stat;
    }

    private CorporationVictoryPoints getCorpVP(Attributes attrs) {
        CorporationVictoryPoints stat = new CorporationVictoryPoints();
        setCorporationDetails(attrs, stat);
        setVictoryPoint(attrs, stat);
        return stat;
    }

    private FactionKills getFactionKill(Attributes attrs) {
        FactionKills stat = new FactionKills();
        setFactionDetails(attrs, stat);
        setKills(attrs, stat);
        return stat;
    }

    private FactionVictoryPoints getFactionVP(Attributes attrs) {
        FactionVictoryPoints stat = new FactionVictoryPoints();
        setFactionDetails(attrs, stat);
        setVictoryPoint(attrs, stat);
        return stat;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("characters".equals(qName))
            characters = false;
        else if ("corporations".equals(qName))
            corporations = false;
        else if ("factions".equals(qName))
            corporations = false;
        else
            super.endElement(uri, localName, qName);
    }

    @Override
    public ApiResponse getResponse() {
        return response;
    }
}