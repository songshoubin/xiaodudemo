package com.xiaodu.cache.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Redis缓存信息VO
 *
 * @author shen
 * @date 2019-5-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisVo implements Serializable {

    @NotBlank
    private String key;

    @NotBlank
    private String value;
}
