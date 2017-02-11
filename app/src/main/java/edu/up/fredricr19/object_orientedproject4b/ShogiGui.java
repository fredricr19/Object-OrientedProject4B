package edu.up.fredricr19.object_orientedproject4b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class ShogiGui extends SurfaceView {
    shogiPiece a;

    String[][] pieces = {{"王", "將", "King", "false"}, {"飛", "車", "Rook", "false"}, {"角", "行", "Bishop", "true"},
            {"金", "將", "Gold", "false"},{"銀", "將", "Silver", "false"}, {"桂", "馬", "Knight", "false"},
            {"香", "車", "Lance", "false"}, {"歩", "兵", "Pawn", "true"}};

    public ShogiGui(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        a = new shogiPiece(context, attrs);
    }

    @Override
    public void onDraw(Canvas canvas) {
        //length/width of a space on the board
        float spaceDim = 150;
        float topLeftX = 95;
        float topLeftY = 350;
        //int start = 5;
        int pieceSize = 120;

        //paint for lines
        Paint BoardLine = new Paint();
        BoardLine.setColor(0xFFFFFFFF);
        BoardLine.setStrokeWidth(6f);

        //canvas.drawLine(50, 300, 1486, 300, BoardLine);
        //canvas.drawLine(50, 300 + spaceDim, 1486, 300 + spaceDim, BoardLine);


        // draw vertical lines; start xy is top point, end xy is bottom point
        for(int i = 0; i < 10; i++){
            canvas.drawLine(topLeftX + i * spaceDim, topLeftY, topLeftX + i * spaceDim, topLeftY + 9 * spaceDim, BoardLine);
            canvas.drawLine(topLeftX, topLeftY + i * spaceDim, topLeftX + 9 * spaceDim, topLeftY+ i * spaceDim, BoardLine);
        }

        a.position((int)(topLeftX + pieceSize/2) + 12, (int)(topLeftY + pieceSize/2) + 15, pieceSize, pieces[7]);
        a.drawShogiPiece(canvas);

        a.position((int)(topLeftX + pieceSize/2) + 12, (int)(topLeftY + 3*pieceSize/2) + 3*15, pieceSize, pieces[3]);
        a.drawShogiPiece(canvas);

        a.position((int)(5*topLeftX/2 + pieceSize/2) + 2*12, (int)(topLeftY + pieceSize/2) + 15, pieceSize, pieces[1]);
        a.drawShogiPiece(canvas);

        a.position((int)(5*topLeftX/2 + pieceSize/2) + 2*12, (int)(topLeftY + 3*pieceSize/2) + 3*15, pieceSize, pieces[0]);
        a.drawShogiPiece(canvas);

        a.position((int)(11*topLeftX/3 + pieceSize/2) + 5*12, (int)(topLeftY + 5*pieceSize/2) + 5*15, pieceSize, pieces[2]);
        a.drawShogiPiece(canvas);
    }


}
