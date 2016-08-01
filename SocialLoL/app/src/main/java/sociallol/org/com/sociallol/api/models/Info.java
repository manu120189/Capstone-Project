
package sociallol.org.com.sociallol.api.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Info {

    @SerializedName("attack")
    @Expose
    private Integer attack;
    @SerializedName("defense")
    @Expose
    private Integer defense;
    @SerializedName("magic")
    @Expose
    private Integer magic;
    @SerializedName("difficulty")
    @Expose
    private Integer difficulty;

    /**
     * 
     * @return
     *     The attack
     */
    public Integer getAttack() {
        return attack;
    }

    /**
     * 
     * @param attack
     *     The attack
     */
    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    /**
     * 
     * @return
     *     The defense
     */
    public Integer getDefense() {
        return defense;
    }

    /**
     * 
     * @param defense
     *     The defense
     */
    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    /**
     * 
     * @return
     *     The magic
     */
    public Integer getMagic() {
        return magic;
    }

    /**
     * 
     * @param magic
     *     The magic
     */
    public void setMagic(Integer magic) {
        this.magic = magic;
    }

    /**
     * 
     * @return
     *     The difficulty
     */
    public Integer getDifficulty() {
        return difficulty;
    }

    /**
     * 
     * @param difficulty
     *     The difficulty
     */
    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

}
