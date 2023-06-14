### 필요사항 
-   웹 생태계의 스펙
    -   HTML, HTTP(1.1 , HTTP/2)
-   기본 SDK, 라이브러리/프레임워크 이해와 활용
-   클라이언트를 위한 API 설계
-   서버/컴퍼넌트/객체 간의 역할 분담/의존성/통신 방법 설계
-   저장소 활용
    -   DBMS 설계
    -   Cache 적용
        -   Global/Local cache 적용범위, 라이프 싸이클, 솔루션 선택
    -   파일 저장 정책/솔루션 선택 활용
-   검색엔진 연동 방식 결정
-   빌드 도구
    -   Maven/Gradle
-   배포 전략
-   성능 테스트/프로파일링/튜닝
    -   JVM 레벨의 튜닝 (GC 옵션 등)
        -   웹 서버(Nginx,Tomcat)등의 설정/튜닝
    -   OS 설정의 주요 값 확인
-   인접 기술에 대한 이해
    -   DBMS, Front End 등
-   서버 개발자에만 해당하지는 않는 항목
    -   테스트 코드 작성/리팩토링 기법
    -   버전 관리 전략
        -   branch 정책 등

### 데이터베이스

 네이버의 서비스에서도 MySQL, CUBRID, Redis, Memchaced, HBase, MonoDB, Elasticsearch 등 다양한 저장소를 활용하고 있습니다. 네이버와 라인에서 Arcus, Elasticsearch, Cassandra, Redis, HBase와 같은 다양한 저장소가 쓰인 사례는 아래 글을 참고

✔️ Stored prodecure도 가급적 사용하지 않는 경우가 많습니다
Stored procedure는 급하게 개발된 서비스에서는 많이 사용되었습니다. 네트워크 호출비용이 없어서 성능에 이득이 있고, DB안에 저장되니 배포절차가 단순했기 때문입니다. 그러나 길게 작성된 Stored prodecure는 만들었던 사람도 수정하기 힘든 경우가 많습니다. 데이터와 독립적으로 로직을 테스트하기도 어렵습니다. 별다른 배포절차가 없으니 버전관리가 제대로 되지 않는 경우가 많았습니다. 그리고 데이터의 연산에 DB서버의 CPU 자원을 소모함으로서 서비스가 커가면서 DB에 병목이 될수 있는 가능성을 더 키울 수 있습니다.

✔️ DB서버 1대로 트래픽이나 저장량이 감당이 안 될 때,
성능 향상을 위해서 Local cache, Global cache를 동원하기도 합니다. 어느 정도 복제지연(Replication replay)이 그다지 민감하지 않은 서비스에서는 쓰기 작업은 Master 노드로, 읽기작업은 복제되는 Master의 데이터를 복제한 여러 대의 Slave로 DB를 구성하기도 합니다.

 어떤 솔루션을 쓰든 RDB는 사용량이 늘어났을 때 분산하는 비용이 비쌉니다. 그래서 성장할 가능성이 큰 서비스라면 RDB의 자원을 아껴서 쓸 필요가 있습니다.
