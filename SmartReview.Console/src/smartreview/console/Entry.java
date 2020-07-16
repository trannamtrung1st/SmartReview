/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.console;

import java.io.IOException;
import java.util.Scanner;
import smartreview.helper.XMLHelper;

/**
 *
 * @author TNT
 */
public class Entry {

    public static void main(String[] args) throws IOException {
        generateCodeFromSchema();
    }

    static void generateCodeFromSchema() throws IOException {
        System.out.println("Input output, package name, xsd path (Enter to submit): ");
        String output = new Scanner(System.in).nextLine();
        String forcePkgName = new Scanner(System.in).nextLine();
        String xsd = new Scanner(System.in).nextLine();
        XMLHelper.generateCodeFromSchema(output, forcePkgName, xsd, null);
    }
}
