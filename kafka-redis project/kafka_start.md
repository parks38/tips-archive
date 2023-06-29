# 카프카 실행하기 

```
suna.park@10sunapark MINGW64 /c/kafka_2.13-3.5.0/bin/windows
$ zookeeper-server-start.bat ../../config/zookeeper.properties
```

>> kafka-server-start.bat ../../config/server.properties


https://pika-chu.tistory.com/1276?category=615473

### 터미널 시작 
bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test-topic
test-topic 이름으로 토픽을 생성

파티션 1개, replication이 1이라서 자기 자신만 있다.

 

bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092
토픽 리스트 확인

 

bin\windows\kafka-topics.bat --describe --bootstrap-server localhost:9092 --topic test-topic
test-topic 토픽 상세 보기

 

bin\windows\kafka-topics.bat --zookeeper localhost:2181 --alter --topic test-topic -partitions 2
test-topic 토픽의 파티션 수 변경

토픽의 파티션 수는 증가만 가능

파티션의 수만큼 컨슈머 역시 추가해줘야 처리 성능이 좋아질 수 있다.

 

bin\windows\kafka-consumer-groups.bat --bootstrap-server localhost:9092 --list
컨슈머 그룹 리스트 확인

 

bin\windows\kafka-consumer-groups.bat --bootstrap-server localhost:9092 --group test-consumer --describe
컨슈머 상태와 오프셋 확인

 

 bin\windows\kafka-topics.bat --zookeeper localhost:2181 --delete --topic MyTopic
MyTopic 토픽 삭제

delete.topic.enable이 true로 설정되어 있어야 한다.