package hello.core.order;

import hello.core.discount.DisCountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DisCountPolicy disCountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DisCountPolicy disCountPolicy) {
        this.memberRepository = memberRepository;
        this.disCountPolicy = disCountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member findMember = memberRepository.findById(memberId);
        int disCount = disCountPolicy.discount(findMember, itemPrice);
        return new Order(memberId, itemName, itemPrice, disCount);
    }
}
