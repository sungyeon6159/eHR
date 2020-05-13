ALTER TABLE BOARD
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

DROP INDEX PK_BOARD;

/* 게시판 */
DROP TABLE BOARD 
	CASCADE CONSTRAINTS;

/* 게시판 */
CREATE TABLE BOARD (
	BOARD_ID NUMBER GENERATED AS IDENTITY, /* 게시글_순번 */
	TITLE VARCHAR2(100 CHAR) NOT NULL, /* 제목 */
	READ_CNT NUMBER(7) DEFAULT 0, /* 조회수 */
	CONTENTS CLOB, /* 내용 */
	REG_ID VARCHAR2(20 BYTE), /* 등록자ID */
	REG_DT DATE DEFAULT SYSDATE
 /* 등록일 */
);

COMMENT ON TABLE BOARD IS '게시판';

COMMENT ON COLUMN BOARD.BOARD_ID IS '게시글_순번';

COMMENT ON COLUMN BOARD.TITLE IS '제목';

COMMENT ON COLUMN BOARD.READ_CNT IS '조회수';

COMMENT ON COLUMN BOARD.CONTENTS IS '내용';

COMMENT ON COLUMN BOARD.REG_ID IS '등록자ID';

COMMENT ON COLUMN BOARD.REG_DT IS '등록일';

CREATE UNIQUE INDEX PK_BOARD
	ON BOARD (
		BOARD_ID ASC
	);

ALTER TABLE BOARD
	ADD
		CONSTRAINT PK_BOARD
		PRIMARY KEY (
			BOARD_ID
		);

--등록        
INSERT INTO board (
    title,
    contents,
    reg_id
) VALUES (
    :v1,
    :v2,
    :v3
);

--삭제
DELETE FROM board
WHERE board_id = :v0;


--단건조회
SELECT
    board_id,
    title,
    read_cnt,
    contents,
    reg_id,
    TO_CHAR(reg_dt,'YYYY/MM/DD HH24MISS') reg_dt
FROM board
WHERE board_id = :V0;

--수정
UPDATE board
SET title = :v1,
    read_cnt = (SELECT NVL(MAX(read_cnt),0)+1
                FROM board
                WHERE board_id = :v0),
    contents = :v3,
    reg_id = :v4,
    reg_dt = SYSDATE
WHERE board_id = :v0
;
--READ_CNT=READ_CNT+1
SELECT NVL(MAX(read_cnt),0)+1
FROM board
WHERE board_id = :v0;



--목록조회
SELECT T1.*,T2.*
FROM(
    SELECT B.BOARD_ID,
           B.TITLE,
           B.READ_CNT,
           B.CONTENTS,
           B.REG_ID,
           CASE TO_CHAR(B.REG_DT,'YYYY/MM/DD') WHEN TO_CHAR(SYSDATE,'YYYY/MM/DD') 
                                               THEN TO_CHAR(SYSDATE,'HH24:MI') 
           ELSE TO_CHAR(B.REG_DT,'YYYY/MM/DD') END REG_DT
    FROM(
        SELECT ROWNUM AS rnum,a.*
        FROM (
            SELECT *
            FROM board
            WHERE 1=1
            --검색조건
            ORDER BY reg_dt desc
        )A
        WHERE ROWNUM <=10
    )B
    WHERE rnum >=1
)T1
NATURAL JOIN
(
    SELECT COUNT(*) totalCnt
    FROM board
    WHERE 1=1
    --검색조건
)T2

