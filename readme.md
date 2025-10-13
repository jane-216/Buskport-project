Buskport web 서비스에서 사용할 api 서버

### api 목록
v1 버전
#### 비고
회원가입을 제외한 모든 api는 로그인 쿠키를 헤더에 넣어서 사용해야 동작함
me가 포함된 api는 로그인한 사용자의 정보로 동작하는 api이다.

#### users
- /api/v1/users/me [GET]
	- 로그인한 사용자의 정보 조회 
- /api/v1/users/me [DELETE]
    - 로그인한 사용자 탈퇴
    - 해당 사용자가 작성한 게시글 및 획득한 모든 리워드 정보를 함께 삭제한다.
- /api/v1/users/{userId} [GET]
    - userId로 유저 정보 조회
- /api/v1/users [POST]
    - 새로운 유저 추가
    - 로그인이 필요없음
    - 매개변수
      - user 객체를 json형태로 requestBody로 받음 
```
// user 객체
public class UserCreateRequestDto {
 private String socialProvider;
 private String socialId;
 private String nickname;
 private Integer age;
 private String gender;
 private String phoneNumber;
 private String activityRegion;
 private String preferredGenres;
 private String position;
 private String introduction;
 private String portfolioUrl;
 private String kakaotalkId;
}
```

#### 공연
- /api/v1/performances [GET]
    - 매개변수를 requestParam으로 받음
      - startDate : 조회 구간 시작 날짜
      - endDate : 조회 구간 끝 날짜
    - 공연 목록 조회
- /api/v1/performances/{performanceId} [GET]
  - 공연 id로 공연 단건 조회
- /api/v1/performances [POST]
  - 공연 추가
  - 매개변수
    - 공연 객체를 json으로 requestBody로 받음
- /api/v1/performances [PUT]
  - 공연 정보를 수정함 (공연을 생성한 사용자만 사용 가능)
  - 매개변수
    - 공연 객체를 json으로 requestBody로 받음
- /api/v1/performances/{performancesId} [DELETE]
  - 공연 id로 공연 정보 삭제 (공연을 생성한 사용자만 사용 가능)
- /api/v1/performances/{performancedId}/participants [POST]
  - 공연에 연주자 추가 
```
// performance 객체
public class PerformanceDto {
	    private Long performanceId;
	    private Long organizer;
	    private String title;
	    private String songList;
	    private String promoUrl;
	    private LocalDateTime performanceDatetime;
	    private String requiredPositions;
	    private String status;
	    private String chatUrl;
	    private List<PerformanceParticipantDto> participants;
}
```

#### 리워드
- /api/v1/users/me/rewards [GET]
  - 로그인한 유저의 획득 리워드 목록을 조회
- /api/v1/users/{userId}/rewards [GET]
  - 타유저의 획득 리워드 목록 조회
```
// 유저가 획득한 리워드 객체
public class UserRewardDto {
	private Long userRewardId;
	private Integer rewardId;
	private Long userId;
	private LocalDateTime earnedAt;
}
```

#### 게시글
- /api/v1/posts/{postId} [GET]
  - 게시글 id로 게시글 조회
- /api/v1/posts [GET]
  - 게시글 전체 조회 
- /api/v1/posts [POST]
  - 새로운 게시글 등록
  - 매개변수로 게시글 객체를 json으로 requestBody로 받음
- /api/v1/posts/{postId} [DELETE]
  - 게시글 id로 게시글 삭제
  - 게시글 작성자만 삭제 가능
- /api/v1/users/me/posts [GET]
  - 매개변수로 전달한 user가 작성한 모든 게시글 목록 조회
```
// 게시글 객체
public class PostDto {
	private Long postId;
	private Long userId;
	private String title;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
```
   
