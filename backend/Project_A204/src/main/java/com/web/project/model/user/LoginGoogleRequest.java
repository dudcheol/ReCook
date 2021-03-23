package com.web.project.model.user;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Valid
@Data
@ToString
@Getter
@Setter
public class LoginGoogleRequest {
	@ApiModelProperty(required = true)
	@NotNull
	String userEmail;
	@ApiModelProperty(required = true)
	@NotNull
	String userGid;
	@ApiModelProperty(required = true)
	@NotNull
	String userName;
	@ApiModelProperty(required = true)
	String userImage;
}
