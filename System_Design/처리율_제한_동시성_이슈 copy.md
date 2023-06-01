## API 게이트웨이/ 스로틀링 알고리즘  
----
MSA 아키텍쳐와 같이 분산 시스템 환경에서는 네트워크를 통해 데이터를 전달 받는다.  
=>  API 는 HTTP, WebSocket 의 프로토콜과 Json, Xml 등 데이터 포맷 방식을 요청 작업의 식별자를 제공하는 것

✅ API 게이트웨이가 필요한 이유?  
API 호출할때 자동화된 프로세스에 연동하여 사용
 - `자동화 프로세스`라는 특성   
    => 비밀성/가용성, 보안적인 요소, 사용량 제어 등을 위해 사용
 -  API throttling 그리고 관련 알고리즘 고려 필요  

✅ [장점]
- 과도한 트래픽으로부터 서비스를 보호.
- Resource 사용에 대한 공정성과 합리성 유도.
- 트래픽 비용이 서비스 예산을 넘는 것을 방지.
- Rate에 대해 과금을 부과하는 Business Model로 활용.

### ➡️ API 게이트웨이  
- API 제공 시 보안 고려 항목 중 비밀성과 가용성 보장이 가장 중요 
- API 보안 고려 사항 
    - 취약한 개체 수준 인가
    - 취약한 사용자 인증
    - 과도한 데이터 누출
    - 리소스 부족 및 속도 제한
    - 취약한 기능 수준 인가 
    - 대량 할당
    - 잘못된 보안 구성 
    - 인젝션
    - 부적절한 자산 관리
    - 불충분한 로깅 및 모니터링 

- [예방 방법](https://blog.lgcns.com/2529) 
    - Token 
    - rate limiting/throttling
    - 암호화
    - API Gateway
    - 위협 모델링   
    </br>

- 보안이 중요한 이유?  
    - 기업은 API를 이용해 서비스 연결과 데이터 전송 진행 
        - API가 손상, 노출 또는 해킹 경우 주요 데이터 보안 유출 사고의 원인.  => 민감한 의료, 금융 및 개인 정보가 노출
    - API 보안 접근 방식은 데이터의 종류에 따라 다름. 
    - MSA 환경에서는 책임을 모듈시스템으로 분리 + API 게이트웨이 패턴을 이용 

✅ [API 방식]
- REST(Representational State Transfer) 
    - HTTP를 사용하며 TLS(Transport Layer Security) 암호화를 지원
    - TLS는 인터넷 연결을 비공개로 유지하고 두 시스템(서버와 서버 또는 서버와 클라이언트) 간에 전송된 데이터가 암호화되어 수정되지 않았는지 확인하는 표준

- SOAP API
    - 웹 서비스 보안(WS Security)이라는 기본 제공 프로토콜을 사용
    - 기밀성과 인증에 따른 룰 세트를 정의
    - SOAP API는 OASIS(Organization for the Advancement of Structured Information Standards) 및 W3C(World Wide Web Consortium) => 국제 표준화 기구에서 정한 표준을 지원
        - XML 암호화, XML 서명 및 SAML 토큰을 결합해 인증 및 권한 부여를 확인. 
    - SOAP API는 보다 포괄적인 보안 조치를 갖추고 있지만, 더 많은 관리가 필요함. => 민감한 데이터를 처리하는 조직에 권장

![API Gateway](/img/gate-way-1.png) 

- 인증과 권한의 자원 접근에 비밀성을 보장
- API 시스템으로 트래픽의 과도한 증가로 비가용적인 상황 제한
   - 속도의 제한/ 부하 분산에 사용 
- ✅ 목적 
    - 인증 및 권한 부여
    - 서비스 검색 통합
    - 응답 캐싱 
    - 정책, 회로 차단기, QoS 다시 시도
    - 속도 제한
    - 부하 분산
    - 로깅, 추적, 상관관계
    - 헤더, 쿼리 문자열 및 청구 변환
    - IP 허용 목록 추가    

### ➡️ API Throttling   

![API Throttling](/img/gateway.png) 

- 보안성 강화 위해 이용  
    - 버그로 인한 요청량 증가 혹은 악의적인 사용자 DoS(Denial of service) 공격 시도로 인해 가용성 문제인 경우 사용 
- 과금의 조율 위해 사용  
    - CPU/메모리 스케일업, 분산처리 위해 스케일아웃 서버 자원 화장 방법이 있지만 요금 부담이 큼 
    - API 게이트웨이를 통해 스로틀링을 도입하여 회사의 요청속도를 제어하여 SLA 협의 가능하게 함. 

### ➡️ API 스로틀링 알고리즘 
- Leaky Bucket
- Token Bucket
- Fixed Window, Sliding Window

### ➡️ Leaky Bucket 
- 구멍 난 양동이 
구멍의 크기만큼 물이 빠져나가고 급수량이 양이 배수량보다 많으면 물독이 넘치게 됩니다. 
  - 시간당 물의 유입양 > 시간당 물의 배출양 => 넘침 

  - 고정된 버킷 최대의 깊이 (T) 의 버킷에 네트워크 요청 유입 속도의 한계값을 정하고 지정한 속도에 맞춰서 일정하게 처리하고 한계값을 초과하면 요청은 버린다. 

  최대 처리량 = 버킷 최대깊이 ∩ 한계값 
    - 고정된 API 요청 최대향과 한계값을 정하고 속도에 맞춰 HTTP Request 처리하고 초과하면 HTTP 429 Response 반환   

- 알고리즘 구현 
    - 요청된 순서를 큐를 사용해서 구현 가능 
    - 사용 플랫폼 : Amazon MWS, NGINX, Uger-go, Shopify, Guava

![Leaky Bucket](/img/leaky_bucket.png)  

### ➡️ Token Bucket 
- 일시적으로 많은 트래픽이 와도 토큰이 있다면 처리가 가능하여 `토큰 손실 처리를 통해 평균 처리 속도 제한` 가능 
- 처리 패킷 손실을 줄이기 위해 평균 유입 속도를 제한하려고 버스트 요청을 허용

![Leaky Bucket](/img/token_bucket.png)   
1) 토큰은 정해진 비율로 토큰 버킷에 넣음. 
2) 버킷은 `최대 n개의 토큰을 저장`하며, 버킷이 `가득차면 새로 추가된 토큰은 삭제되거나 거부`된다.
3) 요청이 들어오면 `큐에 들어가며 요청을 처리하기 전에 토큰 버킷의 토큰을 획득`해야 하며, 토큰을 보유한 후에 요청이 처리되며 처리된 후에는 토큰을 삭제한다.
4) 토큰 버킷은 토큰이 배치되는 `속도를 기반으로 액세스 속도를 제어`한다.
5) 전송 횟수를 누적할 수 있으며, 버킷이 가득차면 패킷 손실 없이 토큰이 손실된다.

