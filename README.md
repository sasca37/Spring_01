동작 과정 
                                      -------------------------------[스프링 부트]--------------------------------------
웹브라우저 -> url(localhost:8080/~) -> 내장 톰켓 서버 -> 스프링컨테이너 ( 컨트롤러 -> viewResolver(템플릿엔진 처리(html변환) ) ->  웹브라우저 

@ResponsBody 
-> viewResolver를 사용하지 않고 http body 부분에 직접 값을 넣어주겠다고 명시하는 애노테이션 

문자면 String 값 반환 , 객체면 JSON값 반환 ( viewResolver 대신에 HttpMessageConverter가 동작)
문자 처리 : StringHttpMessageConverter
객체 처리 : MappingJackson2HttpMessageConverter

