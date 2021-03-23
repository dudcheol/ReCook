package com.web.project.model.user;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
public class UpdateRequest {
	@ApiModelProperty(required = true)
	@NotNull
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,}$")
	String userPassword;
	@ApiModelProperty(required = true)
	@NotNull
	String userName;
	@ApiModelProperty(required = true)
	@NotNull
	String userImage;
	@ApiModelProperty(required = true)
	@NotNull
	String userIntroduce;
}
