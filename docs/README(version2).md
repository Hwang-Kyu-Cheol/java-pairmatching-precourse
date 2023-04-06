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