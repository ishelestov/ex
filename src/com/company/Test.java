package com.company;

/**
 * Created by vanya on 25.02.15.
 */
public class Test {

    public static final String EXAMPLE_TEST = "This             is my small example "
            + "string which I'm going to " + "use for pattern matching.";

    public static void main(String[] args) {
        System.out.println(EXAMPLE_TEST.matches("\\w.*"));
        String[] splitString = (EXAMPLE_TEST.split("\\s+"));
        System.out.println(splitString.length);// should be 14
        for (String string : splitString) {
            if (string.contains("going")){
                System.out.println(string);
            }
        }
        // replace all whitespace with tabs
        System.out.println(EXAMPLE_TEST.replaceAll("\\s+", "*"));
//        System.out.println(EXAMPLE_TEST.
    }
}
