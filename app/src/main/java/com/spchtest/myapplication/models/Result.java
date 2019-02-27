package com.spchtest.myapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("resource_id")
    @Expose
    private String resourceId;

    @SerializedName("fields")
    @Expose
    private List<Field> fields = null;

    @SerializedName("records")
    @Expose
    private List<Record> records = null;

    @SerializedName("_links")
    @Expose
    private Links links;

    @SerializedName("limit")
    @Expose
    private Integer limit;

    @SerializedName("total")
    @Expose
    private Integer total;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
