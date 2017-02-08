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
    int PANEL_SIZE = 130;
    int start = 5;

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
    String[][] pieces = {{"王", "將", "King", "false"}, {"飛", "車", "Rook", "true"}, {"角", "行", "Bishop", "false"},
            {"金", "將", "Gold", "false"},{"銀", "將", "Silver", "false"}, {"桂", "馬", "Knight", "false"},
            {"香", "車", "Lance", "false"}, {"歩", "兵", "Pawn", "false"}};

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

    public void drawShogiPiece(Canvas canvas){
        Paint shogiPaint = new Paint();
        Paint shogiOut = new Paint();
        Paint shogiText = new Paint();

        shogiPaint.setColor(0xFFD2B48C);

        shogiOut.setStyle(Paint.Style.STROKE);
        shogiOut.setStrokeWidth(2);

        Path shogiPiece = new Path();
        shogiPiece.reset();
        shogiPiece.moveTo(xCords[0], yCords[0]);
        shogiPiece.lineTo(xCords[1], yCords[1]);
        shogiPiece.lineTo(xCords[2], yCords[2]);
        shogiPiece.lineTo(xCords[3], yCords[3]);
        shogiPiece.lineTo(xCords[4], yCords[4]);
        shogiPiece.lineTo(xCords[0], yCords[0]);
        shogiPiece.lineTo(xCords[1], yCords[1]);

        shogiText.setTypeface(type);

        canvas.drawPath(shogiPiece, shogiPaint);
        canvas.drawPath(shogiPiece, shogiOut);

        if(s[3].equals("true")){
            shogiText.setColor(Color.RED);
        }else{
            shogiText.setColor(Color.BLACK);
        }

        if(s[2].length() == 4 || s[2].length() == 5){
            n = 1/2;
        }

        if(!shortHand) {
            shogiText.setTextSize((float)font);
            canvas.drawText(s[0], xText, yText1 - 6, shogiText);
            canvas.drawText(s[1], xText, yText2 - 6, shogiText);
            font = font/2;
        }else{
            shogiText.setTextSize((float)2*font);
            canvas.drawText(s[0], xText - 3*start, (yText1+yText2)/2 + 6, shogiText);
            font = 3*font/4;
            if(s[2].length() > 2){
                n = 3;
            }else{
                n = 3*start;
            }
        }

        shogiText.setTextSize(font);
        canvas.drawText(s[2], xText - (int)(n*s[2].length()), yText2 + (yText2 - yText1) - 6, shogiText);
    }

    public void drawUserPieces(Canvas canvas){
        String[] zed = pieces[7];

        /*---------Pawns---------*/
        for(int i = 0; i < 9; i++){
            position((i+1)*start+i*PANEL_SIZE, start, PANEL_SIZE, pieces[7]);
            drawShogiPiece(canvas);
        }

        /*---------Bishop---------*/
        position(2*start+PANEL_SIZE, 3*start+PANEL_SIZE, PANEL_SIZE, pieces[2]);
        drawShogiPiece(canvas);

        /*---------Rook---------*/
        position(8*start+7*PANEL_SIZE, 3*start+PANEL_SIZE, PANEL_SIZE, pieces[1]);
        drawShogiPiece(canvas);

        /*---------Bottom Row---------*/
        int y = 5*start+2*PANEL_SIZE;
        for(int i = 1; i <= 9; i++){
            if(i == 1 || i == 9){
                zed = pieces[6]; //Lance
            }else if(i == 2 || i == 8){
                zed = pieces[5]; //Knight
            }else if(i == 3 || i == 7){
                zed = pieces[4]; //Silver
            }else if(i == 4 || i == 6){
                zed = pieces[3]; //Gold
            }else if(i == 5){
                zed = pieces[0]; //King
            }

            position(i*start+(i-1)*PANEL_SIZE, y, PANEL_SIZE, zed);
            drawShogiPiece(canvas);
        }
    }

    @Override
    public void onDraw(Canvas canvas){
        drawUserPieces(canvas);
    }
}