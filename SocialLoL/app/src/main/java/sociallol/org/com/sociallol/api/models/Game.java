
package sociallol.org.com.sociallol.api.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Game {

    @SerializedName("gameId")
    @Expose
    private Long gameId;
    @SerializedName("invalid")
    @Expose
    private Boolean invalid;
    @SerializedName("gameMode")
    @Expose
    private String gameMode;
    @SerializedName("gameType")
    @Expose
    private String gameType;
    @SerializedName("subType")
    @Expose
    private String subType;
    @SerializedName("mapId")
    @Expose
    private Integer mapId;
    @SerializedName("teamId")
    @Expose
    private Integer teamId;
    @SerializedName("championId")
    @Expose
    private Integer championId;
    @SerializedName("spell1")
    @Expose
    private Integer spell1;
    @SerializedName("spell2")
    @Expose
    private Integer spell2;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("ipEarned")
    @Expose
    private Integer ipEarned;
    @SerializedName("createDate")
    @Expose
    private Date createDate;
    @SerializedName("fellowPlayers")
    @Expose
    private List<FellowPlayer> fellowPlayers = new ArrayList<FellowPlayer>();
    @SerializedName("stats")
    @Expose
    private GameStats stats;

    /**
     * 
     * @return
     *     The gameId
     */
    public Long getGameId() {
        return gameId;
    }

    /**
     * 
     * @param gameId
     *     The gameId
     */
    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    /**
     * 
     * @return
     *     The invalid
     */
    public Boolean getInvalid() {
        return invalid;
    }

    /**
     * 
     * @param invalid
     *     The invalid
     */
    public void setInvalid(Boolean invalid) {
        this.invalid = invalid;
    }

    /**
     * 
     * @return
     *     The gameMode
     */
    public String getGameMode() {
        return gameMode;
    }

    /**
     * 
     * @param gameMode
     *     The gameMode
     */
    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    /**
     * 
     * @return
     *     The gameType
     */
    public String getGameType() {
        return gameType;
    }

    /**
     * 
     * @param gameType
     *     The gameType
     */
    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    /**
     * 
     * @return
     *     The subType
     */
    public String getSubType() {
        return subType;
    }

    /**
     * 
     * @param subType
     *     The subType
     */
    public void setSubType(String subType) {
        this.subType = subType;
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
     *     The championId
     */
    public Integer getChampionId() {
        return championId;
    }

    /**
     * 
     * @param championId
     *     The championId
     */
    public void setChampionId(Integer championId) {
        this.championId = championId;
    }

    /**
     * 
     * @return
     *     The spell1
     */
    public Integer getSpell1() {
        return spell1;
    }

    /**
     * 
     * @param spell1
     *     The spell1
     */
    public void setSpell1(Integer spell1) {
        this.spell1 = spell1;
    }

    /**
     * 
     * @return
     *     The spell2
     */
    public Integer getSpell2() {
        return spell2;
    }

    /**
     * 
     * @param spell2
     *     The spell2
     */
    public void setSpell2(Integer spell2) {
        this.spell2 = spell2;
    }

    /**
     * 
     * @return
     *     The level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 
     * @param level
     *     The level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 
     * @return
     *     The ipEarned
     */
    public Integer getIpEarned() {
        return ipEarned;
    }

    /**
     * 
     * @param ipEarned
     *     The ipEarned
     */
    public void setIpEarned(Integer ipEarned) {
        this.ipEarned = ipEarned;
    }

    /**
     * 
     * @return
     *     The createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 
     * @param createDate
     *     The createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 
     * @return
     *     The fellowPlayers
     */
    public List<FellowPlayer> getFellowPlayers() {
        return fellowPlayers;
    }

    /**
     * 
     * @param fellowPlayers
     *     The fellowPlayers
     */
    public void setFellowPlayers(List<FellowPlayer> fellowPlayers) {
        this.fellowPlayers = fellowPlayers;
    }

    /**
     * 
     * @return
     *     The stats
     */
    public GameStats getStats() {
        return stats;
    }

    /**
     * 
     * @param stats
     *     The stats
     */
    public void setStats(GameStats stats) {
        this.stats = stats;
    }

}
