# community_project

### 요구사항 정리
맴버

1. 회원가입시 중복된 아이디는 걸러줘야한다.

2. 맴버 종류는 3가지가 존재하는데 유동(unfixed) 고정(fixed) 관리자(admin)으로 나뉜다.

3. 관리자 같은 경우는 모든 게시글을 삭제할 수 있는 권한이 있고 그 외에는 본인만이 게시글을 삭제할 수 있다.

4. 댓글은 맴버의 role에 따라 구분 가능한 마커로 표시해준다.


카테고리

1. 카테고리는 부모카테고리인 큰 범주와 자식카테고리인 소 범주가 존재한다. 큰 범주에는 포괄적인 태그(ex) 게임)과 같은 것을 처리하고 소 범주에는 국지적인 태그(ex) 블루아카이브)를 처리한다.

2. 게시글에 글을 쓸 때 카테고리 id를 기반으로 작성가능하므로 findById가 필요한데 소범주 카테고리에만 글을 작성할 수 있기에 parent가 null인 게시글만 카테고리를 불러오도록 설계해야한다.


게시판



1. 게시글을 조회하는 페이지 접근할 때 마다 view에 +1을 해준다.

2. 추천같은 경우는 추천한 유저를 알아야하고 그 게시물 또한 알아야하므로 임의의 테이블로 만들어 구성하였다.

3. 게시글 조회는 유저 이름(id가 아니다)으로 검색, 내용으로 검색, 재목으로 검색, 댓글로 검색을 만들 예정이다.

4. 유저이름으로 검색을 위해 게시판과 Member를 id로 조인하고 name에 해당하는 결과 값만 가져오게 구성할 예정이다.

5. 내용으로 검색은 content에 해당 구절이 있는지 판단하게 쿼리문을 날려 처리할 예정이다. https://parksay-dev.tistory.com/43 에서 참고

6. 재목으로 검색도 5.번과 동일하게 처리할 예정이다.

7. 댓글로 검색은 borad와 댓글정보를 join해서 댓글내용이 포함되는지 가져올 예정이다.

8. 게시판 조회는 기본적으로 자식카테고리별로 구분이 된다. 따라서 모든 게시물을 한 화면에 표시하는 것이 아닌 카테고리를 눌러 게시판으로 접근하는 형태가 될 예정이다.


댓글

1.비로그인 댓글 작성이 가능하게 만들 것이다. 이는 comment_nologin에 저장되며 비밀번호을 입력시 댓글 수정 및 삭제가 가능하다.

2. 로그인시 댓글 작성은 본인이면 수정 및 삭제가 가능하다.

3. 관리자는 모든 댓글을 삭제할수 있는 권한을 가지고 있다.



# 작업일지

## 2023-04-26 작업내용

테스트코드 다듬기, 요구사항 구체화

Board기능 findByMemberName 구현

Board기능 findBytitle 구현

아이디 세션처리 공부해서 로그인 상태 체크해서 로그인 상태 구분하기.

Comment 기능 매커니즘

1. 댓글 작성시 댓글 데이터가 1차로 Comment테이블에 들어감
2. 테이블에 들어가는경우 ID를 삽입한 Dto에 넣으므로 해당 ID를 가지고 로그인 상태와 비로그인 상태를 구분하여 데이터를 삽입한다.
saveComment -> 1차처리

saveCommentLogin -> 로그인상태시 댓글처리

saveCommentNoLogin -> 로그인 상태아닐시 댓글처리

findByBoardId -> board의 id로 댓글을 가져오는 기능인데, 쿼리문으로 join해서 가져오지 않으면 result에 하나하나 join을 날려 처리해야줘야하므로 테이블과 join해서 가져오도록 할 예정.

CommentLoginDto -> 로그인한 댓글의 맴버의 역활과 아이디 정도만 가져옴(생각해보니 닉네임 가져와야할거 같네 내일 추가작업해서 수정)

CommentNoLoginDto -> 비로그인한 댓글의 정보가져옴

## 트러블슈팅

변수 이름 틀려서 1시간 정도 시간을 낭비하였으니 다음에는 조심해서 처리할 것!



## 2023-04-26 작업내용

commentLoginDto 구현

findByComment 구현

board랑 comment join해서 가져와야함

board_id로 board와 comment를 join한 후 comment컨텐츠 있는지 체크해서 innerjoin후 member테이블의 memberid와 join해서 정보가져오기

MemberService 구현

회원가입시 유동닉은 닉네임 중복 검사를 해줘야한다. -> validDuplicateFixMember

findByParent, findByChild 구현

