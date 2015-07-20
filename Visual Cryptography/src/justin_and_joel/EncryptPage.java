/*
Visual Cryptography Project

Copyright (c) 2015 Justin Blackmon and Joel Bush

For licensing information refer to LICENSE.md

This project is a Java application that utilizes visual cryptography techniques to encrypt and decrypt images.
*/

package justin_and_joel;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EncryptPage extends JFrame {
	
	private boolean imageFlag;
	JRadioButton rdbtnImage;
	JRadioButton rdbtnText;
	JButton btnOriginal;
	JButton btnModified;
	JTextArea textArea;
	JButton btnEncrypt;
	JButton btnClear;

	private JPanel contentPane;

	/**
	 * Launch the application .
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EncryptPage frame = new EncryptPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EncryptPage() {
		
		imageFlag = false;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Create Encrypted Images");
		lblTitle.setBounds(138, 12, 192, 15);
		contentPane.add(lblTitle);
		
		rdbtnImage = new JRadioButton("Image");
		rdbtnImage.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				imageFlag = true;
				System.out.println(imageFlag);
				rdbtnText.setSelected(false);
			}
		});
		rdbtnImage.setBounds(8, 35, 85, 23);
		contentPane.add(rdbtnImage);
		
		rdbtnText = new JRadioButton("Text");	
		rdbtnText.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				imageFlag = false;
				System.out.println(imageFlag);
				rdbtnImage.setSelected(false);
			}
		});
		
		rdbtnText.setBounds(91, 35, 79, 23);
		contentPane.add(rdbtnText);
		
		btnOriginal = new JButton("Original");
		btnOriginal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(imageFlag == false){
					System.out.println("Select image radio button");
					return;
				}
				else{
					
					Main.path = ImageFunctions.GetPathName();
					Main.file = new File(Main.path);

					
					Main.originalImage = ImageFunctions.Display(Main.file);
					
					
					
					
					// All items below are a demo solution to expanding the initial image to an image 4 times as big
					int width = Main.originalImage.getWidth();
					int height = Main.originalImage.getHeight();
					
					BufferedImage expanded = new BufferedImage(
					        width*2, height*2,
					        BufferedImage.TYPE_BYTE_BINARY);
					
					System.out.println(Main.originalImage.getHeight());
					System.out.println(expanded.getHeight());
					System.out.println(Main.originalImage.getRGB(0, 0));
					
					for( int i = 0; i < height; i++) {
						for( int j = 0; j < width; j++) {
							if (Main.originalImage.getRGB(j, i) != -1) {
								expanded.setRGB(j*2, i*2, 255);
							}
							else {
								expanded.setRGB(j*2,  i*2, -1);
							}
						}
					}
					
					JLabel exp = new JLabel(new ImageIcon(expanded));
					JFrame temp = new JFrame();
					temp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					temp.getContentPane().add(exp);
					temp.pack();
					temp.setLocation(600,200);
					temp.setVisible(true);
				}
			}
		});
		btnOriginal.setBounds(12, 66, 117, 25);
		contentPane.add(btnOriginal);
		
		btnModified = new JButton("Modified");
		btnModified.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(imageFlag == false){
					System.out.println("Select image radio button");
					return;
				}
				else{
					Main.save_path = ImageFunctions.GetPathName();
					
					
				}
			}
		});
		btnModified.setBounds(12, 100, 117, 25);
		contentPane.add(btnModified);
		
		JLabel lblMessage = new JLabel("Message:");
		lblMessage.setBounds(12, 137, 70, 15);
		contentPane.add(lblMessage);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(12, 164, 192, 79);
		contentPane.add(textArea);
		
		btnEncrypt = new JButton("Encrypt");
		btnEncrypt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String text = textArea.getText();
				if(imageFlag == false && text.equals("")){
					System.out.println("Enter some text");
					
				}
				else if(imageFlag == false && !text.equals("")){
					//Add function to convert text to image
					
				}
				else if(imageFlag == true){
					//Add function for encrypting
					//BufferedImage black_white = ImageFunctions.EncryptImage(Main.save_path, Main.originalImage);
					BufferedImage black_white = new BufferedImage(
					        Main.originalImage.getWidth(), Main.originalImage.getHeight(),
					        BufferedImage.TYPE_BYTE_BINARY);
					    
					Graphics2D graphics = black_white.createGraphics();
					graphics.drawImage(Main.originalImage, 0, 0, null);

					
					Main.output_file = new File(Main.save_path);
					ImageFunctions.Save(black_white, Main.output_file);
					ImageFunctions.Display(Main.output_file);
					
				}
			}
		});
		btnEncrypt.setBounds(12, 255, 97, 25);
		contentPane.add(btnEncrypt);
		
		btnClear = new JButton("Clear");
		btnClear.setBounds(121, 255, 79, 25);
		contentPane.add(btnClear);
		
		
		
	}
	
	
}
