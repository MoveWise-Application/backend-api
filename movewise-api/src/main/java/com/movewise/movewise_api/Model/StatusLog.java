package com.movewise.movewise_api.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "status_log")
public class StatusLog extends BaseEntity {

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orderLog;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request requestLog;

    @ManyToOne
    @JoinColumn(name = "reporter_id", referencedColumnName = "id")
    @JsonIgnore
    private User reporter;
}
