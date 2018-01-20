package toy.one;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Toy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UIManager.put("OptionPane.yesButtonText", "Next");
		UIManager.put("OptionPane.noButtonText", "Previous");
		
		ArrayList<Beans> toy = new ArrayList<Beans>();
		DAO dao = new DAO();
		
		int d = 0;
		
		dao.load(toy);
		
		JTextField txt = new JTextField(5);
		JPanel panel = new JPanel(new BorderLayout());
		
		panel.add(new JLabel("<html>Hello! Welcome to our toy store, digit a number below to perform an action:<br>1. Add;<br>2. Delete;<br>"
				+ "3. Change;<br>4. Show Toy list;<br>5. Save;<br>6. Load.<br></html>"), BorderLayout.CENTER);
		panel.add(txt, BorderLayout.AFTER_LAST_LINE);
		
		

		try{
			
			while (txt.getText() != "7"){
				
				d = JOptionPane.showOptionDialog(null, panel, "Welcome", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
						null, new String[] {"OK", "Cancel"}, null);
			if(d == 0) {	
				switch(txt.getText()){
					case "1": 
						dao.addToy(toy);
						break;
					case "2":
						dao.deleteToy(toy);
						break;
					case "3":
						dao.changeToy(toy);
						break;
					case "4":
						dao.showList(toy);
						break;
					case "5":
						dao.save(toy);
						break;
					case "6":
						dao.load(toy);
						break;
					default:
						JOptionPane.showMessageDialog(null, "Invalid option!");
						break;
					}
				} else {
					int i = JOptionPane.showOptionDialog(null, "Do you wish to save before exit?", "Save", JOptionPane.OK_CANCEL_OPTION, 
							JOptionPane.QUESTION_MESSAGE, null, new String[] {"Yes","No"}, null);
					if(i == 0)
						dao.save(toy);
					System.exit(0);
				}
			}
		} catch (Exception e){
			
			e.printStackTrace();
			
		}
	}
}
