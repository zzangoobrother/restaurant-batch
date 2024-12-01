## 음식점 배치
해당 배치 어플리케이션은 멀티 쓰레드를 사용합니다. 배치에서 멀티 쓰레드 사용시 오류 처리나 재실행에 어려움이 있지만, 해당 어플리케이션은 로컬에 있는
csv 파일을 DB에 입력하는 어플리케이션이기 때문에 멀티 쓰레드 방식을 적용했습니다.

MAC M2 10core, 램 16GB 기준으로 

약 1분 10초가 걸립니다.

기존 싱글 쓰레드에서는

약 7분 30초가 걸렸습니다.

### 실행 방법

#### csv 파일 추가
이메일 첨부파일로 보내드린 압축 파일을 압축 해제 후 csv 파일을 resource/data 디렉토리 내부에 넣어 주세요.

#### docker mysql 실행
```shell
docker-compose up -d
```

spring batch 실행시 배치 실해시 필요한 각종 table과 flyway를 활용한 V1.0.0__init.sql를 실행하게 됩니다.

### 데이터베이스 DDL

![restaurant ddl.png](src%2Fmain%2Fresources%2Fimages%2Frestaurant%20ddl.png)
