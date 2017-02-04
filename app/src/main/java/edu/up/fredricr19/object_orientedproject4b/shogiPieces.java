package edu.up.fredricr19.object_orientedproject4b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.SurfaceView;

class shogiPiece extends SurfaceView {
    int PANEL_SIZE = 125;
    int start = 4;

    float[] xCords;
    float[] yCords;
    float radius;
    int xText;
    int yText1;
    int yText2;
    int font;
    float n = 1;
    boolean shortHand = true;

    Typeface type;

    String[] s;
    String[][] pieces = {{"王", "將", "King", "false"}, {"飛", "車", "Rook", "false"}, {"角", "行", "Bishop", "true"},
            {"金", "將", "Gold", "true"},{"銀", "將", "Silver", "false"}, {"桂", "馬", "Knight", "false"},
            {"香", "車", "Lance", "false"}, {"歩", "兵", "Pawn", "true"}};

    public shogiPiece(Context context, AttributeSet attrs){
        super(context, attrs);
        setWillNotDraw(false);

        type = Typeface.createFromAsset(context.getAssets(),"fonts/Helvetica.ttf");
    }

    public void position(int x, int y, int r, String[] piece){
        float[] xC = {x, x+r/4, x+r/2, x+3*r/4, x+r};
        float[] yC = {y+r, y+r/5, y, y+r/5, y+r};

        xCords = xC;
        yCords = yC;

        font = (r/4);
        radius = (float)r;

        xText = x+r/2 - font/2;
        yText1 = y+r/2;
        yText2 = y+r/2 + font;

        s = piece;
    }

    //@Override
    public void drawShogiPiece(Canvas canvas){
        //position(start, start, PANEL_SIZE, pieces[2]);

        Paint shogiPaint = new Paint();
        Paint shogiOut = new Paint();
        Paint shogiText = new Paint();

        shogiPaint.setColor(0xFFD2B48C);

        shogiOut.setStyle(Paint.Style.STROKE);
        shogiOut.setStrokeWidth(5);

        Path shogiPiece = new Path();
        shogiPiece.reset();
        shogiPiece.moveTo(xCords[0], yCords[0]);
        shogiPiece.lineTo(xCords[1], yCords[1]);
        shogiPiece.lineTo(xCords[2], yCords[2]);
        shogiPiece.lineTo(xCords[3], yCords[3]);
        shogiPiece.lineTo(xCords[4], yCords[4]);
        shogiPiece.lineTo(xCords[0], yCords[0]);
        shogiPiece.lineTo(xCords[1], yCords[1]);

        canvas.drawPath(shogiPiece, shogiPaint);
        canvas.drawPath(shogiPiece, shogiOut);

        if(s[3] == "true"){
            shogiText.setColor(Color.RED);
        }else{
            shogiText.setColor(Color.BLACK);
        }

        if(s[2].length() == 4){
            n = 1/2;
        }

        shogiText.setTypeface(type);

        if(!shortHand) {
            shogiText.setTextSize((float)font);
            canvas.drawText(s[0], xText, yText1 - 6, shogiText);
            canvas.drawText(s[1], xText, yText2 - 6, shogiText);
            font = font/2;
        }else{
            shogiText.setTextSize((float)2*font);
            canvas.drawText(s[0], xText - 4*start, (yText1+yText2)/2 + 6, shogiText);
            font = 3*font/4;
            if(s[2].length() > 2){
                n = 3;
            }else{
                n = 2;
            }
        }

        shogiText.setTextSize(font);
        canvas.drawText(s[2], xText - n*s[2].length(), yText2 + (yText2 - yText1) - 6, shogiText);
    }

    @Override
    public void onDraw(Canvas canvas){
        position(start, start, PANEL_SIZE, pieces[1]);
        drawShogiPiece(canvas);

        position(start, 2*PANEL_SIZE, PANEL_SIZE, pieces[3]);
        drawShogiPiece(canvas);

        position(2*PANEL_SIZE, start, PANEL_SIZE, pieces[5]);
        drawShogiPiece(canvas);

        position(2*PANEL_SIZE, 2*PANEL_SIZE, PANEL_SIZE, pieces[7]);
        drawShogiPiece(canvas);
    }
}