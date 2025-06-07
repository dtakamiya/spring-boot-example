package com.example.demo.infrastructure;

import com.example.demo.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AccountMapper {
    @Select("SELECT id, name, balance FROM account WHERE id = #{id}")
    Account findById(@Param("id") Long id);

    @Update("UPDATE account SET name = #{name}, balance = #{balance} WHERE id = #{id}")
    void update(Account account);
} 