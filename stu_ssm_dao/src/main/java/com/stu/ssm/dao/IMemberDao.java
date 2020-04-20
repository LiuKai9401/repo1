package com.stu.ssm.dao;

import com.stu.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface IMemberDao {

    /**
     * 根据id查询出会员信息
     * @param memberId
     * @return
     */
    @Select("select * from member where id = #{memberId}")
    public Member findById(String memberId);
}
