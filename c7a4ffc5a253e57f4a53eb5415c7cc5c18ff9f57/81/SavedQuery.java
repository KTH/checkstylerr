/*
 * REST API
 * Rockset's REST API allows for creating and managing all resources in Rockset. Each supported endpoint is documented below.  All requests must be authorized with a Rockset API key, which can be created in the [Rockset console](https://console.rockset.com). The API key must be provided as `ApiKey <api_key>` in the `Authorization` request header. For example: ``` Authorization: ApiKey aB35kDjg93J5nsf4GjwMeErAVd832F7ad4vhsW1S02kfZiab42sTsfW5Sxt25asT ```  All endpoints are only accessible via https.  Build something awesome!
 *
 * OpenAPI spec version: v1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.rockset.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.rockset.client.model.SavedQueryParameter;
import com.rockset.client.model.SavedQueryStats;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * SavedQuery
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-01-21T23:08:54.250Z")
public class SavedQuery {
  @SerializedName("workspace")
  private String workspace = null;

  @SerializedName("created_by")
  private String createdBy = null;

  @SerializedName("created_at")
  private String createdAt = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("version")
  private Integer version = null;

  @SerializedName("version_tag")
  private String versionTag = null;

  @SerializedName("query_sql")
  private String querySql = null;

  @SerializedName("collections")
  private List<String> collections = null;

  @SerializedName("parameters")
  private List<SavedQueryParameter> parameters = null;

  /**
   * status of this query
   */
  @JsonAdapter(StateEnum.Adapter.class)
  public enum StateEnum {
    ACTIVE("ACTIVE"),
    
    ARCHIVED("ARCHIVED");

    private String value;

    StateEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static StateEnum fromValue(String text) {
      for (StateEnum b : StateEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<StateEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final StateEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public StateEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return StateEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("state")
  private StateEnum state = null;

  @SerializedName("stats")
  private SavedQueryStats stats = null;

  public SavedQuery workspace(String workspace) {
    this.workspace = workspace;
    return this;
  }

   /**
   * workspace of this saved query
   * @return workspace
  **/

@JsonProperty("workspace")
@ApiModelProperty(example = "commons", value = "workspace of this saved query")
  public String getWorkspace() {
    return workspace;
  }

  public void setWorkspace(String workspace) {
    this.workspace = workspace;
  }

  public SavedQuery createdBy(String createdBy) {
    this.createdBy = createdBy;
    return this;
  }

   /**
   * user that created this query
   * @return createdBy
  **/

@JsonProperty("created_by")
@ApiModelProperty(example = "..@rockset.com", value = "user that created this query")
  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public SavedQuery createdAt(String createdAt) {
    this.createdAt = createdAt;
    return this;
  }

   /**
   * ISO-8601 date of when saved query was created
   * @return createdAt
  **/

@JsonProperty("created_at")
@ApiModelProperty(example = "2001-08-28T00:23:41Z", value = "ISO-8601 date of when saved query was created")
  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public SavedQuery name(String name) {
    this.name = name;
    return this;
  }

   /**
   * query name
   * @return name
  **/

@JsonProperty("name")
@ApiModelProperty(example = "myQuery", value = "query name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public SavedQuery version(Integer version) {
    this.version = version;
    return this;
  }

   /**
   * query version
   * @return version
  **/

@JsonProperty("version")
@ApiModelProperty(example = "1", value = "query version")
  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public SavedQuery versionTag(String versionTag) {
    this.versionTag = versionTag;
    return this;
  }

   /**
   * optional version tag
   * @return versionTag
  **/

@JsonProperty("version_tag")
@ApiModelProperty(example = "production version foo", value = "optional version tag")
  public String getVersionTag() {
    return versionTag;
  }

  public void setVersionTag(String versionTag) {
    this.versionTag = versionTag;
  }

  public SavedQuery querySql(String querySql) {
    this.querySql = querySql;
    return this;
  }

   /**
   * SQL text of this query
   * @return querySql
  **/

@JsonProperty("query_sql")
@ApiModelProperty(example = "SELECT 'Foo'", value = "SQL text of this query")
  public String getQuerySql() {
    return querySql;
  }

  public void setQuerySql(String querySql) {
    this.querySql = querySql;
  }

  public SavedQuery collections(List<String> collections) {
    this.collections = collections;
    return this;
  }

  public SavedQuery addCollectionsItem(String collectionsItem) {
    if (this.collections == null) {
      this.collections = new ArrayList<String>();
    }
    this.collections.add(collectionsItem);
    return this;
  }

   /**
   * collections queried by this query
   * @return collections
  **/

@JsonProperty("collections")
@ApiModelProperty(value = "collections queried by this query")
  public List<String> getCollections() {
    return collections;
  }

  public void setCollections(List<String> collections) {
    this.collections = collections;
  }

  public SavedQuery parameters(List<SavedQueryParameter> parameters) {
    this.parameters = parameters;
    return this;
  }

  public SavedQuery addParametersItem(SavedQueryParameter parametersItem) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<SavedQueryParameter>();
    }
    this.parameters.add(parametersItem);
    return this;
  }

   /**
   * parameters associated with this query
   * @return parameters
  **/

@JsonProperty("parameters")
@ApiModelProperty(value = "parameters associated with this query")
  public List<SavedQueryParameter> getParameters() {
    return parameters;
  }

  public void setParameters(List<SavedQueryParameter> parameters) {
    this.parameters = parameters;
  }

  public SavedQuery state(StateEnum state) {
    this.state = state;
    return this;
  }

   /**
   * status of this query
   * @return state
  **/

@JsonProperty("state")
@ApiModelProperty(example = "ACTIVE", value = "status of this query")
  public StateEnum getState() {
    return state;
  }

  public void setState(StateEnum state) {
    this.state = state;
  }

  public SavedQuery stats(SavedQueryStats stats) {
    this.stats = stats;
    return this;
  }

   /**
   * stats related to this query
   * @return stats
  **/

@JsonProperty("stats")
@ApiModelProperty(value = "stats related to this query")
  public SavedQueryStats getStats() {
    return stats;
  }

  public void setStats(SavedQueryStats stats) {
    this.stats = stats;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SavedQuery savedQuery = (SavedQuery) o;
    return Objects.equals(this.workspace, savedQuery.workspace) &&
        Objects.equals(this.createdBy, savedQuery.createdBy) &&
        Objects.equals(this.createdAt, savedQuery.createdAt) &&
        Objects.equals(this.name, savedQuery.name) &&
        Objects.equals(this.version, savedQuery.version) &&
        Objects.equals(this.versionTag, savedQuery.versionTag) &&
        Objects.equals(this.querySql, savedQuery.querySql) &&
        Objects.equals(this.collections, savedQuery.collections) &&
        Objects.equals(this.parameters, savedQuery.parameters) &&
        Objects.equals(this.state, savedQuery.state) &&
        Objects.equals(this.stats, savedQuery.stats);
  }

  @Override
  public int hashCode() {
    return Objects.hash(workspace, createdBy, createdAt, name, version, versionTag, querySql, collections, parameters, state, stats);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SavedQuery {\n");
    
    sb.append("    workspace: ").append(toIndentedString(workspace)).append("\n");
    sb.append("    createdBy: ").append(toIndentedString(createdBy)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    versionTag: ").append(toIndentedString(versionTag)).append("\n");
    sb.append("    querySql: ").append(toIndentedString(querySql)).append("\n");
    sb.append("    collections: ").append(toIndentedString(collections)).append("\n");
    sb.append("    parameters: ").append(toIndentedString(parameters)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    stats: ").append(toIndentedString(stats)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

