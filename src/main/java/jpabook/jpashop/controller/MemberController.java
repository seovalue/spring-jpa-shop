package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService; //Controller는 기본적으로 Service를 갖다쓰는경우가 많다.

    @GetMapping(value = "/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm()); //Controller에서 View로 넘어갈 때 데이터를 싣어서 넘어간다.
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(@Valid MemberForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "members/createMemberForm"; //에러가 존재한다면, 다시 createMemberForm을 띄운다.
        }
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/"; //첫번째 페이지로 넘어가도록 redirect
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        /**
         * 이 방법도, 만약 실무에서 사용한다면 화면에 뿌리기 위한 엔티티를 구성하여 전달하는 것이 낫다!
         * API를 만들 때는 멤버 엔티티를 반환하는 것은 절대 금물
         */
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); //attributeName은 html에서 전달받을 ${} 이름과 같다.
        return "members/memberList";
    }
}
