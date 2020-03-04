package com.rest.webservice.provider.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel("Message")
public class Message {
    @NotNull(message = "Date can not be empty")
    @Past(message = "Date should be in past time")
    @ApiModelProperty("Date should be in past time")
    private Date timestamp;

    @NotBlank(message = "Phone number should not be empty")
    @Length(max = 14, message = "Phone number should have length less than 14 characters")
    @ApiModelProperty("Phone number should have length less than 14 characters")
    private String senderPhoneNumber;

    @NotBlank(message = "Text should not be empty")
    @Length(max = 4_096, message = "Text should have length less than 4096 characters")
    @ApiModelProperty("Text should have length less than 4096 characters")
    private String text;
}
