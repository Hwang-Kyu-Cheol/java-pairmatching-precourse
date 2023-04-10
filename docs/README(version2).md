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
#### 5. 유효한 사용자 입력 종류
* 기능 선택 입력 : [1,2,3,Q]
* 과정 입력 : [프론트엔드, 백엔드]
* 레벨 입력 : [레벨1, 레벨2, ...]
* 미션 입력 : [자동차경주, 로또, 숫자야구게임, ...]
* 양자택일 입력 : [예, 아니오]
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
    3번까지 매칭이 안될 경우, IllegalStateException 던짐.
* Method : findPair(Course course, Level level, Mission mission)
  * 설명 : 코스, 레벨, 미션에 따른 이전에 매칭된 결과를 반환.
    매칭된 결과가 없다면, IllegalStateException 던짐.
* Method : resetPair()
  * 설명 : 이전에 매칭된 모든 결과를 초기화.
    