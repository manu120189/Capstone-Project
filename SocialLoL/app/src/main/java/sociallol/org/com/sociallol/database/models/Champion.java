package sociallol.org.com.sociallol.database.models;

import com.j256.ormlite.field.DatabaseField;

public class Champion {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(unique = true)
    public Integer championId;
    @DatabaseField
    public String championName;
    @DatabaseField
    public String championKey;
    @DatabaseField
    public String lore;
    @DatabaseField
    public String role;
    @DatabaseField
    public String championTitle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getChampionId() {
        return championId;
    }

    public void setChampionId(Integer championId) {
        this.championId = championId;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public String getChampionKey() {
        return championKey;
    }

    public void setChampionKey(String championKey) {
        this.championKey = championKey;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getChampionTitle() {
        return championTitle;
    }

    public void setChampionTitle(String championTitle) {
        this.championTitle = championTitle;
    }
}
