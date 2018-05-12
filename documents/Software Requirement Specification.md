## SimpleMerge Requirements Specification
Version 1.0

### 1. Introduction
이 프로젝트는 Java 환경에서의 Diff 유틸리티 프로그램 구현을 목표로 합니다. 'SimpleMerge'라는 이름을 가진 이 프로그램은, 사용자가 Diff 유틸리티를 통해 수행하고자 하는 기능과 사용자의 편의성을 증가시키는 기능들을 제공합니다. 해당 SRS 문서에는 프로그램 구현 시 고려해야 할 기능들과 제한 사항들이 명시되어 있습니다.

### 2. Use cases
#### UC1. 텍스트의 편집
- Preconditions
    - 사용자가 수정을 원하는 파일이 정상적으로 로드되어 있음. 
    - Edit 버튼이 활성화되어 있음.
- Main Flow
    - 사용자는 Panel에 있는 파일 내용을 수정할 수 있다. [Subflow 1]
- Subflows
    - [Subflow 1] 현재 Compare 기능이 동작 중일 경우, 수정이 이루어진 후에는 변경된 내용에 맞춰서 비교 사항이 갱신되어야 한다.
- Alternative flows
    - 없음.


### 5. Development and Target Platforms
모든 개발은 Java 언어로 이루어지며, GUI 개발을 위해서는 JavaFX를 사용합니다.
일반적인 유닛 테스트는 JUnit 프레임워크를 통해 이루어지며, GUI 테스트를 위해서는 TestFX 프레임워크를 사용합니다.
프로그램 실행을 위해서는 컴퓨터에 Java 환경이 준비되어 있어야 합니다.
