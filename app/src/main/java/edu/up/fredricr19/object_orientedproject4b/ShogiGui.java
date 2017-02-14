package edu.up.fredricr19.object_orientedproject4b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.ArrayList;

public class ShogiGui extends SurfaceView {
    ArrayList<shogiPieces> arr = new ArrayList<>();

    public ShogiGui(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    public void drawSide(float x, float y, float spaceDim, boolean player){
        String w = "Pawn";
        int p = 5;
        int m = 3;
        int l = 1;

        if(player){
            p = 13;
            m = 15;
            l = 17;
        }

        for(int i = 1; i <= 17; i+=2) {
            arr.add(new shogiPieces((x + i * spaceDim / 2), (y + p * spaceDim / 2), "Pawn"));
        }

        arr.add(new shogiPieces((int)(x + 3*spaceDim/2), (int)(y + m*spaceDim/2), "Bishop"));
        arr.add(new shogiPieces((int)(x + 15*spaceDim/2), (int)(y + m*spaceDim/2), "Rook"));

        for(int i = 1; i<=17; i+=2){
            if(i == 1 || i == 17){
                w = "Lance"; //Lance
            }else if(i == 3 || i == 15){
                w = "Lance"; //Knight
            }else if(i == 5 || i == 13){
                w = "Silver"; //Silver
            }else if(i == 7 || i == 11){
                w = "Gold"; //Gold
            }else if(i == 9){
                w = "King"; //King
            }

            arr.add(new shogiPieces((int)(x + i*spaceDim/2), (int)(y + l*spaceDim/2), w));
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        //length/width of a space on the board
        float spaceDim = 150;
        float topLeftX = 90;
        float topLeftY = 100;

        //paint for lines
        Paint BoardLine = new Paint();
        BoardLine.setColor(Color.BLACK);
        BoardLine.setStrokeWidth(6);

        // draw vertical lines; start xy is top point, end xy is bottom point
        for(int i = 0; i < 10; i++){
            canvas.drawLine(topLeftX + i * spaceDim, topLeftY, topLeftX + i * spaceDim, topLeftY + 9 * spaceDim, BoardLine);
            canvas.drawLine(topLeftX, topLeftY + i * spaceDim, topLeftX + 9 * spaceDim, topLeftY+ i * spaceDim, BoardLine);
        }

        int i = 0;

        drawSide(topLeftX, topLeftY, spaceDim, true);
        for(shogiPieces piece : arr){
            if(i % 2 == 0){
                piece.promotePiece(true);
            }
            piece.drawShogiPiece(canvas, true);
            i+=1;
        }

        arr = new ArrayList<>();

        drawSide(topLeftX, topLeftY, spaceDim, false);
        for(shogiPieces piece : arr){
            piece.drawShogiPiece(canvas, false);
        }
    }
}