- 사용 플랫폼 : AWS API , EC2, Spring Cloud Netflix Zuul, Bucket4j  



### ➡️ Fixed Window Counter  

- 정해진 시간 단위로 window가 만들어지고 요청 건수가 기록되어 해당 window의 요청 건수가 정해진 건수보다 크면 해당 요청은 처리가 거부됨
-  경계의 시간대에 요청이 오면 두배의 부하
- 구현은 쉽지만, 기간 경계의 트래픽 편향 문제가 발생  


### ➡️ Sliding Window Log
- Fixed window counter의 단점인 기간 경계의 편향에 대응하기 위한 알고리즘. 
- 로그를 관리 위해 구현과 메모리 비용이 높은 문제점


### ➡️ Sliding Window Counter
- Fixed window counter의 경계 문제와 Sliding window log의 로그 보관 비용 등의 문제점을 보완 알고리즘



### ➡️ 주요 서비스들의 Rate Limit 정보 

- Rate Limit이 적용하려면 RFC 6585에 429 Too Many Request HTTP 상태 코드가 제시하고 하기 정보들을 설정해 줌. 
    - RateLimit-Limit(허용되는 요청의 최대수)
    - RateLimit-Remaining(남은 요청 수)
    - RateLimit-Reset(요청 최대값이 재설정될 때까지의 시간) 

![Leaky Bucket](/img/rate-limiter.png)  


#### ➡️ Rate Limit 모범 사례
- Rate Limit 알고리즘은 트래픽 패턴 분석 후 적용 
    - Token Bucket 알고리즘 : 유료 서비스가 트래픽 체증에 민감하지 않은 경우 
    - 그 외에는 Fixed Wondow나 Sliding Window 
- 기본적 인프라 트래픽을 수용할 수 없는 경우 Rate Limit을 적용
    -  외부 서비스에 영향을 최소화하는 노력 후 Rate Limit을 적용해야 함. 
- Rate Limit 정보를 명확하게 알리고, API 응답에도 요청 정보와 남은 정보 등 트래픽이 초과했을때 오류값 등을 명확히 정의 해야 함 . 
