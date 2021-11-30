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

/**
 * API keys are used to authenticate requests to Rockset&#39;s API. An API key is tied to the user who creates it. A new API key can be created for each use case, with a maximum of 10 API keys per user.
 */
@ApiModel(description = "API keys are used to authenticate requests to Rockset's API. An API key is tied to the user who creates it. A new API key can be created for each use case, with a maximum of 10 API keys per user.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-12-28T20:02:03.641Z")
public class ApiKey {
  @SerializedName("type")
  private String type = null;

  @SerializedName("created_at")
  private String createdAt = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("key")
  private String key = null;

   /**
   * has value &#x60;api_key&#x60; for an API key object
   * @return type
  **/
  @ApiModelProperty(example = "api_key", value = "has value `api_key` for an API key object")
  public String getType() {
    return type;
  }

  public ApiKey createdAt(String createdAt) {
    this.createdAt = createdAt;
    return this;
  }

   /**
   * ISO-8601 date
   * @return createdAt
  **/
  @ApiModelProperty(example = "2001-08-28T00:23:41Z", value = "ISO-8601 date")
  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public ApiKey name(String name) {
    this.name = name;
    return this;
  }

   /**
   * descriptive label
   * @return name
  **/
  @ApiModelProperty(example = "my-event-logger-key", required = true, value = "descriptive label")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ApiKey key(String key) {
    this.key = key;
    return this;
  }

   /**
   * string of 64 alphanumeric characters
   * @return key
  **/
  @ApiModelProperty(example = "aB35kDjg93J5nsf4GjwMeErAVd832F7ad4vhsW1S02kfZiab42sTsfW5Sxt25asT", required = true, value = "string of 64 alphanumeric characters")
  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiKey apiKey = (ApiKey) o;
    return Objects.equals(this.type, apiKey.type) &&
        Objects.equals(this.createdAt, apiKey.createdAt) &&
        Objects.equals(this.name, apiKey.name) &&
        Objects.equals(this.key, apiKey.key);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, createdAt, name, key);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiKey {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
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

