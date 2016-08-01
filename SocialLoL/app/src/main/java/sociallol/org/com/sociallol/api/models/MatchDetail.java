
package sociallol.org.com.sociallol.api.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class MatchDetail {

    @SerializedName("matchId")
    @Expose
    private Integer matchId;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("platformId")
    @Expose
    private String platformId;
    @SerializedName("matchMode")
    @Expose
    private String matchMode;
    @SerializedName("matchType")
    @Expose
    private String matchType;
    @SerializedName("matchCreation")
    @Expose
    private Integer matchCreation;
    @SerializedName("matchDuration")
    @Expose
    private Integer matchDuration;
    @SerializedName("queueType")
    @Expose
    private String queueType;
    @SerializedName("mapId")
    @Expose
    private Integer mapId;
    @SerializedName("season")
    @Expose
    private String season;
    @SerializedName("matchVersion")
    @Expose
    private String matchVersion;
    @SerializedName("participants")
    @Expose
    private List<Participant> participants = new ArrayList<Participant>();
    @SerializedName("participantIdentities")
    @Expose
    private List<ParticipantIdentity> participantIdentities = new ArrayList<ParticipantIdentity>();
    @SerializedName("teams")
    @Expose
    private List<Team> teams = new ArrayList<Team>();

    /**
     * 
     * @return
     *     The matchId
     */
    public Integer getMatchId() {
        return matchId;
    }

    /**
     * 
     * @param matchId
     *     The matchId
     */
    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    /**
     * 
     * @return
     *     The region
     */
    public String getRegion() {
        return region;
    }

    /**
     * 
     * @param region
     *     The region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * 
     * @return
     *     The platformId
     */
    public String getPlatformId() {
        return platformId;
    }

    /**
     * 
     * @param platformId
     *     The platformId
     */
    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    /**
     * 
     * @return
     *     The matchMode
     */
    public String getMatchMode() {
        return matchMode;
    }

    /**
     * 
     * @param matchMode
     *     The matchMode
     */
    public void setMatchMode(String matchMode) {
        this.matchMode = matchMode;
    }

    /**
     * 
     * @return
     *     The matchType
     */
    public String getMatchType() {
        return matchType;
    }

    /**
     * 
     * @param matchType
     *     The matchType
     */
    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    /**
     * 
     * @return
     *     The matchCreation
     */
    public Integer getMatchCreation() {
        return matchCreation;
    }

    /**
     * 
     * @param matchCreation
     *     The matchCreation
     */
    public void setMatchCreation(Integer matchCreation) {
        this.matchCreation = matchCreation;
    }

    /**
     * 
     * @return
     *     The matchDuration
     */
    public Integer getMatchDuration() {
        return matchDuration;
    }

    /**
     * 
     * @param matchDuration
     *     The matchDuration
     */
    public void setMatchDuration(Integer matchDuration) {
        this.matchDuration = matchDuration;
    }

    /**
     * 
     * @return
     *     The queueType
     */
    public String getQueueType() {
        return queueType;
    }

    /**
     * 
     * @param queueType
     *     The queueType
     */
    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    /**
     * 
     * @return
     *     The mapId
     */
    public Integer getMapId() {
        return mapId;
    }

    /**
     * 
     * @param mapId
     *     The mapId
     */
    public void setMapId(Integer mapId) {
        this.mapId = mapId;
    }

    /**
     * 
     * @return
     *     The season
     */
    public String getSeason() {
        return season;
    }

    /**
     * 
     * @param season
     *     The season
     */
    public void setSeason(String season) {
        this.season = season;
    }

    /**
     * 
     * @return
     *     The matchVersion
     */
    public String getMatchVersion() {
        return matchVersion;
    }

    /**
     * 
     * @param matchVersion
     *     The matchVersion
     */
    public void setMatchVersion(String matchVersion) {
        this.matchVersion = matchVersion;
    }

    /**
     * 
     * @return
     *     The participants
     */
    public List<Participant> getParticipants() {
        return participants;
    }

    /**
     * 
     * @param participants
     *     The participants
     */
    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    /**
     * 
     * @return
     *     The participantIdentities
     */
    public List<ParticipantIdentity> getParticipantIdentities() {
        return participantIdentities;
    }

    /**
     * 
     * @param participantIdentities
     *     The participantIdentities
     */
    public void setParticipantIdentities(List<ParticipantIdentity> participantIdentities) {
        this.participantIdentities = participantIdentities;
    }

    /**
     * 
     * @return
     *     The teams
     */
    public List<Team> getTeams() {
        return teams;
    }

    /**
     * 
     * @param teams
     *     The teams
     */
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

}
