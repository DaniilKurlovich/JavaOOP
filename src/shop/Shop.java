package shop;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Shop {
    private Map<Integer, ShopItem> items  = new HashMap<Integer, ShopItem>() {};

    public Shop(String pathFile) throws FileNotFoundException
    {
        Scanner scan = new Scanner(new File("shopItems.txt"));
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            String characteristic = "";
            int cost = 0;
        }
    }



}
