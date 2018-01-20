package toy.one;

import javax.swing.JOptionPane;

public class Exc extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Throws Exception for the final user
	 * @param e Handles of which type of Exception it is
	 */
	public static void handleException(Exception e) {
		if(e instanceof NumberFormatException) {
			JOptionPane.showMessageDialog(null, "You must put a number, not a character","Error",JOptionPane.ERROR_MESSAGE);
		}else if(e instanceof IndexOutOfBoundsException) {
			JOptionPane.showMessageDialog(null, "Invalid number","Error",JOptionPane.ERROR_MESSAGE);
		}else if(e instanceof ArrayIndexOutOfBoundsException) {
			JOptionPane.showMessageDialog(null, "Invalid ","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
}
