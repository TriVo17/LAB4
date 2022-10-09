
package tools;

import data.Products;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import javafx.scene.chart.PieChart.Data;
import sun.util.BuddhistCalendar;

public class MyTool {

    public static final String filename = "product.txt";
    public static final Scanner SC = new Scanner(System.in);
    
    public static boolean validStr(String str, String regEx) {
        boolean matches = str.matches(regEx);
        return matches;
    }

    public static String readPattern(String message, String pattern) {
        String input = "";
        boolean valid;
        do {
            System.out.print(message);
            input = SC.nextLine().trim();
            valid = validStr(input, pattern);
        } while (!valid);
        return input;
    }

    public static List<Products> loadFromFile(String fName) {
        List<Products> list = new ArrayList<Products>();
        try {
            FileReader fr = new FileReader(fName);
            BufferedReader bf = new BufferedReader(fr);
            String details; 
            while((details=bf.readLine())!=null) {
                StringTokenizer stk = new StringTokenizer(details,",:");
                int ID = Integer.parseInt(stk.nextToken());
                String productName = stk.nextToken().toUpperCase();
                int productPrice = Integer.parseInt(stk.nextToken());
                int quantity = Integer.parseInt(stk.nextToken());
                String status = stk.nextToken().toUpperCase();
                Products product = new Products(ID, productName, productPrice,
                        quantity, status);
                list.add(product);
            }
            bf.close(); fr.close();
        } catch (Exception e) {
        }
        return list;
    }

    public static int getInt(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static void saveToFile(List<Products> list) {
        if(list.isEmpty()) {
            System.out.println("Empty list!");
            return;
        }
        try {
            FileWriter fw = new FileWriter(filename,true);
            PrintWriter pw = new PrintWriter(fw);
            for (Products products : list) {
                pw.println(products.getID() + "," + products.getName() 
                        + "," + products.getPrice() + "," + 
                        products.getQuantity() + "," + products.getStatus());
            }
            pw.close(); fw.close(); 
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

}
