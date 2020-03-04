package com.rest.webservice.provider.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Entity
@ApiModel("Contact")
public class Contact {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Name should not be empty")
    @Length(max = 255, message = "Name should have length less than 255 characters")
    @ApiModelProperty("Name should have length less than 255 characters")
    private String name;

    @NotBlank(message = "Phone number should not be empty")
    @Length(max = 14, message = "Phone number should have length less than 14 characters")
    @ApiModelProperty("Phone number should have length less than 14 characters")
    private String phoneNumber;
}
