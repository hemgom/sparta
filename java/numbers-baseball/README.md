# 목차
1. [프로젝트 목적](#numbers-baseball)
   - [패키지 구성](#package)
2. [요구사항](#요구사항)
   - [필수 기능](#필수-기능)
   - [도전 기능](#도전-기능)
3. [구현 및 트러블 슈팅](#구현-및-트러블-슈팅)
<br/><br/><br/>

# Numbers Baseball
캠프 `CH2` 에서 학습한 `Java` 를 통해 `숫자 야구 게임` 을 구현하는 `개인과제(프로젝트)`, 이번 프로젝트의 목표는 `Calculator` 프로젝트보다 `Java` 를 더 `객체지향스럽게` 사용하는 것이다.
<br/><br/>
해당 프로젝트는 `corretto-17(version 17.0.1)` JDK 를 사용하였다.

## Package
```
src/main/java/numberBaseball ┬─ Main.java
                             │
                             ├─ /application
                             │
                             ├─ /enums
                             │
                             ├─ /exception
                             │
                             └─ /domain ┬─ /checkCorrectAnswer
                                        │
                                        ├─ /generateCorrectAnswer
                                        │
                                        ├─ /saveGameRecord
                                        │
                                        └─ /validateUserInput
```
- `Main.java` : 애플리케이션 `실행`
- `/application` : `애플리케이션` 위치
- `/enums` : 클래스 및 메서드에서 사용되는 상수들을 보유한 `enum 클래스` 위치
- `/exception` : `예외 클래스` 위치
- `/checkCorrectAnswer` : `정답 확인` 과 관련된 클래스(인터페이스) 위치
- `/generateCorrectAnswer` : `정답 생성` 과 관련된 클래스(인터페이스) 위치
- `/saveGameRecord` : `게임 기록 저장` 과 관련된 클래스(인터페이스) 위치
- `/validateUserInput` : `사용자 입력 검증` 과 관련된 클래스(인터페이스) 위치
<br/><br/><br/>

# 요구사항
요구사항의 `Level` 은 그저 요구사항 적용 단계일 뿐, 레벨별로 애플리케이션을 따로 구현할 필요는 없다 생각된다.<br/>
만약 다른 단계임에도 같은 기능에 대한 요구사항이 있다면 새로운 클래스나 메서드를 생성할 것이다.

## 필수 기능
### Level.01 - 기본 '숫자 야구 게임' 구현
- [x] `정답` 생성 
  - `정답` 을 간편하게 `ans` 라 표현할 때, `ans` 는 아래의 조건을 만족해야 함
    - `ans` 는 서로 다른 숫자로 구성된 `3자리 숫자(자연수)` 이다.
    - `ans` 의 각 자리에 위치할 수 있는 숫자는 `1 ~ 9` 사이의 숫자들 뿐이다.
- [x] `플레이어` 입력
  - `플레이어` 를 간편하게 `P` 라 표할 할 때, `P` `1 ~ 9` 사이의 숫자들로 구성된 `3자리 수` 를 입력한다.
  - `3자리 수` 는 서로 다른 숫자이어야 한다.
- [x] 결과 출력 및 게임 로직 적용
  - 정답과 입력을 비교해 `볼`, `스트라이크`, `아웃` 아웃으로 결과를 도출
    - `아웃` : 숫자도 위치, 둘 다 틀린 경우
    - `볼` : 숫자가 있긴하나 위치가 다른 경우
    - `스트라이트` : 숫자와 위치가 둘 다 정확한 경우
  - 결과가 `3 스트라이크(정답)` 이라면 게임이 종료됨
- [x] 게임 이어서하기
  - 정답을 맞출 때까지 계속해 입력할 수 있어야 하며, 정답 시 축하 메시지 출력

### Level.02 - 입력 및 출력을 개선한 '숫자 야구 게임'
- [x] 입력 값의 유효성을 검사
  - 입력 값이 `Level.01` 의 입력에 대한 조건을 만족하는지 확인
  - 유효하지 않다면 `"올바르지 않은 입력 값입니다."` 를 출력한다.
- [x] 출력 개선
  - 프로그램 시작 시 `안내 문구(메뉴)` 출력
    - `1` 입력 시 구현한 `필수 기능` 을 수행되며 게임이 진행
    - `2` 입력에 대한 기능 `게임 기록을 조회는 Level.03 에 등장, 현재는 입력을 막아 둘 것
    - `3` 입력 시 게임이 종료됨
  - 정답을 맞춘 경우에도 해당 `안내 문구(메뉴)` 가 출력되어야 함
<br/><br/>

## 도전 기능
### Level.03 - 기능 추가 및 개선한 '숫자 야구 게임'
- [x] `게임 기록 통계` 기능 추가
  - 현재 진행 중인 게임이 몇 번째 게임인지 기록
  - 플레이어가 정답을 맞출 때까지 `시도한 횟수` 를 기록 게임이 끝났을 때, 모든 게임에 대한 기록을 출력
- [x] 출력 개선
  - `게임 기록 통계` 기능이 추가 되었으니 정답시 출력되는 안내 문구에서 `2` 번을 선택할 수 있게 수정
  - `3` 번을 누르면 이전 처럼 게임이 종료되며, 이전 게임 기록은 초기화 된다.
  - `1,2,3` 이외의 입력에 대한 오류 메시지 출력

### Level.04 - 기능 추가 및 개선한 '숫자 야구 게임'
- [x] `게임 난이도 조절` 기능 추가
  - 난이도를 입력 받을 수 있고 난이도에 따라 숫자의 자릿수를 조절할 수 있다.
  - 자리수(난이도)는 `3,4,5` 중에 선택 가능, 이외의 숫자는 예외 메시지 출력
  - 자리수(난이도)를 입력하는 경우 자동적으로 게임이 시작됨
<br/><br/><br/>

# 구현 및 트러블 슈팅
구현에 대한 내용과 어떤 문제점을 예상, 파악해 코드를 수정했는지에 대한 내용을 담은 포스팅 목록
- [구현 전 요구사항 분석 및 설계](https://development-diary-for-me.tistory.com/135)
- [Level01-02 요구사항 반영 후 리팩토링(1)](https://development-diary-for-me.tistory.com/144)
- [Level03 요구사항 반영 후 리팩토링(2)](https://development-diary-for-me.tistory.com/145)
- [Level04 요구사항 반영](https://development-diary-for-me.tistory.com/146)
- [리팩토링(3)](https://development-diary-for-me.tistory.com/147)
- [피드백 반영 - start() 메서드 복잡도 낮추기](https://development-diary-for-me.tistory.com/168)
- [피드백 반영 - playGame() 메서드 복잡도 낮추기](https://development-diary-for-me.tistory.com/169)
- [피드백 반영 - start() 메서드 복잡도 낮추기(2)](https://development-diary-for-me.tistory.com/170)