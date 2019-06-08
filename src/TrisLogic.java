import java.io.Serializable;

public class TrisLogic implements Serializable {

	private static final long serialVersionUID = 1L;
	private String[][] trisLogic= {{"N", "N", "N"},
					 			   {"N", "N", "N"},
					               {"N", "N", "N"}};
	
	public TrisLogic() {
		
	}
	
	public boolean putSign(int x, int y, String sign) {
		trisLogic[x][y] = sign;
		return checkResult();
	}
	
	private boolean checkResult() {
		for(int i=0; i<3; i++) {
			if(("X".equals(trisLogic[i][0])) || ("O".equals(trisLogic[i][0])) &&
			   ("X".equals(trisLogic[i][1])) || ("O".equals(trisLogic[i][1])) &&
			   ("X".equals(trisLogic[i][2])) || ("O".equals(trisLogic[i][2]))) {
				if(trisLogic[i][0].equals(trisLogic[i][1]) && trisLogic[i][1].equals(trisLogic[i][2])) {
					return true;
				}
			}	
		}
		for(int j=0; j<3; j++) {
			if(("X".equals(trisLogic[0][j])) || ("O".equals(trisLogic[0][j])) &&
			   ("X".equals(trisLogic[1][j])) || ("O".equals(trisLogic[1][j])) &&
			   ("X".equals(trisLogic[2][j])) || ("O".equals(trisLogic[2][j]))) {
				if(trisLogic[0][j].equals(trisLogic[1][j]) && trisLogic[1][j].equals(trisLogic[2][j])) {
					return true;
				}
			}
		}
		if(("X".equals(trisLogic[0][0])) || ("O".equals(trisLogic[0][0])) &&
		   ("X".equals(trisLogic[1][1])) || ("O".equals(trisLogic[1][1])) &&
		   ("X".equals(trisLogic[2][2])) || ("O".equals(trisLogic[2][2]))) {
			if(trisLogic[0][0].equals(trisLogic[1][1]) && trisLogic[1][1].equals(trisLogic[2][2])) {
				return true;
			}
		}
		if(("X".equals(trisLogic[0][2])) || ("O".equals(trisLogic[0][2])) &&
		   ("X".equals(trisLogic[1][1])) || ("O".equals(trisLogic[1][1])) &&
		   ("X".equals(trisLogic[2][0])) || ("O".equals(trisLogic[2][0]))) {
			if(trisLogic[0][2].equals(trisLogic[1][1]) && trisLogic[1][1].equals(trisLogic[2][0])) {
				return true;
			}
		}
		return false;
	}
}
