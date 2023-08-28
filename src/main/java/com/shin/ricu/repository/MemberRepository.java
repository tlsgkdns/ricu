package com.shin.ricu.repository;

import com.shin.ricu.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, String> {

    @Query("select m from Member m where m.nickname = :nickname")
    public Member getMemberByNickName(String nickname);

    @Query("select exists (select m from Member m where m.nickname = :nickname)")
    public boolean existsByNickname(String nickname);
}
