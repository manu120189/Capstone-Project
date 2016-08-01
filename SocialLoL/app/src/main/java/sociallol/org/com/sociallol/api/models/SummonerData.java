
package sociallol.org.com.sociallol.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SummonerData {

    @SerializedName("summoner")
    @Expose
    private Summoner summoner;

    /**
     * 
     * @return
     *     The summoner
     */
    public Summoner getSummoner() {
        return summoner;
    }

    /**
     * 
     * @param summoner
     *     The summoner
     */
    public void setSummoner(Summoner summoner) {
        this.summoner = summoner;
    }

}
