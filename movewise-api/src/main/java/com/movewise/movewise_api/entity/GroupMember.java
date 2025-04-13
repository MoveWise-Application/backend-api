package com.movewise.movewise_api.entity;

import java.time.LocalDateTime;

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
@Table(name = "group_member")
public class GroupMember extends BaseEntity {

    @Column(name = "assigned_at", nullable = true)
    private LocalDateTime assignedAt;

    @Column(name = "is_accepted", nullable = false)
    @Builder.Default
    private boolean isAccepted = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User member;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private AssignmentGroup group;
}
