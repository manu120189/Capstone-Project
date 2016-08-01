package sociallol.org.com.sociallol.utils;

import java.util.List;

public class SummonerProfileHelper{
    private String name;
    private Integer summonerLevel;
    private Integer profileIconId;
    private Double averageGoldGeneral;
    private Integer[] averagesGeneral;
    private Integer[] championAverages1;
    private Integer[] championAverages2;
    private Integer[] championAverages3;
    private Integer[] championAverages4;
    private String championKey1;
    private String championKey2;
    private String championKey3;
    private String championKey4;

    private List<LeagueHelper> leagues;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(Integer summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public Integer getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(Integer profileIconId) {
        this.profileIconId = profileIconId;
    }

    public Double getAverageGoldGeneral() {
        return averageGoldGeneral;
    }

    public void setAverageGoldGeneral(Double averageGoldGeneral) {
        this.averageGoldGeneral = averageGoldGeneral;
    }

    public Integer[] getAveragesGeneral() {
        return averagesGeneral;
    }

    public void setAveragesGeneral(Integer[] averagesGeneral) {
        this.averagesGeneral = averagesGeneral;
    }

    public Integer[] getChampionAverages1() {
        return championAverages1;
    }

    public void setChampionAverages1(Integer[] championAverages1) {
        this.championAverages1 = championAverages1;
    }

    public Integer[] getChampionAverages2() {
        return championAverages2;
    }

    public void setChampionAverages2(Integer[] championAverages2) {
        this.championAverages2 = championAverages2;
    }

    public Integer[] getChampionAverages3() {
        return championAverages3;
    }

    public void setChampionAverages3(Integer[] championAverages3) {
        this.championAverages3 = championAverages3;
    }

    public Integer[] getChampionAverages4() {
        return championAverages4;
    }

    public void setChampionAverages4(Integer[] championAverages4) {
        this.championAverages4 = championAverages4;
    }

    public List<LeagueHelper> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<LeagueHelper> leagues) {
        this.leagues = leagues;
    }

    public String getChampionKey1() {
        return championKey1;
    }

    public void setChampionKey1(String championKey1) {
        this.championKey1 = championKey1;
    }

    public String getChampionKey2() {
        return championKey2;
    }

    public void setChampionKey2(String championKey2) {
        this.championKey2 = championKey2;
    }

    public String getChampionKey3() {
        return championKey3;
    }

    public void setChampionKey3(String championKey3) {
        this.championKey3 = championKey3;
    }

    public String getChampionKey4() {
        return championKey4;
    }

    public void setChampionKey4(String championKey4) {
        this.championKey4 = championKey4;
    }
}