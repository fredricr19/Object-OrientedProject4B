package edu.up.fredricr19.object_orientedproject4b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class ShogiGui extends SurfaceView {
    shogiPieces a;

    String[][] pieces = {{"王", "將", "King"}, {"飛", "車", "Rook"}, {"角", "行", "Bishop"}, {"金", "將", "Gold"},
            {"銀", "將", "Silver"}, {"桂", "馬", "Knight"}, {"香", "車", "Lance"}, {"歩", "兵", "Pawn"}};

    public ShogiGui(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        a = new shogiPieces(context, attrs);
    }

    public int[][] makePlayer(float x, float y, float r){
        int[][] arr = new int[20][5];
        for(int i = 1; i <= 17; i+=2){
            for(int j = 0; j < 9; j++){
                arr[j] = new int[] {j, 7, (int)(x+i*r/2), (int)(y+13*r/2), 0};
            }
        }

        arr[8] = new int[] {8, 2, (int)(x+3*r/2), (int)(y+15*r/2), 0};
        arr[9] = new int[] {9, 1, (int)(x+15*r/2), (int)(y+15*r/2), 0};

        for(int i = 1; i <= 17; i+=2){
            int w = 0;
            if(i == 1 || i == 17){
                w = 6; //Lance
            }else if(i == 3 || i == 15){
                w = 5; //Knight
            }else if(i == 5 || i == 13){
                w = 4; //Silver
            }else if(i == 7 || i == 11){
                w = 3; //Gold
            }else if(i == 9){
                w = 0; //King
            }
            for(int j = 11; j < 20; j++){
                arr[j] = new int[] {j, w, (int)(x+i*r/2), (int)(y+17*r/2), 0};
            }
        }

        return arr;
    }

    @Override
    public void onDraw(Canvas canvas) {
        //length/width of a space on the board
        float spaceDim = 150;
        float topLeftX = 50;
        float topLeftY = 50;
        int pieceSize = 130;

        int[][] player = makePlayer((int)topLeftX, (int)topLeftY, pieceSize);

        //paint for lines
        Paint BoardLine = new Paint();
        BoardLine.setColor(Color.BLACK);
        BoardLine.setStrokeWidth(6);

        // draw vertical lines; start xy is top point, end xy is bottom point
        for(int i = 0; i < 10; i++){
            canvas.drawLine(topLeftX + i * spaceDim, topLeftY, topLeftX + i * spaceDim, topLeftY + 9 * spaceDim, BoardLine);
            canvas.drawLine(topLeftX, topLeftY + i * spaceDim, topLeftX + 9 * spaceDim, topLeftY+ i * spaceDim, BoardLine);
        }

        boolean b = false;
        for(int[] l : player){
            if(l[4] == 1){
                b = true;
            }

            a.drawShogiPiece(canvas, l[2], l[3], pieceSize, pieces[l[1]], b);
        }

        /*//Pawns
        for(int i = 1; i <= 17; i+=2){
            a.drawShogiPiece(canvas, (int)(topLeftX + i*spaceDim/2), (int)(topLeftY + 13*spaceDim/2), pieceSize, pieces[7], false);
        }

        //Bishop
        a.drawShogiPiece(canvas, (int)(topLeftX + 3*spaceDim/2), (int)(topLeftY + 15*spaceDim/2), pieceSize, pieces[2], false);

        //Rook
        a.drawShogiPiece(canvas, (int)(topLeftX + 15*spaceDim/2), (int)(topLeftY + 15*spaceDim/2), pieceSize, pieces[1], false);

        int w = 0;
        for(int i = 1; i <= 17; i+=2){
            if(i == 1 || i == 17){
                w = 6; //Lance
            }else if(i == 3 || i == 15){
                w = 5; //Knight
            }else if(i == 5 || i == 13){
                w = 4; //Silver
            }else if(i == 7 || i == 11){
                w = 3; //Gold
            }else if(i == 9){
                w = 0; //King
            }

            a.drawShogiPiece(canvas, (int)(topLeftX + i*spaceDim/2), (int)(topLeftY + 17*spaceDim/2), pieceSize, pieces[w], false);
        }*/
    }
}
