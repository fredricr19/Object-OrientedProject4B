package edu.up.fredricr19.object_orientedproject4b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by Javier on 25-Jan-17.
 */

public class ShogiGui extends SurfaceView {
    shogiPiece a;

    public ShogiGui(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setWillNotDraw(false);

        a = new shogiPiece(context, attrs);
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        //length/width of a space on the board
        float spaceDim = 150;
        float topLeftX = 95;
        float topLeftY = 350;
        int start = 5;
        int pieceSize = 120;

        //paint for lines
        Paint BoardLine = new Paint();
        BoardLine.setColor(0xFFFFFFFF);
        BoardLine.setStrokeWidth(6f);

        //canvas.drawLine(50, 300, 1486, 300, BoardLine);
        //canvas.drawLine(50, 300 + spaceDim, 1486, 300 + spaceDim, BoardLine);



        //draw vertical lines; start xy is top point, end xy is bottom point
        int j;
        for(j = 0; j < 10; j++){
            canvas.drawLine(topLeftX + j * spaceDim, topLeftY, topLeftX + j * spaceDim, topLeftY + 9 * spaceDim, BoardLine);
        }

        a.position((int)(topLeftX + pieceSize/2) + 12, (int)(topLeftY + pieceSize/2) + 15, pieceSize, shogiPiece.pieces[7]);
        a.drawShogiPiece(canvas);

        a.position((int)(5*topLeftX/2 + pieceSize/2) + 24, (int)(topLeftY + pieceSize/2) + 15, pieceSize, shogiPiece.pieces[1]);
        a.drawShogiPiece(canvas);

        a.position((int)(5*topLeftX/2 + pieceSize/2) + 24, (int)(topLeftY + 3*pieceSize/2) + 45, pieceSize, shogiPiece.pieces[0]);
        a.drawShogiPiece(canvas);

        //draw horizontal lines; start xy is left point, end xy is right
        int i;
        for(i = 0; i < 10; i++){
            canvas.drawLine(topLeftX, topLeftY + i * spaceDim, topLeftX + 9 * spaceDim, topLeftY+ i * spaceDim, BoardLine);
        }


        /*
        //creates the various paints used in the board
        Paint shogiboard = new Paint();
        Paint opponent = new Paint();
        Paint player = new Paint();
        Paint captured = new Paint();
        Paint square = new Paint();
        Paint text = new Paint();

        //creates the colors used
        shogiboard.setColor(0xFFB69B4C);
        opponent.setColor(0xFFFF0F0F);
        player.setColor(0xFF85bff8);
        captured.setColor(0xFFC7AC5D);
        square.setColor(0xFF000000);
        text.setColor(0xFF000000);

        //misc settings for paints
        square.setStyle(Paint.Style.STROKE);
        text.setTextSize(48f);
        Paint text2 = text;

        //backgroung for the main display
        Bitmap background =
                BitmapFactory.decodeResource(getResources(), R.drawable.bam222);
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(background, 0, 0, null);

        //the board and player sides
        canvas.drawRect(230.5f, 100f, 1500f,250f, captured);
        canvas.drawCircle(230.5f, 275f,180f,opponent);
        canvas.drawText("Opponent",130f,200f,text2);
        canvas.drawRect(50f, 1650f, 1319f,1800f, captured);
        canvas.drawCircle(1319f, 1625f,180f,player);
        text.setUnderlineText(true);
        canvas.drawText("Player",1250f,1725f,text);
        canvas.drawRect(50f, 250f, 1500f, 1650f, shogiboard);
        canvas.drawRect(50f, 250f, 1500f, 1650f, square);
        canvas.drawRect(75f, 275f, 1475f, 1625f, square);
        canvas.drawLine(230.5f, 275f, 230.5f, 1625f, square);
        canvas.drawLine(386f, 275f, 386f, 1625f, square);
        canvas.drawLine(541.5f, 275f, 541.5f, 1625f, square);
        canvas.drawLine(697f, 275f, 697f, 1625f, square);
        canvas.drawLine(852.5f, 275f, 852.5f, 1625f, square);
        canvas.drawLine(1008f, 275f, 1008f, 1625f, square);
        canvas.drawLine(1163.5f, 275f, 1163.5f, 1625f, square);
        canvas.drawLine(1319f, 275f, 1319f, 1625f, square);
        canvas.drawLine(75f, 425f, 1475f, 425f, square);
        canvas.drawLine(75f, 575f, 1475f, 575f, square);
        canvas.drawLine(75f, 725f, 1475f, 725f, square);
        canvas.drawLine(75f, 875f, 1475f, 875f, square);
        canvas.drawLine(75f, 1025f, 1475f, 1025f, square);
        canvas.drawLine(75f, 1175f, 1475f, 1175f, square);
        canvas.drawLine(75f, 1325f, 1475f, 1325f, square);
        canvas.drawLine(75f, 1475f, 1475f, 1475f, square);
        canvas.drawCircle(1210,1710, 20,text);

        //pieces for the game
        Bitmap jewel =
                BitmapFactory.decodeResource(getResources(), R.drawable.king);
        canvas.drawColor(00000000);
        canvas.drawBitmap(jewel, 710, 1470, null);
        Bitmap king =
                BitmapFactory.decodeResource(getResources(), R.drawable.king2);
        canvas.drawBitmap(king, 710, 275, null);
        Bitmap pawn =
                BitmapFactory.decodeResource(getResources(), R.drawable.pawn_s);
        canvas.drawBitmap(pawn, 95, 725, null);
        canvas.drawBitmap(pawn, 710, 1025, null);
        canvas.drawBitmap(pawn, 560, 725, null);
        canvas.drawBitmap(pawn, 410, 875, null);
        canvas.drawBitmap(pawn, 75, 1650, null);
        Bitmap pawn2 =
                BitmapFactory.decodeResource(getResources(), R.drawable.pawn2);
        canvas.drawBitmap(pawn2, 225, 575, null);
        canvas.drawBitmap(pawn2, 860, 1025, null);
        canvas.drawBitmap(pawn2, 410, 100, null);
        canvas.drawBitmap(pawn2, 1330, 570, null);
        canvas.drawBitmap(pawn2, 1163, 725, null);
        Bitmap knight =
                BitmapFactory.decodeResource(getResources(), R.drawable.horse);
        canvas.drawBitmap(knight, 400, 1170, null);
        canvas.drawBitmap(knight, 225, 1640, null);
        Bitmap knight2 =
                BitmapFactory.decodeResource(getResources(), R.drawable.horse2);
        canvas.drawBitmap(knight2, 560, 100, null);
        Bitmap bishop =
                BitmapFactory.decodeResource(getResources(), R.drawable.bishop);
        canvas.drawBitmap(bishop, 860, 720, null);
        Bitmap bishop2 =
                BitmapFactory.decodeResource(getResources(), R.drawable.bishop2);
        canvas.drawBitmap(bishop2, 240, 1325, null);
        Bitmap lance =
                BitmapFactory.decodeResource(getResources(), R.drawable.lance);
        canvas.drawBitmap(lance, 1330, 720, null);
        canvas.drawBitmap(lance, 90, 870, null);
        canvas.drawBitmap(lance, 375, 1640, null);
        Bitmap lance2 =
                BitmapFactory.decodeResource(getResources(), R.drawable.lance2);
        canvas.drawBitmap(lance2, 95, 575, null);
        Bitmap rook =
                BitmapFactory.decodeResource(getResources(), R.drawable.rook);
        canvas.drawBitmap(rook, 525, 1640, null);
        canvas.drawBitmap(rook, 1172, 1320, null);
        Bitmap silver =
                BitmapFactory.decodeResource(getResources(), R.drawable.silver);
        canvas.drawBitmap(silver, 710, 720, null);
        Bitmap silver2 =
                BitmapFactory.decodeResource(getResources(), R.drawable.silver2);
        canvas.drawBitmap(silver2, 710, 100, null);
        canvas.drawBitmap(silver2, 1008, 1025, null);
        canvas.drawBitmap(silver2, 230, 875, null);
        Bitmap gold =
                BitmapFactory.decodeResource(getResources(), R.drawable.goldgeneral);
        canvas.drawBitmap(gold, 225, 1475, null);
        canvas.drawBitmap(gold, 675,1640, null);

        */

    }


}
