import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static char board[][] = {
            {'r','k','b','q','a','b','k','r'},
            {'p','p','p','p','p','p','p','p'},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ','Q',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' ',' '},
            {'P','P','P','P','P','P','P','P'},
            {'R','K','B','Q','A','B','K','R'}};
    /*
    * pawn   = P/p
    * knight = K/k
    * bishop = B/b
    * rook   = R/r
    * queen  = Q/q
    * king   = A/a
    */

    static int kingPosC, kingPosL;
    public static void main(String[] args) {
        //JFrame f = new JFrame("Chess");
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //UserInterface ui = new UserInterface();

        //f.add(ui);
        //f.setSize(500, 500);
        //f.setVisible(true);
        System.out.println(possibleMoves());
    }

    public static String possibleMoves() {
        String list = "";

        for(int  i = 0; i < 64; i++){
            switch (board[i/8][i%8]) {
                case 'P': list += possibleP(i);
                    break;
                case 'R': list += possibleR(i);
                    break;
                case 'K': list += possibleK(i);
                    break;
                case 'B': list += possibleB(i);
                    break;
                case 'Q': list += possibleQ(i);
                    break;
                case 'A': list += possibleA(i);
                    break;
            }
        }

        return list;//x1,y1,x2,y2, captured piece
    }

    public static String possibleP(int i) {
        String list = "";

        return list;
    }

    public static String possibleR(int i) {
        String list = "";

        return list;
    }

    public static String possibleK(int i) {
        String list = "";

        return list;
    }

    public static String possibleB(int i) {
        String list = "";

        return list;
    }

    public static String possibleQ(int i) {
        String list = "";
        char oldPiece;
        int r = i/8;
        int c = i%8;
        int temp = 1;

        for (int j = -1; j <= 1; j++) {
            for (int k = -1; k <= 1; k++) {
                try {
                    //handles diagonal movement
                    while(' ' == board[r+temp*j][c+temp*k]) {
                        oldPiece = board[r+temp*j][c+temp*k];
                        board[r][c] = ' ';
                        board[r+temp*j][c+temp*k] = 'Q';

                        if (kingSafe()) {
                            list = list + r + c + (r+temp*j) + (c+temp*k) + oldPiece;
                        }

                        board[r][c] = 'Q';
                        board[r+temp*j][c+temp*k] = oldPiece;

                        temp ++;
                    }

                    //handles straight movement
                    if(Character.isLowerCase(board[r+temp*j][c+temp*k])) {
                        oldPiece = board[r+temp*j][c+temp*k];
                        board[r][c] = ' ';
                        board[r+temp*j][c+temp*k] = 'Q';

                        if (kingSafe()) {
                            list = list + r + c + (r+temp*j) + (c+temp*k) + oldPiece;
                        }

                        board[r][c] = 'Q';
                        board[r+temp*j][c+temp*k] = oldPiece;
                    }
                } catch (Exception e) {}
                temp = 1;
            }
        }

        return list;
    }

    public static String possibleA(int i) {
        String list = "";
        char oldPiece;
        int r = i/8;
        int c = i%8;

        for(int j = 0; j < 9; j++) {
            if (j != 4) {
                try {
                    if (Character.isLowerCase(board[r - 1 + j / 3][c - 1 + j % 3]) || ' ' == board[r - 1 + j / 3][c - 1 + j % 3]) {
                        oldPiece = board[r - 1 + j / 3][c - 1 + j % 3];
                        board[r][c] = ' ';
                        board[r - 1 + j / 3][c - 1 + j % 3] = 'A';

                        int kingTemp = kingPosC;
                        kingPosC = i + (j / 3) * 8 + j % 3 - 9;

                        if (kingSafe()) {
                            list = list + r + c + (r - 1 + j / 3) + (c - 1 + j % 3) + oldPiece;
                        }

                        board[r][c] = 'A';
                        board[r - 1 + j / 3][c - 1 + j % 3] = oldPiece;
                        kingPosC = kingTemp;
                    }
                } catch (Exception e) {}
            }
        }

        return list;
    }

    public static boolean kingSafe() {
        return true;
    }
}