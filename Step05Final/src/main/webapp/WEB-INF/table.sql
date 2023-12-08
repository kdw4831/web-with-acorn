-- 사용자(회원) 정보를 저장할 테이블
create table user_info(
	id VARCHAR2(100) CONSTRAINT user_info_id_pk PRIMARY KEY,
	pwd VARCHAR2(100) CONSTRAINT user_info_pwd_nn NOT null,
	email VARCHAR2(100),
	profile VARCHAR2(100), --프로필 이미지 경로를 저장할 칼럼
	regdate DATE
);


-- 업로드된 파일의 정보를 저장할 테이블
CREATE TABLE board_file(
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(100) NOT NULL,
	title VARCHAR2(100) NOT NULL,
	orgFileName VARCHAR2(100) NOT NULL, -- 원본 파일명
	saveFileName VARCHAR2(100) NOT NULL, -- 서버에 실제로 저장된 파일명
	fileSize NUMBER NOT NULL, -- 파일의 크기 
	regdate DATE
);

CREATE SEQUENCE board_file_seq; 



--게시글을 저장할 테이블
CREATE TABLE board_cafe(
	num NUMBER PRIMARY KEY, --글번호
	writer VARCHAR2(100) NOT NULL,-- 작성자(로그인된 아이디)
	title VARCHAR2(100) NOT NULL, --제목
	content CLOB, --글 내용
	viewCount NUMBER, --조회수
	regdate DATE --글 작성일
);
--게시글의 번호를 얻어낼 시퀀스
CREATE SEQUENCE board_cafe_seq;

-- 어떤 세션에서 몇번글을 읽었는지 정보를 저장할 테이블
CREATE TABLE readed_data(
	num NUMBER REFERENCES board_cafe(num),
	session_id VARCHAR2(50)
)

--페이징 처리를 하기 위해서는 1.정렬 2.행 번호 부여 3.원하는 행만 select
SELECT *
FROM
	(SELECT result1.*,ROWNUM AS rnum 
	FROM
		(SELECT num, writer,title,orgFileName, fileSize,regdate
		FROM board_file
		ORDER BY num DESC)result1)
WHERE rnum BETWEEN 6 AND 10



