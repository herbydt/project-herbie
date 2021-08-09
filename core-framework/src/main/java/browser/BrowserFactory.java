package browser;

import utilities.Global;

public class BrowserFactory {

    public IBrowser getBrowser(String name)
    {
        String s = name.toLowerCase().toString();

        if (s.equals("chrome")) {
            return new Chrome();
        } else if (s.equals("firefox")) {
			return new Firefox();
        } else if (s.equals(Global.Browser.MOBILE)) {
            return  new Chrome();
//        } else if (s.equals("ie")) {
//            return new InternetExplorer();
//        } else if (s.equals("safari")) {
//            return new Safari();
        }

        return null;
    }

}
