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
import com.rockset.client.model.AwsExternalIdIntegration;
import com.rockset.client.model.AwsKeyIntegration;
import com.rockset.client.model.GcpServiceAccount;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * Integrations that can be associated with data sources to create collections. Only one type of integration may be specified.
 */
@ApiModel(description = "Integrations that can be associated with data sources to create collections. Only one type of integration may be specified.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-01-16T22:16:35.734Z")
public class Integration {
  @SerializedName("created_at")
  private String createdAt = null;

  @SerializedName("created_by")
  private String createdBy = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("description")
  private String description = null;

  @SerializedName("aws")
  private AwsKeyIntegration aws = null;

  @SerializedName("aws_external_id")
  private AwsExternalIdIntegration awsExternalId = null;

  @SerializedName("gcp_service_account")
  private GcpServiceAccount gcpServiceAccount = null;

  public Integration createdAt(String createdAt) {
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

  public Integration createdBy(String createdBy) {
    this.createdBy = createdBy;
    return this;
  }

   /**
   * email of user who created the integration
   * @return createdBy
  **/
  @ApiModelProperty(example = "hello@rockset.com", required = true, value = "email of user who created the integration")
  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Integration name(String name) {
    this.name = name;
    return this;
  }

   /**
   * descriptive label and unique identifier
   * @return name
  **/
  @ApiModelProperty(example = "event-logs", required = true, value = "descriptive label and unique identifier")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integration description(String description) {
    this.description = description;
    return this;
  }

   /**
   * longer explanation for the integration
   * @return description
  **/
  @ApiModelProperty(example = "AWS account with event data for the data science team.", value = "longer explanation for the integration")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integration aws(AwsKeyIntegration aws) {
    this.aws = aws;
    return this;
  }

   /**
   * credentials for an AWS key integration
   * @return aws
  **/
  @ApiModelProperty(value = "credentials for an AWS key integration")
  public AwsKeyIntegration getAws() {
    return aws;
  }

  public void setAws(AwsKeyIntegration aws) {
    this.aws = aws;
  }

  public Integration awsExternalId(AwsExternalIdIntegration awsExternalId) {
    this.awsExternalId = awsExternalId;
    return this;
  }

   /**
   * details of an AWS External Id integration
   * @return awsExternalId
  **/
  @ApiModelProperty(value = "details of an AWS External Id integration")
  public AwsExternalIdIntegration getAwsExternalId() {
    return awsExternalId;
  }

  public void setAwsExternalId(AwsExternalIdIntegration awsExternalId) {
    this.awsExternalId = awsExternalId;
  }

  public Integration gcpServiceAccount(GcpServiceAccount gcpServiceAccount) {
    this.gcpServiceAccount = gcpServiceAccount;
    return this;
  }

   /**
   * details of a GCP Service Account integration
   * @return gcpServiceAccount
  **/
  @ApiModelProperty(value = "details of a GCP Service Account integration")
  public GcpServiceAccount getGcpServiceAccount() {
    return gcpServiceAccount;
  }

  public void setGcpServiceAccount(GcpServiceAccount gcpServiceAccount) {
    this.gcpServiceAccount = gcpServiceAccount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Integration integration = (Integration) o;
    return Objects.equals(this.createdAt, integration.createdAt) &&
        Objects.equals(this.createdBy, integration.createdBy) &&
        Objects.equals(this.name, integration.name) &&
        Objects.equals(this.description, integration.description) &&
        Objects.equals(this.aws, integration.aws) &&
        Objects.equals(this.awsExternalId, integration.awsExternalId) &&
        Objects.equals(this.gcpServiceAccount, integration.gcpServiceAccount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(createdAt, createdBy, name, description, aws, awsExternalId, gcpServiceAccount);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Integration {\n");
    
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    createdBy: ").append(toIndentedString(createdBy)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    aws: ").append(toIndentedString(aws)).append("\n");
    sb.append("    awsExternalId: ").append(toIndentedString(awsExternalId)).append("\n");
    sb.append("    gcpServiceAccount: ").append(toIndentedString(gcpServiceAccount)).append("\n");
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

