/**
 * Copyright(c) Nordstrom,Inc. All Rights reserved. This software is the confidential and
 * proprietary information of Nordstrom, Inc. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the terms of the license
 * agreement you entered into with Nordstrom, Inc.
 */

package com.nordstrom.mlsort.tf;

import java.util.List;

/**
 * To create Component terraform element for funnel.
 */
public class TFObjComponent4Funnel implements TerraformObjects {

  private String parent_group_id;
  private TFObjPosition tfObjPosition;

  /**
   * The getter for parent_group_id.
   * 
   * @return the parent_group_id
   */
  public String getParent_group_id() {
    return parent_group_id;
  }

  /**
   * The setter for parent_group_id.
   * 
   * @param parent_group_id the parent_group_id to set
   */
  public void setParent_group_id(String parent_group_id) {
    this.parent_group_id = parent_group_id;
  }

  /**
   * The getter for tfObjPosition.
   * 
   * @return the tfObjPosition
   */
  public TFObjPosition getTfObjPosition() {
    return tfObjPosition;
  }

  /**
   * The setter for tfObjPosition.
   * 
   * @param tfObjPosition the tfObjPosition to set
   */
  public void setTfObjPosition(TFObjPosition tfObjPosition) {
    this.tfObjPosition = tfObjPosition;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.nordstrom.mlsort.tf.TerraformObjects#generateStringRepresentation()
   */
  @Override
  public String generateStringRepresentation() {
    String element = null;
    try {
      List<String> list = TFUtil.getFieldNameAndValues(this, false);
      element = TFUtil.generateString(TFUtil.getElementNameFromClassName(this.getClass().getName()),
          list, false);
    } catch (IllegalAccessException exception) {
      exception.printStackTrace();
    }
    return element;
  }

}
