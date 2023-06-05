package ui;

import entity.*;

import java.util.InputMismatchException;
import java.util.Scanner;

// home
public class Home {
    private Scanner sc;
    private MemoList memoList;

    public Home() {
        sc = new Scanner(System.in);
        memoList = new MemoList();
    }

    public void start() {
        while(true) {
            // 0. 메모장 옵션 출력
            printOptions();
            // 옵션 번호 입력받기
            int choice = selectNum();


            switch(choice) {
                // 1. 입력 (구현)
                case 1 -> {
                    // 이름, 비밀번호, 메모 입력받기
                    Memo memo = writeMemo();
                    // 글 생성 후 메모리스트에 저장
                    memoList.addMemo(memo);
                }

                // 2. 목록 보기 (구현)
                case 2 -> {
                    // 작성된 메모 최신순으로 출력하기
                    memoList.printMemoList();
                }

                // 3. 수정 (구현)
                case 3 -> {
                    if (memoList.getCount() < 1) {
                        System.out.println("수정할 데이터가 없습니다.");
                        break;
                    }
                    
                    memoList.printMemoList(); // 목록 표시
                    // (수정할) 글 번호 입력 받기
                    // 존재 시 수정, 아닐 시 메시지 출력
                    while((choice = selectNum()) > memoList.getCount())
                        System.out.println("번호에 맞는 글이 존재하지 않습니다.");

                    // 이름, 비밀번호, 메모 입력받기, 메시지 출력
                    Memo memo = editMemo(choice);
                    
                    // 비밀 번호 확인. 일치 시, 내용 수정. 불일치 시 에러 메시지 출력
                    memoList.editMemo(memo);
                }

                // 4. 삭제 (구현)
                case 4 -> {
                    if (memoList.getCount() < 1) {
                        System.out.println("삭제할 데이터가 없습니다.");
                        break;
                    }

                    memoList.printMemoList(); // 목록 표시
                    
                    // (삭제할) 글 번호 입력 받기
                    // 존재하지 않을 시, 아닐 시 메시지 출력
                    while((choice = selectNum()) > memoList.getCount())
                        System.out.println("번호에 맞는 글이 존재하지 않습니다.");

                    // 비밀 번호 확인. 일치 여부 판단 후 기능 실행
                    boolean check = passwordCheck(choice);
                    if (check) {
                        memoList.deleteMemo(choice);
                    } else {
                        System.out.println("패스워드가 올바르지않습니다. 삭제할  수 없습니다.");
                    }
                }

                // 5. 종료
                case 5 -> {
                    System.out.println("메모장을 종료합니다.");
                    System.exit(0);
                }
            }
        }
    }

    // 메모장 옵션 출력
    public void printOptions() {
        StringBuilder sb = new StringBuilder();
        sb.append("-------------------------\n");
        sb.append("\t[메모장 시작 페이지]\t\n");
        sb.append("1. 입력\n2. 목록 보기\n3. 수정\n4. 삭제\n5. 종료\n");
        sb.append("-------------------------\n");
        System.out.print(sb);
    }

    // 번호 입력받기
    public int selectNum() {
        System.out.print("번호를 입력해주세요: ");
        int selectNum = 0;

        try {
            selectNum = sc.nextInt();
            sc.nextLine();
        } catch(InputMismatchException e) {
            System.out.println("올바른 숫자를 입력해주세요.");
            System.exit(-1);
        }

        return selectNum;
    }

    // 메모 작성하기 (구현)
    public Memo writeMemo() {
        sc = new Scanner(System.in); //이렇게 해야 입력이됨
        // (1) 글 번호 불러오기
        int maxMemoNo = memoList.getCount() + 1;
        // (2) 이름 입력
        System.out.println("작성자를 입력해주세요.");
        String name = sc.nextLine();
        // (3) 내용 입력
        System.out.println("메모내용을 입력해주세요.");
        String post = sc.nextLine();
        // (4) 비밀번호 입력
        System.out.println("비밀번호를 입력해주세요.");
        String password = sc.nextLine();
        // (5) 입력된 내용 Memo 생성자로 전달 후 반환
        Memo temp = new Memo(maxMemoNo, name, password, post);
        return temp;
    }


    // 메모 수정하기 (구현) (새로만듦)
    public Memo editMemo(int choice) {
        // (1) 글 번호 불러오기
        int maxMemoNo = choice;
        // (2) 이름 입력
        System.out.println("작성자를 입력해주세요.");
        String name = sc.nextLine();
        // (3) 내용 입력
        System.out.println("메모내용을 입력해주세요.");
        String post = sc.nextLine();
        // (4) 비밀번호 입력
        System.out.println("비밀번호를 입력해주세요.");
        String password = sc.nextLine();
        // (5) 입력된 내용 Memo 생성자로 전달 후 반환
        Memo temp = new Memo(maxMemoNo, name, password, post);
        return temp;
    }

    // 비밀번호 체크
    public Boolean passwordCheck(int choice) {
        boolean check = true;
        sc = new Scanner(System.in); //이렇게 해야 입력이됨
        // 비밀번호 입력
        System.out.println("비밀번호를 입력해주세요.");
        String password = sc.nextLine();
        String orgPassword = memoList.getMemo(choice-1).getPassword();
        if (!password.equals(orgPassword)) {
            check = false;

        }
        return check;
    }


}
