package domain;

/*
	Usage

	CodeMaker(int width, int nColors, boolean repetition);
	  Creates an instance of the class CodeMaker with the given parameters.

	TODO createCode(String difficulty);
	  Creates a code depending on the given difficulty.
	  This definition as well as the parameters are subject to change.
*/

public class CodeMaker extends Algorithm {

	public CodeMaker(int width, int nColors, boolean repetition) {
		this.width = width;
		this.nColors = nColors;
		this.repetition = repetition;
	}

	public String createCode(String difficulty) {
		String codeOut = "";
		if ( difficulty.equals("EASY") || difficulty.equals("MEDIUM") ||  difficulty.equals("HARD") ) { codeOut = easyCode(); }
		else if ( difficulty.endsWith("HARDCODER") ) { codeOut = "AABB"; }
		System.out.println(codeOut);
		return codeOut;
	}

	private String easyCode() {
		String code = "";
		for (int i = 0; i < width; ++i) {
			int j = (int)(Math.floor(nColors * Math.random()));
			code += (char)('A' + j);
		}
		return code;
	}

	/*
	  // Test Method
	public static void main(String[] args) {
		CodeMaker codemaker = new CodeMaker(4,6,false);
	}
	*/
}