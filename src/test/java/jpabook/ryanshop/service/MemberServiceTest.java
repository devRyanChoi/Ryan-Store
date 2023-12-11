package jpabook.ryanshop.service;

import jpabook.ryanshop.domain.Member;
import jpabook.ryanshop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    public void SignUp() throws Exception {
        //given
        Member member = new Member();
        member.setName("Choi");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void DuplicatedMemberException() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("yoo");

        Member member2 = new Member();
        member2.setName("yoo");

        //when
        memberService.join(member1);
        memberService.join(member2); //it would have to happen it!

        //then
        fail("it would have to happen it!");
    }
}