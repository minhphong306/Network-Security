/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encoding;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PhongPP
 */
public class Uuencoding {

    // Fill to eight bit
    public String fillCharToEight(String needToFill) {
        String filledChars = "";
        int len = 8 - needToFill.length();
        for (int i = 0; i < len; i++) {
            filledChars += "0";
        }
        filledChars += needToFill;

        return filledChars;
    }

    // Add 32 to the character can be print
    public char getUuencodingCharacter(String binString) {
        return (char) (Integer.parseInt(binString, 2) + 32);
    }

    public String getBinaryString(String originString) {
        String binString = "";
        for (int i = 0; i < originString.length(); i++) {
            char c = originString.charAt(i);
            binString += this.fillCharToEight(Integer.toBinaryString(c));
        }

        return binString;
    }
    
    public String getBinarySub32String(String originString) {
        String binString = "";
        for (int i = 0; i < originString.length(); i++) {
            char c = (char)(originString.charAt(i) - 32);
            binString += this.fillCharToEight(Integer.toBinaryString(c));
        }

        return binString;
    }

    public List<String> getListUuencodingBinString(String bin8) {
        int length = bin8.length();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < length; i += 6) {
            if ((i + 6) <= length) {
                list.add(this.fillCharToEight(bin8.substring(i, i + 6)));
            }
        }

        if (length % 6 != 0) {
            list.add(this.fillCharToEight(bin8.substring(length - (length % 6), length)));
        }
        return list;
    }
    
    public char getCanReadCharFromBinary(String binaryString){
        return (char) (Integer.parseInt(binaryString, 2) + 32);
    }
    
    public char getCharFromBinary(String binaryString){
        return (char) (Integer.parseInt(binaryString, 2));
    }

    public List<String> getListUuDecodingBinString(String bin6){
        List<String> list = new ArrayList<>();
        int length = bin6.length();
        String bin8 = "";
        for (int i = 2; i < length; i += 8) {
            //if ((i + 6) <= length) {
                bin8 += bin6.substring(i, i + 6);
           // }
        }
        
        for(int i = 0;i < bin8.length(); i+= 8){
            list.add(bin8.substring(i, i+8));
        }
        return list;
    }
    
    
    public static void main(String[] args) {
        Uuencoding uu = new Uuencoding();
        String orgin = "My name is James Bond";
        String bin_orgin = uu.getBinaryString(orgin);
        String uuEncode = "";
        String uuDecode = "";
        String bin_encode;
        
        List<String> list = uu.getListUuencodingBinString(bin_orgin);
        for(String item : list){
            uuEncode += uu.getCanReadCharFromBinary(item);
        }
        
        bin_encode = uu.getBinarySub32String(uuEncode);
        List<String> list1 = uu.getListUuDecodingBinString(bin_encode);
        for(String item : list1){
            uuDecode += uu.getCharFromBinary(item);
            
        }
        
        System.out.println("- Origin string: " + orgin);
        System.out.println("- Origin binary string: " + bin_orgin);
        
        System.out.println("- Encode str: " + uuEncode);        
        System.out.println("- Encode binary string: " + bin_encode);
        
        System.out.println("- Decode Str: " + uuDecode);
    }
}
