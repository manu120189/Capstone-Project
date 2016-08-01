
package sociallol.org.com.sociallol.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;


public class Summoner {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("profileIconId")
    @Expose
    private Integer profileIconId;
    @SerializedName("revisionDate")
    @Expose
    private Date revisionDate;
    @SerializedName("summonerLevel")
    @Expose
    private Integer summonerLevel;

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
     *     The profileIconId
     */
    public Integer getProfileIconId() {
        return profileIconId;
    }

    /**
     * 
     * @param profileIconId
     *     The profileIconId
     */
    public void setProfileIconId(Integer profileIconId) {
        this.profileIconId = profileIconId;
    }

    /**
     * 
     * @return
     *     The revisionDate
     */
    public Date getRevisionDate() {
        return revisionDate;
    }

    /**
     * 
     * @param revisionDate
     *     The revisionDate
     */
    public void setRevisionDate(Date revisionDate) {
        this.revisionDate = revisionDate;
    }

    /**
     * 
     * @return
     *     The summonerLevel
     */
    public Integer getSummonerLevel() {
        return summonerLevel;
    }

    /**
     * 
     * @param summonerLevel
     *     The summonerLevel
     */
    public void setSummonerLevel(Integer summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

}
