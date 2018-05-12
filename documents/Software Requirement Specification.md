## SimpleMerge Requirements Specification
Version 1.0

### 1. Introduction
이 프로젝트는 Java 환경에서의 Diff 유틸리티 프로그램 구현을 목표로 합니다. 'SimpleMerge'라는 이름을 가진 이 프로그램은, 사용자가 Diff 유틸리티를 통해 수행하고자 하는 기능과 사용자의 편의성을 증가시키는 기능들을 제공합니다. 해당 SRS 문서에는 프로그램 구현 시 고려해야 할 기능들과 제한 사항들이 명시되어 있습니다.

### 2. Use cases
#### UC1. 새 텍스트 생성
- Preconditions
    - 없음.
- Main Flow
    - 저장되지 않은 새로운 텍스트를 생성하고, Panel을 빈 문자열로 채운다.
- Subflows
    - 없음.
- Alternative Flows
    - 없음.

#### UC2. 텍스트 불러오기
- Preconditions
    - 없음.
- Main Flow
    - 버튼을 누르면, 불러오기 창이 나타나고 사용자는 불러오고 싶은 파일을 선택할 수 있다. [A1] [A2] [A3] [S1]
- Subflows
    - [S1] 파일을 불러온 후에는, 파일의 내용을 읽어 Panel에 보여준다.
- Alternative Flows
    - [A1] 존재하지 않는 파일을 선택하려고 하면, 경고 창을 출력하고 작업을 종료한다.
    - [A2] 빈 파일 이름이나 허용되지 않는 이름의 파일을 선택하려고 하면, 경고 창을 출력하고 작업을 종료한다.
    - [A3] 파일 선택 중 '취소'를 누르면, 불러오기 작업이 즉시 종료된다.

#### UC3. 텍스트의 갱신(Refresh)
- Preconditions
    - 사용자가 수정을 원하는 파일이 정상적으로 로드되어 있음.
- Main Flow
    - 갱신(Refresh) 버튼을 클릭하면, 불러왔던 파일의 내용을 시스템에서 다시 가져온다. [A1] [S1]
- Subflows
    - [S1] 새롭게 내용을 불러온 후에는 Panel에 새 내용을 기록한다.
- Alternative Flows
    - [A1] 기존 파일을 불러온 것이 아니라, 저장되지 않은 새로운 파일을 생성한 것일땐 경고 창이 출력되고 갱신(Refresh) 작업이 즉시 종료된다.

#### UC4. 텍스트의 편집
- Preconditions
    - 사용자가 수정을 원하는 파일이 정상적으로 로드되어 있음. 
    - Edit 버튼이 활성화되어 있음.
- Main Flow
    - 사용자는 Panel에 있는 파일 내용을 수정할 수 있다. [S1]
- Subflows
    - [S1] 현재 Compare 기능이 동작 중일 경우, 수정이 이루어진 후에는 변경된 내용에 맞춰서 비교 사항이 갱신되어야 한다.
- Alternative Flows
    - 없음.

#### UC5. 텍스트의 저장
- Preconditions
    - 사용자가 수정을 원하는 파일이 정상적으로 로드되어 있음.
- Main Flow
    - Save 버튼을 누르면, Panel에 쓰여진 내용을 파일에 저장한다. [A1] [A2]
- Subflows
    - 없음.
- Alternative Flows
    - [A1] 새로 만들어진 파일이라 저장할 경로가 존재하지 않으면, 별도의 창을 통해 경로를 지정한다.
    - [A2] 권한 문제로 파일에 저장할 수 없을 때는, 경고 창이 출력되고 작업이 즉시 종료된다.

### 5. Development and Target Platforms
모든 개발은 Java 언어로 이루어지며, GUI 개발을 위해서는 JavaFX를 사용합니다.
일반적인 유닛 테스트는 JUnit 프레임워크를 통해 이루어지며, GUI 테스트를 위해서는 TestFX 프레임워크를 사용합니다.
프로그램 실행을 위해서는 컴퓨터에 Java 환경이 준비되어 있어야 합니다.
