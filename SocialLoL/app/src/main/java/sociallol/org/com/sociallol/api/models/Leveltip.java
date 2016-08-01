
package sociallol.org.com.sociallol.api.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Leveltip {

    @SerializedName("label")
    @Expose
    private List<String> label = new ArrayList<String>();
    @SerializedName("effect")
    @Expose
    private List<String> effect = new ArrayList<String>();

    /**
     * 
     * @return
     *     The label
     */
    public List<String> getLabel() {
        return label;
    }

    /**
     * 
     * @param label
     *     The label
     */
    public void setLabel(List<String> label) {
        this.label = label;
    }

    /**
     * 
     * @return
     *     The effect
     */
    public List<String> getEffect() {
        return effect;
    }

    /**
     * 
     * @param effect
     *     The effect
     */
    public void setEffect(List<String> effect) {
        this.effect = effect;
    }

}
