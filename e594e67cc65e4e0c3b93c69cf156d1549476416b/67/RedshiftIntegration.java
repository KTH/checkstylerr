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
import com.rockset.client.model.AwsAccessKey;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * RedshiftIntegration
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-11-21T00:10:08.979Z")
public class RedshiftIntegration {
  @SerializedName("aws_access_key")
  private AwsAccessKey awsAccessKey = null;

  @SerializedName("username")
  private String username = null;

  @SerializedName("password")
  private String password = null;

  @SerializedName("host")
  private String host = null;

  @SerializedName("port")
  private Integer port = null;

  @SerializedName("s3_bucket_path")
  private String s3BucketPath = null;

   /**
   * AWS access key credentials
   * @return awsAccessKey
  **/

@JsonProperty("aws_access_key")
@ApiModelProperty(value = "AWS access key credentials")
  public AwsAccessKey getAwsAccessKey() {
    return awsAccessKey;
  }

   /**
   * Username associated with Redshift cluster
   * @return username
  **/

@JsonProperty("username")
@ApiModelProperty(example = "awsuser", required = true, value = "Username associated with Redshift cluster")
  public String getUsername() {
    return username;
  }

   /**
   * Password associated with Redshift cluster
   * @return password
  **/

@JsonProperty("password")
@ApiModelProperty(example = "pswd....", required = true, value = "Password associated with Redshift cluster")
  public String getPassword() {
    return password;
  }

   /**
   * Redshift Cluster host
   * @return host
  **/

@JsonProperty("host")
@ApiModelProperty(example = "test.yuyugt.us-west-2.redshift.amazonaws.com", required = true, value = "Redshift Cluster host")
  public String getHost() {
    return host;
  }

   /**
   * Redshift Cluster port
   * @return port
  **/

@JsonProperty("port")
@ApiModelProperty(example = "5439", required = true, value = "Redshift Cluster port")
  public Integer getPort() {
    return port;
  }

   /**
   * unload S3 bucket path
   * @return s3BucketPath
  **/

@JsonProperty("s3_bucket_path")
@ApiModelProperty(example = "s3://redshift-unload", required = true, value = "unload S3 bucket path")
  public String getS3BucketPath() {
    return s3BucketPath;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RedshiftIntegration redshiftIntegration = (RedshiftIntegration) o;
    return Objects.equals(this.awsAccessKey, redshiftIntegration.awsAccessKey) &&
        Objects.equals(this.username, redshiftIntegration.username) &&
        Objects.equals(this.password, redshiftIntegration.password) &&
        Objects.equals(this.host, redshiftIntegration.host) &&
        Objects.equals(this.port, redshiftIntegration.port) &&
        Objects.equals(this.s3BucketPath, redshiftIntegration.s3BucketPath);
  }

  @Override
  public int hashCode() {
    return Objects.hash(awsAccessKey, username, password, host, port, s3BucketPath);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RedshiftIntegration {\n");
    
    sb.append("    awsAccessKey: ").append(toIndentedString(awsAccessKey)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    host: ").append(toIndentedString(host)).append("\n");
    sb.append("    port: ").append(toIndentedString(port)).append("\n");
    sb.append("    s3BucketPath: ").append(toIndentedString(s3BucketPath)).append("\n");
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

