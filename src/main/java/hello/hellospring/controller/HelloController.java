package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    //http body 부분에 직접 넣어주겠다를 명시하는 애노테이션
    // 문자면 String 값, 객체면 제이슨 값 ( viewResolver 대신에 HttpMessageConverter가 동작)
    // 문자 처리 : StringHttpMessageConverter
    // 객체 처리 : MappingJackson2HttpMessageConverter

    @ResponseBody
    @GetMapping("hello-string")
    public String helloString(@RequestParam("name") String name) {
        return "hello" +name;
    }

    //json 방식 (객체를 만들어서 날렸을 때 json으로 출력됨 )
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name ){
        Hello hello = new Hello(); //c shift enter 시 자동완성
        hello.setName(name);
        return hello;
    }

    static class  Hello {
        // private 외부에서 바로못가져옴 (프로퍼티 접근방식)
        private String name;

        //메서드를 통해 가져옴
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
