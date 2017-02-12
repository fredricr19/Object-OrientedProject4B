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
    int PANEL_SIZE = 100;
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
    boolean useEnglish = false;

    Typeface type;

    String[] s;
    String[][] pieces = {{"王", "將", "King", "false"}, {"飛", "車", "Rook", "false"}, {"角", "行", "Bishop", "true"},
            {"金", "將", "Gold", "false"},{"銀", "將", "Silver", "false"}, {"桂", "馬", "Knight", "false"},
            {"香", "車", "Lance", "false"}, {"歩", "兵", "Pawn", "false"}};

    String[][] englishPieces = {{"K", "King", "false"}, {"R", "Rook", "false"}, {"B", "Bishop", "false"},
            {"G", "Gold", "false"},{"S", "Silver", "false"}, {"H", "Knight", "false"},
            {"L", "Lance", "false"}, {"P", "Pawn", "false"}};

    public shogiPiece(Context context, AttributeSet attrs){
        super(context, attrs);
        setWillNotDraw(false);

        type = Typeface.createFromAsset(context.getAssets(), "fonts/Helvetica.ttf");
    }

    public void position(int x, int y, int r, String[] piece){
        xCords = new float[] {x-r/2, x-r/4, x, x+r/4, x+r/2};
        yCords = new float[] {y+r/2, y-r/4, y-r/2, y-r/4, y+r/2};

        font = (r/4);
        radius = (float)r;

        xText = x - font/2;
        yText1 = y - r/4 + font;
        yText2 = y - r/4 + 2*font;

        s = piece;
    }

    public void drawShogiPiece(Canvas canvas){
        Paint shogiPaint = new Paint();
        Paint shogiOut = new Paint();
        Paint shogiText = new Paint();

        shogiPaint.setColor(0xFFD2B48C);

        shogiOut.setColor(Color.BLACK);
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
        shogiPiece.lineTo(xCords[1], yCords[1]);

        shogiText.setTypeface(type);

        canvas.drawPath(shogiPiece, shogiPaint);
        canvas.drawPath(shogiPiece, shogiOut);

        if(useEnglish && s[2].equals("true")){
            shogiText.setColor(Color.RED);
        }else if(!useEnglish && s[3].equals("true")){
            shogiText.setColor(Color.RED);
        }else{
            shogiText.setColor(Color.BLACK);
        }

        if(s[2].length() == 4 || s[2].length() == 5){
            n = 1/2;
        }

        if(useEnglish){
            shogiText.setTextSize((float)5*font/2);
            canvas.drawText(s[0], xText - 2*start, (yText1+yText2)/2 + 3*start, shogiText);

            if(s[0].equals("P")) {
                int y = (yText1+yText2)/2 + 5*start;

                shogiText.setStrokeWidth(5);
                canvas.drawLine(xText-3*start, y, xText+7*start, y, shogiText);
            }
        }else{
            if(!shortHand) {
                shogiText.setTextSize((float)3*font/2);
                canvas.drawText(s[0], xText - 8, yText1, shogiText);
                canvas.drawText(s[1], xText - 8, yText2 + 16, shogiText);
            }else{
                shogiText.setTextSize((float)2*font);
                canvas.drawText(s[0], xText - 3*start - 2, (yText1+yText2)/2 + 6, shogiText);

                if(s[2].length() > 2){
                    n = 3;
                }else{
                    n = 3*start;
                }

                shogiText.setTextSize(3*font/4);
                canvas.drawText(s[2], xText - (int)(n*s[2].length()), yText2 + (yText2 - yText1) - 6, shogiText);
            }
        }
    }

    public void drawUserPieces(Canvas canvas){
        if(useEnglish){
            pieces = englishPieces;
        }

        String[] zed = pieces[7];

        //Pawns
        for(int i = 0; i < 9; i++){
            position((i+1)*start+i*PANEL_SIZE, start, PANEL_SIZE, pieces[7]);
            drawShogiPiece(canvas);
        }

        //Bishop
        position(2*start+PANEL_SIZE, 3*start+PANEL_SIZE, PANEL_SIZE, pieces[2]);
        drawShogiPiece(canvas);

        //Rook
        position(8*start+7*PANEL_SIZE, 3*start+PANEL_SIZE, PANEL_SIZE, pieces[1]);
        drawShogiPiece(canvas);

        //Bottom Row
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

    //@Override
    //public void onDraw(Canvas canvas){ drawUserPieces(canvas); }
}