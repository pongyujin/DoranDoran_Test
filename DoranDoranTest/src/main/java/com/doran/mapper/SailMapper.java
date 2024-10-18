package com.doran.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.doran.entity.Sail;

@Mapper
public interface SailMapper {

	// 1. 항해 정보 insert
	public void insert(Sail sail);
	// 2. 항해 코멘트 수정
	public void comment(Sail sail);
	// 3. 항해 상세내용 보기
	public Sail content(Sail sail);
	// 4. 항해 삭제하기
	public void delete(Sail sail);
	// 5. 선박코드로 전체 항해 정보 불러오기
	public List<Sail> sailList(Sail sail);
}