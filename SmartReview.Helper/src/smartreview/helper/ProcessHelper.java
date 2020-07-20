/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartreview.helper;

import java.io.IOException;

/**
 *
 * @author TNT
 */
public class ProcessHelper {

    public static Process startParser(String location, String args) throws IOException {
        String disk = location.substring(0, location.indexOf("\\"));
        String workDir = location.substring(0, location.lastIndexOf("\\"));
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", disk + " && cd " + workDir + " && java -jar " + location + " " + args);
//        ProcessBuilder builder = new ProcessBuilder(
//                "cmd.exe", "/c", "start", "cmd.exe", "/K", "\"" + disk + " && cd " + workDir + " && java -jar " + location + " " + args + "\"");
        Process p = builder.start();
        return p;
    }
}
