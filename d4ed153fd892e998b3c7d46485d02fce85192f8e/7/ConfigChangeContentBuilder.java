package com.ctrip.framework.apollo.biz.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.ctrip.framework.apollo.biz.entity.Item;
import com.ctrip.framework.apollo.common.utils.BeanUtils;
import com.ctrip.framework.apollo.core.dto.ItemChangeSets;
import com.ctrip.framework.apollo.core.dto.ItemDTO;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class ConfigChangeContentBuilder {

  private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

  private List<Item> createItems = new LinkedList<>();
  private List<ItemPair> updateItems = new LinkedList<>();
  private List<Item> deleteItems = new LinkedList<>();


  public ConfigChangeContentBuilder createItem(Item item) {
    createItems.add(item);
    return this;
  }

  public ConfigChangeContentBuilder updateItem(Item oldItem, Item newItem) {
    ItemPair itemPair = new ItemPair(oldItem, newItem);
    updateItems.add(itemPair);
    return this;
  }

  public ConfigChangeContentBuilder deleteItem(Item item) {
    deleteItems.add(item);
    return this;
  }

  public String build() {
    //因为事务第一段提交并没有更新时间,所以build时统一更新
    for (Item item : createItems) {
      item.setDataChangeLastModifiedTime(new Date());
    }

    for (ItemPair item : updateItems) {
      item.newItem.setDataChangeLastModifiedTime(new Date());
    }

    for (Item item : deleteItems) {
      item.setDataChangeLastModifiedTime(new Date());
    }
    return gson.toJson(this);
  }

  class ItemPair {

    Item oldItem;
    Item newItem;

    public ItemPair(Item oldItem, Item newItem) {
      this.oldItem = oldItem;
      this.newItem = newItem;
    }
  }

}
