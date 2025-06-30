package piece;

import main.GamePanel;
import main.Type;

public class Pawn extends Piece{

	public Pawn(int color, int col, int row) {
		super(color, col, row);
		
		type = Type.PAWN;
		
		if (color == GamePanel.WHITE) {
			image = getImage("/piece/w-pawn");
		}
		else {
			image = getImage("/piece/b-pawn");
		}
		
		// TODO Auto-generated constructor stub
	}
	
	public boolean canMove(int targetCol, int targetRow) {
		
		if (isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow) == false) {
			
			int moveValue;
			
			if (color == GamePanel.WHITE) {
				moveValue = -1;
			}
			else {
				moveValue = +1;
			}
			
			// Gets hitting Piece
			hittingP = getHittingP(targetCol, targetRow);
			
			// 1 square movement
			if (targetCol == preCol && targetRow == preRow + moveValue && hittingP == null) {
				return true;
			}
			
			if (targetCol == preCol && targetRow == preRow + (2*moveValue) && hittingP == null && moved == false &&
					pieceIsOnStraightLine(targetCol, targetRow) == false) {
				return true;
			}
			
			// diagonal movement and capturing
			if (Math.abs(targetCol - preCol) == 1 & targetRow == preRow + moveValue && hittingP != null && hittingP.color != color) {
				return true;
			}
			
			// En Passant
			if (Math.abs(targetCol - preCol) == 1 & targetRow == preRow + moveValue) {
				for (Piece piece : GamePanel.simPieces) {
					if (piece.col == targetCol && piece.row == preRow && piece.twoStepped == true) {
						hittingP = piece;
						return true;
					}
				}
			}
			
		}
		
		
		return false;
	}
	
}