-   [Storm과 Elasticsearch Percolator를 이용한 NELO2 알람 기능 개선](https://d2.naver.com/helloworld/1044388)
-   [LINE 소셜 네트워크 서비스의 아키텍처](https://d2.naver.com/helloworld/809802) 를 참고하실 수 있습니다.
-   [LINE 스토리지, 한달에 수십억 건의 데이터를 Redis와 HBase에 저장하다](https://charsyam.wordpress.com/2012/04/29/%EB%B0%9C-%EB%B2%88%EC%97%AD-line-%EC%8A%A4%ED%86%A0%EB%A6%AC%EC%A7%80-%ED%95%9C%EB%8B%AC%EC%97%90-%EC%88%98%EC%8B%AD%EC%96%B5-%EA%B1%B4%EC%9D%98-%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%A5%BC-redis%EC%99%80/)

### 개발툴

**Jenkins, AWS 등 Backend에 도움이 되는 도구**

개인적으로는 아래와 같이 개발자의 수준을 분류하고 싶습니다.

-   레벨0: 이미 쓰고 있는 개발도구의 사용법을 알려주거나 가이드 문서를 줘도 잘 못 씀
-   레벨1: 알려주거나 같은 팀에서 만든 가이드 문서에 있는 만큼만 쓸 수 있음
-   레벨2 ⭐⭐
    -   개발도구의 공식 레퍼런스를 보고 사용법을 스스로 익힐 수 있음
    -   자신이 경험한 사용법을 문서화해서 팀 내에 전파할 수 있음
-   레벨3
    -   여러 개발도구를 비교 분석해서 상황에 적합한 도구를 선택할 수 있음
    -   공식 레퍼런스 문서에서 부족한 부분을 수정해서 기여할 수 있음
-   레벨4
    -   개발도구의 문제를 소스 코드를 수정해서 Fork/패치해서 사용할 수 있음


### 병렬처리

Servlet기반의 Java웹서버들은 기본적으로 사용자의 요청을 병렬적으로 처리합니다. 그래서 객체가 멀티스레드에서 공유되는 것인지, 아닌지를 의식하는 일은 중요합니다. 클래스의 멤버변수에는 항상 멀티스레드에서 접근해도 안전한(Thread-safe)한 변수만 두면 된다는 단순한 규칙만으로도 많은 문제를 예방할 수 있습니다. 그런데 Local cache를 적용할 때는 멀티스레드에서 공유된 객체가 쉽게 눈에 안 띌 수도 있습니다. 그래서 ==Cache대상의 객체는 Immutable하게 유지==하는 것이 안전합니다.

응답에 영향을 주지는 않지만 실행시간이 길어질 수도 있는 update 구문을 DB에 실행하는 작업같은 것입니다. 그런 경우 Java에서는 [Executors](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Executors.html), [ThreadPoolExecutor](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ThreadPoolExecutor.html)에 있는 많은 옵션들이 정교한 제어를 하는데 도움이 됩니다. `new Thread()`로 직접 스레드를 생성하는 방식은 JDK5 이후로는 권장하지 않습니다.

[하나의 메모리 누수를 잡기까지](http://d2.naver.com/helloworld/1326256) 


### 보안 

 XSS, CSRF, SQL Injection 공격에 대해서 대처하는 방법
 보안 취약점이 자주 발견되는 Struts2 같은 프레임웍들은 신경써서 대처하기 위해 주요 변경을 알리는 메일링 그룹에 가입하기도 했습니다.

### 테스트 

HTTP API에 대한 테스트는 작성하기도 쉽고 작성했을 때의 이득도 큽니다. 최근 진행 중인 프로젝트에서는 [Rest-assured](http://rest-assured.io/) 와 [Spring MVC Test Integration](https://docs.spring.io/spring-security/site/docs/current/reference/html/test-mockmvc.html) 을 이용해서 HTTP API를 통합 테스트 하고 있습니다

-   유지보수 기간의 생산성을 높여주고 새로 프로젝트에 투입될 사람에게도 이득을 주는 테스트
-   프로젝트 오픈 일정 직전까지의 코드 변경과 버그 발견에 도움을 주는 테스트
-   오늘 당장 프로그램을 목표한 곳까지 작성하는 일을 더 빨리 마치게 해주는 테스트

### 자료구조/알고리즘

JDK의 Collection framework의 소스를 볼 때에도 기본적인 자료구조에 대한 이해
. [Java HashMap은 어떻게 동작하는가?](https://d2.naver.com/helloworld/831311)
대용량 데이터를 어떻게 저장하고 탐색할지를 결정할 때도 자료구조는 중요합니다. [LINE 소셜 네트워크 서비스의 아키텍처](https://d2.naver.com/helloworld/809802), [SSD는 소프트웨어 아키텍처를 어떻게 바꾸고 있는가?](https://d2.naver.com/helloworld/162498) 의 기사에서 B-Tree, B+Tree를 어떻게 활용했는지도 참고

### 개발 프레임워크

**백엔드 개발 프레임워크의 트렌드는 어떤가요?**
비동기 I/O를 활용하는 서버 개발
RDB를 호출하는 어플리케이션이 대부분인데 이를 뒷받침해야 할 비동기 JDBC 스펙의 구현체가 공식적으로 나오지 않은 탓도 있습니다. 최근 나온 Spring 5에서 비동기 IO개발을 지원하는 Webflux 모듈도 주목을 받고 있습니다.
[대용량 세션을 위한 로드밸런서](https://d2.naver.com/helloworld/605418)


### Serverless

**Serverless 아키텍처**

AWS의 람다나 네이버 클래우드 플랫폼의 [Cloud Functions](https://www.ncloud.com/product/compute/cloudFunctions)에 Servleless 아키텍처를 지원하는 플랫폼
Serverless와 같이 인프라 환경이 고도로 자동화/추상화된 환경에서는 이제 전통적인 어플리케이션에서 했던 JVM, 커널 파라미터, 웹 서버 튜닝이 이제 필수 지식이 아니라고 생각할 수도 있습니다. 그러나 인프라의 기반 구조를 잘 이해하면 추상화된 서비스도 잘 쓸 수 있게 될 여지

### 용어의 범위
HTTP 프로토콜 위에서 JSON혹은 XML의 형식으로 통신하는 API를 폭넓게 REST API라고 부르는 경우가 많습니다. 그런데 현업에서 많은 이들이 REST라고 부르는 API들은 창시자인 Roy Fielding의 기준으로는 REST가 아닙니다. 대표적으로 상태가 Hyper link를 통해 전이되어야 한다는 HATEOAS를 대부분의 API를 충족시키지 않습니다. 이에 대해서는 [그런 REST API로 괜찮은가](https://deview.kr/2017/schedule/212)를 참조하실 수 있습니다. REST API의 범위에 대한 논란을 피하고 싶다면 HTTP API 혹은 Web API라고 칭하는 것이 무난합니다.

테스트 코드를 작성하는 일을 통칭해서 TDD (Test Driven Develop)
진정한 단위 테스트는 실행시점에 구성되어야할 기반요소가 없기 때문에 대체로 굉장히 빠르게 실행된다. (True unit tests typically run extremely quickly, as there is no runtime infrastructure to set up.) [https://docs.spring.io/spring/docs/current/spring-framework-reference/testing.html#unit-testing](https://docs.spring.io/spring/docs/current/spring-framework-reference/testing.html#unit-testing)

VO (Value Object)라는 패턴 이름도 실무에서 혼란스럽게 쓰이고 있습니다. getter/setter만 있는, 값을 실어나르는 VO라고 칭하는 사람이 있는데 이는 DTO로 칭하는 것이 혼란의 여지가 적습니다

-   VO
    -   [http://martinfowler.com/bliki/ValueObject.html](http://martinfowler.com/bliki/ValueObject.html)
    -   [https://en.wikipedia.org/wiki/Value_object](https://en.wikipedia.org/wiki/Value_object)
    -   [https://docs.microsoft.com/ko-kr/dotnet/standard/microservices-architecture/microservice-ddd-cqrs-patterns/implement-value-objects](https://docs.microsoft.com/ko-kr/dotnet/standard/microservices-architecture/microservice-ddd-cqrs-patterns/implement-value-objects)
-   TO
    -   [http://martinfowler.com/eaaCatalog/dataTransferObject.html](http://martinfowler.com/eaaCatalog/dataTransferObject.html)
    -   [http://www.oracle.com/technetwork/java/transferobject-139757.html](http://www.oracle.com/technetwork/java/transferobject-139757.html)

-   [Microsoft REST API Guidelines](https://github.com/Microsoft/api-guidelines/blob/vNext/Guidelines.md)
-   [JSON API 스펙](http://jsonapi.org/)

### 시스템 분리 

 많은 개발자가 동시에 협업하면서 개발하는데 MSA가 장점이 있기 떄문입니다. 이전에는 MSA와 같은 구조로 서비스를 만드는 것이 비용이 더 컸었습니다. 서버/네트워크를 더 많이 사용해야 하고, 서버/구성요소마다 설정하는 시간이 더 들어가고 문제가 생겼을 때 모니터링과 추적을 하는 것도 중간에 원격호출이 없을 때보다는 쉽지 않았습니다. 그러나 요즘에는 인프라시스템, 모니터링, 프레임워크의 발전으로 과거보다는 작게 단위로 서비스를 쪼개는 비용이 내려갔습니다

## 네이버의 백엔드 개발

### 개발,배포 방식
-    1회 배포
    -   최대한 무정지 배포
-   버전 관리/ 코드 리뷰
    -   master에서 추가할 기능별로 feature branch(topic branch)를 따고 주로 master를 목표로 PR(pull request) 요청
-   DB 스키마 관리
    -   개발환경에서는 [Liquibase](https://www.liquibase.org/) 로 관리
    -   운영환경에는 Liquibase에서 생성된 SQL을 사내 스키마 관리 시스템을 통해서 요청
-   테스트
    -   PR를 올리면 CI서버에서 빌드 실행. API 서버의 테스트 코드와 FE코드에 검사도구인 Eslint의 검증을 통과해야 merge 가능
    -   현재는 서버 모듈만 테스트 코드 작성
        -   통합 테스트: JUnit + Rest assured + ([Spring MVC Test framework](https://docs.spring.io/spring/docs/current/spring-framework-reference/testing.html#spring-mvc-test-framework) 또는 Embeded Tomat 활용) 이용
        -   단위 테스트: JUnit + Mockito 활용
        -   특별히 의식하지는 않았는데 Line coverage 는 74% 정도
    -   Docker를 이용한 테스트 환경 활용
        -   마크업 등 UI변경을 확인해야 하는 경우는 PR에 'Docker build'라는 라벨을 붙임
        -   그러면 docker 이미지가 만들어져서 사내 docker 이미지 배포용 cluster에 해당 branch를 확인할 수 있도록 배포가 됨
        -   docker로 배포된 서버에서 어느 정도 테스트 후 merge
    -   주1회 배포전 QA시간을 따로 잡고 개발팀 전체가 함께 테스트. (전담 QA가 없는 프로젝트인 경우)
    -   테스트 시나리오는 그 주 변경된 기능과 영향받는 기능 위주로 작성
    -   큰 기능 추가, 전체적인 코드 변경 시점에는
        -   스테이징 서버에서 1~2주 정도 개발팀이 미리 운영 데이터로 시스템을 써보기도 함
            -   전체 기능에 대한 테스트 시나리오를 다 수행해보기도 함

### 인프라 기술 활용
네이버 내부에서 가상 서버(VM)를 관리하는 방식은 IaaS
-   [Docker + Kubernetes를 이용한 빌드 서버 가상화 사례](https://www.slideshare.net/naver-labs/docker-kubernetes)
-   DEVIEW 2015의 [Docker Orchestration](https://deview.kr/2015/schedule#session/117) 발표를 참고하실 수 있습니다.

로그수집시스템 Nelo, 어플리케이션 성능 측정 도구인 [Pinpoint](https://github.com/naver/pinpoint), 성능테스트 도구인 [nGrinder](https://github.com/naver/ngrinder), 분산메모리 저장소인 [NbaseARC](https://github.com/naver/nbase-arc)




https://deview.kr/2021/sessions





https://github.com/y2o2u2n/servive-as-a-junior-backend-developer
https://www.developerfastlane.com/blog/backend-developer-study-plan  
