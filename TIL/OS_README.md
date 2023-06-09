## OS Questions 
> 프로세스와 스레드의 차이(Process vs Thread)  

`프로세스`는 프로그램을 실행시켜 동적으로 돌아가는 상태이므로 운영체제가 자원 할당받은 작업의 단위를 의미합니다. 
`스레드`는 프로세스가 할당받은 자원들을 이용하는 여러 실행 흐름의 단위를 의미하며 각각 Stack 만 따로 할당받고 주소 공간이나 자원들(Code, Data, Heap 영역)은 공유하는 특징이 있습니다.  

- Q. 프로세스의 문제점? 
    - 생성에 오버헤드 가능
    - 컨텍스트 스위칭에 비효율성이 있으며 오버헤드가 큼
    - 프로세스 사이에 통신이 어렵다 (IPC 사용 필요)
        - IPC란?   
        프로세스들이 주소공간이 완전히 분리되어 있어 두 프로세스 사이에서 코드를 제외한 메모리 공간이 공유되지 않아 프로세스가 다른 프로세스의 메모리 접근이 불가능합니다. 따라서 프로세스들이 서로 통신 할 수 있도록 운영체제 커널에서 IPC를 지원합니다. 

대표적인 IPC는 공유메모리, 신호, 파이프 세가지 정도가 있습니다. 
- Q. 스레드 출현 목적?  
    - 프로세스 보다 크기가 작은 실행 단위 필요
    - 생성 및 소멸에 따른 오버헤드 감소
    - 컨텍스트 스위칭 빠름
    - 프로세스들의 통신 시간, 방법 어려움 해소 

- Q. 스레드 주소 공간?  
    하나의 스레드가 동작하기 위해 총 6개의 공기 있다.   
        - 사적공간 : 스레드 코드 공간, 스레드 전용 전역변수 공간, 스택 공간  
        - 공유공간  
        - 커널   
        - 스택  

> 멀티 프로세스 대신 멀티 스레드를 사용하는 이유?  

멀티 스레드를 사용 하는 것은 프로그램을 여러 사용하기 보다 하나의 프로그램 안에서 여러 작업을 한는 것이다.  
![multithread](/img/multi-thread.png)  

- 장점 
    - 자원의 효율성 증대  
        - 멀티 프로세스 대신 멀디스레드를 사용하면 `프로세스를 생성하여 자원을 할당하는 시스템 콜이 줄어` 자원을 효율적으로 관리 할 수 있음.   
            - context switching 시 단순한 CPU 레지스터 교체, RAM 과 CPU 사이의 캐쉬 메모리의 데아터까지 초기화 하여 오버헤드가 크다.
        - 스레드는 프로세스 내 스레드를 공유하기 때문에 데이터를 주고 받는것이 간단하여 시스템 자원 소모가 준다.   
    - 처리 비용 감소 및 응답 시간 단축 
        - IPC 보다 스레드 간의 통신 비용이 적어 부담이 줌.  
        - 프로세스 간의 전환 속도보다 스레드 간의 전환 속도가 빠름. (context switching 시, 스레드는 stack 영역만 처리) 
      
- Q. 멀티 스레드 환경에서의 주의사항을 설명해주세요.  
    - 동기화 문제! 
    - 스레드 간의 자원 고유는 전역 변수 (데이터 세그멘트)를 이용하여 함께 사용할 때 충동 발생 가능.  
     - 하나의 스레드 망가지면 하나의 데이터 공간을 공유하는 모든 스레드를 작동불능 상태!!! 
     - 해결법 : Critical Section 기법

- Q. 컨텍스트 스위칭 오버헤드 발생 
=> 컨텍스트 스위칭은 `빠른 반응성과 동시성을 제공`하지만, 실행되는 프로세스의 변경 과정에서 프로세스의 상태, 레지스터 값 등이 저장되고 불러오는 등의 작업이 수행하기 때문에 시스템에 많은 부담을 준다.   

![overhead](/img/overhead.png)  
  - overhead란? P1 execute 시, idle(쉬고 있는) 이 될때, P2 가 바로 execute 되지 않고 idle 을 상태에 있다가 execute 될때의 간극.  
    - 컨텍스트 스위칭 과정에서 `PCB를 저장하고 복원하는데 비용` 발생 
    - CPU 캐시 메모리 무효화 따른 비용/ 
    - CPU 스케줄링 알고리즘에 따라 프로세스 스케쥴링을 선택하는 비용  

- Q. 스레드를 많이 사용할 수록 성능이 좋아지는가? 
https://inpa.tistory.com/entry/%F0%9F%91%A9%E2%80%8D%F0%9F%92%BB-Is-more-threads-always-better  

> Thread-safe  


> 동기와 비동기의 차이   
  
- https://inpa.tistory.com/entry/%F0%9F%91%A9%E2%80%8D%F0%9F%92%BB-%EB%8F%99%EA%B8%B0%EB%B9%84%EB%8F%99%EA%B8%B0-%EB%B8%94%EB%A1%9C%ED%82%B9%EB%85%BC%EB%B8%94%EB%A1%9C%ED%82%B9-%EA%B0%9C%EB%85%90-%EC%A0%95%EB%A6%AC
- 동기화 객체의 종류
- 뮤텍스와 세마포어의 차이
- 프로세스 동기화
    - Critical Section/해결책
- 교착상태(데드락, Deadlock)의 개념과 조건
- 사용자 수준 스레드와 커널 수준 스레드
- 외부 단편화와 내부 단편화
- Context Switching
- Swapping
- 스케줄러의 종류
    - 장기 스케줄러
    - 단기 스케줄러
    - 중기 스케줄러
- CPU 스케줄러
    - FCFS
    - SJF
    - SRT
    - Priority scheduling
    - RR
- 메모리 관리 전략
    - 메모리 관리 배경
    - Paging
    - Segmentation
- 가상 메모리
    - 배경
    - 가상 메모리가 하는 일
    - Demand Paging (요구 페이징)
    - 페이지 교체 알고리즘
- 캐시의 지역성
    - Locality
    - Caching line
- 사용자 수준 스레드와 커널 수준 스레드의 차이를 설명해주세요.


[참고]  
 - https://zeroco.tistory.com/74
 - 스레드 프로세서 : https://gmlwjd9405.github.io/2018/09/14/process-vs-thread.html  
 - https://inpa.tistory.com/entry/%F0%9F%91%A9%E2%80%8D%F0%9F%92%BB-%ED%94%84%EB%A1%9C%EC%84%B8%EC%8A%A4-%E2%9A%94%EF%B8%8F-%EC%93%B0%EB%A0%88%EB%93%9C-%EC%B0%A8%EC%9D%B4  