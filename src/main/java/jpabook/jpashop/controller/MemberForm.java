package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * Controller에서 넘어오는 Validation과 도메인의 Validation이 다를 수 있기 때문에
 * Member 엔티티와 MemberForm이 따로 존재하도록 설계.
 * <p>
 * 화면에서 주고받는 Form 데이터는 Form용도로 Fit하게 설계해서 이용하는 것이 낫다.
 * 엔티티의 화면 종속적인 기능을 제외하기 위해.
 * 엔티티는 Dependency 없이 존재할 수 있도록 하는 것이 중요하다. (핵심 기능을 위한 로직만 가질 수 있도록!)
 */
@Getter
@Setter
public class MemberForm {
    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
