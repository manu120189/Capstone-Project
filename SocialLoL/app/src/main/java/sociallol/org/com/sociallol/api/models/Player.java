
package sociallol.org.com.sociallol.api.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Player {

    @SerializedName("summonerId")
    @Expose
    private Integer summonerId;
    @SerializedName("summonerName")
    @Expose
    private String summonerName;
    @SerializedName("matchHistoryUri")
    @Expose
    private String matchHistoryUri;
    @SerializedName("profileIcon")
    @Expose
    private Integer profileIcon;

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
     *     The summonerName
     */
    public String getSummonerName() {
        return summonerName;
    }

    /**
     * 
     * @param summonerName
     *     The summonerName
     */
    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    /**
     * 
     * @return
     *     The matchHistoryUri
     */
    public String getMatchHistoryUri() {
        return matchHistoryUri;
    }

    /**
     * 
     * @param matchHistoryUri
     *     The matchHistoryUri
     */
    public void setMatchHistoryUri(String matchHistoryUri) {
        this.matchHistoryUri = matchHistoryUri;
    }

    /**
     * 
     * @return
     *     The profileIcon
     */
    public Integer getProfileIcon() {
        return profileIcon;
    }

    /**
     * 
     * @param profileIcon
     *     The profileIcon
     */
    public void setProfileIcon(Integer profileIcon) {
        this.profileIcon = profileIcon;
    }

}
