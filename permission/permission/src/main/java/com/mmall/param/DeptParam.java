package com.mmall.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class DeptParam {

    private Integer id;

    @NotBlank(message = "部门名称不可以为空")
    @Length(max = 15, min = 2, message = "部门名称长度需要在2-15个字直接")
    private String name;

    // 上一级部门id
    private Integer parentId;

    @NotNull(message = "展示顺序不可以为空")
    private Integer seq;

    @Length(max = 150, message = "备注的长度不能超过150个字")
    private String remark;



}
