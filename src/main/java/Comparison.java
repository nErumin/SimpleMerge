import java.util.ArrayList;

public class Comparison {
    //private static ArrayList<String> leftPanelList;
    //private static ArrayList<String> rightPanelList;
    private int pairNum;
    private int partition;
    private int[] leftShadowLine;
    private int[] rightShadowLine;
    private int[][] solPair;
    private int[][] sameLineIndex = new int [2][];
    private Integer[][] board;
    private String[][] solution;
    private ArrayList<String> diffLine = new ArrayList<String>();


    Comparison() {
        partition = 0;
    }

    /**
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
        this.board = new Integer[x.length()][y.length()];

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
        if((x.length() == y.length()) && (lcsLength(x,y) == x.length())) {
            return true;
        } else return false; // The other case, false
    }

    /**
     * Don't care right now
     * It is not finished
     * @param noworiginPos
     * @param nowcomparedPos
     * @param x
     * @param y
     */
    public void figureSameIndex(int noworiginPos, int nowcomparedPos, String[] x, String[] y) {
        int i=noworiginPos,j=nowcomparedPos;
        if (this.lineIsEqual(x[i], y[j])) {
            sameLineIndex[0][partition] =  noworiginPos;
            while (this.lineIsEqual(x[i], y[j])) {
                i++;
                j++;
            }
            sameLineIndex[1][partition] = i;
            partition++;
        }
    }

    /**
     * test for LcS by strings
     * @param x : ArrayList for left panel
     * @param y : ArrayList for right panel
     * */
    public int[][] lcsPanelLength(ArrayList<String> x, ArrayList<String> y) {
        // Add 0(null) string for counting
        x.add(0,"0");
        y.add(0,"0");
        this.board = new Integer[x.size()][y.size()];
        this.solution = new String[x.size()][y.size()];

        for (int i = 0; i < x.size(); i++) {
            board[i][0] = 0;
        }
        for (int j = 0; j < y.size(); j++) {
            board[0][j] = 0;
        }

        for (int i = 1; i < x.size(); i++) {
            for (int j = 1; j < y.size(); j++) {
                if ((x.get(i) == y.get(j)) && (x.get(i) != null && y.get(j) != null)) {
                    solution[i][j] = "diagonal";
                    board[i][j] = board[i - 1][j - 1] + 1;
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
        int[][] mirrorSolPair = new int[x.size()][2];
        int i = x.size()-1;
        int j = y.size()-1;
        int k = 0;
        while(board[i][j] != 0) {
            if(solution[i][j] == "diagonal") {
                mirrorSolPair[k][0] = i - 1;
                mirrorSolPair[k][1] = j - 1;
                i--;
                j--;
                k++;
            } else {
                if(solution[i][j] == "up") {
                    i--;
                } else {
                    j--;
                }
            }
        }
        pairNum = k;
        solPair = new int[k][2];
        for(j = k - 1, i = 0; i < k; i++ ,j--) {
            solPair[i][0] = mirrorSolPair[j][0];
            solPair[i][1] = mirrorSolPair[j][1];
            System.out.println(solPair[i][0] + " " + solPair[i][1]);
        }
        x.remove(0);
        y.remove(0);
        return solPair;
    }

    /**
     * Fix the panel with blank addition
     * @param leftPanelList
     * @param rightPanelList
     */
    public ArrayList<String> panelFix(ArrayList<String> leftPanelList, ArrayList<String> rightPanelList) {
        int howMany; lcsPanelLength(leftPanelList, rightPanelList);
        int[][] pair = solPair;

        if(pair.length == 0 || rightPanelList.size() == 0 || leftPanelList.size() == 0) {
            rightShadowLine = new int[rightPanelList.size()];
            for(int i = 0; i < rightPanelList.size(); i++) {
                rightShadowLine[i] = i;
                diffLine.add("" + i);
            }
            for(int i = 0; i < leftPanelList.size(); i++) {
                leftShadowLine[i] = i;
                diffLine.add("" + i);
            }
        }
        for(int i = 0; i < pairNum; i++) {
            if(pair[i][0] > pair[i][1]) {
                howMany = pair[i][0] - pair[i][1];
                for(int j = i + 1; j < pairNum; j++) {
                    pair[j][1] += howMany;
                }
                while(howMany > 0) {
                    System.out.println(howMany);
                    rightPanelList.add(pair[i][1],null);
                    howMany--;
                }
            } else {
                howMany = pair[i][1] - pair[i][0];
                for(int j = i + 1; j < pairNum; j++) {
                    pair[j][0] += howMany;
                }
                while(howMany > 0) {
                    System.out.println(howMany);
                    leftPanelList.add(pair[i][0],null);
                    howMany--;
                }
            }
        }
        return diffLine;
    }

    public static void main(String[] args) {
        Comparison board = new Comparison();
        ArrayList<String> leftPanelList = new ArrayList<String>();
        ArrayList<String> rightPanelList = new ArrayList<String>();

        leftPanelList.add("안녕하세요.");
        leftPanelList.add(null);
        leftPanelList.add("Hello world!");
        leftPanelList.add(null);
        leftPanelList.add("123456");
        leftPanelList.add(null);
        leftPanelList.add("!@#$%^");

        rightPanelList.add("Hello world!");
        rightPanelList.add(null);
        rightPanelList.add("안녕하세요.");
        rightPanelList.add(null);
        rightPanelList.add("123456");
        rightPanelList.add(null);
        rightPanelList.add("!@#$%^");

        board.panelFix(leftPanelList, rightPanelList);

        for(int i = 0; i < 9; i++)
            System.out.println(leftPanelList.get(i) + " " + rightPanelList.get(i));
    }
}
