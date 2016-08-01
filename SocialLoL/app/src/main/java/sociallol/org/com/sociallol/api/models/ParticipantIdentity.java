
package sociallol.org.com.sociallol.api.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ParticipantIdentity {

    @SerializedName("participantId")
    @Expose
    private Integer participantId;
    @SerializedName("player")
    @Expose
    private Player player;

    /**
     * 
     * @return
     *     The participantId
     */
    public Integer getParticipantId() {
        return participantId;
    }

    /**
     * 
     * @param participantId
     *     The participantId
     */
    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    /**
     * 
     * @return
     *     The player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * 
     * @param player
     *     The player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

}
