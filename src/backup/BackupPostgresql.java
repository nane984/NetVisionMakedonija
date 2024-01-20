/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backup;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author branko.scekic
 */
public class BackupPostgresql {

    public void deleteOld(String databaseName) {
        
        System.out.println("pocetak  " );

        //File dir = new File("C:/dbbackup");
        File dir = new File(System.getProperty("user.home") + File.separator + "backup_" + databaseName);

        for (File file : dir.listFiles()) {
            if (file.getName().equals("backup_betonara_db_" + getYesterdayDateString() + ".sql") || file.getName().equals("Report folder")) {
                //do nothing
            } else {
                //delete file
                file.delete();
            }
        }
    }

    public void makeBackup(String databaseName, String databasePassword) {
        File backupFilePath = new File(System.getProperty("user.home") + File.separator + "backup_" + databaseName);

        if (!backupFilePath.exists()) {
            File dir = backupFilePath;
            dir.mkdirs();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String backupFileName = "backup_" + databaseName + "_" + sdf.format(new Date()) + ".sql";

        List<String> commands = getPgComands(databaseName, backupFilePath, backupFileName, "backup");
        if (!commands.isEmpty()) {
            try {
                ProcessBuilder pb = new ProcessBuilder(commands);
                pb.environment().put("PGPASSWORD", databasePassword);

                Process process = pb.start();

                try (BufferedReader buf = new BufferedReader(
                        new InputStreamReader(process.getErrorStream()))) {
                    String line = buf.readLine();
                    while (line != null) {
                        System.err.println(line);
                        line = buf.readLine();
                    }
                }

                process.waitFor();
                process.destroy();

                JOptionPane.showMessageDialog(null, "Kreiran je backup baze");

            } catch (IOException | InterruptedException ex) {
                JOptionPane.showMessageDialog(null, ex);
                Logger.getLogger(BackupPostgresql.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Doslo je do greske prilikom kreiranja backup baze");

            }
        } else {
            System.out.println("Error: Invalid params.");
        }
    }

    public void getBackup(String databaseName, String databasePassword) {
        File backupFilePath = new File(System.getProperty("user.home") + File.separator + "backup_" + "betonara_db");

        

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String backupFileName = databaseName;

        List<String> commands = getPgComands("betonara_db", backupFilePath, backupFileName, "restore");
        if (!commands.isEmpty()) {
            try {
                ProcessBuilder pb = new ProcessBuilder(commands);
                pb.environment().put("PGPASSWORD", databasePassword);

                Process process = pb.start();

                try (BufferedReader buf = new BufferedReader(
                        new InputStreamReader(process.getErrorStream()))) {
                    String line = buf.readLine();
                    while (line != null) {
                        System.err.println(line);
                        line = buf.readLine();
                    }
                }

                process.waitFor();
                process.destroy();

                JOptionPane.showMessageDialog(null, "Preuzeli ste backup baze uspesno");

            } catch (IOException | InterruptedException ex) {

                JOptionPane.showMessageDialog(null, "Doslo je do greske prilikom preuzimanja backup baze " + ex);

            }
        } else {
            System.out.println("Error: Invalid params.");
        }
    }

    private List<String> getPgComands(String databaseName, File backupFilePath, String backupFileName, String type) {
        ArrayList<String> commands = new ArrayList<>();
        switch (type) {
            case "backup":
                commands.add("pg_dump");
                commands.add("-h"); //database server host
                commands.add("localhost");
                commands.add("-p"); //database server port number
                commands.add("5432");
                commands.add("-U"); //connect as specified database user
                commands.add("postgres");
                commands.add("-F"); //output file format (custom, directory, tar, plain text (default))
                commands.add("c");
                commands.add("-b"); //include large objects in dump
                commands.add("-v"); //verbose mode
                commands.add("-f"); //output file or directory name
                commands.add(backupFilePath.getAbsolutePath() + File.separator + backupFileName);
                commands.add("-d"); //database name
                commands.add(databaseName);
                break;
            case "restore":
                commands.add("pg_restore");
                commands.add("-c");
                commands.add("-h");
                commands.add("localhost");
                commands.add("-p");
                commands.add("5432");
                commands.add("-U");
                commands.add("postgres");
                commands.add("-d");
                commands.add(databaseName);
                commands.add("-v");
                commands.add(backupFilePath.getAbsolutePath() + File.separator + backupFileName);
                break;
            default:
                return Collections.EMPTY_LIST;
        }
        return commands;
    }

    private static String getYesterdayDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(yesterday());
    }

    private static Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

}
