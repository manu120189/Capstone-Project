
package sociallol.org.com.sociallol.api.models;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatchList {

    @SerializedName("matches")
    @Expose
    private List<Match> matches = new ArrayList<Match>();
    @SerializedName("totalGames")
    @Expose
    private Integer totalGames;
    @SerializedName("startIndex")
    @Expose
    private Integer startIndex;
    @SerializedName("endIndex")
    @Expose
    private Integer endIndex;

    /**
     * 
     * @return
     *     The matches
     */
    public List<Match> getMatches() {
        return matches;
    }

    /**
     * 
     * @param matches
     *     The matches
     */
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    /**
     * 
     * @return
     *     The totalGames
     */
    public Integer getTotalGames() {
        return totalGames;
    }

    /**
     * 
     * @param totalGames
     *     The totalGames
     */
    public void setTotalGames(Integer totalGames) {
        this.totalGames = totalGames;
    }

    /**
     * 
     * @return
     *     The startIndex
     */
    public Integer getStartIndex() {
        return startIndex;
    }

    /**
     * 
     * @param startIndex
     *     The startIndex
     */
    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * 
     * @return
     *     The endIndex
     */
    public Integer getEndIndex() {
        return endIndex;
    }

    /**
     * 
     * @param endIndex
     *     The endIndex
     */
    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

}
