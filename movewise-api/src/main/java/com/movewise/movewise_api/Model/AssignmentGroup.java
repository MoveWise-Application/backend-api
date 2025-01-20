package com.movewise.movewise_api.Model;

import com.movewise.movewise_api.Model.Enumberable.GroupStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "assignment_group")
public class AssignmentGroup extends BaseEntity {
    @Enumerated(EnumType.ORDINAL)
    private GroupStatus groupStatus;
}
