/*
Visual Cryptography Project

Copyright (c) 2015 Justin Blackmon and Joel Bush

For licensing information refer to LICENSE.md

This project is a Java application that utilizes visual cryptography techniques to encrypt and decrypt images.
*/

package justin_and_joel;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.stream.ImageOutputStream;

public class Main {

	/**
	 * @param args
	 */
	
	static String path; //Holds current file path
	static String save_path; // Saved file path
	static File file; //Holds current open file
	static File output_file;
	static BufferedImage originalImage;
	
	public static void main(String[] args) {
		System.out.println("Hello World");
		System.out.println("Hello World again");
		System.out.println("Hello World Once again");
		System.out.println("Final Hello World Once again");
		System.out.println("Most final Hello World");
		FirstPage.main(null);
		

	}

}
