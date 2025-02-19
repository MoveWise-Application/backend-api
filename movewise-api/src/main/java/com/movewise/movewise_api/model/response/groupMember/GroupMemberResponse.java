package com.movewise.movewise_api.model.response.groupMember;

import java.sql.Timestamp;

import com.movewise.movewise_api.entity.AssignmentGroup;
import com.movewise.movewise_api.model.response.user.UserResponse;

public class GroupMemberResponse {
    public Timestamp assignedAt;

    public boolean isAccepted;

    public UserResponse member;

    public AssignmentGroup group;
}
