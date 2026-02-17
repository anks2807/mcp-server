package com.ttn.mcp_server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "help_desk")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HelpDeskTicket {

    @Id
    @Column(name = "help_desk_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int helpDeskId;

    @Column(name = "summary")
    String summary;

    @Column(name = "user_name")
    String username;

    @Column(name = "status")
    String status;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "eta")
    LocalDateTime eta;

    public HelpDeskTicket() {
    }

    private HelpDeskTicket(Builder builder) {
        this.helpDeskId = builder.helpDeskId;
        this.summary = builder.summary;
        this.username = builder.username;
        this.status = builder.status;
        this.createdAt = builder.createdAt;
        this.eta = builder.eta;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int helpDeskId;
        private String summary;
        private String username;
        private String status;
        private LocalDateTime createdAt;
        private LocalDateTime eta;

        public Builder helpDeskId(int helpDeskId) {
            this.helpDeskId = helpDeskId;
            return this;
        }

        public Builder summary(String summary) {
            this.summary = summary;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder eta(LocalDateTime eta) {
            this.eta = eta;
            return this;
        }

        public HelpDeskTicket build() {
            return new HelpDeskTicket(this);
        }
    }
}
