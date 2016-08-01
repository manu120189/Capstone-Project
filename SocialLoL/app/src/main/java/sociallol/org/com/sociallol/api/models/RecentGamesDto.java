
package sociallol.org.com.sociallol.api.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class RecentGamesDto {

    @SerializedName("summonerId")
    @Expose
    private Long summonerId;
    @SerializedName("games")
    @Expose
    private List<Game> games = new ArrayList<Game>();

    /**
     * 
     * @return
     *     The summonerId
     */
    public Long getSummonerId() {
        return summonerId;
    }

    /**
     * 
     * @param summonerId
     *     The summonerId
     */
    public void setSummonerId(Long summonerId) {
        this.summonerId = summonerId;
    }

    /**
     * 
     * @return
     *     The games
     */
    public List<Game> getGames() {
        return games;
    }

    /**
     * 
     * @param games
     *     The games
     */
    public void setGames(List<Game> games) {
        this.games = games;
    }

}
