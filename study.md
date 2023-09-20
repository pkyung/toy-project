# 알게된 점 총 정리🎓

## form 태그는 get과 post만 지원 -> put과 delete 사용하기
application.properties 에 추가

    spring.mvc.hiddenmethod.filter.enabled=true

하나의 input 태그를 만들어 속이는 방법 사용

    <form method="post" action="/">
        <input type="hidden" name="_method" value="put">
    </form>

## form 에서 dto를 인식하려면 AllArgsConstructor가 있어야함
스프링은 http 요청으로 전송된 데이터를 자동으로 DTO 객체에 매핑한다. 이를 통해 json이나 form으로 받은 데이터를 자바 객체로 만드는데 DTO의 생성자를 사용해서 필드값을 설정한다. AllArgsConstructor가 없으면 기본 생성자나 세터로 해야하므로 있어야한다. 

## @EnableJpaAuditing 
Configuration 어노테이션을 통해 JPA 에서 auditing을 가능하게 한다. TimeStamped class를 사용할 때 필요하다.

## jpa update 