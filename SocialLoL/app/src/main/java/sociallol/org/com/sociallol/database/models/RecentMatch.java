package sociallol.org.com.sociallol.database.models;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

public class RecentMatch {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField
    public Long summonerId;
    @DatabaseField
    public Long gameId;
    public String summonerName;
    public String championKey;
    @DatabaseField
    public Boolean win;
    @DatabaseField
    public Integer item0;
    @DatabaseField
    public Integer item1;
    @DatabaseField
    public Integer item2;
    @DatabaseField
    public Integer item3;
    @DatabaseField
    public Integer item4;
    @DatabaseField
    public Integer item5;
    @DatabaseField
    public Integer item6;
    @DatabaseField
    public Integer spell1;
    @DatabaseField
    public Integer spell2;
    @DatabaseField
    public Integer kills;
    @DatabaseField
    public Integer deaths;
    @DatabaseField
    public Integer assists;
    @DatabaseField
    public Integer gold;
    @DatabaseField
    public Integer minions;
    @DatabaseField
    public Integer championId;

    @DatabaseField
    public Integer timePlayed;
    @DatabaseField
    public Date created;
    public String spell1Name;
    public String spell2Name;
    @DatabaseField
    public String gameType;

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

    public Boolean getWin() {
        return win;
    }

    public void setWin(Boolean win) {
        this.win = win;
    }

    public Integer getItem0() {
        return item0;
    }

    public void setItem0(Integer item0) {
        this.item0 = item0;
    }

    public Integer getItem1() {
        return item1;
    }

    public void setItem1(Integer item1) {
        this.item1 = item1;
    }

    public Integer getItem2() {
        return item2;
    }

    public void setItem2(Integer item2) {
        this.item2 = item2;
    }

    public Integer getItem3() {
        return item3;
    }

    public void setItem3(Integer item3) {
        this.item3 = item3;
    }

    public Integer getItem4() {
        return item4;
    }

    public void setItem4(Integer item4) {
        this.item4 = item4;
    }

    public Integer getItem5() {
        return item5;
    }

    public void setItem5(Integer item5) {
        this.item5 = item5;
    }

    public Integer getItem6() {
        return item6;
    }

    public void setItem6(Integer item6) {
        this.item6 = item6;
    }

    public Integer getSpell1() {
        return spell1;
    }

    public void setSpell1(Integer spell1) {
        this.spell1 = spell1;
    }

    public Integer getSpell2() {
        return spell2;
    }

    public void setSpell2(Integer spell2) {
        this.spell2 = spell2;
    }

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }


    public Integer getChampionId() {
        return championId;
    }

    public void setChampionId(Integer championId) {
        this.championId = championId;
    }


    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getMinions() {
        return minions;
    }

    public void setMinions(Integer minions) {
        this.minions = minions;
    }

    public Integer getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(Integer timePlayed) {
        this.timePlayed = timePlayed;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public String getSpell1Name() {
        return spell1Name;
    }

    public void setSpell1Name(String spell1Name) {
        this.spell1Name = spell1Name;
    }

    public String getSpell2Name() {
        return spell2Name;
    }

    public void setSpell2Name(String spell2Name) {
        this.spell2Name = spell2Name;
    }

    public String getChampionKey() {
        return championKey;
    }

    public void setChampionKey(String championKey) {
        this.championKey = championKey;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
}
