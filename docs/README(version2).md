## 핵심 기능 목록
#### 1. 패어 매칭
#### 2. 페어 조회
#### 3. 페어 초기화
- - -
## Constant
#### 1. Course
* Field : String name
  * name : "백엔드", "프론트엔드"
#### 2. Level
* Field : String name
  * name : "레벨1", "레벨2", ... "레벨5"
#### 3. Mission
* Field : Level level, String name
  * level : LEVEL1, LEVEL2, ... LEVEL5
  * name : "자동차경주", "로또", "숫자야구게임", ...
#### 4. NameList
* Field : Course course, String filePath
  * course : BACKEND, FRONTEND
  * filePath : 파일위치(ex. "src/main/resources/frontend-crew.md")
#### 5. ErrorMessage
* Field : String value
  * value : 에러 메시지
#### 6. Function
* Field : String value
  * value : "1", "2", "3", "Q"
#### 7. TwoWayChoice
* Field : String value
  * value : "네", "아니오"
- - -
## Domain
#### 1. Pair : 두명 또는 세명이 된 페어
* Field : int MAX_SIZE, List<String> crewNames
  * maxSize : 2
  * crewNames : ["백호", "철수"]
#### 2. MatchingResult : 매칭된 결과
* Field : Course course, Level level, Mission mission, List<Pair> pairs
  * course, level, mission : /constant 사용
  * pairs : 페어 리스트
- - -
## Repository
#### 1. CrewRepository : 크루 저장소
* Field : Map<Course, List<String>> store
  * store : 코스(key)에 따라 크루 이름 리스트(value)가 저장되는 공간
#### 2. MatchingResultRepository : 매칭된 결과 저장소
* Field : Map<Long, MatchingResult> store, long index
  * store : index(key)에 따라 매칭된 결과(value)가 저장되는 공간
- - -
## Service
#### 1. PairMatchingService 핵심 기능
* Method : matchPair(Course course, Level level, Mission mission)
  * 설명 : 코스, 레벨, 미션에 따른 새롭게 매칭된 결과 반환. 
    이전에 매칭된 결과가 있다면, 이전 매칭 결과를 지우고 새롭게 매칭.
    3번까지 매칭이 안될 경우, IllegalStateException 던짐.
* Method : findPair(Course course, Level level, Mission mission)
  * 설명 : 코스, 레벨, 미션에 따른 이전에 매칭된 결과를 반환.
    매칭된 결과가 없다면, IllegalStateException 던짐.
* Method : resetPair()
  * 설명 : 이전에 매칭된 모든 결과를 초기화.
- - -
## Util
#### 1. InputValidator
* Method : validateSelectingFunctionInput(String input)
  * 설명 : 기능을 선택할 때 input이 유효한지 판단하는 함수.
    유효하지 않을 경우, IllegalArgumentException 던짐.
* Method : validateSelectingCourseLevelMissionInput(String input)
  * 설명 : (코스, 레벨, 미션)을 선택할 때 input이 유효한지 판단하는 함수.
    유효하지 않을 경우, IllegalArgumentException 던짐.
* Method : validateSelectingRematchInput(String input)
  * 설명 : 재매치를 선택할 때 input이 유효한지 판단하는 함수.
    유효하지 않을 경우, IllegalArgumentException 던짐.
#### 2. InputResolver
* Method : resolveSelectingFunctionInput(String input)
  * 설명 : 기능 선택 입력을 validate하고, Function enum으로 반환하는 함수.
* Method : resolveSelectingCourseLevelMissionInput(String input)
  * 설명 : (코스, 레벨, 미션) 선택 입력을 validate하고, 입력을
  CourseLevelMission 객체로 반환하는 함수.
* Method : resolveSelectingRematchInput(String input)
  * 설명 : 재미치 선택 입력을 validate하고, 입력을 TwoWayChoice enum으로 
  반환하는 함수.