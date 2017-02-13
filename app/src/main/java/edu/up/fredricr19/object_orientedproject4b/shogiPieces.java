package edu.up.fredricr19.object_orientedproject4b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class shogiPieces extends SurfaceView {
    boolean shortHand = true;
    boolean useEnglish = false;

    Typeface type;

    /*String[][] pieces = {{"王", "將", "King"}, {"飛", "車", "Rook"}, {"角", "行", "Bishop"}, {"金", "將", "Gold"},
            {"銀", "將", "Silver"}, {"桂", "馬", "Knight"}, {"香", "車", "Lance"}, {"歩", "兵", "Pawn"}};*/

    public shogiPieces(Context context, AttributeSet attrs){
        super(context, attrs);

        type = Typeface.createFromAsset(context.getAssets(), "fonts/Helvetica.ttf");
    }

    public void drawShogiPiece(Canvas canvas, int x, int y, int r, String[] s, boolean promoted, boolean player){
        float[] xCords = {x-r/2, x-r/4, x, x+r/4, x+r/2};
        float[] yCords = new float[] {y+r/2, y-r/4, y-r/2, y-r/4, y+r/2};

        double n;

        int font = (r/4);
        int start = 5;

        int xText = x - font/2;
        int yText1 = y - r/4 + font;
        int yText2 = y - r/4 + 2*font;


        Paint shogiPaint = new Paint();
        Paint shogiOut = new Paint();
        Paint shogiText = new Paint();

        shogiPaint.setColor(0xFFD2B48C);

        shogiOut.setColor(Color.BLACK);
        shogiOut.setStyle(Paint.Style.STROKE);
        shogiOut.setStrokeWidth(4);

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

        if(!player){
            canvas.rotate(180f, x, y);
        }

        canvas.drawPath(shogiPiece, shogiPaint);
        canvas.drawPath(shogiPiece, shogiOut);

        if(promoted){
            shogiText.setColor(Color.RED);
        }else{
            shogiText.setColor(Color.BLACK);
        }

        if(useEnglish){
            shogiText.setTextSize((float)5*font/2);

            if(s[2].substring(0, 1).equals("L")){
                canvas.drawText(s[2].substring(0, 1), xText - start, (yText1+yText2)/2 + 3*start, shogiText);
            }else{
                canvas.drawText(s[2].substring(0, 1), xText - 2*start, (yText1+yText2)/2 + 3*start, shogiText);
            }


            if(s[2].equals("Pawn")) {
                int y1 = (yText1+yText2)/2 + 5*start;

                shogiText.setStrokeWidth(5);
                canvas.drawLine(xText-2*start, y1, xText+6*start, y1, shogiText);
            }
        }else{
            if(!shortHand) {
                shogiText.setTextSize((float)3*font/2);
                canvas.drawText(s[0], xText - 8, yText1, shogiText);
                canvas.drawText(s[1], xText - 8, yText2 + 16, shogiText);
            }else{
                shogiText.setTextSize((float)2*font);
                canvas.drawText(s[0], xText - 3*start, (yText1+yText2)/2 + 6, shogiText);

                if(s[2].equals("Silver")){
                    n = 2;
                }else if(s[2].equals("Pawn")){
                    n = 3;
                }else if(s[2].equals("Knight")){
                    n = 2.8;
                }else if(s[2].equals("Bishop") || s[2].equals("Rook")){
                    n = 3;
                }else if(s[2].equals("Lance")){
                    n = 3;
                }else if(s[2].equals("King") || s[2].equals("Gold")){
                    n = 2.5;
                }else{
                    n = 3;
                }

                shogiText.setTextSize(3*font/4);
                canvas.drawText(s[2], xText - (int)(n*s[2].length()), yText2 + (yText2 - yText1) - 6, shogiText);
            }
        }
        if(!player){
            canvas.rotate(180f, x, y);
        }
    }
}