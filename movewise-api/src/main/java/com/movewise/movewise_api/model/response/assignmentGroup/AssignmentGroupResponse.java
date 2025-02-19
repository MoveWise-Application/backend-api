package com.movewise.movewise_api.model.response.assignmentGroup;

import java.util.List;
import java.util.UUID;

import com.movewise.movewise_api.entity.enumberable.GroupStatus;
import com.movewise.movewise_api.model.response.groupMember.GroupMemberResponse;
import com.movewise.movewise_api.model.response.transportation.TransportationResponse;

public class AssignmentGroupResponse {
    public GroupStatus groupStatus;

    public TransportationResponse transportation;

    public UUID requestId;

    public List<GroupMemberResponse> members;
}
