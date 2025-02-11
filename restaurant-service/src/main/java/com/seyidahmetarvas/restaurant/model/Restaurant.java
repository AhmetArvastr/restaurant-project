package com.seyidahmetarvas.restaurant.model;

import com.seyidahmetarvas.restaurant.model.enums.Status;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Document(indexName = "restaurants")
@Setting(settingPath = "static/es-settings.json")
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Field(name = "name", type = FieldType.Text, analyzer = "custom_analyzer", searchAnalyzer = "custom_search")
    private String name;

    @Field(name = "address", type = FieldType.Text)
    private String address;

    @Field(name = "phone", type = FieldType.Text)
    private String phone;

    @Field(name = "email", type = FieldType.Text)
    private String email;

    @Field(name = "website", type = FieldType.Text)
    private String website;

    @Field(name = "description", type = FieldType.Text)
    private String description;

    @Field(name = "workingHours", type = FieldType.Text)
    private String workingHours;

    @Field(name = "latitude", type = FieldType.Double)
    private double latitude;

    @Field(name = "longitude", type = FieldType.Double)
    private double longitude;

    @Field(name = "restaurantRate", type = FieldType.Double)
    private Double restaurantRate;

    @Field(name = "status", type = FieldType.Text)
    private Status status;

    @Field(name = "createdAt", type = FieldType.Date)
    @CreatedDate
    private LocalDateTime createdAt;

    @Field(name = "updatedAt", type = FieldType.Date)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Field(name = "creatorId", type = FieldType.Long)
    @CreatedBy
    private Long creatorId;

    @Field(name = "updatedId", type = FieldType.Long)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Double.compare(latitude, that.latitude) == 0 && Double.compare(longitude, that.longitude) == 0 && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(website, that.website) && Objects.equals(description, that.description) && Objects.equals(workingHours, that.workingHours) && Objects.equals(restaurantRate, that.restaurantRate) && status == that.status && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(creatorId, that.creatorId) && Objects.equals(updatedId, that.updatedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, phone, email, website, description, workingHours, latitude, longitude, restaurantRate, status, createdAt, updatedAt, creatorId, updatedId);
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
