import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class PasswordChecker2nd {
    private final static Logger logger = LogManager.getLogger(PasswordChecker2nd.class.getName());
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws Exception{
        try {
            System.out.println("Please enter desired password:");
            String password = in.nextLine();

            passwordIsOK(password);

        }
        catch (PasswordException ex) {
            logger.error(ex.getMessage());
        }
    }

    public static boolean passwordIsOK(String password) throws Exception {
        if(passwordChecker(password)) {
            logger.debug("User password is OK");
            return true;

        }
        else {
            logger.debug("User password is not OK");
            return false;
        }
    }

    public static boolean passwordChecker(String password) throws Exception {

        Pattern letterPattern = Pattern.compile("[a-zA-Z]");
        Pattern upperLetterPattern = Pattern.compile("[A-Z]");
        Pattern lowerLetterPattern = Pattern.compile("[a-z]");
        Pattern digitPattern = Pattern.compile("[0-9]");
        Pattern specialPattern = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

        Matcher hasLetter = letterPattern.matcher(password);
        Matcher hasUpperLetter = upperLetterPattern.matcher(password);
        Matcher hasLowerLetter = lowerLetterPattern.matcher(password);
        Matcher hasDigit = digitPattern.matcher(password);
        Matcher hasSpecial = specialPattern.matcher(password);

        boolean digit = false;
        boolean upperCase = false;
        boolean lowerCase = false;
        boolean special = false;

        if(hasLetter.find() && hasUpperLetter.find() && hasLowerLetter.find() && hasDigit.find() && hasSpecial.find() && password.length() > 8)
        {
            upperCase = true;
            lowerCase = true;
            digit = true;
            special = true;
            System.out.println("Valid password");

        }else{
            if(!hasLetter.find())
            {
                System.out.println("password should exist");
            }
            if(!hasUpperLetter.find())
            {
                System.out.println("password should have at least one uppercase letter");
            }
            if(!hasLowerLetter.find())
            {
                System.out.println("password should have at least one lowercase letter");
            }
            if(!hasDigit.find())
            {
                System.out.println("password should at least have one digit");
            }
            if(!hasSpecial.find())
            {
                System.out.println("password should have at least one special character");
            }
            if(password.length() > 8)
            {
                System.out.println("password should be longer than than 8 characters");
            }
            logger.debug("User password is not ok");

            System.out.println("");
            System.out.println("Please try again...");
            System.out.println("");

            PasswordChecker2nd.main(null);
        }

        return digit && upperCase && lowerCase && special;

    }

    public static class PasswordException extends Exception {
        public PasswordException(String message){
            super("Invalid Password : " + message);
        }
    }
}


