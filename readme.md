<img width="609" height="765" alt="KakaoTalk_20251019_134702356" src="https://github.com/user-attachments/assets/94075660-7074-4cd8-847e-093bf02f3f4a" />

1. Spring Security Layer
	- Handles user authentication, verifying whether incoming API requests are valid by checking the cookie included in the request header.
		- If the request is invalid, a 403 Forbidden error is returned.
		- Login and signup APIs are configured to operate without cookie-based authentication.

2. Controller Layer
	- Serves as the interface layer for APIs.
		- Responsible for receiving API requests and returning responses to the client.

3. Service Layer
	- Contains the core business logic of the application.
		- Handles functions such as user registration, performance reservations, and reward management, encapsulating the logic required for various services.

4. Repository Layer
	- Defines database entities and performs CRUD (Create, Read, Update, Delete) operations.

5. JDBC Driver
	- Utilizes the JDBC driver to enable communication and integration with MySQL.

API Server for BuskPort Web Service

### API List
Version: v1
#### Notes
- All APIs require a login cookie to be included in the header, except for the signup endpoint.
- APIs that include “me” operate based on the currently logged-in user’s information.

#### users
- /api/v1/users/me [GET]
	- Retrieves information of the logged-in user.
- /api/v1/users/me [DELETE]
    - Deletes the logged-in user’s account.
    - All posts and rewards associated with that user are also deleted.
- /api/v1/users/{userId} [GET]
    - Retrieves information for a user by their userId.
- /api/v1/users [POST]
    - Creates a new user (login not required).
    - Parameters: Receives a user object in JSON format via requestBody.
```
// user object
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

#### Performances
- /api/v1/performances [GET]
    - Retrieves a list of performances.
	- Accepts parameters via requestParam:
      - startDate : start date of the query range
      - endDate : start date of the query range
- /api/v1/performances/{performanceId} [GET]
  - Retrieves a single performance by its ID.
- /api/v1/performances [POST]
  - Creates a new performance.
  - Parameters: receives a performance object in JSON format via requestBody.
- /api/v1/performances [PUT]
  - Updates an existing performance (only accessible by the creator).
  - Parameters: receives a performance object in JSON format via requestBody.
- /api/v1/performances/{performancesId} [DELETE]
  - Deletes a performance by its ID (only accessible by the creator).
- /api/v1/performances/{performancedId}/participants [POST]
  - Adds participants (performers) to a performance.
```
// performance object
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

#### Rewards
- /api/v1/users/me/rewards [GET]
  - Retrieves the list of rewards earned by the logged-in user.
- /api/v1/users/{userId}/rewards [GET]
  - Retrieves the list of rewards earned by another user.
```
// User reward object
public class UserRewardDto {
	private Long userRewardId;
	private Integer rewardId;
	private Long userId;
	private LocalDateTime earnedAt;
}
```

#### Posts
- /api/v1/posts/{postId} [GET]
  - Retrieves a post by its ID.
- /api/v1/posts [GET]
  - Retrieves all posts.
- /api/v1/posts [POST]
  - Creates a new post.
  - Parameters: receives a post object in JSON format via requestBody.
- /api/v1/posts/{postId} [DELETE]
  - Deletes a post by its ID (only accessible by the post’s author).
- /api/v1/users/me/posts [GET]
  - Retrieves all posts written by the logged-in user.
```
// Post object
public class PostDto {
	private Long postId;
	private Long userId;
	private String title;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
```
   
