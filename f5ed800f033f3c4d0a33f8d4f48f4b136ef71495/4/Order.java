package com.hcsp.wxshop.generate;

import java.util.Date;

public class Order {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ORDER.ID
     *
     * @mbg.generated Sun Mar 22 20:27:48 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ORDER.USER_ID
     *
     * @mbg.generated Sun Mar 22 20:27:48 CST 2020
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ORDER.GOODS_ID
     *
     * @mbg.generated Sun Mar 22 20:27:48 CST 2020
     */
    private Long goodsId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ORDER.NUMBER
     *
     * @mbg.generated Sun Mar 22 20:27:48 CST 2020
     */
    private Integer number;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ORDER.TOTAL_PRICE
     *
     * @mbg.generated Sun Mar 22 20:27:48 CST 2020
     */
    private Long totalPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ORDER.STATUS
     *
     * @mbg.generated Sun Mar 22 20:27:48 CST 2020
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ORDER.CREATED_AT
     *
     * @mbg.generated Sun Mar 22 20:27:48 CST 2020
     */
    private Date createdAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ORDER.UPDATED_AT
     *
     * @mbg.generated Sun Mar 22 20:27:48 CST 2020
     */
    private Date updatedAt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ORDER.ID
     *
     * @return the value of ORDER.ID
     *
     * @mbg.generated Sun Mar 22 20:27:48 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ORDER.ID
     *
     * @param id the value for ORDER.ID
     *
     * @mbg.generated Sun Mar 22 20:27:48 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ORDER.USER_ID
     *
     * @return the value of ORDER.USER_ID
     *
     * @mbg.generated Sun Mar 22 20:27:48 CST 2020
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ORDER.USER_ID
     *
     * @param userId the value for ORDER.USER_ID
     *
     * @mbg.generated Sun Mar 22 20:27:48 CST 2020
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ORDER.GOODS_ID
     *
     * @return the value of ORDER.GOODS_ID
     *
     * @mbg.generated Sun Mar 22 20:27:48 CST 2020
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ORDER.GOODS_ID
     *
     * @param goodsId the value for ORDER.GOODS_ID
     *
     * @mbg.generated Sun Mar 22 20:27:48 CST 2020
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ORDER.NUMBER
     *
     * @return the value of ORDER.NUMBER
     *
     * @mbg.generated Sun Mar 22 20:27:48 CST 2020
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ORDER.NUMBER
     *
     * @param number the value for ORDER.NUMBER
     *
     * @mbg.generated Sun Mar 22 20:27:48 CST 2020
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ORDER.TOTAL_PRICE
     *
     * @return the value of ORDER.TOTAL_PRICE
     *
     * @mbg.generated Sun Mar 22 20:27:48 CST 2020
     */
    public Long getTotalPrice() {
        return totalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ORDER.TOTAL_PRICE
     *
     * @param totalPrice the value for ORDER.TOTAL_PRICE
     *
     * @mbg.generated Sun Mar 22 20:27:48 CST 2020
     */
    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ORDER.STATUS
     *
     * @return the value of ORDER.STATUS
     *
     * @mbg.generated Sun Mar 22 20:27:48 CST 2020
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ORDER.STATUS
     *
     * @param status the value for ORDER.STATUS
     *
     * @mbg.generated Sun Mar 22 20:27:48 CST 2020
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ORDER.CREATED_AT
     *
     * @return the value of ORDER.CREATED_AT
     *
     * @mbg.generated Sun Mar 22 20:27:48 CST 2020
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ORDER.CREATED_AT
     *
     * @param createdAt the value for ORDER.CREATED_AT
     *
     * @mbg.generated Sun Mar 22 20:27:48 CST 2020
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ORDER.UPDATED_AT
     *
     * @return the value of ORDER.UPDATED_AT
     *
     * @mbg.generated Sun Mar 22 20:27:48 CST 2020
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ORDER.UPDATED_AT
     *
     * @param updatedAt the value for ORDER.UPDATED_AT
     *
     * @mbg.generated Sun Mar 22 20:27:48 CST 2020
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}