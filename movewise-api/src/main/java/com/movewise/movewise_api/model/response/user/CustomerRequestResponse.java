package com.movewise.movewise_api.model.response.user;

import java.time.LocalDateTime;
import java.util.UUID;

public class CustomerRequestResponse {

    public UUID id;

    public LocalDateTime createdDate;

    public String email;

    public String fullName;

    public String avatar;
}
