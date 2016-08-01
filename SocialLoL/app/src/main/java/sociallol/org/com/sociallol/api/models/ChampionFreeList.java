
package sociallol.org.com.sociallol.api.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import sociallol.org.com.sociallol.api.models.ChampionFree;

@Generated("org.jsonschema2pojo")
public class ChampionFreeList {

    @SerializedName("champions")
    @Expose
    private List<ChampionFree> champions = new ArrayList<ChampionFree>();

    /**
     * 
     * @return
     *     The champions
     */
    public List<ChampionFree> getChampions() {
        return champions;
    }

    /**
     * 
     * @param champions
     *     The champions
     */
    public void setChampions(List<ChampionFree> champions) {
        this.champions = champions;
    }

}
