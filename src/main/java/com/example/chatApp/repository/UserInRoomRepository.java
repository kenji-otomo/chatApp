package com.example.chatApp.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInRoomRepository {

	public void createUIR(@Param("roomFk") String roomFk,
			@Param("userFk") Integer userFk, @Param("deleted") Boolean deleted);
}
