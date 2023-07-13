package community_project.main.util;

import lombok.Getter;

@Getter
public class PageCreate {
    private Criteria pageInfo;
    private long boardAmount;
    private long beginPage;
    private long endPage;
    private int prev;
    private int next;

    // 페이지에 들어가는 버튼 개수 ex) 1,2,3,4,5
    private int buttonNum = 5;
    private void setting(){
        // 현재 페이지 중 가장 뒤페이지 ex) 현재페이지 7 -> 1 , 올림하니까 2가되고 buttonNum 곱해주니까 마지막 페이지는 10
        endPage = (long)(Math.ceil((float)pageInfo.getPageNum() / buttonNum)) * buttonNum;
        // 현재 페이지 중 가장 앞페이지;
        beginPage = (endPage - buttonNum) + 1;
        // 이전 페이지로 가는 버튼
        prev = (beginPage == 1) ? 0 : 1;
        // 다음 페이지로 가는 버튼
        next = boardAmount <= (endPage * pageInfo.getPageAmount()) ? 0 : 1;
        if(next == 0){
            if(boardAmount != 0){
                endPage = (long)Math.ceil((float)boardAmount / pageInfo.getPageAmount());
            }else{
                endPage = 1;
            }
        }
    }

    public void setBoardAmount(Criteria pageInfo,long boardAmount){
        this.pageInfo = pageInfo;
        this.boardAmount = boardAmount;
        setting();
    }
}
