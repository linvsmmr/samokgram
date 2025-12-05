package com.eunbi.samokgram.user.repository;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {
    public int insertUser (
            @Param("loginId") String loginId,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("email") String email,
            @Param("password") String password
    );
}
