Buskport web 서비스에서 사용할 api 서버

### api 목록
#### user
- /users/{userId} [GET]
    - userId로 유저 정보 조회
- /users/{userId} [DELETE]
    - userId로 유저 정보 삭제
    - 해당 사용자가 작성한 게시글 및 획득한 모든 리워드 정보를 함께 삭제한다.
- /users [POST]
    - 새로운 유저 추가
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
- /performances [GET]
    - 매개변수를 requestParam으로 받음
      - startDate : 조회 구간 시작 날짜
      - endDate : 조회 구간 끝 날짜
    - 공연 목록 조회
- /performances/{performanceId} [GET]
  - 공연 id로 공연 단건 조회
- /performances [POST]
  - 공연 추가
  - 매개변수
    - 공연 객체를 json으로 requestBody로 받음
- /performances [PUT]
  - 공연 정보를 수정함
  - 매개변수
    - 공연 객체를 json으로 requestBody로 받음
- /performances/{performancesId}
  - 공연 id로 공연 정보 삭제
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
- /rewards [GET]
  - 전체 리워드 목록을 조회
  - 리워드의 종류를 조회하는 api이다. user가 획득한 리워드가 아님
- /rewards [POST]
  - 새로운 종류의 리워드를 추가한다.
  - 매개변수
    - 리워드 객체를 json으로 requestBody로 받음
- /rewards/{rewardId} [DELETE]
  - 리워드 id로 해당 리워드 종류를 삭제한다.
  - 유저가 획득한 정보도 함께 삭제된다.
- /rewards/users/{userId} [GET]
  - 매개변수로 전달한 유저의 획득 리워드 목록을 조회
```
// reward 객체
public class RewardDto {
	private Integer rewardId;
	private String description;
	private String iconImageUrl;
	private String rewardName;
}

// 유저가 획득한 리워드 객체
public class UserRewardDto {
	private Long userRewardId;
	private Integer rewardId;
	private Long userId;
	private LocalDateTime earnedAt;
}
```

#### 게시글
- /posts/{postId} [GET]
  - 게시글 id로 게시글 조회
- /posts [POST]
  - 새로운 게시글 등록
  - 매개변수로 게시글 객체를 json으로 requestBody로 받음
- posts/{postId} [DELETE]
  - 게시글 id로 게시글 삭제
- posts [GET]
  - 매개변수로 전달한 user가 작성한 모든 게시글 목록 조회
  - 매개변수
    - userId : 게시글을 작성한 userId
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
   
