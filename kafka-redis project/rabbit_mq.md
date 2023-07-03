## rabbit MQ 시작하기 

- 윈도우 환경 

[윈도우 환경 ](https://oingdaddy.tistory.com/165)

- https://vitalholic.tistory.com/376 
- https://heodolf.tistory.com/53 
- https://oingdaddy.tistory.com/165
- https://t2t2tt.tistory.com/27 

```log 
C:\Program Files\RabbitMQ Server\rabbitmq_server-3.8.2\sbin>rabbitmq-plugins enalbe rabbitmq_management
=ERROR REPORT==== 3-Jul-2023::11:32:43.615000 ===
beam\beam_load.c(551): Error loading function elixir:start/2: op put_tuple u x:
  please re-compile this module with an Erlang/OTP 25 compiler


=ERROR REPORT==== 3-Jul-2023::11:32:43.615000 ===
Loading of c:/Program Files/RabbitMQ Server/rabbitmq_server-3.8.2/escript/rabbitmq-plugins/elixir.beam failed: badfile

=CRASH REPORT==== 3-Jul-2023::11:32:43.615000 ===
  crasher:
    initial call: application_master:init/4
    pid: <0.81.0>
    registered_name: []
    exception exit: {bad_return,
                        {{elixir,start,[normal,[]]},
                         {'EXIT',
                             {undef,
                                 [{elixir,start,[normal,[]],[]},
                                  {application_master,start_it_old,4,
                                      [{file,"application_master.erl"},
                                       {line,293}]}]}}}}
      in function  application_master:init/4 (application_master.erl, line 142)
    ancestors: [<0.80.0>]
    message_queue_len: 1
    messages: [{'EXIT',<0.82.0>,normal}]
    links: [<0.80.0>,<0.44.0>]
    dictionary: []
    trap_exit: true
    status: running
    heap_size: 233
    stack_size: 28
    reductions: 174
  neighbours:

ERROR! Failed to start Elixir.
error: {error,
           {elixir,
               {bad_return,
                   {{elixir,start,[normal,[]]},
                    {'EXIT',
                        {undef,
                            [{elixir,start,[normal,[]],[]},
                             {application_master,start_it_old,4,
                                 [{file,"application_master.erl"},
                                  {line,293}]}]}}}}}}
=INFO REPORT==== 3-Jul-2023::11:32:43.649000 ===
    application: elixir
    exited: {bad_return,
                {{elixir,start,[normal,[]]},
                 {'EXIT',
                     {undef,
                         [{elixir,start,[normal,[]],[]},
                          {application_master,start_it_old,4,
                              [{file,"application_master.erl"},
                               {line,293}]}]}}}}
    type: temporary

=INFO REPORT==== 3-Jul-2023::11:32:43.649000 ===
    application: compiler
    exited: stopped
    type: temporary
```
- OTP 21 버전 다운로드 해결  

![img](/img/erlang_error.png)  

- start 
![start](/img/rabbitmq-start.png)  


- spring boot / rabbit MQ 연동하기
    - https://umanking.github.io/2020/11/25/spring-rabbitmq-tutorial/ 
    - https://backtony.github.io/spring/2021-09-21-spring-rabbitmq-1/

- https://velog.io/@power0080/RabbitMQ-%EC%82%AC%EC%9A%A9-%EC%8B%9C-%EA%B2%AA%EC%9D%80-%EB%AC%B8%EC%A0%9C


example questions 
- https://velog.io/@power0080/RabbitMQ-%EC%82%AC%EC%9A%A9-%EC%8B%9C-%EA%B2%AA%EC%9D%80-%EB%AC%B8%EC%A0%9C
- https://github.com/f-lab-edu/buddy-wisdom/blob/main/src/main/java/cobook/buddywisdom/feed/service/FeedService.java#L71
 - https://github.com/f-lab-edu/hcs/blob/main/api/src/main/java/com/hcs/service/ChatRoomService.java#L16
- https://github.com/f-lab-edu/MSG-lab/blob/1c8badd8828f733f31f4a367f4c2a8d530c1786a/MSG-lab-worker/src/main/java/com/example/rabbitmqworker/application/SavePushMessageService.java 

