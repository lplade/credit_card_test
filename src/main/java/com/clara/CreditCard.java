package com.clara;

import java.util.Scanner;

/**
 * Created by we4954cp on 8/31/2016.
 */
public class CreditCard {

    static Scanner stringScanner = new Scanner(System.in);

    public static void main(String[] args) {

        //Ask user for credit card number. store number as a String.
        System.out.println("Please enter the credit card number, digits only:");
        String ccNumber = stringScanner.nextLine();
        boolean isValid = isValidCreditCard(ccNumber);

        if (isValid) {
            System.out.println("This seems to be a valid credit card number");
        } else {
            System.out.println("This is **not** a valid credit card number.");
        }

        stringScanner.close();
    }

    public static boolean isValidCreditCard(String cc) {

        //TODO Replace with your code to process the credit card number, and determine if it is valid.
        //TODO Make sure all the tests pass!

        //populate an array of the digits
        int Digits[] = new int[cc.length()];
        for (int i = 0; i < cc.length(); i++){
            Digits[i] = Character.getNumericValue(cc.charAt(i));
        }

        //First digit MUST be '4' for Visa card
        if (Digits[0] != 4) return false;
        if (cc.length() != 16) return false;

        //Compute the LUHN mod 10 checksum
        int checkSumTotal = 0;

        Boolean doubleNext = false;
        //set up a toggle switch - keeps from having to figure odd/even in case we need to change string length

        for (int i = cc.length()-1; i >= 0; i--) {
            int currentDigitEval = Digits[i];
            if (doubleNext){
                currentDigitEval *= 2; //double
                if (currentDigitEval > 9) {
                    //sum any resulting digits
                    currentDigitEval = (currentDigitEval / 10) + (currentDigitEval % 10);
                }
                doubleNext = false; //flip the toggle
                //System.out.print(currentDigitEval + " + ");
            } else {
                doubleNext = true; //flip the toggle
                //System.out.print(currentDigitEval + " + ");
            }
            checkSumTotal += currentDigitEval;
        }
        //System.out.println();
        //System.out.println("DEBUG: Checksum was " +checkSumTotal);

        if (checkSumTotal % 10 == 0) {
            return true;
        } else {
            return false;
        }

    }




}
