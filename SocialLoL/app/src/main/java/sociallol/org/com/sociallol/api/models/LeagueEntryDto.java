
package sociallol.org.com.sociallol.api.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class LeagueEntryDto {

    @SerializedName("playerOrTeamId")
    @Expose
    private String playerOrTeamId;
    @SerializedName("playerOrTeamName")
    @Expose
    private String playerOrTeamName;
    @SerializedName("division")
    @Expose
    private String division;
    @SerializedName("leaguePoints")
    @Expose
    private Integer leaguePoints;
    @SerializedName("wins")
    @Expose
    private Integer wins;
    @SerializedName("losses")
    @Expose
    private Integer losses;
    @SerializedName("isHotStreak")
    @Expose
    private Boolean isHotStreak;
    @SerializedName("isVeteran")
    @Expose
    private Boolean isVeteran;
    @SerializedName("isFreshBlood")
    @Expose
    private Boolean isFreshBlood;
    @SerializedName("isInactive")
    @Expose
    private Boolean isInactive;

    /**
     * 
     * @return
     *     The playerOrTeamId
     */
    public String getPlayerOrTeamId() {
        return playerOrTeamId;
    }

    /**
     * 
     * @param playerOrTeamId
     *     The playerOrTeamId
     */
    public void setPlayerOrTeamId(String playerOrTeamId) {
        this.playerOrTeamId = playerOrTeamId;
    }

    /**
     * 
     * @return
     *     The playerOrTeamName
     */
    public String getPlayerOrTeamName() {
        return playerOrTeamName;
    }

    /**
     * 
     * @param playerOrTeamName
     *     The playerOrTeamName
     */
    public void setPlayerOrTeamName(String playerOrTeamName) {
        this.playerOrTeamName = playerOrTeamName;
    }

    /**
     * 
     * @return
     *     The division
     */
    public String getDivision() {
        return division;
    }

    /**
     * 
     * @param division
     *     The division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * 
     * @return
     *     The leaguePoints
     */
    public Integer getLeaguePoints() {
        return leaguePoints;
    }

    /**
     * 
     * @param leaguePoints
     *     The leaguePoints
     */
    public void setLeaguePoints(Integer leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    /**
     * 
     * @return
     *     The wins
     */
    public Integer getWins() {
        return wins;
    }

    /**
     * 
     * @param wins
     *     The wins
     */
    public void setWins(Integer wins) {
        this.wins = wins;
    }

    /**
     * 
     * @return
     *     The losses
     */
    public Integer getLosses() {
        return losses;
    }

    /**
     * 
     * @param losses
     *     The losses
     */
    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    /**
     * 
     * @return
     *     The isHotStreak
     */
    public Boolean getIsHotStreak() {
        return isHotStreak;
    }

    /**
     * 
     * @param isHotStreak
     *     The isHotStreak
     */
    public void setIsHotStreak(Boolean isHotStreak) {
        this.isHotStreak = isHotStreak;
    }

    /**
     * 
     * @return
     *     The isVeteran
     */
    public Boolean getIsVeteran() {
        return isVeteran;
    }

    /**
     * 
     * @param isVeteran
     *     The isVeteran
     */
    public void setIsVeteran(Boolean isVeteran) {
        this.isVeteran = isVeteran;
    }

    /**
     * 
     * @return
     *     The isFreshBlood
     */
    public Boolean getIsFreshBlood() {
        return isFreshBlood;
    }

    /**
     * 
     * @param isFreshBlood
     *     The isFreshBlood
     */
    public void setIsFreshBlood(Boolean isFreshBlood) {
        this.isFreshBlood = isFreshBlood;
    }

    /**
     * 
     * @return
     *     The isInactive
     */
    public Boolean getIsInactive() {
        return isInactive;
    }

    /**
     * 
     * @param isInactive
     *     The isInactive
     */
    public void setIsInactive(Boolean isInactive) {
        this.isInactive = isInactive;
    }

    @Override
    public String toString() {
        return "LeagueEntryDto{" +
                "playerOrTeamId='" + playerOrTeamId + '\'' +
                ", playerOrTeamName='" + playerOrTeamName + '\'' +
                ", division='" + division + '\'' +
                ", leaguePoints=" + leaguePoints +
                ", wins=" + wins +
                ", losses=" + losses +
                ", isHotStreak=" + isHotStreak +
                ", isVeteran=" + isVeteran +
                ", isFreshBlood=" + isFreshBlood +
                ", isInactive=" + isInactive +
                '}';
    }
}
