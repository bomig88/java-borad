package bomi.java.board.board.repository;

import bomi.java.board.board.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}
