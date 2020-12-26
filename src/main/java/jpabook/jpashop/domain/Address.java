package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

/**
 * 값 타입은 Immutable하도록 설계하여야한다.
 * 따라서, 생성 시에만 세팅되도록 하는 것이 좋다.
 */
@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    // JPA에서 Reflection을 사용하기 위해 등록된 기본 생성자이다.
    // 함부로 new 로 생성할 수 없다.
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
