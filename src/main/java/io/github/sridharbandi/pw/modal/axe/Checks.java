
package io.github.sridharbandi.pw.modal.axe;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "impact",
        "message",
        "relatedNodes"
})
@Generated("jsonschema2pojo")
public class Checks {

    @JsonProperty("id")
    private String id;
    @JsonProperty("impact")
    private String impact;
    @JsonProperty("message")
    private String message;
    @JsonProperty("relatedNodes")
    private List<RelatedNode> relatedNodes = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("impact")
    public String getImpact() {
        return impact;
    }

    @JsonProperty("impact")
    public void setImpact(String impact) {
        this.impact = impact;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("relatedNodes")
    public List<RelatedNode> getRelatedNodes() {
        return relatedNodes;
    }

    @JsonProperty("relatedNodes")
    public void setRelatedNodes(List<RelatedNode> relatedNodes) {
        this.relatedNodes = relatedNodes;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
