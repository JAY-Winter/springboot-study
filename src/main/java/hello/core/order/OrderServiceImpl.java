package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 새로운 할인 정책에 맞춰서 코드를 이와 같이 변경할 수 있음
    // DIP 위반이다 -> DiscountPolicy 추상화에 의존하는 것이 아니라, FixDiscountPolicy, RateDiscountPolicy 구현체에 의존하고 있음
    // OCP 위반이다 -> FixDiscountPolicy 를 RateDiscountPolicy 로 변경하는 순간 OrderServiceImpl 을 코드를 변경하는 것 즉, 변경에 열려있음

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
