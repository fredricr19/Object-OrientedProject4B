package edu.up.fredricr19.object_orientedproject4b;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class shogiPieces {
    private boolean shortHand = false; //Denotes whether to use short hand (i.e. single character+english) --- Leave Here
    private boolean useEnglish = false; //Denotes whether to use english letter --- Leave Here

    private int x;
    private int y;
    private boolean promoted = false;
    private String[] s;

    private String[][] pieces = {{"王", "將", "王", "King"}, {"飛", "車", "飛", "Rook"}, {"角", "行", "角", "Bishop"},
            {"金", "將", "金", "Gold"}, {"銀", "將", "銀", "Silver"}, {"桂", "馬", "桂", "Knight"},
            {"香", "車", "香", "Lance"}, {"歩", "兵", "歩", "Pawn"}};

    private String[][] promotedPieces = {{"", "", "", "King"}, {"龍", "王", "龍", "Rook"}, {"龍", "馬", "馬", "Bishop"},
            {"", "", "", "Gold"}, {"成", "銀", "全", "Silver"}, {"成", "桂", "圭", "Knight"}, {"成", "香", "杏", "Lance"},
            {"と", "金", "と", "Pawn"}};

    public shogiPieces(float initX, float initY, boolean p, String piece){
        this.x = (int)initX; //Defines x value
        this.y = (int)initY; //Defines y value
        this.promoted = p; //Defines whether piece is promoted

        for(String[] pen15 : pieces){
            if(pen15[3].equals(piece)){
                this.s = pen15; //Defines the piece
                break;
            }
        }
    }

    public void promotePiece(boolean p){
        this.promoted = p;
    }

    //Draws Piece
    public void drawShogiPiece(Canvas canvas, boolean player){
        int r = 100; //Size of the piece
        float[] xCords = {x- r /2, x- r /4, x, x+ r /4, x+ r /2}; //xCords for outline
        float[] yCords = new float[] {y+ r /2, y- r /4, y- r /2, y- r /4, y+ r /2}; //yCords for outline

        double n;  //Helps center english words if shortHand = true
        float a = 0; //Does something --- leave this alone

        int font = (r/4); //Sets font
        int start = 5; //A sort of padding

        int xText = x - font/2;
        int yText1 = y - r /4 + font;
        int yText2 = y - r /4 + 2*font;

        for(String[] aww : promotedPieces){
            if(s[3].equals(aww[3]) && promoted && !s[3].equals("King") && !s[3].equals("Gold")){
                s = aww;
            }
        }

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

        if(!player){
            canvas.rotate(180f, x, y);
        }

        canvas.drawPath(shogiPiece, shogiPaint);
        canvas.drawPath(shogiPiece, shogiOut);

        if(promoted && !s[3].equals("King") && !s[3].equals("Gold")){
            shogiText.setColor(Color.RED);
        }else{
            shogiText.setColor(Color.BLACK);
        }

        if(useEnglish){
            shogiText.setTextSize((float)5*font/2);

            if(s[3].substring(0, 1).equals("L")){
                canvas.drawText(s[3].substring(0, 1), xText - start, (yText1+yText2)/2 + 3*start, shogiText);
            }else{
                canvas.drawText(s[3].substring(0, 1), xText - 2*start, (yText1+yText2)/2 + 3*start, shogiText);
            }


            if(s[3].equals("Pawn")) {
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
                if(promoted){
                    a = 3;
                }

                shogiText.setTextSize((float)2*font);
                canvas.drawText(s[2], xText - 3*start + a, (yText1+yText2)/2 + 6, shogiText);

                if(s[3].equals("Silver")){
                    n = 2;
                }else if(s[3].equals("Pawn")){
                    n = 3;
                }else if(s[3].equals("Knight")){
                    n = 2.8;
                }else if(s[3].equals("Bishop")){
                    n = 2.5;
                }else if(s[3].equals("Rook")){
                    n = 2;
                }else if(s[3].equals("Lance")){
                    n = 2.5;
                }else if(s[3].equals("King") || s[3].equals("Gold")){
                    n = 2.5;
                }else{
                    n = 3;
                }

                shogiText.setTextSize(3*font/4);
                canvas.drawText(s[3], xText - (int)(n*s[3].length()), yText2 + (yText2 - yText1) - 6, shogiText);
            }
        }
        if(!player){
            canvas.rotate(180f, x, y);
        }
    }
}