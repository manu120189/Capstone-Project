
package sociallol.org.com.sociallol.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Match {

    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;
    @SerializedName("champion")
    @Expose
    private Integer champion;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("queue")
    @Expose
    private String queue;
    @SerializedName("season")
    @Expose
    private String season;
    @SerializedName("matchId")
    @Expose
    private Integer matchId;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("platformId")
    @Expose
    private String platformId;
    @SerializedName("gameStatus")
    @Expose
    private String lane;

    /**
     * 
     * @return
     *     The timestamp
     */
    public Integer getTimestamp() {
        return timestamp;
    }

    /**
     * 
     * @param timestamp
     *     The timestamp
     */
    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * 
     * @return
     *     The champion
     */
    public Integer getChampion() {
        return champion;
    }

    /**
     * 
     * @param champion
     *     The champion
     */
    public void setChampion(Integer champion) {
        this.champion = champion;
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
     *     The queue
     */
    public String getQueue() {
        return queue;
    }

    /**
     * 
     * @param queue
     *     The queue
     */
    public void setQueue(String queue) {
        this.queue = queue;
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
     *     The role
     */
    public String getRole() {
        return role;
    }

    /**
     * 
     * @param role
     *     The role
     */
    public void setRole(String role) {
        this.role = role;
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
     *     The gameStatus
     */
    public String getLane() {
        return lane;
    }

    /**
     * 
     * @param lane
     *     The gameStatus
     */
    public void setLane(String lane) {
        this.lane = lane;
    }

}
