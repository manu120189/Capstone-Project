
package sociallol.org.com.sociallol.api.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Var {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("coeff")
    @Expose
    private List<Double> coeff = new ArrayList<Double>();

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
     *     The link
     */
    public String getLink() {
        return link;
    }

    /**
     * 
     * @param link
     *     The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * 
     * @return
     *     The coeff
     */
    public List<Double> getCoeff() {
        return coeff;
    }

    /**
     * 
     * @param coeff
     *     The coeff
     */
    public void setCoeff(List<Double> coeff) {
        this.coeff = coeff;
    }

}
