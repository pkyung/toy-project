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


## mustache는 display를 Boolean으로 가능함
수정, 삭제 권한을 본인 게시글에만 주기 위해 {{mine}}을 사용했다. (boardOne.mustache) true일 때는 태그가 보이고 false일 떄는 태그가 보이지 않는다. 


## JPA update 
jpa에는 수정 메서드가 없다. jpa에서 수정하는 방법은 entity를 조회하여 조회된 entity 데이터를 변경하면 데이터베이스에 자동적으로 반영되는데 이러한 jpa 기능을 dirty checking이라고 한다. 

jpa는 영속성 컨텍스트에 entity를 보관할 때, 최초의 상태를 저장한다. 이를 스냅샷이라 하며 영속성 컨텍스트가 flush 되는 시점에 스냅샷과 entity의 현재 상태를 비교하여 달라진 entity를 찾는다. 이후 변경된 필드들을 이용하여 update 쿼리를 생성하여 쌓아둔다. 모든 작업 이후에 트랜잭션을 커밋하면 쓰기 지연 sql 저장소에 있는 쿼리들을 db에 전달하여 update를 한다. 


## JPARepository에서는 <도메인, pk type> 해야함
JpaRepository<Comment, Long> 이 작업을 하지 않으면 빈 생성이 안된다. 

## org.hibernate.tool.schema.spi.CommandAcceptanceException: Error executing DDL
entity 명에 like 가 있어서 그랬다. likes로 수정하니 에러는 사라졌다. 테이블 명은 복수형이 되도록 하자.

## 정적 파일, 템플릿 파일 경로 설정
[블로그 정리](https://p-kyung.tistory.com/85)

## 친구 추가 db 설계 다시하기
[블로그 정리](https://p-kyung.tistory.com/86)

## 어떤 버튼이 눌렸는지 인식하는 법
[블로그 정리](https://p-kyung.tistory.com/87)