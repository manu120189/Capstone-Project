
package sociallol.org.com.sociallol.api.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Ban {

    @SerializedName("championId")
    @Expose
    private Integer championId;
    @SerializedName("pickTurn")
    @Expose
    private Integer pickTurn;

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
     *     The pickTurn
     */
    public Integer getPickTurn() {
        return pickTurn;
    }

    /**
     * 
     * @param pickTurn
     *     The pickTurn
     */
    public void setPickTurn(Integer pickTurn) {
        this.pickTurn = pickTurn;
    }

}
