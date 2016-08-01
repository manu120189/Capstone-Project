package sociallol.org.com.sociallol.database.models;

import com.j256.ormlite.field.DatabaseField;

public class SummonerSpell {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(unique = true)
    public Integer summonerSpellId;
    @DatabaseField
    public String summonerSpellName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getSummonerSpellId() {
        return summonerSpellId;
    }

    public void setSummonerSpellId(Integer summonerSpellId) {
        this.summonerSpellId = summonerSpellId;
    }

    public String getSummonerSpellName() {
        return summonerSpellName;
    }

    public void setSummonerSpellName(String summonerSpellName) {
        this.summonerSpellName = summonerSpellName;
    }
}
