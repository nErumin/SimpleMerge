## SimpleMerge Requirements Specification
Version 1.1

May 24, 2018

### 1. Introduction
이 프로젝트는 Java 환경에서의 Diff 유틸리티 프로그램 구현을 목표로 한다.

'SimpleMerge'라는 이름을 가진 이 프로그램은, 사용자가 Diff 유틸리티를 통해 수행하고자 하는 기능과 사용자의 편의성을 증가시키는 기능들을 제공한다.

해당 SRS 문서에는 프로그램 구현 시 고려해야 할 기능들과 제한 사항들이 명시되어 있다.

![Use Case Diagram](https://github.com/nErumin/SimpleMerge/blob/master/documents/diagram/Usediagram.png)

### 2. Use cases
#### UC1. 새 텍스트 생성
- Preconditions
    - 없음.
- Main Flow
    - 저장되지 않은 새로운 텍스트를 생성하고, 빈 Panel을 빈 문자열로 채운다. [A1]
- Subflows
    - 없음.
- Alternative Flows
    - [A1] 비어 있는 Panel이 존재하지 않을 경우, 교체할 Panel을 선택하는 창이 나타난다. 선택한 창의 Panel을 빈 문자열을 채운다.

#### UC2. 파일 불러오기
- Preconditions
    - 없음.
- Main Flow
    - Load 버튼을 누르면, 불러오기 창이 나타나 사용자는 불러오고 싶은 파일을 선택할 수 있다. 파일을 불러온 후에는, 빈 Panel에 파일의 내용을 채운다. [A1] [A2] [A3] [A4]
- Subflows
    - 없음.
- Alternative Flows
    - [A1] 존재하지 않는 파일을 선택하려 하면, 경고 창을 출력하고 작업이 취소된다.
    - [A2] 빈 파일 이름이나 허용되지 않는 이름의 파일을 선택하려 하면, 경고 창이 나타나고 작업이 취소된다.
    - [A3] 파일 선택 중 ‘취소’ 버튼을 누르면, 불러오기 작업이 즉시 취소된다.
    - [A4] 비어 있는 Panel이 존재하지 않을 경우, 교체할 Panel을 선택하는 창이 나타난다. 선택한 창의 Panel을 빈 문자열을 채운다.

#### UC3. 파일의 텍스트 다시 불러오기(Refresh)
- Preconditions
    - 사용자가 작업을 원하는 파일이 정상적으로 불러와진 상태이다.
- Main Flow
    - Panel에는 불러온 파일의 내용이 표시되어 있다. [UC2] 파일 불러오기가 완료되면 Refresh 버튼이 활성화된다.
    - Refresh 버튼을 클릭하면, 파일의 내용이 기록된 Panel의 내용을 시스템에서 다시 불러온 파일의 내용으로 교체한다.
- Subflows
    - 없음.
- Alternative Flows
    - 없음.

#### UC4. Panel의 텍스트 편집하기
- Preconditions
    - Panel에 사용자가 작업 중인 텍스트가 정상적으로 표시되고 있다.
    - Panel의 Edit 버튼이 활성화되어 있다.
- Main Flow
    - 사용자는 Panel에 표시되고 있는 텍스트를 수정할 수 있다. 현재 텍스트는 새로 생성된 것[UC1]이거나, 파일에서 불러온 것[UC2]일 수 있다. [S1]
- Subflows
    - [S1] Compare 기능이 활성화되어 있는 경우, 수정이 완료된 후에는 변경된 내용에 따라 비교 사항이 갱신되어야 한다.
- Alternative Flows
    - 없음.

#### UC5. 텍스트를 파일에 저장하기
- Preconditions
    - Panel에 사용자가 작업 중인 텍스트가 정상적으로 표시되고 있다.
- Main Flow
    - Panel에 표시되고 있는 텍스트를 사용자가 편집했을 경우[UC4], Save 버튼이 활성화된다.
    - Save 버튼을 누르면, Panel에 표시되고 있는 텍스트를 특정 파일에 저장한다. [A1] [A2]
- Subflows
    - 없음.
- Alternative Flows
    - [A1] 새롭게 생성한 텍스트[UC1]일 경우, 저장 경로를 지정하는 창이 나타난다.
    - [A2] 권한 문제로 텍스트를 파일에 저장할 수 없을 경우, 경고 창이 나타나고 작업이 취소된다.

#### UC6. Panel 간 텍스트 비교하기
- Preconditions
    - 양쪽 Panel에 사용자가 작업 중인 텍스트가 정상적으로 표시되고 있다.
- Main Flow
    - 두 Panel 모두에 텍스트가 표시되고 있으면, Compare 버튼이 활성화된다. 표시되는 텍스트는 새롭게 생성된 것[UC1]이거나, 파일에서 불러온 것[UC2]일 수 있다.
    - 사용자가 Compare 버튼을 누르면, 양쪽 Panel의 문장들을 비교한다. [S1]
- Subflows
    - [S1] 비교가 끝나면, 각 Panel 내에서 서로 다른 부분을 표시해준다. 
- Alternative Flows
    - 없음.

#### UC7. Panel 텍스트의 병합
- Preconditions
    - 양쪽 Panel에 사용자가 작업 중인 텍스트가 정상적으로 표시되고 있다.
    - 두 Panel의 텍스트에 대해 비교가 수행된[UC6] 상태여야 한다. 
- Main Flow
    - 두 Panel의 텍스트에 대해 비교가 수행되면, 각 Panel로 텍스트를 복사/병합할 수 있는 버튼이 활성화된다.
    - 사용자는 특정 문장이 존재하는 위치에 커서를 두거나, 임의의 텍스트 블록을 선택함으로써 복사/병합을 원하는 텍스트를 지정할 수 있다.
    - 사용자가 한 Panel에서 복사/병합을 원하는 텍스트를 지정한 후 버튼을 클릭하면, 반대쪽 Panel의 동일한 위치에 텍스트가 복사/병합된다. [A1] [A2]
- Subflows
    - 없음.
- Alternative Flows
    - [A1] 복사/병합 대상 위치에 동일한 문장이 존재하면, 경고 창이 나타나고 작업이 취소된다.
    - [A2] 사용자가 복사/병합을 원하는 텍스트를 선택하지 않은 상태에서 버튼을 클릭할 경우, 경고 창이 나타나고 작업이 취소된다.

#### UC8. 텍스트 내 검색
- Preconditions
    - Panel에 사용자가 작업 중인 텍스트가 정상적으로 표시되고 있다.
- Main Flows
    - Panel에 사용자가 작업 중인 텍스트가 정상적으로 표시되면, Search 버튼이 활성화된다. 텍스트는 새로 생성된 것[UC1]이거나, 파일에서 불러온 것[UC2]일 수 있다. [S1]
    - Search 버튼을 클릭하면, 검색할 내용을 묻는 창이 나타난다. 검색하고 싶은 내용을 입력한 후 검색을 시작하면, 가장 먼저 일치하는 부분을 표시해주고, 해당 위치로 커서를 이동시킨다. [A1]
- Subflows
    - 없음.
- Alternative Flows
    - [A1] 검색 결과, 원하는 내용을 Panel 내에서 찾을 수 없을 경우 안내 창이 나타나고 작업이 취소된다.

#### UC9. 텍스트의 서로 다른 부분을 모아 보기
- Preconditions
    - 양쪽 Panel에 사용자가 작업 중인 텍스트가 정상적으로 표시되고 있다.
    - 두 Panel의 텍스트에 대해 비교가 수행된[UC6] 상태여야 한다. 
- Main Flows
    - 두 Panel의 텍스트에 대해 비교가 수행되면, 별도의 View 버튼이 활성화된다.
    - 사용자가 View 버튼을 누르면, 양쪽 Panel에서 차이점이 있는 문장들만 간추려 별도의 창에 표시한다. [S1] [A1]
- Subflows
    - [S1] 별도의 창에 표시할 때, 차이점이 발생하는 부분을 따로 표시해준다.
- Alternative Flows
    - [A1] 두 Panel의 텍스트가 일치해 표시할 문장이 없을 경우, 안내 창이 나타나고 작업이 취소된다.

### 3. Non-Functional Requirements
#### NR1. Usability
- NR1.1 단축키 지원
    - Command + S(저장), Command + N(새 텍스트 생성), Command + O (파일 불러오기)처럼, 다른 곳에서 흔히 사용되는 단축키를 지원한다.
 
- NR1.2 User Interface
    - 인터페이스는 Material Design을 따라 디자인한다.
    - diff 표현 시에는 GitHub의 diff 표시 디자인을 따라 표시해준다.

#### NR2. Reliability
- NR2.1 임시저장
    - 프로그램 사용 중 오류가 발생하여, 작업 내용을 잃는 경우를 대비해 임시 저장 기능을 지원한다. 임시 저장 파일이 특정 시간 간격마다 생성되어, 프로그램 실행 시 파일이 남아있으면 임시 저장 파일 내용을 불러오게 된다.
#### NR3. Supportability
- NR3.1 다양한 Encoding 지원
    - I18N을 지원하기 위해, CJK, UTF-8, UTF-16 등의 Encoding을 지원한다.
- NR3.2 다양한 운영체제 지원(Cross-Platform)
    - 프로그램은 Windows, macOS, Debian 계열의 Linux 운영체제를 지원한다.
#### NR4. Security
- Exploit 방지를 위해, 프로그램에서 사용 중인 자원은 프로그램 외부에서 추가/수정/삭제할 수 없다.
#### NR5. Constraints
- Java 언어를 사용하여 모든 개발이 이루어진다.
- GUI 개발을 위해서 JavaFX를 사용한다.
- 범용 유닛 테스트는 JUnit 프레임워크와 EasyMock을 통해 수행한다.
- GUI 테스트를 위해서 TestFX 프레임워크를 사용한다.
- 프로그램 실행을 위해서 반드시 컴퓨터에 Java가 설치되어 있어야 한다.

### 5. Requirements Dependency Traceability Table

|   |UC1|UC2|UC3|UC4|UC5|UC6|UC7|UC8|UC9|NR1|NR2|NR3|NR4|NR5|
|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|
|**UC1**|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
|**UC2**|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
|**UC3**|   | X |   |   |   |   |   |   |   |   |   |   |   |   |
|**UC4**| X | X |   |   |   |   |   |   |   |   |   |   |   |   |
|**UC5**| X | X |   | X |   |   |   |   |   |   |   |   |   |   |
|**UC6**| X | X |   |   |   |   |   |   |   |   |   |   |   |   |
|**UC7**| X | X |   |   |   | X |   |   |   |   |   |   |   |   |
|**UC8**| X | X |   |   |   |   |   |   |   |   |   |   |   |   |
|**UC9**| X | X |   |   |   | X |   |   |   |   |   |   |   |   |
|**NR1**|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
|**NR2**|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
|**NR3**|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
|**NR4**|   |   |   |   |   |   |   |   |   |   |   |   |   |   |
|**NR5**|   |   |   |   |   |   |   |   |   |   |   |   |   |   |

### 6. Development and Target Platforms
- Windows 10
- MacOS High Sierra 10.13
- Java
- Eclipse IDE
- IntelliJ IDEA
- Travis
- Sentry
- Gradle

### 7. Project Glossary
- Panel : 텍스트 내용이 표시되는 영역으로, Panel 내에서 사용자는 텍스트를 수정할 수 있다. 또한, 사용자는 여러 Panel의 텍스트를 비교해볼 수 있다.

### 8. Document Revision History

Item               | Description                         |
-------------------|-------------------------------------| 
Version            | 1.1                                 |
Date               | May 24, 2018                        |
Change Description | Add EasyMock in the 'Constraint' section.|


Item               | Description                         |
-------------------|-------------------------------------| 
Version            | 1.0                                 |
Date               | May 14, 2018                        |
Change Description | Original creation of the SRS.       |
