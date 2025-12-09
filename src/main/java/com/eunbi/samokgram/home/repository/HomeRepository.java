package com.eunbi.samokgram.home.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HomeRepository {

    public int selectContent();

}
