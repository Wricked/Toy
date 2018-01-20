package toy.one;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BussinessRules {
	public BussinessRules() {}
	public static int chooseId(ArrayList<Beans> lstToy) {
		JPanel panel = new JPanel();
		JLabel lbl = new JLabel("Choose the desired ID. \n (0-" + (lstToy.size()-1) + "):");
		JTextField txt = new JTextField(10);
		panel.add(lbl);
		panel.add(txt);
		do {
			int i = JOptionPane.showConfirmDialog(null, panel, "Input", JOptionPane.OK_CANCEL_OPTION);
			if(i == JOptionPane.OK_OPTION) { 
				if(BussinessRules.validateNumber(txt.getText()))
				{
					int opt = Integer.parseInt(txt.getText());
					return opt;
				}
			}else if (i == JOptionPane.CANCEL_OPTION){
				JOptionPane.showMessageDialog(null, "Operation cancelled.", "Information", JOptionPane.INFORMATION_MESSAGE);
				return -1;
			}
		}while(!txt.getText().matches("[0-9]+"));
		return -1;
	}
	
	public static ArrayList<Object> chooseCategory(){

		ArrayList<Object> cat = new ArrayList<Object>();
		try{
			
			BufferedReader in = new BufferedReader(new FileReader("Categories.txt"));
						
			String line;
					
			while((line = in.readLine()) != null){
				cat.add(line);
			}
			
			in.close();
			return cat;
			
		} catch (Exception e) {

			File file = new File("Categories.txt");
			Writer writter;
			try {
				writter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
				writter.write("Doll");
				((BufferedWriter) writter).newLine();
				writter.write("Car");
				((BufferedWriter) writter).newLine();
				writter.write("Ball");
				((BufferedWriter) writter).newLine();
				writter.write("Bear");
				((BufferedWriter) writter).newLine();
				writter.write("Lego");
				((BufferedWriter) writter).newLine();
				writter.write("Plane");
				((BufferedWriter) writter).newLine();
				writter.write("Puzzle");
				((BufferedWriter) writter).newLine();
				writter.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			return cat;
			
		}
	}
	
	public static boolean isEmpty(ArrayList<Beans> lstToy) {
		if(lstToy.isEmpty()) {
			JOptionPane.showMessageDialog(null, "The toy's list is empty!");
			return true;
		}
		return false;
	}
	
	public static boolean validateName (String name) {
		if(name == null)
			return false;
		else if(name.length()>51) {
			JOptionPane.showMessageDialog(null, "Too many characters!","Error in Length",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(name.length()<3) {
			JOptionPane.showMessageDialog(null, "At least 3 characters!","Error in Length",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public static boolean validateStock (String stock) {
		if(BussinessRules.stringToInt(stock)) {
			if(Integer.parseInt(stock)>5001 || Integer.parseInt(stock)<1) {
				JOptionPane.showMessageDialog(null, "Invalid toy stock! Must be between 1-5000.", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			return false;
		}
		return true;
	}
	
	public static boolean stringToInt(String string) {
		if(string == null)
			return false;
	    try {
	        Integer.parseInt(string);
	        return true;
	    }
	    catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Not a number! Please enter a number.");
	        return false;
	    }
	}
	
	public static boolean validateNumber(String input) {
		if (!input.matches("[0-9]+")) {
			JOptionPane.showMessageDialog(null, "It's not a number","No number",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public static JLabel imageLabel(String pathToFile) {
		ImageIcon icon = new ImageIcon(pathToFile); 
		JLabel thumb = new JLabel();
		thumb.setIcon(icon);
		return thumb;
	}
	
}