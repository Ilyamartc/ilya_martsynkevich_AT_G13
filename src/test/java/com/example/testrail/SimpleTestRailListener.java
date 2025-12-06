package com.example.testrail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SimpleTestRailListener implements ITestListener {
    
    private static final Logger logger = LogManager.getLogger(SimpleTestRailListener.class);
    
    private static final String RUN_ID = "2369"; 
    
    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Starting test: {}", result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        updateTestResult(result, new Result(1, "Test passed successfully"));
        logger.info("✓ Test passed: {}", result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        String errorMessage = getErrorMessage(result);
        updateTestResult(result, new Result(5, "Test failed: " + errorMessage));
        logger.info("✗ Test failed: {}", result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        updateTestResult(result, new Result(2, "Test was skipped"));
        logger.info("⊘ Test skipped: {}", result.getMethod().getMethodName());
    }
    
    private void updateTestResult(ITestResult result, Result testResult) {
        try {
            Method testMethod = result.getMethod().getConstructorOrMethod().getMethod();
            TestRailCase annotation = testMethod.getAnnotation(TestRailCase.class);
            
            if (annotation == null) {
                logger.warn("No @TestRailCase annotation found for test method: {}", testMethod.getName());
                return;
            }
            
            String testCaseId = String.valueOf(annotation.value());
            reportResult(RUN_ID, testCaseId, testResult);
            logger.info("Updated TestRail case {} with status: {}", testCaseId, testResult.getStatus_id());
            
        } catch (Exception e) {
            logger.error("Failed to update TestRail result: {}", e.getMessage());
        }
    }
    
    private String getErrorMessage(ITestResult result) {
        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            return throwable.getMessage();
        }
        return "Unknown error";
    }
    
    public static void reportResult(String runId, String caseId, Result result) {
        try {
            String url = String.format("http:
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            
            
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            
            
            String credentials = "fakemail@tech.co:Abc123";
            String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
            connection.setRequestProperty("Authorization", "Basic " + encodedCredentials);
            
            connection.setDoOutput(true);
            
            
            String jsonBody = String.format("{\"status_id\":%d,\"comment\":\"%s\"}", result.getStatus_id(), result.getComment());
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            
            
            int responseCode = connection.getResponseCode();
            System.out.println("TestRail API response code: " + responseCode);
            
            if (responseCode == 200) {
                System.out.println("✓ Successfully updated TestRail case " + caseId);
            } else {
                
                java.io.InputStream errorStream = connection.getErrorStream();
                if (errorStream != null) {
                    java.util.Scanner scanner = new java.util.Scanner(errorStream).useDelimiter("\\A");
                    String errorResponse = scanner.hasNext() ? scanner.next() : "";
                    System.err.println("TestRail API Error: " + errorResponse);
                }
                System.err.println("✗ Failed to update TestRail case " + caseId + " (HTTP " + responseCode + ")");
            }
            
        } catch (IOException e) {
            System.err.println("Failed to update TestRail: " + e.getMessage());
        }
    }
    
    public static class Result {
        private int status_id;
        private String comment;
        
        public Result(int status_id, String comment) {
            this.status_id = status_id;
            this.comment = comment;
        }
        
        public int getStatus_id() {
            return status_id;
        }
        
        public String getComment() {
            return comment;
        }
    }
}
