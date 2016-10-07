package com.beimin.eveapi.model.corporation;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class RoleHistory {
    private Date changeTime;
    private long characterID;
    private String characterName;
    private long issuerID;
    private String issuerName;
    private String roleLocationType;
    private final Set<SecurityRole> oldRoles = new HashSet<SecurityRole>();
    private final Set<SecurityRole> newRoles = new HashSet<SecurityRole>();

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(final Date changeTime) {
        this.changeTime = changeTime;
    }

    public long getCharacterID() {
        return characterID;
    }

    public void setCharacterID(final long characterID) {
        this.characterID = characterID;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(final String characterName) {
        this.characterName = characterName;
    }

    public long getIssuerID() {
        return issuerID;
    }

    public void setIssuerID(final long issuerID) {
        this.issuerID = issuerID;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(final String issuerName) {
        this.issuerName = issuerName;
    }

    public String getRoleLocationType() {
        return roleLocationType;
    }

    public void setRoleLocationType(final String roleLocationType) {
        this.roleLocationType = roleLocationType;
    }

    public Set<SecurityRole> getOldRoles() {
        return oldRoles;
    }

    public Set<SecurityRole> getNewRoles() {
        return newRoles;
    }

    public void addOldRole(final SecurityRole securityRole) {
        oldRoles.add(securityRole);
    }

    public void addNewRole(final SecurityRole securityRole) {
        newRoles.add(securityRole);
    }
}
