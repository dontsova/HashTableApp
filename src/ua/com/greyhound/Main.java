package ua.com.greyhound;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class DataItem {
    private int iData;

    public DataItem(int ii) {
        iData = ii;
    }
    public int getKey() {
        return iData;
    }
}


class HashTable {
    private DataItem[] hashArray;
    private int arraySize;
    DataItem nonItem;

    public HashTable(int size) {
        arraySize = size;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem(-1);
    }
    //---------------------------------------------------------------------
    public void displayTable(){
        System.out.print("Table: ");

        for(int j = 0; j < arraySize; j++) {
            if(hashArray[j] != null)
                System.out.print(hashArray[j].getKey() + " ");
            else
                System.out.print("** ");
        }
        System.out.println("");
    }
    //----------------------------------------------------------------------
    public int hashFunc(int key) {
        return key % arraySize;
    }
    //----------------------------------------------------------------------
    public void insert(DataItem item) {
        int key = item.getKey();
        int hashVal = hashFunc(key);

        while(hashArray[hashVal] != null &&
                hashArray[hashVal].getKey() != -1) {
            ++hashVal;
            hashVal %= arraySize;
        }
        hashArray[hashVal] = item;
    }
    //----------------------------------------------------------------------
    public DataItem delete(int key) {
        int hashVal  = hashFunc(key);

        while(hashArray[hashVal] != null) {
            if(hashArray[hashVal].getKey() == key) {
                DataItem temp = hashArray[hashVal];
                hashArray[hashVal] = nonItem;
                return temp;
            }
            ++hashVal;
            hashVal %= arraySize;
        }
        return null;
    }
    //----------------------------------------------------------------------
    public DataItem find(int key){
        int hashVal = hashFunc(key);

        while(hashArray[hashVal] != null) {
            if(hashArray[hashVal].getKey() == key)
                return hashArray[hashVal];
            ++hashVal;
            hashVal %= arraySize;
        }
        return null;
    }
}

public class Main {

    public static void main(String[] args) {

        DataItem aDataItem;

        int aKey, size, n, keysPerCell;

        System.out.print("Enter size of hash table: ");
        size = getInt();
        System.out.print("Enter initial number of items: ");
        n = getInt();
        keysPerCell = 10;

        // Create table
        HashTable theHashTable = new HashTable(size);

        //Insert data
        for(int j=0; j<n; j++) {
            aKey = (int)(java.lang.Math.random()*
            keysPerCell * size);
            aDataItem = new DataItem(aKey);
            theHashTable.insert(aDataItem);
        }

        //user interact
        while(true) {
            System.out.print("Enter first letter of ");
            System.out.print("show. insert. delete. or find: ");
            char choice = getChar();
            switch(choice) {
                case 's':
                    theHashTable.displayTable();
                    break;

                case 'i':
                    System.out.print("Enter key value to insert: ");
                    aKey = getInt();
                    aDataItem = new DataItem(aKey);
                    theHashTable.insert(aDataItem);
                    break;

                case 'd':
                    System.out.print("Enter key value to delete: ");
                    aKey = getInt();
                    theHashTable.delete(aKey);
                    break;

                case 'f':
                    System.out.print("Enter key value to find: ");
                    aKey = getInt();
                    aDataItem = theHashTable.find(aKey);
                    if (aDataItem != null) {
                        System.out.print("Found: " +aKey);
                    } else {
                      System.out.print("Could not find: " + aKey);
                    }
                    break;
                default:
                    System.out.print("Invalid entry\n");
            }
        }
    }

    //----------------------------------------------------------------
    public static String getString()  {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = null;
        try {
            s = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    //----------------------------------------------------------------
    public static char getChar() {
        String s = getString();
        return s.charAt(0);
    }

    //----------------------------------------------------------------
    public static int getInt() {
        String s = getString();
        return Integer.parseInt(s);
    }

}








































