package model;

import javafx.util.Pair;
import utility.IterableUtility;
import utility.StringUtility;
import java.util.ArrayList;
import java.util.List;

public class Comparer {
    private int pairNum;

    /*
     * calculate LcS length
     * @param x: Left Panel String
     * @param y: Right Panel String
     * @return LcS Length
     */
    private int lcsLength(String x, String y) {
        int i, j;

        // Add 0(null) string for counting
        x = "0" + x;
        y = "0" + y;
        Integer[][] board = new Integer[x.length()][y.length()];

        for (i = 0; i < x.length(); i++) {
            board[i][0] = 0;
        }
        for (j = 0; j < y.length(); j++) {
            board[0][j] = 0;
        }

        for (i = 1; i < x.length(); i++) {
            for (j = 1; j < y.length(); j++) {
                if (x.charAt(i) == y.charAt(j)) {
                    board[i][j] = board[i - 1][j - 1] + 1;
                } else {
                    board[i][j] = ((board[i][j - 1] > board[i - 1][j]) ? board[i][j - 1] : board[i - 1][j]);
                }
            }
        }
        return board[x.length() - 1][y.length() - 1];
    }

    /**
     * After checking string, return True / False
     * @param x: Left Panel String
     * @param y: Right Panel String
     * @return Whether the two line are same
     */
    public boolean lineIsEqual(String x, String y) {
        // check two strings using a lcsLength function
        // Two strings length should be same.
        if ((x.length() == y.length()) && (lcsLength(x,y) == x.length())) {
            return true;
        } else return false; // The other case, false
    }

    /**
     * 패널 내의 문장들에 대해서 문장을 단위로 LCS 알고리즘으로
     * 서로 같은 문장을 찾아내서, 해당 문장의 인덱스를 짝지어 저장 후
     * 반환하는 함수
     * @param leftSplitter 왼쪽 패널  내용
     * @param rightSplitter 오른쪽 패널 내용
     * */
    private int[][] lcsSamePosition(Splittable leftSplitter, Splittable rightSplitter) {

        List<String> x = IterableUtility.toList(leftSplitter.lines());
        List<String> y = IterableUtility.toList(rightSplitter.lines());
        // 표를 이용해 연산하기 위해 0을 맨앞에 인위적으로 넣어줌.
        x.add(0,"0");
        y.add(0,"0");
        Integer[][] board = new Integer[x.size()][y.size()];
        String[][] solution = new String[x.size()][y.size()];

        // 표의 1열과 1행은 0이어야 하므로 0으로 처리.
        for (int i = 0; i < x.size(); i++) {
            board[i][0] = 0;
        }
        for (int j = 0; j < y.size(); j++) {
            board[0][j] = 0;
        }

        //패널 내 문자열에 대해 LCS
        for (int i = 1; i < x.size(); i++) {
            for (int j = 1; j < y.size(); j++) {
                if ((x.get(i).equals(y.get(j))) && (x.get(i) != null && y.get(j) != null) &&
                    !x.get(i).equals(StringUtility.EMPTY_STRING)) {
                    if(i>=j&&x.get(i-1).equals(x.get(i))&&!(x.get(i-1).equals(y.get(j-1)))){
                        solution[i][j] = "up";
                        board[i][j] = board[i - 1][j];
                    }
                    else if(i<=j&&y.get(j-1).equals(y.get(j))&&!(x.get(i-1).equals(y.get(j-1)))){
                        solution[i][j] = "left";
                        board[i][j] = board[i][j - 1];
                    }else {
                        solution[i][j] = "diagonal";
                        board[i][j] = board[i - 1][j - 1] + 1;
                    }
                } else {
                    if((board[i][j - 1] > board[i - 1][j])) {
                        solution[i][j] = "left";
                        board[i][j] = board[i][j - 1];
                    }
                    else {
                        solution[i][j] = "up";
                        board[i][j] = board[i - 1][j];
                    }
                }
            }
        }
        //인덱스를 담을 공간, 되짚어 가기 때문에 값이 반대순서로 담김
        int[][] mirrorSolPair = new int[x.size()][2];
        int i = x.size()-1;
        int j = y.size()-1;
        int k = 0;
        //사전에 저장된 이동경로 정보를 바탕으로 백트래킹
        while (board[i][j] != 0) {
            if (solution[i][j].equals("diagonal")) {
                mirrorSolPair[k][0] = i - 1;
                mirrorSolPair[k][1] = j - 1;
                i--;
                j--;
                k++;
            } else {
                if (solution[i][j].equals("up")) {
                    i--;
                } else {
                    j--;
                }
            }
        }
        pairNum = k;
        //반대 순서로 담긴 정보를 다시 정방향 전환
        int[][] solPair = new int[k][2];
        for (j = k - 1, i = 0; i < k; i++ ,j--) {
            solPair[i][0] = mirrorSolPair[j][0];
            solPair[i][1] = mirrorSolPair[j][1];
        }
        x.remove(0);
        y.remove(0);
        return solPair;
    }

    /**
     * 양쪽 패널을 병합에 용이하고 사용자가 편하게 볼 수 있도록
     * 줄바꿈을 추가하여 인위적 수정
     * @param leftSplitter 왼쪽 패널 문자열
     * @param rightSplitter 오른쪽 패널 문자열
     */
    public Pair<List<String>, List<String>> panelFix(Splittable leftSplitter, Splittable rightSplitter) {
        int howMany;
        int[][] pair;

        pair = lcsSamePosition(leftSplitter, rightSplitter);

        List<String> leftPanelList = IterableUtility.toList(leftSplitter.lines());
        List<String> rightPanelList = IterableUtility.toList(rightSplitter.lines());

        //순서쌍 간의 차이를 이용해서 인위적으로 줄넘김 문자열 삽입
        for (int i = 0; i < pairNum; i++) {
            if (pair[i][0] > pair[i][1]) {
                howMany = pair[i][0] - pair[i][1];
                for (int j = i + 1; j < pairNum; j++) {
                    pair[j][1] += howMany;
                }
                while (howMany > 0) {
                    rightPanelList.add(pair[i][1],"");
                    howMany--;
                }
            } else {
                howMany = pair[i][1] - pair[i][0];
                for (int j = i + 1; j < pairNum; j++) {
                    pair[j][0] += howMany;
                }
                while (howMany > 0) {
                    leftPanelList.add(pair[i][0],"");
                    howMany--;
                }
            }
        }

        /*
         * After match the same string
         * or when length of two panel's are different,
         * fill in the null string.
         */
        // Check panel length
        int buf = leftPanelList.size() - rightPanelList.size();
        int gap = Math.abs(buf);
        while (gap > 0) {
            if (buf < 0) {
                leftPanelList.add(StringUtility.EMPTY_STRING);
            } else {
                rightPanelList.add(StringUtility.EMPTY_STRING);
            }
            gap--;
        }
        return new Pair<>(leftPanelList, rightPanelList);
    }

    public List<String> findDifLine(Splittable leftSplitter, Splittable rightSplitter){
        List<String> diffLine = new ArrayList<>();
        List<String> leftPanelList = IterableUtility.toList(leftSplitter.lines());
        List<String> rightPanelList = IterableUtility.toList(rightSplitter.lines());

        // Rescan both panel and check different string
        for (int i = 0; i < Math.min(leftPanelList.size(), rightPanelList.size()); i++) {
            if (!leftPanelList.get(i).equals(rightPanelList.get(i))) {
                diffLine.add("" + i);
            }
        }

        return diffLine; // return index
    }
}
