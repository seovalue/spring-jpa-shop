package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BookForm {

    // 공통 속성
    private Long id;

    @NotEmpty(message = "이름 입력은 필수입니다.")
    private String name;
    @Min(value = 0, message = "가격은 0원 이상이어야합니다.")
    private int price;
    @Min(value = 1, message = "수량은 1개 이상이어야합니다.")
    private int stockQuantity;

    //책과 관련된 추가 속성
    private String author;
    private String isbn;
}
