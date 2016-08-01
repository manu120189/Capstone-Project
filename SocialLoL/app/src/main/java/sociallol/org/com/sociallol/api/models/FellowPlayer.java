
package sociallol.org.com.sociallol.api.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class FellowPlayer {

    @SerializedName("summonerId")
    @Expose
    private Integer summonerId;
    @SerializedName("teamId")
    @Expose
    private Integer teamId;
    @SerializedName("championId")
    @Expose
    private Integer championId;

    /**
     * 
     * @return
     *     The summonerId
     */
    public Integer getSummonerId() {
        return summonerId;
    }

    /**
     * 
     * @param summonerId
     *     The summonerId
     */
    public void setSummonerId(Integer summonerId) {
        this.summonerId = summonerId;
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

}
