-- board테이블
create table board(
    no number,
    title varchar2(500),
    content varchar2(2000),
    reg_date date default sysdate,
    read_count number default 0,
    constraint pk_board_no primary key(no)
);

-- attachment테이블
create table attachment(
    no number,
    board_no number not null,
    original_filename varchar2(256) not null,
    renamed_filename varchar2(256) not null,
    upload_date date default sysdate,
    download_count number default 0,
    status char(1) default 'Y',
    constraint pk_attachment_no primary key(no),
    constraint fk_attachment_board_no foreign key(board_no) references board(no) on delete cascade,
    constraint ck_attachment_status check(status in ('Y','N'))
);

create sequence seq_board_no;
create sequence seq_attachment_no;
create sequence seq_board_read_count;

insert into board(no,title,content,reg_date,read_count) 
values (seq_board_no.nextval,'안녕하세요','이것은 게시판 테스트글입니다.확인부탁드립니다.',to_date('21/05/10','RR/MM/DD'),0);
insert into board(no,title,content,reg_date,read_count) 
values (seq_board_no.nextval,'오늘하루는 어떤가요','오늘하루는 어떤가요.확인부탁드립니다.',to_date('21/06/18','RR/MM/DD'),0);
insert into board(no,title,content,reg_date,read_count) 
values (seq_board_no.nextval,'금요일이다','오늘은 금요일입니다.확인부탁드립니다.',to_date('21/07/02','RR/MM/DD'),0);
--commit;

select * from board;
select * from attachment;

select *
from board
where no = 21;
