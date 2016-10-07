package com.beimin.eveapi.handler.eve;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.beimin.eveapi.handler.AbstractContentHandler;
import com.beimin.eveapi.model.eve.FactionStats;
import com.beimin.eveapi.model.eve.FactionWar;
import com.beimin.eveapi.response.eve.FacWarStatsResponse;

public class FacWarStatsHandler extends AbstractContentHandler {
    private FacWarStatsResponse response;
    private boolean factions;
    private boolean factionWars;

    @Override
    public void startDocument() throws SAXException {
        response = new FacWarStatsResponse();
    }

    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes attrs) throws SAXException {
        if (ELEMENT_ROWSET.equals(qName)) {
            final String name = getString(attrs, "name");
            factions = name.equals("factions");
            factionWars = name.equals("factionWars");
        } else if (ELEMENT_ROW.equals(qName)) {
            if (factions) {
                final FactionStats item = new FactionStats();
                item.setFactionID(getInt(attrs, "factionID"));
                item.setFactionName(getString(attrs, "factionName"));
                item.setPilots(getInt(attrs, "pilots"));
                item.setSystemsControlled(getInt(attrs, "systemsControlled"));
                item.setKillsYesterday(getInt(attrs, "killsYesterday"));
                item.setKillsLastWeek(getInt(attrs, "killsLastWeek"));
                item.setKillsTotal(getInt(attrs, "killsTotal"));
                item.setVictoryPointsYesterday(getInt(attrs, "victoryPointsYesterday"));
                item.setVictoryPointsLastWeek(getInt(attrs, "victoryPointsLastWeek"));
                item.setVictoryPointsTotal(getInt(attrs, "victoryPointsTotal"));
                response.addStat(item);
            } else if (factionWars) {
                final FactionWar item = new FactionWar();
                item.setFactionID(getInt(attrs, "factionID"));
                item.setFactionName(getString(attrs, "factionName"));
                item.setAgainstID(getInt(attrs, "againstID"));
                item.setAgainstName(getString(attrs, "againstName"));
                response.addStat(item);
            }
        }
        super.startElement(uri, localName, qName, attrs);
    }

    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        if ("killsYesterday".equals(qName)) {
            response.setKillsYesterday(getInt());
        } else if ("killsLastWeek".equals(qName)) {
            response.setKillsLastWeek(getInt());
        } else if ("killsTotal".equals(qName)) {
            response.setKillsTotal(getInt());
        } else if ("victoryPointsYesterday".equals(qName)) {
            response.setVictoryPointsYesterday(getInt());
        } else if ("victoryPointsLastWeek".equals(qName)) {
            response.setVictoryPointsLastWeek(getInt());
        } else if ("victoryPointsTotal".equals(qName)) {
            response.setVictoryPointsTotal(getInt());
        } else if (ELEMENT_ROWSET.equals(qName)) {
            factions = false;
            factionWars = false;
        }
        super.endElement(uri, localName, qName);
    }

    // @Override
    // protected Digester getDigester() {
    // Digester digester = super.getDigester();
    // digester.addBeanPropertySetter("eveapi/result/totals/killsYesterday");
    // digester.addBeanPropertySetter("eveapi/result/totals/killsLastWeek");
    // digester.addBeanPropertySetter("eveapi/result/totals/killsTotal");
    // digester.addBeanPropertySetter("eveapi/result/totals/victoryPointsYesterday");
    // digester.addBeanPropertySetter("eveapi/result/totals/victoryPointsLastWeek");
    // digester.addBeanPropertySetter("eveapi/result/totals/victoryPointsTotal");
    //
    // digester.addFactoryCreate("eveapi/result/rowset/row", new AbstractObjectCreationFactory() {
    // @Override
    // public Object createObject(Attributes attributes) throws Exception {
    // if (attributes.getValue("pilots") != null)
    // return new ApiFactionStats();
    // return new ApiFactionWar();
    // }
    // });
    // digester.addSetProperties("eveapi/result/rowset/row");
    // digester.addSetNext("eveapi/result/rowset/row", "addStat");
    //
    // return digester;
    // }

    @Override
    public FacWarStatsResponse getResponse() {
        return response;
    }
}