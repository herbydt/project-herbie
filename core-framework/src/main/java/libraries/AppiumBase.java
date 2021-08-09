package libraries;

import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppiumBase {

    public static Process appiumProcess;
    String platform = System.getProperty("os.name");
    AppiumDriverLocalService  service;

    public void startAppium() throws Exception {

        service = AppiumDriverLocalService.buildDefaultService();
        ProcessBuilder builder = null;
        try {
            if (platform.contains("Mac")) {
                String[] command = {"osascript", "-e", "tell application \"Terminal\" to do script \"appium &\""};
                builder = new ProcessBuilder(command);
                appiumProcess = builder.start();
                System.out.println("DEBUG: Appium started. \n");
                Thread.sleep(15000); // wait a bit to start appium
            } else if (platform.contains("Windows")) {
                service.start();
            } if (platform.contains("Linux")) {
                String[] command = { "gnome-terminal", "-e", "appium"};
                builder = new ProcessBuilder(command);
                appiumProcess = builder.start();
                System.out.println("DEBUG: Appium started. \n");
                Thread.sleep(15000); // wait a bit to start appium
            }else {
                System.out.println("FAILED: Platform not Mac, Windows or Linux. \n");
            }
        } catch (Exception e) {
            throw new Exception("FAILED: Error starting appium. " + e.getCause());
        }
    }

    public void stopAppium() throws Exception {

        ProcessBuilder builder = null;
        if (platform.contains("Mac")) {
            String[] killCommand = { "osascript", "-e", "tell application \"Terminal\" to do script \"killall -9 node\""};
            builder = new ProcessBuilder(killCommand);
            String[] closeTerminal = { "osascript", "-e", "tell application \"Terminal\" to do script \"killall Terminal\""};
            builder = new ProcessBuilder(closeTerminal);

            try {
                builder.start();
                System.out.println("DEBUG: Appium stopped. \n");
                appiumProcess.destroy();
            } catch (Exception e) {
                throw new Exception("FAILED: Error stopping appium. " + e.getCause());
            }
        } else if(platform.contains("Windows")) {
            if(appiumProcess != null) {
                appiumProcess.destroy();
                appiumProcess = null;
            }

            service.stop();
            killWindowProcess("adb.exe");
            killWindowProcess("node.exe");
            Thread.sleep(5000);
        } else if(platform.contains("Linux")) {
            if(appiumProcess != null) {

                String[] killCommand = { "gnome-terminal", "-e", "killall -KILL node"};
                builder = new ProcessBuilder(killCommand);

                try {
                    builder.start();
                    System.out.println("DEBUG: Appium stopped. \n");
                    appiumProcess.destroy();
                } catch (Exception e) {
                    throw new Exception("FAILED: Error stopping appium. " + e.getCause());
                }
            }
        } else {
            System.out.println("FAILED: Cannot stop appium. \n");
        }
    }

    public void killWindowProcess(String processName) throws Exception {
        try {
            String line;
            Process process = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while((line = input.readLine()) != null) {
                if(line.indexOf(processName, 0) != -1) {
                    System.out.println("DEBUG: Killing processor: " + processName);
                    String cmd = "taskkill /F /IM " + processName;
                    Runtime.getRuntime().exec(cmd);
                }
            }
            input.close();
        } catch (Exception e) {
            throw new Exception("FAILED: " + e.getCause());
        }
    }
}