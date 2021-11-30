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
import com.rockset.client.model.SqlExpression;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * OutputField
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-06-13T17:30:07.788Z")
public class OutputField {
  @SerializedName("field_name")
  private String fieldName = null;

  @SerializedName("value")
  private SqlExpression value = null;

  /**
   * Error in Mapping execution: &#39;skip&#39; or &#39;fail&#39; 
   */
  @JsonAdapter(OnErrorEnum.Adapter.class)
  public enum OnErrorEnum {
    SKIP("SKIP"),
    
    FAIL("FAIL");

    private String value;

    OnErrorEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static OnErrorEnum fromValue(String text) {
      for (OnErrorEnum b : OnErrorEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<OnErrorEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final OnErrorEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public OnErrorEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return OnErrorEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("on_error")
  private OnErrorEnum onError = null;

  public OutputField fieldName(String fieldName) {
    this.fieldName = fieldName;
    return this;
  }

   /**
   * The name of a field, parsed as a SQL qualified name 
   * @return fieldName
  **/

@JsonProperty("field_name")
@ApiModelProperty(example = "zip_hash", value = "The name of a field, parsed as a SQL qualified name ")
  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public OutputField value(SqlExpression value) {
    this.value = value;
    return this;
  }

   /**
   * The name of a sql function
   * @return value
  **/

@JsonProperty("value")
@ApiModelProperty(value = "The name of a sql function")
  public SqlExpression getValue() {
    return value;
  }

  public void setValue(SqlExpression value) {
    this.value = value;
  }

  public OutputField onError(OnErrorEnum onError) {
    this.onError = onError;
    return this;
  }

   /**
   * Error in Mapping execution: &#39;skip&#39; or &#39;fail&#39; 
   * @return onError
  **/

@JsonProperty("on_error")
@ApiModelProperty(example = "['SKIP', 'FAIL']", value = "Error in Mapping execution: 'skip' or 'fail' ")
  public OnErrorEnum getOnError() {
    return onError;
  }

  public void setOnError(OnErrorEnum onError) {
    this.onError = onError;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OutputField outputField = (OutputField) o;
    return Objects.equals(this.fieldName, outputField.fieldName) &&
        Objects.equals(this.value, outputField.value) &&
        Objects.equals(this.onError, outputField.onError);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fieldName, value, onError);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OutputField {\n");
    
    sb.append("    fieldName: ").append(toIndentedString(fieldName)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    onError: ").append(toIndentedString(onError)).append("\n");
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

