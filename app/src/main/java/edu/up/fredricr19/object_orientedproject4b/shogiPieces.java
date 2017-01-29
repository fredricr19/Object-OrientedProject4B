package edu.up.fredricr19.object_orientedproject4b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceView;

class shogiPiece extends SurfaceView{
    int PANEL_SIZE = 250;

    public shogiPiece(Context context, AttributeSet attrs){
        super(context, attrs);
        setWillNotDraw(false);
    }

    //public static final Color Tan = new Color(210, 180, 140);

    int[] xCords;
    int[] yCords;
    int xText;
    int yText1;
    int yText2;
    int font;
    boolean isUser;

    public void position(int x, int y, int r, boolean user){
        int[] xC = {x, x+r/4, x+r/2, x+3*r/4, x+r};
        int[] yC1 = {y+r, y+r/5, y, y+r/5, y+r}; //Rightside up
        int[] yC2 = {y, y+4*r/5, y+r, y+4*r/5, y}; //Upside down

        xCords = xC;
        if(user){
            yCords = yC1;
        }else{
            yCords = yC2;
        }

        font = r/4;

        xText = x+r/2 - font/2;
        yText1 = y+r/2;
        yText2 = y+r/2 + font;

        isUser = user;
    }

    public void drawShogi(Canvas canvas){
        Paint shogiPaint = new Paint();
        Paint shogiText = new Paint();

        shogiText.setTextSize(48f);

        shogiPaint.setColor(Color.GRAY);

        Path shogiPiece = new Path();
        shogiPiece.reset();
        shogiPiece.moveTo(xCords[0], yCords[0]);
        shogiPiece.lineTo(xCords[1], yCords[1]);
        shogiPiece.lineTo(xCords[2], yCords[2]);
        shogiPiece.lineTo(xCords[3], yCords[3]);
        shogiPiece.lineTo(xCords[4], yCords[4]);


        canvas.drawPath(shogiPiece, shogiPaint);


        /*g.setColor(Tan);
        g.fillPolygon(xCords, yCords, 5);

        g.setColor(Color.BLACK);
        g.drawPolygon(xCords, yCords, 5);*/

        String[] king = {"王", "將", "King", "false"};
        String[] rook = {"飛", "車", "Rook", "false"};
        String[] bishop = {"角", "行", "Bishop", "false"};
        String[] gold = {"金", "將", "Gold", "true"};
        String[] silver = {"銀", "將", "Silver", "false"};
        String[] knight = {"桂", "馬", "Knight", "false"};
        String[] lance = {"香", "車", "Lance", "false"};
        String[] pawn = {"歩", "兵", "Pawn", "true"};

        String[] s = silver;

        if(s[3] == "true"){
            shogiText.setColor(Color.RED);
        }

        if(isUser){
            canvas.drawText(s[0], xText, yText1, shogiText);
            canvas.drawText(s[1], xText, yText2, shogiText);
            canvas.drawText(s[2], xText, 3*yText1/2, shogiText);
        }else{
            /*Graphics2D g2 = (Graphics2D)g;
            AffineTransform at = new AffineTransform();

            at.setToRotation(Math.PI);
            g2.setTransform(at);

            g2.setColor(Color.black);
            g2.setFont(new Font("TimesRoman", Font.PLAIN, font));*/
            canvas.drawText(s[1], -27*xText/16, -yText1 + PANEL_SIZE/4, shogiText);
            canvas.drawText(s[0], -27*xText/16, -yText2 + PANEL_SIZE/4, shogiText);
        }
    }
}