
package sociallol.org.com.sociallol.api.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class RankedStatsDto {

    @SerializedName("summonerId")
    @Expose
    private Integer summonerId;
    @SerializedName("modifyDate")
    @Expose
    private Date modifyDate;
    @SerializedName("champions")
    @Expose
    private List<Champion> champions = new ArrayList<Champion>();

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
     *     The modifyDate
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * 
     * @param modifyDate
     *     The modifyDate
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * 
     * @return
     *     The champions
     */
    public List<Champion> getChampions() {
        return champions;
    }

    /**
     * 
     * @param champions
     *     The champions
     */
    public void setChampions(List<Champion> champions) {
        this.champions = champions;
    }

}
