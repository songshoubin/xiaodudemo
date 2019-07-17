package com.xiaodu.user.dao;

import com.xiaodu.user.model.TestUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TestUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestUser record);

    int insertSelective(TestUser record);

    TestUser selectByPrimaryKey(Integer id);

    List<TestUser> findUser(@Param("shardingTotalCount") int shardingTotalCount, @Param("shardingItem")int shardingItem);

    int updateByPrimaryKeySelective(TestUser record);

    int updateByPrimaryKey(TestUser record);
}