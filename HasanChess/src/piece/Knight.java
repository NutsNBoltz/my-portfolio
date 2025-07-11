package piece;

import main.GamePanel;
import main.Type;

public class Knight extends Piece{

	public Knight(int color, int col, int row) {
		super(color, col, row);
		
		type = Type.KNIGHT;
				
		if (color == GamePanel.WHITE) {
			image = getImage("/piece/w-knight");
		}
		else {
			image = getImage("/piece/b-knight");
		}
		
		// TODO Auto-generated constructor stub
	}
	public boolean canMove(int targetCol, int targetRow) {
		
		if (isWithinBoard(targetCol, targetRow)) {
			
			if (Math.abs(targetRow-preRow) * Math.abs(targetCol-preCol) == 2) {
				if (isValidSquare(targetCol, targetRow)) {
					return true;
				}
			}
		}
		
		return false;
	}

}
