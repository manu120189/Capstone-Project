
package sociallol.org.com.sociallol.api.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class LeagueDto {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tier")
    @Expose
    private String tier;
    @SerializedName("queue")
    @Expose
    private String queue;
    @SerializedName("entries")
    @Expose
    private List<LeagueEntryDto> entries = new ArrayList<LeagueEntryDto>();

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
     *     The tier
     */
    public String getTier() {
        return tier;
    }

    /**
     * 
     * @param tier
     *     The tier
     */
    public void setTier(String tier) {
        this.tier = tier;
    }

    /**
     * 
     * @return
     *     The queue
     */
    public String getQueue() {
        return queue;
    }

    /**
     * 
     * @param queue
     *     The queue
     */
    public void setQueue(String queue) {
        this.queue = queue;
    }

    /**
     * 
     * @return
     *     The entries
     */
    public List<LeagueEntryDto> getEntries() {
        return entries;
    }

    /**
     * 
     * @param entries
     *     The entries
     */
    public void setEntries(List<LeagueEntryDto> entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        return "LeagueDto{" +
                "name='" + name + '\'' +
                ", tier='" + tier + '\'' +
                ", queue='" + queue + '\'' +
                ", entries=" + entries +
                '}';
    }
}
