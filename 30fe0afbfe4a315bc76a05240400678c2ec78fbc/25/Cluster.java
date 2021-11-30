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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Cluster
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2021-02-25T08:10:23.542Z")
public class Cluster {
  @SerializedName("id")
  private String id = null;

  /**
   * cluster type
   */
  @JsonAdapter(ClusterTypeEnum.Adapter.class)
  public enum ClusterTypeEnum {
    PUBLIC("PUBLIC"),
    
    PRIVATE("PRIVATE");

    private String value;

    ClusterTypeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static ClusterTypeEnum fromValue(String text) {
      for (ClusterTypeEnum b : ClusterTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<ClusterTypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final ClusterTypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public ClusterTypeEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return ClusterTypeEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("cluster_type")
  private ClusterTypeEnum clusterType = null;

  @SerializedName("aws_region")
  private String awsRegion = null;

  @SerializedName("domain")
  private String domain = null;

  @SerializedName("top_level_domain")
  private String topLevelDomain = null;

  @SerializedName("apiserver_url")
  private String apiserverUrl = null;

  public Cluster id(String id) {
    this.id = id;
    return this;
  }

   /**
   * unique identifier for the cluster
   * @return id
  **/

@JsonProperty("id")
@ApiModelProperty(example = "asdf98-as9df8adf-adsf9asfd", value = "unique identifier for the cluster")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Cluster clusterType(ClusterTypeEnum clusterType) {
    this.clusterType = clusterType;
    return this;
  }

   /**
   * cluster type
   * @return clusterType
  **/

@JsonProperty("cluster_type")
@ApiModelProperty(example = "PRIVATE", value = "cluster type")
  public ClusterTypeEnum getClusterType() {
    return clusterType;
  }

  public void setClusterType(ClusterTypeEnum clusterType) {
    this.clusterType = clusterType;
  }

  public Cluster awsRegion(String awsRegion) {
    this.awsRegion = awsRegion;
    return this;
  }

   /**
   * aws region
   * @return awsRegion
  **/

@JsonProperty("aws_region")
@ApiModelProperty(example = "us-west-2", value = "aws region")
  public String getAwsRegion() {
    return awsRegion;
  }

  public void setAwsRegion(String awsRegion) {
    this.awsRegion = awsRegion;
  }

  public Cluster domain(String domain) {
    this.domain = domain;
    return this;
  }

   /**
   * domain of org using cluster
   * @return domain
  **/

@JsonProperty("domain")
@ApiModelProperty(example = "rockset", value = "domain of org using cluster")
  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public Cluster topLevelDomain(String topLevelDomain) {
    this.topLevelDomain = topLevelDomain;
    return this;
  }

   /**
   * top level domain of org using cluster
   * @return topLevelDomain
  **/

@JsonProperty("top_level_domain")
@ApiModelProperty(example = ".com", value = "top level domain of org using cluster")
  public String getTopLevelDomain() {
    return topLevelDomain;
  }

  public void setTopLevelDomain(String topLevelDomain) {
    this.topLevelDomain = topLevelDomain;
  }

  public Cluster apiserverUrl(String apiserverUrl) {
    this.apiserverUrl = apiserverUrl;
    return this;
  }

   /**
   * api server url for cluster
   * @return apiserverUrl
  **/

@JsonProperty("apiserver_url")
@ApiModelProperty(example = "api.rockset.us-west-2.rockset.com", value = "api server url for cluster")
  public String getApiserverUrl() {
    return apiserverUrl;
  }

  public void setApiserverUrl(String apiserverUrl) {
    this.apiserverUrl = apiserverUrl;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cluster cluster = (Cluster) o;
    return Objects.equals(this.id, cluster.id) &&
        Objects.equals(this.clusterType, cluster.clusterType) &&
        Objects.equals(this.awsRegion, cluster.awsRegion) &&
        Objects.equals(this.domain, cluster.domain) &&
        Objects.equals(this.topLevelDomain, cluster.topLevelDomain) &&
        Objects.equals(this.apiserverUrl, cluster.apiserverUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, clusterType, awsRegion, domain, topLevelDomain, apiserverUrl);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Cluster {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    clusterType: ").append(toIndentedString(clusterType)).append("\n");
    sb.append("    awsRegion: ").append(toIndentedString(awsRegion)).append("\n");
    sb.append("    domain: ").append(toIndentedString(domain)).append("\n");
    sb.append("    topLevelDomain: ").append(toIndentedString(topLevelDomain)).append("\n");
    sb.append("    apiserverUrl: ").append(toIndentedString(apiserverUrl)).append("\n");
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

