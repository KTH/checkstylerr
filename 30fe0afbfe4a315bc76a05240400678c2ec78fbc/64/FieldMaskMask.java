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
 * Field masking function name + args. The args is a JSON object.
 */

@ApiModel(description = "Field masking function name + args. The args is a JSON object.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2021-02-25T08:10:23.542Z")
public class FieldMaskMask {
  @SerializedName("name")
  private String name = null;

  @SerializedName("args")
  private Object args = null;

  public FieldMaskMask name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/

@JsonProperty("name")
@ApiModelProperty(required = true, value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public FieldMaskMask args(Object args) {
    this.args = args;
    return this;
  }

   /**
   * Get args
   * @return args
  **/

@JsonProperty("args")
@ApiModelProperty(value = "")
  public Object getArgs() {
    return args;
  }

  public void setArgs(Object args) {
    this.args = args;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FieldMaskMask fieldMaskMask = (FieldMaskMask) o;
    return Objects.equals(this.name, fieldMaskMask.name) &&
        Objects.equals(this.args, fieldMaskMask.args);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, args);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FieldMaskMask {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    args: ").append(toIndentedString(args)).append("\n");
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

