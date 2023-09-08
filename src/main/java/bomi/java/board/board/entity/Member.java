package bomi.java.board.board.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String password;

    public void patch(Member member) {
        if (member.email != null) {
            this.email = member.getEmail();
        }
        if (member.password != null) {
            this.password = member.getPassword();
        }
    }
}
