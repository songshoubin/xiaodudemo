package com.xiaodu.common.mapper;

import lombok.Getter;
import lombok.Setter;

/**
 * 实体基类
 *
 * @author shen
 * @date 2019-4-23
 */
@Getter
@Setter
public class EntityBase {
    //@Version 乐观锁后面todo
    private String version;
}
