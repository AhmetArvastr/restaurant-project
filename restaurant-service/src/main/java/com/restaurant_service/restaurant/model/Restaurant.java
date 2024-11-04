package com.restaurant_service.restaurant.model;

import com.restaurant_service.restaurant.model.enums.Status;
import org.springframework.data.annotation.*;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.time.LocalDateTime;

@SolrDocument(solrCoreName = "restaurants")
public class Restaurant {
    @Id
    @Indexed(name = "id", type = "string")
    private String id;

    @Indexed(name = "name", type = "string")
    private String name;

    @Indexed(name = "address", type = "string")
    private String address;

    @Indexed(name = "phone", type = "string")
    private String phone;

    @Indexed(name = "email", type = "string")
    private String email;

    @Indexed(name = "website", type = "string")
    private String website;

    @Indexed(name = "description", type = "string")
    private String description;

    @Indexed(name = "workingHours", type = "string")
    private String workingHours;

    @Indexed(name = "latitude", type = "pdouble")
    private double latitude;

    @Indexed(name = "longitude", type = "pdouble")
    private double longitude;

    @Indexed(name = "restaurantRate", type = "pdouble")
    private Double restaurantRate;

    @Indexed(name = "status", type = "string")
    private Status status;

    @Indexed(name = "createdAt", type = "pdate")
    @CreatedDate
    private LocalDateTime createdAt;

    @Indexed(name = "updatedAt", type = "pdate")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Indexed(name = "creatorId", type = "plong")
    @CreatedBy
    private Long creatorId;

    @Indexed(name = "updatedId", type = "plong")
    @LastModifiedBy
    private Long updatedId;

    public Restaurant() {
    }

    public Restaurant(String name, String address, String phone, String email, String website,
                      String description, String workingHours, double latitude, double longitude, Double restaurantRate,
                      Status status) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.description = description;
        this.workingHours = workingHours;
        this.latitude = latitude;
        this.longitude = longitude;
        this.restaurantRate = restaurantRate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Double getRestaurantRate() {
        return restaurantRate;
    }

    public void setRestaurantRate(Double restaurantRate) {
        this.restaurantRate = restaurantRate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getUpdatedId() {
        return updatedId;
    }

    public void setUpdatedId(Long updatedId) {
        this.updatedId = updatedId;
    }
}
