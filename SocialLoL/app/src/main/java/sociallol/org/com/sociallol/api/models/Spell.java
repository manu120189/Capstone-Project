
package sociallol.org.com.sociallol.api.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Spell {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("sanitizedDescription")
    @Expose
    private String sanitizedDescription;
    @SerializedName("tooltip")
    @Expose
    private String tooltip;
    @SerializedName("sanitizedTooltip")
    @Expose
    private String sanitizedTooltip;
    @SerializedName("leveltip")
    @Expose
    private Leveltip leveltip;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("resource")
    @Expose
    private String resource;
    @SerializedName("maxrank")
    @Expose
    private Integer maxrank;
    @SerializedName("cost")
    @Expose
    private List<Integer> cost = new ArrayList<Integer>();
    @SerializedName("costType")
    @Expose
    private String costType;
    @SerializedName("costBurn")
    @Expose
    private String costBurn;
    @SerializedName("cooldown")
    @Expose
    private List<Double> cooldown = new ArrayList<Double>();
    @SerializedName("cooldownBurn")
    @Expose
    private String cooldownBurn;
    @SerializedName("effect")
    @Expose
    private List<Object> effect = new ArrayList<Object>();
    @SerializedName("effectBurn")
    @Expose
    private List<String> effectBurn = new ArrayList<String>();
    @SerializedName("vars")
    @Expose
    private List<Var> vars = new ArrayList<Var>();
    @SerializedName("range")
    @Expose
    private List<Integer> range = new ArrayList<Integer>();
    @SerializedName("rangeBurn")
    @Expose
    private String rangeBurn;
    @SerializedName("key")
    @Expose
    private String key;

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
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The sanitizedDescription
     */
    public String getSanitizedDescription() {
        return sanitizedDescription;
    }

    /**
     * 
     * @param sanitizedDescription
     *     The sanitizedDescription
     */
    public void setSanitizedDescription(String sanitizedDescription) {
        this.sanitizedDescription = sanitizedDescription;
    }

    /**
     * 
     * @return
     *     The tooltip
     */
    public String getTooltip() {
        return tooltip;
    }

    /**
     * 
     * @param tooltip
     *     The tooltip
     */
    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    /**
     * 
     * @return
     *     The sanitizedTooltip
     */
    public String getSanitizedTooltip() {
        return sanitizedTooltip;
    }

    /**
     * 
     * @param sanitizedTooltip
     *     The sanitizedTooltip
     */
    public void setSanitizedTooltip(String sanitizedTooltip) {
        this.sanitizedTooltip = sanitizedTooltip;
    }

    /**
     * 
     * @return
     *     The leveltip
     */
    public Leveltip getLeveltip() {
        return leveltip;
    }

    /**
     * 
     * @param leveltip
     *     The leveltip
     */
    public void setLeveltip(Leveltip leveltip) {
        this.leveltip = leveltip;
    }

    /**
     * 
     * @return
     *     The image
     */
    public Image getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The image
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * 
     * @return
     *     The resource
     */
    public String getResource() {
        return resource;
    }

    /**
     * 
     * @param resource
     *     The resource
     */
    public void setResource(String resource) {
        this.resource = resource;
    }

    /**
     * 
     * @return
     *     The maxrank
     */
    public Integer getMaxrank() {
        return maxrank;
    }

    /**
     * 
     * @param maxrank
     *     The maxrank
     */
    public void setMaxrank(Integer maxrank) {
        this.maxrank = maxrank;
    }

    /**
     * 
     * @return
     *     The cost
     */
    public List<Integer> getCost() {
        return cost;
    }

    /**
     * 
     * @param cost
     *     The cost
     */
    public void setCost(List<Integer> cost) {
        this.cost = cost;
    }

    /**
     * 
     * @return
     *     The costType
     */
    public String getCostType() {
        return costType;
    }

    /**
     * 
     * @param costType
     *     The costType
     */
    public void setCostType(String costType) {
        this.costType = costType;
    }

    /**
     * 
     * @return
     *     The costBurn
     */
    public String getCostBurn() {
        return costBurn;
    }

    /**
     * 
     * @param costBurn
     *     The costBurn
     */
    public void setCostBurn(String costBurn) {
        this.costBurn = costBurn;
    }

    /**
     * 
     * @return
     *     The cooldown
     */
    public List<Double> getCooldown() {
        return cooldown;
    }

    /**
     * 
     * @param cooldown
     *     The cooldown
     */
    public void setCooldown(List<Double> cooldown) {
        this.cooldown = cooldown;
    }

    /**
     * 
     * @return
     *     The cooldownBurn
     */
    public String getCooldownBurn() {
        return cooldownBurn;
    }

    /**
     * 
     * @param cooldownBurn
     *     The cooldownBurn
     */
    public void setCooldownBurn(String cooldownBurn) {
        this.cooldownBurn = cooldownBurn;
    }

    /**
     * 
     * @return
     *     The effect
     */
    public List<Object> getEffect() {
        return effect;
    }

    /**
     * 
     * @param effect
     *     The effect
     */
    public void setEffect(List<Object> effect) {
        this.effect = effect;
    }

    /**
     * 
     * @return
     *     The effectBurn
     */
    public List<String> getEffectBurn() {
        return effectBurn;
    }

    /**
     * 
     * @param effectBurn
     *     The effectBurn
     */
    public void setEffectBurn(List<String> effectBurn) {
        this.effectBurn = effectBurn;
    }

    /**
     * 
     * @return
     *     The vars
     */
    public List<Var> getVars() {
        return vars;
    }

    /**
     * 
     * @param vars
     *     The vars
     */
    public void setVars(List<Var> vars) {
        this.vars = vars;
    }

    /**
     * 
     * @return
     *     The range
     */
    public List<Integer> getRange() {
        return range;
    }

    /**
     * 
     * @param range
     *     The range
     */
    public void setRange(List<Integer> range) {
        this.range = range;
    }

    /**
     * 
     * @return
     *     The rangeBurn
     */
    public String getRangeBurn() {
        return rangeBurn;
    }

    /**
     * 
     * @param rangeBurn
     *     The rangeBurn
     */
    public void setRangeBurn(String rangeBurn) {
        this.rangeBurn = rangeBurn;
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

}
