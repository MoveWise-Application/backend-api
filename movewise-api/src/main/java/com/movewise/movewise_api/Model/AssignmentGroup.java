package com.movewise.movewise_api.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movewise.movewise_api.Model.Enumberable.GroupStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<GroupMember> groupMembers;

    @OneToOne(mappedBy = "assignmentGroup")
    private Request request;

    @ManyToOne
    @JoinColumn(name = "transportation_id")
    private Transportation transportation;
}
