# Calculator
캠프 `CH2` 에서 학습한 `Java` 를 통해 입력받은 값을 연산하는 `계산기` 를 개발하는 `개인과제(프로젝트)`

## Package
```
src/main/java/calculator ┬─ Main.java
                         │
                         ├─ /level02 ┬─ Level02.java 
                         │           ├─ /calculate
                         │           └─ /validation
                         │
                         └─ /level03 ┬─ Level03.java
                                     ├─ /validation
                                     ├─ /enums
                                     └─ /calculate
```
- `Main.java` : 'level01' 계산기 구현 코드와 계산기 선택 및 선택 계산기 호출 코드를 작성
- `/level02` : 'level02' 계산기에 대한 구현은 해당 패키지 안에서 진행
  - `CalculatorApp.java` : 'level02' 계산기에 대한 실행 흐름의 구현 코드를 작성
  - `/calculate` : 연산 관련 기능을 수행하는 클래스 및 인터페이스(구현클래스 포함)가 위치하는 패키지
  - `/validation` : 연산에 사용되는 입력 값들의 유효성을 판단하는 클래스가 위치하는 패키지
- `/level03` : 'level03' 계산기에 대한 구현을 해당 패키기 안에서 진행
  - `Level03.java` : 'level03' 계산기에 대한 실행 흐름을 구현의 구현 코드를 작성
  - `/validation` : 'level02' 의 `/validation` 과 같은 역할을 하지만 enum 클래스를 활용
  - `/enums` : 'level03' 계산기 수행중 필요한 시스템 메시지, 유효성 검사 기준들이 위치
  - `/calculate` : 'level02' 의 `/calculate` 와 같은 역할을 하지만 Generics 를 활용
    <br/><br/><br/>

# 요구사항
기본적으로 `추가 클래스 주입` 의 여부로 2가지 방식의 개발을 진행해야 하는 것을 잊지말자!<br/>
구현한 기능은 해당 문서에 `체크`해서 혼동할 일이 없도록 할 것!

## 필수 기능
### level.01 - 단일 클래스(Main class) 구현
- [x] `양의 정수` 및 `0` 입력
  - 입력 값으로 양의 정수 `2개` 를 입력 받아야 하며, 각 정수는 따로 전달 받는다.
  - 전달된 정수는 필요에 따른 타입을 갖는 변수에 저장한다.
- [x] `사칙연산 기호(+, -, *, /)` 입력
  - 입력받은 사칙연산 기호를 적절한 타입을 선택해 해당 타입을 갖는 변수에 저장한다.
- [x] 입력 받은 값을 통한 연산 수행 후 결과 출력
  - 연산오류 발생시 연산 오류에 대한 내용을 출력하는 예외처리 필요
- [x] 입력과 연산 반복 수행, 사용자가 종료를 원할 때까지
  - 사용자가 `"exit"` 를 입력하지 않는한 계속해서 계산기 기능 수행
  - `"exit"` 입력시 실행 종료(정상) 될 것

### level.02 - 여러 클래스의 객체를 활용한 구현
- [x] 사칙연산 수행후 결과 값 반환 메서드 구현
- [x] 연산 결과를 저장하는 `Collection` 타입 필드를 가진 `Calculator` 클래스 생성
- [x] 양의 정수 2개와 연산 기호를 파라미터로 받아 사칙연산을 수행
- [x] 연산 수행 역할은 `Calculator` 클래스가 담당
  - 연산결과는 `Calculator` 필드에 존재하는 `Collection` 타입의 객체에 저장
- [x] 소스 코드 수정 후에도 수정전 기능들이 반드시 똑같이 동작해야 함
- [x] `main` 메서드에서 `Calculator` 객체의 `Collection` 필드에 직접 접근하지 못하도록 할 것
  - 메서드를 통한 접근을 구현할 것
- [x] `Calculator` 객체에 저장된 연산 결과들 중 가장 먼저 저장된 데이터를 삭제하는 기능을 가진 메서드를 구현
  - 해당 메서드가 `Main` 클래스의 `main` 메서드에 활용할 수 있도록 할 것
<br/><br/>

## 도전 기능
### level.03 - Enum, Generics, Lambda, Stream 을 활용한 구현
- [x] `Enum` 을 활용해 연산자 타입(+, -, *, /)을 관리하고 계산기에 적용
- [x] `Generics` 를 황용해 실수 값을 전달 받아도 연산이 가능하도록 수정
  - 단순하게 타입을 변환하는 것은 안 됨!
- [ ] 저장한 연산 결과를 담은 `Collection` 객체에서 입력 받은 연산의 결과보다 큰 결과 값들을 출력하는 기능 추가
  - 해당 기능을 수행하는 메서드 구현시 `Lambda` 와 `Stream` 을 활용해 구현할 것
<br/><br/><br/>

# 구현 및 트러블 슈팅 관련 포스팅
- [계산기 - level.01(1)](https://development-diary-for-me.tistory.com/116)
- [계산기 - level.01(2)](https://development-diary-for-me.tistory.com/118)
- [계산기 - level.02(1)](https://development-diary-for-me.tistory.com/119)
- [계산기 - level.02(2)](https://development-diary-for-me.tistory.com/121)
- [계산기 - level.03(1)](https://development-diary-for-me.tistory.com/122)
- [계산기 - level.03(2)](https://development-diary-for-me.tistory.com/124)