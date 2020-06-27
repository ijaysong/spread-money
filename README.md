# 카카오 페이 경력 지원 과제
카카오 페이의 머니 뿌리기 기능을 구현하였습니다.   
해당 기능은 크게 뿌리기, 받기, 조회로 구성되어 있습니다. 

## 개발환경
프로젝트 개발환경은 다음과 같습니다.
* IDE : Eclipse 
* Git Tools : Source Tree
* OS : Mac OS X
* SpringBoot 2.3.1
* Java8
* Apache Maven
* DB : MySQL 8.0.17

## 테이블 구성

### 뿌리기 TBL (SPREAD)
|논리명 | 물리명 | 타입        |추가 설명      |
|-----|-------|-----------|-------------|
|ID | ID | INT | Primary Key(Auto Increment) |
|토큰                | TOKEN               | VARCHAR(3)   | Unique|
|뿌리기 한 사람의 ID | SPREAD_USER_ID       | INT | |
|대화방 ID        | CHAT_ROOM_ID  | INT | |
|뿌린 시간            | SPREAD_TIME          | TIMESTAMP   | DEFAULT NOW() |
|뿌린 금액            | SPREAD_TOTAL_MONEY   | INT(7)      | |


### 채팅방 TBL (CHAT_ROOM)
|논리명 | 물리명 | 타입 |추가 설명 |
|-----|-------|-----|--------|
|ID | ID | INT | Primary Key(Auto Increment) |
|채팅방 ID | CHAT_ROOM_ID | INT | |
|사용자 ID | USER_ID | INT| |


### 받기 내역 TBL (RECEIVE_STATEMENT)
|논리명 | 물리명 | 타입 |추가 설명 |
|-----|-------|-----|--------|
|ID | ID | INT | Primary Key(Auto Increment) |
|토큰                  | TOKEN | VARCHAR(3) | |
|뿌릴 인원 | RECEIVE_INDEX | INT | 숫자인덱스 |
|뿌릴 금액(1/n)         | RECIEVE_MONEY | INT(7) | |
|받은 사용자 ID         | RECEIVE_USER_ID | INT | |
