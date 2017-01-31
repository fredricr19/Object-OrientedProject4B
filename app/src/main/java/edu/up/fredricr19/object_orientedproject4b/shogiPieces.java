package edu.up.fredricr19.object_orientedproject4b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceView;

class shogiPiece extends SurfaceView{
    edu.up.fredricr19.object_orientedproject4b.shogiPiece fr = (edu.up.fredricr19.object_orientedproject4b.shogiPiece)findViewById(R.id.shogiPiece);
    int h = fr.getHeight();
    int w = fr.getWidth();

    int PANEL_SIZE = 125;

    float[] xCords;
    float[] yCords;
    float radius;
    int xText;
    int yText1;
    int yText2;
    int font;
    float n = 1;
    boolean shortHand = true;

    public shogiPiece(Context context, AttributeSet attrs){
        super(context, attrs);
        setWillNotDraw(false);
    }

    public void position(int x, int y, int r){
        float[] xC = {x, x+r/4, x+r/2, x+3*r/4, x+r};
        float[] yC = {y+r, y+r/5, y, y+r/5, y+r};

        xCords = xC;
        yCords = yC;

        font = (r/4);
        radius = (float)r;

        xText = x+r/2 - font/2;
        yText1 = y+r/2;
        yText2 = y+r/2 + font;
    }

    @Override
    public void onDraw(Canvas canvas){
        position(0, 5, PANEL_SIZE);

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

        canvas.drawPath(shogiPiece, shogiPaint);
        canvas.drawPath(shogiPiece, shogiOut);

        String[] king = {"王", "將", "King", "false"};
        String[] rook = {"飛", "車", "Rook", "false"};
        String[] bishop = {"角", "行", "Bishop", "false"};
        String[] gold = {"金", "將", "Gold", "true"};
        String[] silver = {"銀", "將", "Silver", "false"};
        String[] knight = {"桂", "馬", "Knight", "false"};
        String[] lance = {"香", "車", "Lance", "false"};
        String[] pawn = {"歩", "兵", "Pawn", "true"};

        String[] s = lance;

        if(s[3] == "true"){
            shogiText.setColor(Color.RED);
        }else{
            shogiText.setColor(Color.BLACK);
        }

        if(s[2].length() == 4){
            n = 1/2;
        }

        if(!shortHand) {
            shogiText.setTextSize((float)font);
            canvas.drawText(s[0], xText, yText1, shogiText);
            canvas.drawText(s[1], xText, yText2, shogiText);
        }else{
            shogiText.setTextSize((float)2*font);
            canvas.drawText(s[0], PANEL_SIZE/2 - PANEL_SIZE/4, (yText1+yText2)/2, shogiText);
        }

        shogiText.setTextSize((float)font/2);
        canvas.drawText(s[2], xText - n*s[2].length(), yText2 + (yText2 - yText1) - 6, shogiText);
    }
}