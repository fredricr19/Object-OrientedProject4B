package edu.up.fredricr19.object_orientedproject4b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.ArrayList;

public class ShogiGui extends SurfaceView {
    shogiPieces a;

    ArrayList<shogiPieces> arr = new ArrayList<shogiPieces>();

    String[][] pieces = {{"王", "將", "王", "King"}, {"飛", "車", "飛", "Rook"}, {"角", "行", "角", "Bishop"},
            {"金", "將", "金", "Gold"}, {"銀", "將", "銀", "Silver"}, {"桂", "馬", "桂", "Knight"},
            {"香", "車", "香", "Lance"}, {"歩", "兵", "歩", "Pawn"}, {"玉", "將", "玉", "King"}};

    public ShogiGui(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    public void drawSide(float x, float y, float spaceDim, boolean player){
        int w = 1;
        int p = 5;
        int m = 3;
        int l = 1;

        if(player){
            p = 13;
            m = 15;
            l = 17;
        }

        for(int i = 1; i <= 17; i+=2){
            arr.add(new shogiPieces((int)(x + i*spaceDim/2), (int)(y + p*spaceDim/2), pieces[7]));

            if(i == 3){
                arr.add(new shogiPieces((int)(x + i*spaceDim/2), (int)(y + m*spaceDim/2), pieces[2]));
            }else if(i == 15){
                arr.add(new shogiPieces((int)(x + i*spaceDim/2), (int)(y + m*spaceDim/2), pieces[1]));
            }

            if(i == 1 || i == 17){
                w = 6; //Lance
            }else if(i == 3 || i == 15){
                w = 5; //Knight
            }else if(i == 5 || i == 13){
                w = 4; //Silver
            }else if(i == 7 || i == 11){
                w = 3; //Gold
            }else if(i == 9){
                w = 0;
            }

            arr.add(new shogiPieces((int)(x + i*spaceDim/2), (int)(y + l*spaceDim/2), pieces[w]));
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        //length/width of a space on the board
        float spaceDim = 150;
        float topLeftX = 50;
        float topLeftY = 50;

        //paint for lines
        Paint BoardLine = new Paint();
        BoardLine.setColor(Color.BLACK);
        BoardLine.setStrokeWidth(6);

        // draw vertical lines; start xy is top point, end xy is bottom point
        for(int i = 0; i < 10; i++){
            canvas.drawLine(topLeftX + i * spaceDim, topLeftY, topLeftX + i * spaceDim, topLeftY + 9 * spaceDim, BoardLine);
            canvas.drawLine(topLeftX, topLeftY + i * spaceDim, topLeftX + 9 * spaceDim, topLeftY+ i * spaceDim, BoardLine);
        }

        drawSide(topLeftX, topLeftY, spaceDim, true);
        for(shogiPieces piece : arr){
            piece.drawShogiPiece(canvas, false, true);
        }

        arr = new ArrayList<shogiPieces>();

        drawSide(topLeftX, topLeftY, spaceDim, false);
        for(shogiPieces piece : arr){
            piece.drawShogiPiece(canvas, false, false);
        }

    }
}
