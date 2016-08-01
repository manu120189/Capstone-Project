
package sociallol.org.com.sociallol.api.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Team {

    @SerializedName("teamId")
    @Expose
    private Integer teamId;
    @SerializedName("winner")
    @Expose
    private Boolean winner;
    @SerializedName("firstBlood")
    @Expose
    private Boolean firstBlood;
    @SerializedName("firstTower")
    @Expose
    private Boolean firstTower;
    @SerializedName("firstInhibitor")
    @Expose
    private Boolean firstInhibitor;
    @SerializedName("firstBaron")
    @Expose
    private Boolean firstBaron;
    @SerializedName("firstDragon")
    @Expose
    private Boolean firstDragon;
    @SerializedName("firstRiftHerald")
    @Expose
    private Boolean firstRiftHerald;
    @SerializedName("towerKills")
    @Expose
    private Integer towerKills;
    @SerializedName("inhibitorKills")
    @Expose
    private Integer inhibitorKills;
    @SerializedName("baronKills")
    @Expose
    private Integer baronKills;
    @SerializedName("dragonKills")
    @Expose
    private Integer dragonKills;
    @SerializedName("riftHeraldKills")
    @Expose
    private Integer riftHeraldKills;
    @SerializedName("vilemawKills")
    @Expose
    private Integer vilemawKills;
    @SerializedName("dominionVictoryScore")
    @Expose
    private Integer dominionVictoryScore;
    @SerializedName("bans")
    @Expose
    private List<Ban> bans = new ArrayList<Ban>();

    /**
     * 
     * @return
     *     The teamId
     */
    public Integer getTeamId() {
        return teamId;
    }

    /**
     * 
     * @param teamId
     *     The teamId
     */
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    /**
     * 
     * @return
     *     The winner
     */
    public Boolean getWinner() {
        return winner;
    }

    /**
     * 
     * @param winner
     *     The winner
     */
    public void setWinner(Boolean winner) {
        this.winner = winner;
    }

    /**
     * 
     * @return
     *     The firstBlood
     */
    public Boolean getFirstBlood() {
        return firstBlood;
    }

    /**
     * 
     * @param firstBlood
     *     The firstBlood
     */
    public void setFirstBlood(Boolean firstBlood) {
        this.firstBlood = firstBlood;
    }

    /**
     * 
     * @return
     *     The firstTower
     */
    public Boolean getFirstTower() {
        return firstTower;
    }

    /**
     * 
     * @param firstTower
     *     The firstTower
     */
    public void setFirstTower(Boolean firstTower) {
        this.firstTower = firstTower;
    }

    /**
     * 
     * @return
     *     The firstInhibitor
     */
    public Boolean getFirstInhibitor() {
        return firstInhibitor;
    }

    /**
     * 
     * @param firstInhibitor
     *     The firstInhibitor
     */
    public void setFirstInhibitor(Boolean firstInhibitor) {
        this.firstInhibitor = firstInhibitor;
    }

    /**
     * 
     * @return
     *     The firstBaron
     */
    public Boolean getFirstBaron() {
        return firstBaron;
    }

    /**
     * 
     * @param firstBaron
     *     The firstBaron
     */
    public void setFirstBaron(Boolean firstBaron) {
        this.firstBaron = firstBaron;
    }

    /**
     * 
     * @return
     *     The firstDragon
     */
    public Boolean getFirstDragon() {
        return firstDragon;
    }

    /**
     * 
     * @param firstDragon
     *     The firstDragon
     */
    public void setFirstDragon(Boolean firstDragon) {
        this.firstDragon = firstDragon;
    }

    /**
     * 
     * @return
     *     The firstRiftHerald
     */
    public Boolean getFirstRiftHerald() {
        return firstRiftHerald;
    }

    /**
     * 
     * @param firstRiftHerald
     *     The firstRiftHerald
     */
    public void setFirstRiftHerald(Boolean firstRiftHerald) {
        this.firstRiftHerald = firstRiftHerald;
    }

    /**
     * 
     * @return
     *     The towerKills
     */
    public Integer getTowerKills() {
        return towerKills;
    }

    /**
     * 
     * @param towerKills
     *     The towerKills
     */
    public void setTowerKills(Integer towerKills) {
        this.towerKills = towerKills;
    }

    /**
     * 
     * @return
     *     The inhibitorKills
     */
    public Integer getInhibitorKills() {
        return inhibitorKills;
    }

    /**
     * 
     * @param inhibitorKills
     *     The inhibitorKills
     */
    public void setInhibitorKills(Integer inhibitorKills) {
        this.inhibitorKills = inhibitorKills;
    }

    /**
     * 
     * @return
     *     The baronKills
     */
    public Integer getBaronKills() {
        return baronKills;
    }

    /**
     * 
     * @param baronKills
     *     The baronKills
     */
    public void setBaronKills(Integer baronKills) {
        this.baronKills = baronKills;
    }

    /**
     * 
     * @return
     *     The dragonKills
     */
    public Integer getDragonKills() {
        return dragonKills;
    }

    /**
     * 
     * @param dragonKills
     *     The dragonKills
     */
    public void setDragonKills(Integer dragonKills) {
        this.dragonKills = dragonKills;
    }

    /**
     * 
     * @return
     *     The riftHeraldKills
     */
    public Integer getRiftHeraldKills() {
        return riftHeraldKills;
    }

    /**
     * 
     * @param riftHeraldKills
     *     The riftHeraldKills
     */
    public void setRiftHeraldKills(Integer riftHeraldKills) {
        this.riftHeraldKills = riftHeraldKills;
    }

    /**
     * 
     * @return
     *     The vilemawKills
     */
    public Integer getVilemawKills() {
        return vilemawKills;
    }

    /**
     * 
     * @param vilemawKills
     *     The vilemawKills
     */
    public void setVilemawKills(Integer vilemawKills) {
        this.vilemawKills = vilemawKills;
    }

    /**
     * 
     * @return
     *     The dominionVictoryScore
     */
    public Integer getDominionVictoryScore() {
        return dominionVictoryScore;
    }

    /**
     * 
     * @param dominionVictoryScore
     *     The dominionVictoryScore
     */
    public void setDominionVictoryScore(Integer dominionVictoryScore) {
        this.dominionVictoryScore = dominionVictoryScore;
    }

    /**
     * 
     * @return
     *     The bans
     */
    public List<Ban> getBans() {
        return bans;
    }

    /**
     * 
     * @param bans
     *     The bans
     */
    public void setBans(List<Ban> bans) {
        this.bans = bans;
    }

}
