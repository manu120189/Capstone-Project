package sociallol.org.com.sociallol.database.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

public class Summoner {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(unique = true)
    public Long summonerId;
    @DatabaseField
    public String summonerName;
    @DatabaseField
    private Integer profileIconId;
    @DatabaseField
    private Integer summonerLevel;
    @DatabaseField
    private Date revisionDate;
    @DatabaseField
    public String region;
    @DatabaseField
    public String leagueName;
    @DatabaseField
    public String tier;
    @DatabaseField
    public String queue;
    @DatabaseField
    public String division;
    @DatabaseField
    public Integer leaguePoints;





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(Long summonerId) {
        this.summonerId = summonerId;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public Integer getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(Integer profileIconId) {
        this.profileIconId = profileIconId;
    }

    public Integer getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(Integer summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public Date getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Date revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public Integer getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(Integer leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    @Override
    public String toString() {
        return "Summoner{" +
                "id=" + id +
                ", summonerId=" + summonerId +
                ", summonerName='" + summonerName + '\'' +
                ", profileIconId=" + profileIconId +
                ", summonerLevel=" + summonerLevel +
                ", revisionDate=" + revisionDate +
                ", region='" + region + '\'' +
                ", leagueName='" + leagueName + '\'' +
                ", tier='" + tier + '\'' +
                ", queue='" + queue + '\'' +
                ", division='" + division + '\'' +
                ", leaguePoints=" + leaguePoints +
                '}';
    }
}
