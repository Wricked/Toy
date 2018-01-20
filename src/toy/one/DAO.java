package toy.one;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DAO {
	
	/**
	 * Add a new toy to ArrayList<Beans>
	 * @param lstToy is the toy list containing every single detail of each toy.
	 */
	JPanel panel = new JPanel(new GridLayout());
	JLabel lbl = new JLabel();
	JTextField txt = new JTextField();
	JComboBox<Object> cate = new JComboBox<>(BussinessRules.chooseCategory().toArray());
	public void addToy(ArrayList<Beans> lstToy) throws Exception{
		String x = null;
		String y = null;
		int a = 1;
		int g = 0;
		
		do {
			if( g == 0){
				panel.add(lbl);
				txt.setText(null);
				lbl.setText("Put the name of the toy (3-50)");
				String j;
				do{
					j = JOptionPane.showInputDialog(null, lbl, "Toy's name", JOptionPane.QUESTION_MESSAGE);
					if ( j == null) {
						g=4;
					} else {
						if(BussinessRules.validateName(j)) {
							x = j;
							g++;
						}
					}
					
				}while(txt.getText().length()<3 && txt.getText().length()>51);
				
			} else if ( g == 1) {
				lbl.setText("Put the category of the toy");
				cate = new JComboBox<>(BussinessRules.chooseCategory().toArray());
				panel.add(lbl);
				panel.add(cate);
				
				int c = JOptionPane.showConfirmDialog(null, panel, "Toy's name", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				panel.remove(cate);
				
				if( c == JOptionPane.YES_OPTION) {
					y = (String) cate.getSelectedItem();
					g++;
				} else if (c == JOptionPane.NO_OPTION) {
					g--;					
				} else {
					g=4;
				}
			} else if ( g == 2) {
				lbl.setText("How many of this toy came?");
				panel.add(txt);
				txt.setText(null);
				int c;
				do{
					c = JOptionPane.showConfirmDialog(null, panel, "Toy's name", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					panel.remove(txt);
					
					if( c == JOptionPane.YES_OPTION) {
						if( BussinessRules.validateStock(txt.getText())) {
							a = Integer.parseInt(txt.getText());
							g++;
						}
					} else if( c == JOptionPane.NO_OPTION ) {
						g--;
					} else {
						g=4;
					}
				}while(a>5000 && a<0);
			}
		}while( g < 3);
		if(g == 3) {
			lstToy.add(new Beans(x,y,a));
		
			JOptionPane.showMessageDialog(null, "Toy added successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Operation cancelled.", "Information", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	/**
	 * Removes toy from a list, including a decreasing from another ID from the list who would succeed.
	 * @param lstToy is the toy list containing every single detail of each toy.
	 */
	public void deleteToy(ArrayList<Beans> lstToy) throws Exception{
		if(!BussinessRules.isEmpty(lstToy)) {
			String[] options = {"Name", "ID", "Cancel"};
			panel.add(lbl);
			lbl.setText("You can either delete your toy by it's name, or by it's ID. Press cancel to exit.");
			
			int op = JOptionPane.showOptionDialog(null, lbl, "Choose a type", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, 
					null, options, null);
			do{
				if(op == 0){
					
					String name = JOptionPane.showInputDialog(null, "Type the name of the toy", "Name selection"
							,JOptionPane.QUESTION_MESSAGE);
					
					for(int a = 0; a < lstToy.size(); a++ ){
						
						Beans beansAux = lstToy.get(a);
						if (beansAux.getName().equals(name)){
							lstToy.remove(a);
							JOptionPane.showMessageDialog(null, "Toy deleted successfuly!\n" + beansAux.getName(), "Toy erased", JOptionPane.INFORMATION_MESSAGE);
							break;
						}
					}
				} if (op == 1){
					
					int i = BussinessRules.chooseId(lstToy);
					for(int a = 0; a < lstToy.size(); a++ ) {
						if( i == a) {
							lstToy.remove(i);
							JOptionPane.showMessageDialog(null, "Toy deleted successfuly!\n", 
									"Toy erased", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				} else if ( op == 2){
					
					JOptionPane.showMessageDialog(null, "Operation cancelled.", "Information", JOptionPane.INFORMATION_MESSAGE);
				}
			}while(op<0 && op>2);
		}
	}
	/**
	 * Change either a name or type from a specific toy
	 * @param lstToy is the toy list containing every single detail of each toy.
	 */
	public void changeToy(ArrayList<Beans> lstToy) throws Exception{
		if(!BussinessRules.isEmpty(lstToy)) {
			int i = BussinessRules.chooseId(lstToy);
			if ( i > -1 && i < lstToy.size()){
				int asw = 0;
				String[] options = {"Name", "Type", "Stock", "Cancel"};
				
				panel.add(lbl);
				Beans bea = lstToy.get(i);
				
				while(asw != 3){
					lbl.setText("Please, choose what you like to modify, press Cancel to exit.");
					asw = JOptionPane.showOptionDialog(null, panel, "Choose a option", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, 
							null, options, options[0]);
					if(asw == 0){
						String rename;
						do {
							rename = JOptionPane.showInputDialog(null, "The current toy name is: " + bea.getName()+ 
									"\nPlease, put the new value:", "Renaming " + bea.getName(), JOptionPane.QUESTION_MESSAGE);
							if(rename == null){
								asw = 4;
							}
							else { 
								if(BussinessRules.validateName(rename)) {
									bea.setName(rename);
									JOptionPane.showMessageDialog(null, "Toy name updated to: " + bea.getName(), "Name updated successfully! ",
											JOptionPane.INFORMATION_MESSAGE);
									asw = 4;
								}
							}
						}while (asw != 4);
					}else if ( asw == 1) {
						JPanel painel = new JPanel();
						
						JLabel texto = new JLabel("<html>The current toy type is: " + bea.getType() + " <br>Please, put the new value:</html>");
						painel.add(texto);
						painel.add(cate);
						int c = JOptionPane.showOptionDialog(null, painel, 
								"Type selection of " + bea.getName(), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
								null, new String[] {"OK", "Cancel"}, null);
						bea.setType(cate.getSelectedItem().toString());
						if(c == 0)
							JOptionPane.showMessageDialog(null, "Type updated for: " + bea.getName() +"\nNew type: " + bea.getType(),
								"Toy update", JOptionPane.INFORMATION_MESSAGE);
						else
							asw = 4;
					} else if ( asw == 2) {
						String stock;
						do {
							stock = JOptionPane.showInputDialog(null, "The current toy stock is: " + bea.getStock() + "\nPut a new value: ", "Stock value", 
									JOptionPane.QUESTION_MESSAGE);
							if(stock == null)
								asw = 4;
							else 
								if(BussinessRules.validateStock(stock)) {
									bea.setStock(Integer.parseInt(stock));
									JOptionPane.showMessageDialog(null, "Stock updated for: " + bea.getName() + "\nNew Value: " + bea.getStock(),
											"Stock update", JOptionPane.INFORMATION_MESSAGE);
									asw = 4;
								}
						}while(asw != 4	);
					} else if( asw == 3){
						JOptionPane.showMessageDialog(null, "Operation cancelled.", "Information", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}
	/**
	 * Shows to the user a list containing each toy specifications
	 * @param lstToy is the toy containing every single detail of each toy.
	 */
	public void showList(ArrayList<Beans> lstToy) throws Exception{
		if(!BussinessRules.isEmpty(lstToy)) {
			String[] options = {"Next", "Previous", "Go to", "Cancel"};
			JPanel painel = new JPanel(new BorderLayout());
			painel.add(lbl,BorderLayout.WEST);
			painel.add(txt,BorderLayout.SOUTH);
			int b = 0;
			int i = 0;
			do{
				Beans beansAux = lstToy.get(i);
				lbl.setText("<html>ID: " + i + "<br>Name : " + beansAux.getName() + "<br>Type: " 
						+ beansAux.getType() + " <br>Stock: " + beansAux.getStock() + "<br></html>");
				b = JOptionPane.showOptionDialog(null, painel, "List " + i + " of " + (lstToy.size() - 1) ,JOptionPane.YES_NO_CANCEL_OPTION, 
						JOptionPane.INFORMATION_MESSAGE, null, options , null);
				if(b == 0){
					i++;
					if(i==(lstToy.size()-1))
						JOptionPane.showMessageDialog(null, "Last toy from the list");
				} else if ( b == 1) {
					if(i==0)
						JOptionPane.showMessageDialog(null, "You already are on first toy of this list!", 
								"Error!", JOptionPane.ERROR_MESSAGE);
					else {
						i--;
					}
				} else if ( b == 2) {
					if(!txt.getText().isEmpty()) {
						int go = Integer.parseInt(txt.getText());
						if(go < lstToy.size() && go > -1) {
							if( BussinessRules.stringToInt(txt.getText()))
								i = go;
						} else {
							JOptionPane.showMessageDialog(null, "It's more than the array list size!", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					}
				} else if ( b == 3){
					b = -1;
				}
			}while(b<lstToy.size() && b > -1);
			painel.removeAll();
		}
	}
	/**
	 * Saves the current toy list into a .txt file.
	 * @param lstToy is the toy containing every single detail of each toy.
	 */
	public void save(ArrayList<Beans> lstToy){
		Beans bean = new Beans();
		Writer writter = null;
		
		try{
			
			File file = new File("Toy.txt");
			writter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));

			for(int ini = 0; ini < lstToy.size(); ini++){
				
				bean = lstToy.get(ini);
				writter.write(bean.getName());
				((BufferedWriter) writter).newLine();
				writter.write(bean.getType());
				((BufferedWriter) writter).newLine();
				writter.write(String.valueOf(bean.getStock()));
				((BufferedWriter) writter).newLine();
				
			}
			
			JOptionPane.showMessageDialog(null, "File saved successfully!", "Confirmation",JOptionPane.INFORMATION_MESSAGE);
			
		} catch (IOException e) {
			
		   JOptionPane.showMessageDialog(null, "Error during save the file.\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		   e.printStackTrace();
		   
		} finally {
			   try {
				   
				   writter.close();
				   
			   } catch (Exception ex) {
				   
				   ex.printStackTrace();
				   
			   }
		}
	}
	/**
	 * Loads the toy.exe file located, reading every line and putting into a List.
	 * @param lstToy List of Toy which will be loaded with information.
	 * @throws Exception Catches an Exception and throws it to main file.
	 * @return Returns the list updated with toys.
	 */
	public List<Beans> load(ArrayList<Beans> lstToy) {
		try {
			lstToy.clear();
			BufferedReader in = new BufferedReader(new FileReader("Toy.txt"));
			String a;
			String b;
			String c;
			while((a = in.readLine()) != null && (b = in.readLine()) != null && (c = in.readLine()) != null){
				lstToy.add(new Beans (a,b,Integer.parseInt(c)));
			}
			JOptionPane.showMessageDialog(null, "File loaded successfully!", "Confirmation",JOptionPane.INFORMATION_MESSAGE);
			in.close();
			return lstToy;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error loading the file.\n" + e.getMessage() + "\nTry creating or moving the toy.txt to same folder as .jar file",
					"Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
}