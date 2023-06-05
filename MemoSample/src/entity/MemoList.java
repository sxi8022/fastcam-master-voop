package entity;

import java.util.LinkedList;

//메모 목록
public class MemoList {
    private int count;
    
    // 메모 목록
    private LinkedList<Memo> list;

    public MemoList() {
        count = 0; //현재 사용안함
        this.list = new LinkedList<>();
    }

    // 메모 전체 조회 - Getter
    public LinkedList<Memo> getMemoList() { return list; }

    // 메모 전체 조회 - Setter
    public void setMemoList(LinkedList<Memo> memoList) {
        this.list = memoList;
    }

    // 작성 최신순 메모 출력 (구현)
    public void printMemoList() {
        // 기존 list를 for문을 통해 접근해 출력하기
        for (int i = 0; i < list.size(); i++) {
            Memo memo = list.get(i);
            System.out.println(" 글번호 : " +memo.getNum()
                + " 작성자: " + memo.getName()
                + " 내용: " + memo.getPost()
                + " 작성일시: " + memo.getTime()
            );
        }
    }

    // 메모 1건 추가 (구현)
    public void addMemo(Memo memo){
        // 기존 list에 Memo 추가
        list.add(memo);
        count++;
    }

    // 글 수정/삭제 시, 글 번호를 받아서 해당 메모를 반환 (구현)
    public Memo getMemo(int idx) {
        // list에서 idx에 해당하는 Memo 반환하기
        return list.get(idx);
    }

    // 메모 1건 수정 (구현)
    public void editMemo(Memo memo) {
        // 해당 Memo의 게시글(post) 필드 갱신
        int num = memo.getNum();
        String password = memo.getPassword();
        if (list.get(num-1).getPassword().equals(password)) {
            list.set(num-1, memo);
            System.out.println("수정 완료");
        } else {
            System.out.println("비밀번호가 맞지 않습니다.");
        }
    }

    // 메모 1건 삭제 (구현)
    public void deleteMemo(int idx) {
        // list에서 해당 memo를 제거
        // 글 삭제 후 글 번호 재지정 작업
        list.remove(idx-1);
        // count 수정
        editMemoNum();
    }

    // 글 삭제 후 Memo의 num 필드 수정 (구현)
    public void editMemoNum(){
        count = 0;
        if (list.size() > 0) {
            for (int i = 0 ; i < list.size(); i++) {
                Memo memo = list.get(i);
                memo.setNum(i+1); //글번호는 1번부터 시작
                list.set(i, memo);
                count++;
            }
        }
    }

    // 글번호 부여
   /* public int getCount() {
        return count;
    }*/
    // 배열크기 활용
    public int getCount() {
        return this.list.size();
    }

    // 글 삭제 후 count 값 수정 (현재 불필요)
    /*public void minusCount(){
        count--;
    }*/
}
