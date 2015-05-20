package framework.json2java.generated;
import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * list of products
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "titleSortName",
    "productType"
})
public class Product {

    /**
     * id
     * <p>
     * 
     * 
     */
    @JsonProperty("id")
    private String id;
    @JsonProperty("titleSortName")
    private String titleSortName;
    /**
     * Type of products
     * <p>
     * 
     * 
     */
    @JsonProperty("productType")
    private List<ProductTypes> productType = new ArrayList<ProductTypes>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * id
     * <p>
     * 
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * id
     * <p>
     * 
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The titleSortName
     */
    @JsonProperty("titleSortName")
    public String getTitleSortName() {
        return titleSortName;
    }

    /**
     * 
     * @param titleSortName
     *     The titleSortName
     */
    @JsonProperty("titleSortName")
    public void setTitleSortName(String titleSortName) {
        this.titleSortName = titleSortName;
    }

    /**
     * Type of products
     * <p>
     * 
     * 
     * @return
     *     The productType
     */
    @JsonProperty("productType")
    public List<ProductTypes> getProductType() {
        return productType;
    }

    /**
     * Type of products
     * <p>
     * 
     * 
     * @param productType
     *     The productType
     */
    @JsonProperty("productType")
    public void setProductType(List<ProductTypes> productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(titleSortName).append(productType).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Product) == false) {
            return false;
        }
        Product rhs = ((Product) other);
        return new EqualsBuilder().append(id, rhs.id).append(titleSortName, rhs.titleSortName).append(productType, rhs.productType).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
