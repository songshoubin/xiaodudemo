package com.xiaodu.elasticJob.dao;

import com.xiaodu.elasticJob.model.TaskJob;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TaskJobMapper {
    List<TaskJob> findByStatus(Integer status);

    int deleteByPrimaryKey(Long id);

    int insert(TaskJob record);

    int insertSelective(TaskJob record);

    TaskJob selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskJob record);

    int updateByPrimaryKey(TaskJob record);
}