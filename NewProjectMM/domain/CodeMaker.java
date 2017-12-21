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

    private CtrlDominio ctrlDominio;

    public CodeMaker(CtrlDominio controladorDominio) {
        this.ctrlDominio = controladorDominio;
	}

	public String createCode() {
		String code = randomCode();
		System.out.println(code);
		return code;
	}

	private String randomCode() {
        int random = (int)(Math.floor(ctrlDominio.answerMatrix[0].length * Math.random()));
        return ctrlDominio.answerMatrix[0][random];
	}

	/*
	  // Test Method
	public static void main(String[] args) {
		CodeMaker codemaker = new CodeMaker(4,6,false);
	}
	*/
}
