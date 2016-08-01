package sociallol.org.com.sociallol.database.models;

import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

public class SummonerFriend {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(unique = true)
    public Long summonerId;

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
}
