package utilities;

public class Global {
    public static final String COMMA_TWO_DECIMAL_FORMAT = "#,##0.00";
    public static final String DAFABET_CASHIER_DATEFORMAT = "dd/MM/yyyy hh:mm:ss a";
    public static final String TAURUS_PPS_DATE_24HFORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final String TAURUS_DATE_FORMAT = "dd/MM/yyyy";
    public static String ENVIRONMENT = "";
    public static String PLAYER = "";
    public static String EMAIL  = "";
    public static String TOTAL_BALANCE = "";

    public static void setEnvironment(String environment) throws Exception{
        ENVIRONMENT = getEnvironment(environment);
    }

    private static String getEnvironment(String environment) throws Exception
    {
        if(environment.equalsIgnoreCase("QA1"))
        {
            return "qa1";
        }
        else if(environment.equalsIgnoreCase("TCT"))
        {
            return "tct";
        }
        else if(environment.equalsIgnoreCase("STG"))
        {
            return "stg";
        }
        else if(environment.equalsIgnoreCase("STG3"))
        {
            return "stg3";
        }
        else if(environment.equalsIgnoreCase("UAT"))
        {
            return "uat";
        }
        else if(environment.equalsIgnoreCase("PROD"))
        {
            return "prod";
        }
        else
            throw new Exception("Invalid Environment");
    }



    public static class Browser {
        private Browser() {
            // Define a private constructor since this is a utility class
        }
        public static final String CHROME = "chrome";
        public static final String FIREFOX = "firefox";
        public static final String MOBILE = "mobile";
    }
}
