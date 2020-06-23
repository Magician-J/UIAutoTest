//package test_framework;
//
//import org.junit.platform.engine.discovery.ClassNameFilter;
//import org.junit.platform.engine.discovery.DiscoverySelectors;
//import org.junit.platform.launcher.Launcher;
//import org.junit.platform.launcher.LauncherDiscoveryRequest;
//import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
//import org.junit.platform.launcher.core.LauncherFactory;
//import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
//import org.junit.platform.launcher.listeners.TestExecutionSummary;
//
//import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
//import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
//import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;
//
///**
// * @author jiaoyl
// * @date 2020/6/23 21:52
// * 选择用例执行
// */
//public class Main {
//    public static void main(String args[]){
//        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
//                .selectors(
//                        selectPackage("test_framework"),
//                        selectClass(DDT.class)
//                )
//                .filters(
//                        includeClassNamePatterns(".*")
//                )
//                .build();
//
////        Launcher launcher = LauncherFactory.create();
//        Launcher launcher = LauncherFactory.create();
//
//// Register a listener of your choice
//        SummaryGeneratingListener listener = new SummaryGeneratingListener();
//        launcher.registerTestExecutionListeners(listener);
//
//        launcher.execute(request);
//
//        TestExecutionSummary summary = listener.getSummary();
//// Do something with the TestExecutionSummary.
//    }
//}
