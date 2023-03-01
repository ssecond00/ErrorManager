package com.errormanager.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@Data
@Entity(name = "ERRORS")
public class Errors {

    @NonNull
    @Column(name = "id")
    @Id
    private String id;

    @NonNull
    @Column(name = "error_message")
    private String errorMessage;

    @NonNull
    @Column(name = "error_code")
    private String errorCode;

}
