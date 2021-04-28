package com.mystudyohlle.account;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SignUpFormValidator implements Validator {

    private final AccountRepository accountRepository;

    //@RequiredArgsConstructor가 아래 생성자를 자동생성
//    public SignUpFormValidator(AccountRepository accountRepository) {
//        this.accountRepository = accountRepository;
//    }

    // 어떤 bean이 생성자가 하나만 있고 생성자가 받는 파라미터들이 bean으로 등록되어있다면
    //스프링 4.2 이후는 자동으로 등록해주므로 autowired나 inject와 같은 어노테이션이 필요없어짐

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(SignUpForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SignUpForm signUpForm = (SignUpForm) o;
        if(accountRepository.existsByEmail(signUpForm.getEmail())){
            errors.rejectValue("email", "invalid.email"
                    , new Object[]{signUpForm.getEmail()}
                    ,"이미 사용중인 이메일입니다");
        }

        if(accountRepository.existsByNickname(signUpForm.getNickname())){
            errors.rejectValue("nickname", "invalid.nickname"
                    , new Object[]{signUpForm.getNickname()}
                    ,"이미 사용중인 닉네임입니다");
        }
    }
}
