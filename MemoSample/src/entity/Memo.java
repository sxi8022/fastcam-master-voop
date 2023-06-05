package entity;

import java.util.*;

// 메모
public class Memo {
    private int num;        // 글 번호
    private String name;    // 작성자 이름
    private String password;// 비밀번호
    private String post;    // 게시글
    private String time;    // 작성일 (컴퓨터 시스템 시간 기준)

    // get, set 함수 작성
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Memo(int num, String name, String password, String post) {
        this.num = num;
        this.name = name;
        this.password = password;
        this.post = post;
        time = dateFormat(Calendar.getInstance());
    }

    // 작성일 형식 반환 함수
    public String dateFormat(Calendar today) {
        return today.get(Calendar.YEAR)+"-" + String.format("%02d", (today.get(Calendar.MONTH)+1))+"-"
                + String.format("%02d",today.get(Calendar.DATE))+" " + String.format("%02d",today.get(Calendar.HOUR))+":"
                + String.format("%02d",today.get(Calendar.MINUTE))+":" + String.format("%02d",today.get(Calendar.SECOND));
    }

    // 글 수정 작업 (구현)
    /*    public void editPost() {
        // post(내용) 변경
        // time(시간)을 새로 수정
    }*/
}
