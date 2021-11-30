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
import java.util.ArrayList;
import java.util.List;

/**
 * User
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-06-11T18:52:18.700Z")
public class User {
  @SerializedName("type")
  private String type = null;

  @SerializedName("created_at")
  private String createdAt = null;

  @SerializedName("email")
  private String email = null;

  @SerializedName("first_name")
  private String firstName = null;

  @SerializedName("last_name")
  private String lastName = null;

  @SerializedName("roles")
  private List<String> roles = null;

  @SerializedName("state")
  private String state = null;

   /**
   * has value &#x60;user&#x60; for a user object
   * @return type
  **/
  @ApiModelProperty(example = "user", value = "has value `user` for a user object")
  public String getType() {
    return type;
  }

  public User createdAt(String createdAt) {
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

  public User email(String email) {
    this.email = email;
    return this;
  }

   /**
   * user email
   * @return email
  **/
  @ApiModelProperty(example = "hello@rockset.com", required = true, value = "user email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public User firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

   /**
   * user first name
   * @return firstName
  **/
  @ApiModelProperty(example = "John", value = "user first name")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public User lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

   /**
   * user last name
   * @return lastName
  **/
  @ApiModelProperty(example = "Doe", value = "user last name")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public User roles(List<String> roles) {
    this.roles = roles;
    return this;
  }

  public User addRolesItem(String rolesItem) {
    if (this.roles == null) {
      this.roles = new ArrayList<String>();
    }
    this.roles.add(rolesItem);
    return this;
  }

   /**
   * List of roles for a given user
   * @return roles
  **/
  @ApiModelProperty(example = "\"[\\\"admin\\\", \\\"member\\\", \\\"read-only\\\"]\"", value = "List of roles for a given user")
  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  public User state(String state) {
    this.state = state;
    return this;
  }

   /**
   * state of user - NEW / ACTIVE
   * @return state
  **/
  @ApiModelProperty(example = "ACTIVE", value = "state of user - NEW / ACTIVE")
  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.type, user.type) &&
        Objects.equals(this.createdAt, user.createdAt) &&
        Objects.equals(this.email, user.email) &&
        Objects.equals(this.firstName, user.firstName) &&
        Objects.equals(this.lastName, user.lastName) &&
        Objects.equals(this.roles, user.roles) &&
        Objects.equals(this.state, user.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, createdAt, email, firstName, lastName, roles, state);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    roles: ").append(toIndentedString(roles)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
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

