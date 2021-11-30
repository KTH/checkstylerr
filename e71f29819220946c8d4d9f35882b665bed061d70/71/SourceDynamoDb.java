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
 * SourceDynamoDb
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-06-17T23:04:08.108Z")
public class SourceDynamoDb {
  @SerializedName("table_name")
  private String tableName = null;

  @SerializedName("aws_region")
  private String awsRegion = null;

  public SourceDynamoDb tableName(String tableName) {
    this.tableName = tableName;
    return this;
  }

   /**
   * name of DynamoDB table containing data
   * @return tableName
  **/

@JsonProperty("table_name")
@ApiModelProperty(example = "dynamodb_table_name", required = true, value = "name of DynamoDB table containing data")
  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public SourceDynamoDb awsRegion(String awsRegion) {
    this.awsRegion = awsRegion;
    return this;
  }

   /**
   * AWS region name of DynamoDB table, by default us-west-2 is used
   * @return awsRegion
  **/

@JsonProperty("aws_region")
@ApiModelProperty(example = "us-east-2", value = "AWS region name of DynamoDB table, by default us-west-2 is used")
  public String getAwsRegion() {
    return awsRegion;
  }

  public void setAwsRegion(String awsRegion) {
    this.awsRegion = awsRegion;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SourceDynamoDb sourceDynamoDb = (SourceDynamoDb) o;
    return Objects.equals(this.tableName, sourceDynamoDb.tableName) &&
        Objects.equals(this.awsRegion, sourceDynamoDb.awsRegion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tableName, awsRegion);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SourceDynamoDb {\n");
    
    sb.append("    tableName: ").append(toIndentedString(tableName)).append("\n");
    sb.append("    awsRegion: ").append(toIndentedString(awsRegion)).append("\n");
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