초기 게시판 접근시 카테고리로 접근해야하므로 부모 카테고리만 가져오는 findByParent와 부모의 자식만 가져오는 findByChild를 만들어야한다.

findByCategory 및 페이징 처리 추가

게시글 검색할 때 페이징 처리 추가

초기에 게시판 접근시 카테고리로 접근하기 떄문에 카테고리랑 join한 findByCategory를 추가했다.

+ 모든 게시판은 카테고리를 기준으로 탐색결정하기에 categoryId를 기준으로 접근하고 이후에 추가로 조건으로 검색가능하다.

## 트러블슈팅

Dto의 생성자를 기준으로 값을 대입해서 가져오는데 id를 포함한 생성자가 없어서 에러가 나고있었다.



## 2023-05-03 작업

페이징 처리를 위한 Count 리턴해 주는 쿼리문들 추가

페이징 처리를 위한 Criteria -> 페이지 현재 번호 및 몇개씩 불러올 것인지 처리하는 부분

페이징 처리를 위한 PageCreate -> 페이지 버튼 개수는 몇개로 설정할 것인지? 이전 버튼과 다음 버튼 처리

로그인처리를 위해 세션 쪽과 쿠키 공부 + MyBatis 강의 남은거 처리



## 2023-05-08 작업

메인 페이지 작업, 자식 카테고리 작업




## 2023-05-09 작업

게시판 페이지 작업

/board/categoryId //게시판 조회

/board/categoryId/boardId // 게시글 조회

페이지 정보는 파라미터로 넘겨줌

게시글 작성은 추후에 구현할 예정이며 로그인 시 작성 가능하게 만들 예정



## 2023-05-13 작업

회원가입 페이지 구성

id,password,name과 고닉 여부를 체크박스를 통해서 넘겨받는다.

고닉인 경우 회원가입시 곂치는 닉네임이 있으면 안되므로 name으로 탐색시 empty가 나오는 경우 회원가입이 가능하고

유동닉인 경우 고닉 혹은 운영자의 닉네임은 사용하면 안되므로 탐색한 결과를 루프를 돌려 memberRole을 확인해 처리를 해주었습니다.

valid를 통해서 id, password, name이 입력되지 않는 경우 다시 원래 로그인 페이지로 돌아가 오류 메세지를 출력하게끔 만들었습니다.



## 2023-05-14 작업

인터셉트로 게시글 작성시 로그인 페이지로 넘어가고 로그인 성공시 원래 페이지로 리다이렉트되게 변경

기존에는 로그인하면 메인화면으로 넘어갔는데 수정하여 바로 직전페이지로 이동하게 변경했습니다.



## 2023-05-15 작업

글쓰기시 pathvariable을 통해서 카테고리Id를 가져오고 세션을 통해서 memberId를 가져오도록 구성하기.

게시글 작성 페이지 구성하기 및 게시글 조회까지 구현하기.



## 2023-05-31 작업

댓글 작성시 유저 Role 별로 닉네임 색 변경시켜주기.

운영자 : 주황

고닉 : 파랑

유동닉 : 초록

비로그인 : 검정


## 트러블 슈팅

이 과정에서 처리시 Enum 타입 비교시 지속해서 false가 되어 String으로 변환한 후 비교했는데 타임리프에서 Enum으로 비교하는 것에 대해서는 좀 학습이 필요할 듯



## 2023-07-09 작업

검색기능 추가( 재목으로 검색, 작성자로 검색, 댓글로 검색)

디폴트로 searchType과 keyword에 null이 들어가며 

searchType은 검색조건

keyword는 검색어로 파라미터로 넘어간다.

타임리프 if를 통해 searchType이 null인 경우와 아닌 경우 페이지네이션을 다르게 처리하여 문제를 해결하였습니다.




## 2023-07-11 작업

docker에 redis 설치 및 redis세팅 및 Test코드 작성해서 redis와 spring이 잘 연결되있는지 확인했습니다.

조회수 기능을 추가하였습니다. redis에 해당 게시글ID가 없는 경우 새로 redis set에 넣어주며 게시글 ID가 있는 경우 +1을 해주게됩니다




## 2023-07-12 작업

게시글 삭제 및 댓글 삭제기능을 넣었습니다.

게시글 삭제는 해당 게시글 작성자 및 운영자만 삭제가 가능하게됩니다.

댓글 삭제는 해당 댓글 작성자 및 운영자만 삭제가 가능하게됩니다.

비회원같은 경우 비밀번호 입력칸이 따로 주어지며 해당 게시글 작성자는 삭제버튼을 클릭하여 삭제가 가능합니다.

운영자는 모든 댓글을 삭제할 수 있습니다.





