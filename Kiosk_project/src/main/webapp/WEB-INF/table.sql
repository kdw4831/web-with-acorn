-- 고객의 주문 정보를 저장할 테이블
CREATE TABLE order_info(
   table_num VARCHAR2(100) NOT NULL,
   name VARCHAR2(100) NOT NULL, --메뉴 이름
   price NUMBER NOT NULL --메뉴 가격
   count NUMBER NOT NULL --메뉴 개수
);


-- 상품의 정보를 저장할 테이블
CREATE TABLE menu_info(
   sto_num VARCHAR2(100), --사업자 번호,
   name VARCHAR2(100) NOT NULL, --메뉴 이름
   price NUMBER NOT NULL, --메뉴 가격
   description VARCHAR2(100), --메뉴 설명
   imageUrl VARCHAR2(2000),
   is_sold VARCHAR2(10) CHECK(IS_SOLD IN('YES','NO')), --메뉴 품절 유무 품절이면 "yes" 품절이 아니면 "no"
   category VARCHAR2(100) NOT NULL --메뉴 카테고리
);

-- 고객의 주문 정보를 저장할 테이블
CREATE TABLE order_info(
   table_num NUMBER NOT NULL, --테이블 이름
   menu VARCHAR2(100) NOT NULL, --메뉴 이름
   price NUMBER NOT NULL --메뉴 가격
   count NUMBER NOT NULL --메뉴 개수
);