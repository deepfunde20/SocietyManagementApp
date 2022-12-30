package com.dee.group.service.vo;


import com.dee.group.service.entity.MyGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVoTemplate {

    private MyGroup myGroup;
    private List member;
}
