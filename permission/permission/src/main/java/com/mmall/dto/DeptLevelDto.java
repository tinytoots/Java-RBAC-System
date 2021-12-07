package com.mmall.dto;

import com.google.common.collect.Lists;
import com.mmall.model.SysDept;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Getter
@Setter
@ToString
public class DeptLevelDto extends SysDept {

    // 继续包含自己，组成一个树形结构
    private List<DeptLevelDto> deptList = Lists.newArrayList();

    // 当传入一个SysDept的时候，能直接转成树形结构
    // 如果传入一个部门对象，copy成本地部门dto
    public static DeptLevelDto adapt(SysDept dept) {
        DeptLevelDto dto = new DeptLevelDto();
        BeanUtils.copyProperties(dept, dto);
        return dto;
    }

}
