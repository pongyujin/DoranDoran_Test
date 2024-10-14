package com.doran.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class tbl_camera {
    private int imgNum; // 이미지 번호
    private int siNum; // 선박 번호
    private String obsName; // 장애물 이름
    private String obsImg; // 장애물 이미지
    private String createdAt; // 등록 시간
}
