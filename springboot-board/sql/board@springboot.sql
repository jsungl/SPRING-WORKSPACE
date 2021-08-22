--게시판생성
create table board (	
    no number, 
    title varchar2(50), 
    content varchar2(2000), 
    reg_date date default sysdate, 
    read_count number default 0, 
    constraint pk_board_no primary key(no)
);
--게시판시퀀스생성
create sequence seq_board_no;

--첨부파일테이블 생성
create table attachment (
    no number primary key,
    board_no number not null,
    original_filename varchar2(255) not null,
    renamed_filename varchar2(255) not null,
    status char(1) default 'Y',
    constraint fk_attach_board_no foreign key(board_no) references board(no) on delete cascade,
	constraint ck_status check(status in ('Y','N'))
);
--첨부파일 시퀀스생성
create sequence seq_attachment_no;


--게시판 조회
select * from board;
--사용자지정 시퀀스 조회
select * from user_sequences;
--첨부파일 조회
select * from attachment;

--commit;

insert into board(no,title,content) values(seq_board_no.nextval,'게시판테스트 제목입니다','게시판 내용입니다');
insert into board(no,title,content) values(seq_board_no.nextval,'게시판테스트 제목입니다','게시판 내용입니다');
insert into board(no,title,content) values(seq_board_no.nextval,'게시판테스트 제목입니다','게시판 내용입니다');
insert into board(no,title,content) values(seq_board_no.nextval,'게시판테스트 제목입니다','게시판 내용입니다');
insert into board(no,title,content) values(seq_board_no.nextval,'게시판테스트 제목입니다','게시판 내용입니다');
insert into board(no,title,content) values(seq_board_no.nextval,'게시판테스트 제목입니다','게시판 내용입니다');
insert into board(no,title,content) values(seq_board_no.nextval,'게시판테스트 제목입니다','게시판 내용입니다');
insert into board(no,title,content) values(seq_board_no.nextval,'게시판테스트 제목입니다','게시판 내용입니다');
insert into board(no,title,content) values(seq_board_no.nextval,'게시판테스트 제목입니다','게시판 내용입니다');
insert into board(no,title,content) values(seq_board_no.nextval,'게시판테스트 제목입니다','게시판 내용입니다');
insert into board(no,title,content) values(seq_board_no.nextval,'게시판테스트 제목입니다','게시판 내용입니다');
insert into board(no,title,content) values(seq_board_no.nextval,'게시판테스트 제목입니다','게시판 내용입니다');
insert into board(no,title,content) values(seq_board_no.nextval,'게시판테스트 제목입니다','게시판 내용입니다');
insert into board(no,title,content) values(seq_board_no.nextval,'게시판테스트 제목입니다','게시판 내용입니다');
insert into board(no,title,content) values(seq_board_no.nextval,'게시판테스트 제목입니다','게시판 내용입니다');
