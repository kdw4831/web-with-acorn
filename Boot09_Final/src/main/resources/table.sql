create table user_tbl(
	id NUMBER primary key,
	userName varchar2(20) unique,
	password varchar2(100) not null,
	email varchar2(100) unique,
	role varchar2(10) not null
);
create sequence user_seq;

SELECT * FROM user_tbl;

-- 이미지 갤러리를 만들기 위한 테이블
CREATE table board_gallery(
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(100),  --login된 userName을 집어 넣을 예정
	caption VARCHAR2(100), -- 이미징에 대한 설명
	saveFileName VARCHAR2(100), -- 업로드된 이미지의 이름
	regdate DATE --이미지 업로드 날짜
);

CREATE SEQUENCE board_gallery_seq;
-- LAG(컬럼명, 칸수,존재하지 않을시 기본값) OVER(정렬조건)
SELECT *
FROM
	(SELECT num, writer, caption, 
		LAG(num, 1, 0) OVER(ORDER BY num DESC) as PrevNum,
		LEAD(num, 1, 0) OVER(ORDER BY num DESC) as NextNum
	FROM board_gallery
	ORDER BY num DESC)
WHERE num=5;

