
package sociallol.org.com.sociallol.api.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ChampionDto {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("lore")
    @Expose
    private String lore;
    @SerializedName("allytips")
    @Expose
    private List<String> allytips = new ArrayList<String>();
    @SerializedName("enemytips")
    @Expose
    private List<String> enemytips = new ArrayList<String>();
    @SerializedName("tags")
    @Expose
    private List<String> tags = new ArrayList<String>();
    @SerializedName("spells")
    @Expose
    private List<Spell> spells = new ArrayList<Spell>();
    @SerializedName("passive")
    @Expose
    private Passive passive;

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The key
     */
    public String getKey() {
        return key;
    }

    /**
     * 
     * @param key
     *     The key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The lore
     */
    public String getLore() {
        return lore;
    }

    /**
     * 
     * @param lore
     *     The lore
     */
    public void setLore(String lore) {
        this.lore = lore;
    }

    /**
     * 
     * @return
     *     The allytips
     */
    public List<String> getAllytips() {
        return allytips;
    }

    /**
     * 
     * @param allytips
     *     The allytips
     */
    public void setAllytips(List<String> allytips) {
        this.allytips = allytips;
    }

    /**
     * 
     * @return
     *     The enemytips
     */
    public List<String> getEnemytips() {
        return enemytips;
    }

    /**
     * 
     * @param enemytips
     *     The enemytips
     */
    public void setEnemytips(List<String> enemytips) {
        this.enemytips = enemytips;
    }

    /**
     * 
     * @return
     *     The tags
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * 
     * @param tags
     *     The tags
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * 
     * @return
     *     The spells
     */
    public List<Spell> getSpells() {
        return spells;
    }

    /**
     * 
     * @param spells
     *     The spells
     */
    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }

    /**
     * 
     * @return
     *     The passive
     */
    public Passive getPassive() {
        return passive;
    }

    /**
     * 
     * @param passive
     *     The passive
     */
    public void setPassive(Passive passive) {
        this.passive = passive;
    }

}
