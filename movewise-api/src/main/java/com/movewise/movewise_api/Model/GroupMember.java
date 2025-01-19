package com.movewise.movewise_api.Model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
@Table(name = "group_member")
public class GroupMember extends BaseEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "assigned_at", nullable = true)
    private Timestamp assignedAt;

    @Column(name = "is_accepted", nullable = false, columnDefinition = "bit default 0")
    private boolean isAccepted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User member;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private AssignmentGroup group;
}
