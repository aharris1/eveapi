package com.beimin.eveapi.model.eve;

import java.util.ArrayList;
import java.util.List;

public abstract class FacWarTopStats<K extends KillStat, VP extends VictoryPointsStat, E extends FacWarStat> {
    private final List<K> killsYesterday = new ArrayList<K>();
    private final List<K> killsLastWeek = new ArrayList<K>();
    private final List<K> killsTotal = new ArrayList<K>();
    private final List<VP> victoryPointsYesterday = new ArrayList<VP>();
    private final List<VP> victoryPointsLastWeek = new ArrayList<VP>();
    private final List<VP> victoryPointsTotal = new ArrayList<VP>();

    public void addYesterday(final K stat) {
        killsYesterday.add(stat);
    }

    public void addLastWeek(final K stat) {
        killsLastWeek.add(stat);
    }

    public void addTotal(final K stat) {
        killsTotal.add(stat);
    }

    public void addYesterday(final VP stat) {
        victoryPointsYesterday.add(stat);
    }

    public void addLastWeek(final VP stat) {
        victoryPointsLastWeek.add(stat);
    }

    public void addTotal(final VP stat) {
        victoryPointsTotal.add(stat);
    }

    public List<K> getKillsYesterday() {
        return killsYesterday;
    }

    public List<K> getKillsLastWeek() {
        return killsLastWeek;
    }

    public List<K> getKillsTotal() {
        return killsTotal;
    }

    public List<VP> getVictoryPointsYesterday() {
        return victoryPointsYesterday;
    }

    public List<VP> getVictoryPointsLastWeek() {
        return victoryPointsLastWeek;
    }

    public List<VP> getVictoryPointsTotal() {
        return victoryPointsTotal;
    }
}
