package net.fulcrum.testrail;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Parameters;
/**
 * Arugments for {@link net.fulcrum.testrail.TestRailListener}
 *
 * @author sharanya
 */
public class TestRailArgs {

    //if the listener is enabled or not
    private Boolean enabled;
//    //project id
//    private Integer projectId;
    //test plan id (if one already exists)
    private Integer testPlanId;
    //suite names
    private List<String> suiteNames;
    //url to the TestRail instance
    private String url;
    //username to login to TestRail
    private String username;
    //password to login to TestRail
    private String password;

    private TestRailArgs() {}

    public static TestRailArgs getNewTestRailListenerArgs() {
        TestRailArgs args = new TestRailArgs();
        args.enabled = Boolean.valueOf(System.getProperty("testRail.enabled"));

        if (args.enabled == null || !args.enabled) {
            return args; //no need to process further. TestRail reporting is not enabled
        }

//        String projectId = System.getProperty("testRail.projectId");
//        if (projectId == null) {
//            throw new IllegalArgumentException("TestRail Project ID not specified");
//        } else {
//            try {
//                args.projectId = Integer.valueOf(projectId);
//            } catch(NumberFormatException ex) {
//                throw new IllegalArgumentException("Project Id is not an integer as expected");
//            }
//        }

        String planId = System.getProperty("testRail.testPlanId");
        if (planId == null) {
            throw new IllegalArgumentException("TestRail Test Plan ID not specified");
        } else {
            try {
                args.testPlanId = Integer.valueOf(planId);
            } catch(NumberFormatException ex) {
                throw new IllegalArgumentException("Plan Id is not an integer as expected");
            }
        }

        String suiteNamesStr = System.getProperty("testRail.suiteNames");
        if (suiteNamesStr != null) {
            try {
                String[] suiteNamesArr = suiteNamesStr.split(",");
                args.suiteNames = new ArrayList<String>();
                for (String suiteName : suiteNamesArr) {
                    if (suiteName != null && !suiteName.trim().isEmpty()) {
                        args.suiteNames.add(suiteName.trim());
                    }
                }

            } catch(NumberFormatException ex) {
                throw new IllegalArgumentException("Plan Id is not an integer as expected");
            }
        }

        if ((args.url = "https://fulcrumtechnologies.testrail.com") == null) {
            throw new IllegalArgumentException("TestRail URL not specified (testRail.url)");
        }

        if ((args.username = "ato@fulcrum.net") == null) {
            throw new IllegalArgumentException("TestRail user not specified (testRail.username)");
        }
        //API Key generated PW
        if ((args.password = "4W.nRZdxv/cd4NuxDUmI-xEyJVH8khGulY2JzFYNE") == null) {
            throw new IllegalArgumentException("TestRail password not specified (testRail.password)");
        }

        return args;
    }

    public Boolean getEnabled() {
        return enabled;
    }

//    public Integer getProjectId() {
//        return projectId;
//    }

    public Integer getTestPlanId() {
        return testPlanId;
    }

    public List<String> getSuiteNames() {
        return suiteNames;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
