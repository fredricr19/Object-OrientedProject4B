package edu.up.fredricr19.object_orientedproject4b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

/**
 * @author Javier Resop
 * @author Chase Des Laurier
 * @author Ryan Fredrickson
 */

public class ShogiGui extends SurfaceView implements View.OnTouchListener {


    //array for drawing and managing pieces on the board
    shogiPiece Pieces[][] = new shogiPiece[9][9];
    Bitmap BoardBackground;

    //top left corner of board lines; must be global for manipulating shogi pieces
    //length/width of a space on the board
    public static final float spaceDim = 150; //150 is good
    public static final float backBoardTopLeftX = 20; //20 is good
    public static final float backBoardTopLeftY = 100; //100 is good
    public static final float topLeftX = backBoardTopLeftX + spaceDim / 2; //95 is good
    public static final float topLeftY = backBoardTopLeftY + spaceDim; //350 is good
    private int i, j; //iterators for all the loops
    private boolean pieceIsSelected = false;
    private boolean boardDrawn = false;


    private int row, col; //for iterating and managing the Pieces array

    public ShogiGui(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        BoardBackground = BitmapFactory.decodeResource(getResources(), R.drawable.shogi_board);
        BoardBackground = Bitmap.createScaledBitmap(BoardBackground, (int) (10 * spaceDim), (int) (10 * spaceDim + spaceDim), false);

        shogiPiece aPiece;
        String w = "";

        row = 6;
        for(col = 0; col < 9; col++){
            aPiece = new shogiPiece(row, col, "Pawn");
            Pieces[row][col] = aPiece;
        }

        col = 1;
        aPiece = new shogiPiece(row+1, col, "Bishop");
        Pieces[row+1][col] = aPiece;

        col = 7;
        aPiece = new shogiPiece(row+1, col, "Rook");
        Pieces[row+1][col] = aPiece;

        for(col = 0; col < 9; col++){
            if(col == 0 || col == 8){
                w = "Lance";
            }else if(col == 1 || col == 7){
                w = "Knight";
            }else if(col == 2 || col == 6){
                w = "Silver";
            }else if(col == 3 || col == 5){
                w = "Gold";
            }else{
                w = "King";
            }

            aPiece = new shogiPiece(row+2, col, w);
            Pieces[row+2][col] = aPiece;
        }
    }


    @Override
    public void onDraw(Canvas canvas) {

        //paint for the lines
        Paint BoardLine = new Paint();
        BoardLine.setColor(0xFF000000);
        BoardLine.setStrokeWidth(6f);

        //paint for circles
        Paint CirclePaint = new Paint();
        CirclePaint.setColor(0xFFFFFFFF);
        CirclePaint.setStyle(Paint.Style.FILL);


        //set touch listener for drawing pieces on the board
        this.setOnTouchListener(this);

        //draw Shogi board background
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(BoardBackground, backBoardTopLeftX, backBoardTopLeftY, null);

        //draw vertical lines; start xy is top point, end xy is bottom point
        for(i = 0; i < 10; i++) {
            canvas.drawLine(topLeftX + i * spaceDim, topLeftY, topLeftX + i * spaceDim, topLeftY + 9 * spaceDim, BoardLine);
            canvas.drawLine(topLeftX, topLeftY + i * spaceDim, topLeftX + 9 * spaceDim, topLeftY+ i * spaceDim, BoardLine);
        }

        //draw pieces
        for(i = 0; i < 9; i++) {
            for(j = 0; j < 9; j++){
                if(Pieces[i][j] != null){
                    //Pieces[i][j].changePlayer(false);
                    Pieces[i][j].drawShogiPiece(canvas);

                    if(Pieces[i][j].getSelected())
                        Pieces[i][j].drawMoves(canvas);
                }
            }
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        //dont do anything when dragging or lifting touch
        if(event.getActionMasked() != MotionEvent.ACTION_DOWN)
            return false;


        //determine space that was tapped
        for(row = 0; row < 9; row++) {
            if(event.getY() < topLeftY + (row + 1) * spaceDim) {
                for (col = 0; col < 9; col++) {
                    if(event.getX() < topLeftX + (col + 1) * spaceDim){
                        break;
                    }
                }
                break;
            }
        }


        //tapped space has no piece
        if(Pieces[row][col] == null) {
            if(pieceIsSelected){
                for(int i = 0; i < 9; i++){
                    for(int j = 0; j < 9; j++){
                        if(Pieces[i][j] != null){
                            if(Pieces[i][j].getSelected()){
                                Pieces[row][col] = new shogiPiece(row, col, Pieces[i][j].getPiece());
                                if(!Pieces[i][j].getPlayer()){
                                    Pieces[row][col].setPlayer(false);
                                }
                                Pieces[i][j] = null;
                            }
                        }
                    }
                }
                Pieces[row][col].setSelected(false);
                pieceIsSelected = false;
            }else {
                return false;
            }
        }else{
            //select piece if not selected yet, deselect it if selected
            if(Pieces[row][col].getSelected()){
                Pieces[row][col].setSelected(false);
                pieceIsSelected = false;
            }else{
                for(int i = 0; i < 9; i++){
                    for(int j = 0; j < 9; j++){
                        if(Pieces[i][j] != null){
                            if(Pieces[i][j].getSelected()){
                                Pieces[i][j].setSelected(false);
                            }
                        }
                    }
                }

                Pieces[row][col].setSelected(true);
                pieceIsSelected = true;
            }
        }




        //redraw board with pieces updated
        this.invalidate();

        return true;





        //------------------------------------------------------------------
        //This is the code for drawing a piece in any space that is tapped
        //------------------------------------------------------------------
        /*
        //when user presses down
        if(event.getActionMasked() == MotionEvent.ACTION_DOWN) {

            //determine space that was tapped
            for(row = 0; row < 9; row++)
            {
                if(event.getY() < topLeftY + (row + 1) * spaceDim) {

                    for (col = 0; col < 9; col++) {
                        if(event.getX() < topLeftX + (col + 1) * spaceDim)
                        {
                            break;
                        }
                    }
                    break;
                }
            }


            //new shogi piece
            ShogiPiece NewPiece = new ShogiPiece(topLeftX + col * spaceDim + spaceDim / 2, topLeftY + row * spaceDim + spaceDim / 2, "King");


            //assign newly created piece to Pieces array
            Pieces[row][col] = NewPiece;


            //redraw board with pieces updated
            this.invalidate();

            return true;
        }

        */
    }
}
