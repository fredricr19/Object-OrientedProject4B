package edu.up.fredricr19.object_orientedproject4b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceView;

class shogiPiece extends SurfaceView{
    int PANEL_SIZE = 225;

    float[] xCords;
    float[] yCords;
    int xText;
    int yText1;
    int yText2;
    int font;
    boolean isUser;

    public shogiPiece(Context context, AttributeSet attrs){
        super(context, attrs);
        setWillNotDraw(false);
    }

    public void position(int x, int y, int r, boolean user){
        float[] xC = {x, x+r/4, x+r/2, x+3*r/4, x+r};
        float[] yC1 = {y+r, y+r/5, y, y+r/5, y+r}; //Rightside up
        float[] yC2 = {y, y+4*r/5, y+r, y+4*r/5, y}; //Upside down

        xCords = xC;
        if(user){
            yCords = yC1;
        }else{
            yCords = yC2;
        }

        font = (r/4);

        xText = x+r/2 - font/2;
        yText1 = y+r/2;
        yText2 = y+r/2 + font;

        isUser = user;
    }

    @Override
    public void onDraw(Canvas canvas){
        position(0, 0, PANEL_SIZE, true);

        Paint shogiPaint = new Paint();
        Paint shogiOut = new Paint();
        Paint shogiText = new Paint();

        shogiText.setTextSize((float)font);

        shogiPaint.setColor(0xFFD2B48C);

        shogiOut.setStyle(Paint.Style.STROKE);
        shogiOut.setStrokeWidth(3);

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

        String[] s = knight;


        canvas.drawText(s[0], xText, yText1, shogiText);
        canvas.drawText(s[1], xText, yText2, shogiText);
        shogiText.setTextSize((float)font/2);
        canvas.drawText(s[2], xText - s[2].length(), yText2 + (yText2 - yText1), shogiText);
    }



}