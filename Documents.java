import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.*;

public class Documents {
	private static JTextField txtFileName;
	private static JTextField textField;
	
    private static void createAndShowGUI() {
    	
        JFrame jFrame = new JFrame("documents");
        jFrame.getContentPane().setLayout(null);//new FlowLayout());
        jFrame.setBounds(0, 0, 589, 400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
        //////////////////////////////////////////////////////////
        ///////////////////// Buttons Below  /////////////////////
        //////////////////////////////////////////////////////////
        
        
		/////////////////////// Page Label ///////////////////////
		JLabel pageLabel = new JLabel("DOCUMENTS");
		pageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pageLabel.setBounds(57, 30, 115, 34);
		jFrame.getContentPane().add(pageLabel);
		
		
		//File name label
		JLabel fileLabel = new JLabel("File Name");
		fileLabel.setHorizontalAlignment(SwingConstants.LEFT);
		fileLabel.setBounds(271, 53, 73, 20);
		jFrame.getContentPane().add(fileLabel);
				
		//Small Text Field
		txtFileName = new JTextField("Lorem Generated");
		txtFileName.setBounds(271, 72, 275, 20);
		jFrame.getContentPane().add(txtFileName);
		txtFileName.setColumns(10);
				
		
		//File content label
		JLabel contLabel = new JLabel("File Content");
		contLabel.setHorizontalAlignment(SwingConstants.LEFT);
		contLabel.setBounds(271, 103, 73, 20);
		jFrame.getContentPane().add(contLabel);
				
		//Big Text Field
		JTextArea textArea = new JTextArea("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
		textArea.setLineWrap(true);
		textArea.setBounds(271, 120, 275, 168);
		jFrame.getContentPane().add(textArea);

		
		//Status label
		JLabel stat = new JLabel("Operation Status");
		stat.setHorizontalAlignment(SwingConstants.LEFT);
		stat.setBounds(271, 291, 115, 20);
		jFrame.getContentPane().add(stat);
		
		//Status text field
		textField = new JTextField("...");
		textField.setColumns(10);
		textField.setBounds(271, 309, 275, 20);
		jFrame.getContentPane().add(textField);
        
        
        /////////////////// Create New Button ///////////////////
        JButton newDoc = new JButton("Add/Edit Document");
		newDoc.addActionListener(
        new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		
        		String status = "Operation \"Create New\" failed.";
        		
        		try {
        			//Retrieve file name from text
        			String name = txtFileName.getText();
        			String filename;
        			
        			if (name.contains(".txt"))
        			{
        				filename = name;
        			}
        			else
        			{
        				filename = name + ".txt";
        			}
        			        			
        			//Create new file using given file name
        			FileWriter dir = new FileWriter(filename);
                    BufferedWriter buffer = new BufferedWriter(dir);
                    
                    //Write to the file from what's in the textbox
                    buffer.write(textArea.getText());
                    buffer.newLine();
                    buffer.close();
                    
                    //Success message
                    status = filename + " successfully created.";
                    
                    //Reflect status message
                    textField.setText(status);
                    
                    buffer.close();
                } catch (Exception z) {
                	
                    System.err.format(status);
                }

            }
        });
		newDoc.setFont(new Font("Tahoma", Font.BOLD, 18));
		newDoc.setBounds(10, 90, 214, 47);
		jFrame.getContentPane().add(newDoc);

		
		/////////////////// Open Doc Button ///////////////////
		JButton openDoc = new JButton("Open Document");
		openDoc.addActionListener(
		  new ActionListener(){
		  	public void actionPerformed(ActionEvent e){
		  		
		  		String status = "Operation \"Edit\" failed.";
		  		
		  		try
		  		{        			
		  			//Retrieve file name from text
        			String name = txtFileName.getText();
        			String filename;
        			if (name.contains(".txt"))
        			{
        				filename = name;
        			}
        			else
        			{
        				filename = name + ".txt";
        			}
        			        			
        			String dir = System.getProperty("user.dir");
        			String dirFull = dir + "/" + filename;      	
        			
        			//Create new file using given file name
        			BufferedReader buffer = new BufferedReader(new FileReader(dirFull));
                    
                    //Read in text from file
                    String st;
                    String all = "";
                    
                    while ((st = buffer.readLine()) != null)
                    {
                    	all = all + "\n" + st;
                    }         
                    
                    //Show text from file in textbox
                    textArea.setText(all);
                    
                    //Update status
                    status = filename + " successfully opened.";
                    textField.setText(status);
                    
                } catch (Exception z) {
                	
                    System.err.format(status);
                }
		
		      }
		  });
		openDoc.setFont(new Font("Tahoma", Font.BOLD, 18));
		openDoc.setBounds(10, 148, 214, 47);
		jFrame.getContentPane().add(openDoc);
		
		
		/////////////////// Delete Doc Button ///////////////////
		JButton delDoc = new JButton("Delete Document");
		delDoc.addActionListener(
	        new ActionListener(){
	        	public void actionPerformed(ActionEvent e){
	        		
	        		String status = "Operation \"Delete\" failed.";
	        		String empty = "";
	        		
	        		try
	        		{
	        			//Retrieve file name from text
	        			String name = txtFileName.getText();
	        			String filename;
	        			if (name.contains(".txt"))
	        			{
	        				filename = name;
	        			}
	        			else
	        			{
	        				filename = name + ".txt";
	        			}
	        			
	        			//Delete file
	        			File file = new File(filename);
	        			if (file.delete())
	        			{
	        				status = filename + " successfully deleted.";
	        			}
	        			
	        			//Empty textarea just because
	        			textArea.setText(empty);
	        			
	        			//Reflect status message
	                    textField.setText(status);
	        			
	        			
	                } catch (Exception z) {
	                    // TODO Auto-generated catch block
	                    System.err.println(status);
	                }

	            }
	        });
		delDoc.setFont(new Font("Tahoma", Font.BOLD, 18));
		delDoc.setBounds(10, 206, 214, 47);
		jFrame.getContentPane().add(delDoc);
		
		
		/////////////////// Show Directory Button ///////////////////
		JButton showDir = new JButton("Show Files");
		showDir.addActionListener(
	        new ActionListener(){
	        	public void actionPerformed(ActionEvent e){
	        		
	        		String status = "Operation \"Show Files\" failed.";
	        		
	        		try
	        		{
	        			//Retrieve file name from text
	        			//Retrieve file name from text
	        			String name = txtFileName.getText();
	        			String filename;
	        			
	        			if (name.contains(".txt"))
	        			{
	        				filename = name;
	        			}
	        			else
	        			{
	        				filename = name + ".txt";
	        			}
	        			Path curr = Paths.get("");
	        			String directory = curr.toAbsolutePath().toString();
	        			File dirPath = new File(directory);
	        			
	        			//List files in directory
	        			String allFiles[] = dirPath.list();
	        			String listed = "";
	        			for (int i = 0; i < allFiles.length; i++)
	        			{
	        				if (allFiles[i].contains(".txt"))
	        				{
	        					listed = listed + allFiles[i] + "\n";
	        				}
	        				
	        			}
	        			if(listed == "")
	        			{
	        				textArea.setText("No text files in directory.");
	        			}
	        			else
	        			{
	        				textArea.setText(listed);
	        			}
	        			
	        			//Set file name to empty just because
	        			txtFileName.setText("");
	        			
	        			//Have status reflect success
	        			status = "Showing text files in directory...";
	        			
	        			textField.setText(status);
	        			
	                } catch (Exception z) {
	                    System.err.println(status);
	                }

	            }
	        });
		showDir.setFont(new Font("Tahoma", Font.BOLD, 18));
		showDir.setBounds(10, 264, 214, 47);
		jFrame.getContentPane().add(showDir);
		
		JLabel lblNewLabel = new JLabel("Fill in the text fields");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 56, 214, 20);
		jFrame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("before clicking a button");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 75, 214, 14);
		jFrame.getContentPane().add(lblNewLabel_1);
		
		JLabel pageLabel_1 = new JLabel("______________");
		pageLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		pageLabel_1.setBounds(57, 35, 115, 34);
		jFrame.getContentPane().add(pageLabel_1);
		
		
		
		
		//
	    jFrame.setVisible(true);
	    
    }
    
	public static void main(String[] args) {
    createAndShowGUI();
    
	}
}
