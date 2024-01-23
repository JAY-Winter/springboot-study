package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // 현재 이 코드는 DIP 를 위반한다. 추상화에 의존해야지 구현체에 의존하고 있음.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
