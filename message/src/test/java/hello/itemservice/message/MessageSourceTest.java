package hello.itemservice.message;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    void helloMessage() {
        String result = ms.getMessage("Hello", null, null);
        Assertions.assertThat(result).isEqualTo("안녕");
    }

    @Test
    void notFoundMessageCode() {
        Assertions.assertThatThrownBy(() -> ms.getMessage("no_code", null, null)).isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundMessageCodeDefaultMessage() {
        Assertions.assertThat(ms.getMessage("no_code", null,"기본 메시지", null)).isEqualTo("기본 메시지");

    }

    @Test
    void argumentMessage(){
        String message = ms.getMessage("hello.name", new Object[]{"String"}, null);
        Assertions.assertThat(message).isEqualTo("안녕 String");
    }

    @Test
    void defaultLang(){
        Assertions.assertThat(ms.getMessage("Hello", null, null)).isEqualTo("안녕");
        Assertions.assertThat(ms.getMessage("Hello", null, Locale.KOREA)).isEqualTo("안녕");
    }

    @Test
    void enLang(){
        Assertions.assertThat(ms.getMessage("Hello", null, Locale.ENGLISH)).isEqualTo("hello");
    }
}
