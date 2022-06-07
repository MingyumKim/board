package com.study.board.dto;

import com.study.board.entity.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class MemberDTO {
    private int id;
    private String email;
    private String password;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .email(email)
                .password(password)
                .build();
    }

    @Builder
    public MemberDTO(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
