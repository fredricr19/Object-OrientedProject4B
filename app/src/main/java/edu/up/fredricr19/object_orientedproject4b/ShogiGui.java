package edu.up.fredricr19.object_orientedproject4b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class ShogiGui extends SurfaceView {
    shogiPiece a;

    String[][] pieces = {{"王", "將", "King", "false"}, {"飛", "車", "Rook", "false"}, {"角", "行", "Bishop", "true"},
            {"金", "將", "Gold", "false"},{"銀", "將", "Silver", "false"}, {"桂", "馬", "Knight", "false"},
            {"香", "車", "Lance", "false"}, {"歩", "兵", "Pawn", "false"}};

    public ShogiGui(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        a = new shogiPiece(context, attrs);
    }

    @Override
    public void onDraw(Canvas canvas) {
        //length/width of a space on the board
        float spaceDim = 150;
        float topLeftX = 50;
        float topLeftY = 50;
        int pieceSize = 130;
        int n = 10;

        //paint for lines
        Paint BoardLine = new Paint();
        BoardLine.setColor(Color.BLACK);
        BoardLine.setStrokeWidth(6);

        // draw vertical lines; start xy is top point, end xy is bottom point
        for(int i = 0; i < 10; i++){
            canvas.drawLine(topLeftX + i * spaceDim, topLeftY, topLeftX + i * spaceDim, topLeftY + 9 * spaceDim, BoardLine);
            canvas.drawLine(topLeftX, topLeftY + i * spaceDim, topLeftX + 9 * spaceDim, topLeftY+ i * spaceDim, BoardLine);
        }

        a.position((int)(topLeftX + pieceSize/2) + n, (int)(topLeftY + pieceSize/2) + 10, pieceSize, pieces[7]);
        a.drawShogiPiece(canvas);

        a.position((int)(topLeftX + pieceSize/2) + n, (int)(topLeftY + 3*pieceSize/2) + 3*10, pieceSize, pieces[3]);
        a.drawShogiPiece(canvas);

        a.position((int)(5*topLeftX/2 + pieceSize) + 2*n, (int)(topLeftY + pieceSize/2) + 10, pieceSize, pieces[1]);
        a.drawShogiPiece(canvas);

        a.position((int)(5*topLeftX/2 + pieceSize) + 2*n, (int)(topLeftY + 3*pieceSize/2) + 3*10, pieceSize, pieces[0]);
        a.drawShogiPiece(canvas);

        a.position((int)(8*topLeftX/2 + 3*pieceSize/2) + 3*n, (int)(topLeftY + 5*pieceSize/2) + 5*10, pieceSize, pieces[2]);
        a.drawShogiPiece(canvas);

        a.position((int)(11*topLeftX/2 + 4*pieceSize/2) + 4*n, (int)(topLeftY + 5*pieceSize/2) + 5*10, pieceSize, pieces[5]);
        a.drawShogiPiece(canvas);
    }


}
