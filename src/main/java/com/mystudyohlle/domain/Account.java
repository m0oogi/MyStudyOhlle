package com.mystudyohlle.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

//Entity는 기본적으로 primary key가 필요하다
//id만 사용하는 이유는 찾아보기 (연관관계가 복잡해질때 순환참조하느라 무한루프가 발생 stackoverflow가 발생할 수 있다 )
@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Account {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

    private String password;

    private boolean emailVerified;  //이메일 인증여부

    private String emailCheckToken; //이메일 인증시 사용되는 토큰값

    private LocalDateTime joinedAt;

    //프로필 정보
    private String bio; //자기소개

    private String url; //블로그, github

    private String occupation;

    private String location;    //varchar(255)

    @Lob                            //이미지와같이 용량이커지면 Lob 어노테이션 사용 text type에 매핑이됨
    @Basic(fetch = FetchType.EAGER) //기본 FetchType이 LAZY이기때문에 명시적으로 지정해주는 것 그때그때 가져와주는걸로
    private String profileImage;

    //알림설정관련
    private boolean studyCreatedByEmail;

    private boolean studyCreatedByWeb;

    private boolean studyEnrollmentResultByEmail;

    private boolean studyEnrollmentResultByWeb;

    private boolean studyUpdatedByEmail;

    private boolean studyUpdatedByWeb;

    public void generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID().toString();
    }

}
