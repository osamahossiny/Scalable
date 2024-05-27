import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest {


    private static final int NUM_THREADS = 50; // Number of concurrent threads
    private static final int NUM_REQUESTS = 100; // Total number of requests to send

    private int successfulRegistrations = 0;
    private int successfulLogins = 0;
    private int successfulLogouts = 0;
    private int successfulPasswordChanges = 0;

    static private List<String> registeredEmails = new ArrayList<>(); // Store registered emails for login test
    static private List<String> tokens = new ArrayList<>(); // Store tokens from successful login responses
    //register
    @Test
    public void test1() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<Void>> futures = new ArrayList<>();
        Object lock = new Object();

        for (int i = 0; i < NUM_REQUESTS; i++) {
            futures.add(executorService.submit(() -> {
                String email = "testuser" + System.currentTimeMillis() + ThreadLocalRandom.current().nextInt(1000) + "@example.com";
                String password = "password";
                String registerPayload = generateRegisterPayload(email, password);

                try {
                    HttpResponse registerResponse = HttpUtil.sendPost("http://localhost:8080/api/user/auth/register", registerPayload);
                    int registerResponseCode = registerResponse.getStatusLine().getStatusCode();
                    if (registerResponseCode == 200) {
                        synchronized (lock) {
                            successfulRegistrations++;
                            registeredEmails.add(email); // Store email for login test
                        }
                    }
                    System.out.println("Register Response Code for " + email + ": " + registerResponseCode);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Introduce a slight delay to avoid race conditions
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return null;
            }));
        }

        executorService.shutdown();
        if (!executorService.awaitTermination(10, TimeUnit.MINUTES)) {
            System.err.println("Tasks did not finish in the allotted time");
            executorService.shutdownNow();
        }

        for (Future<Void> future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Number of successful registrations: " + successfulRegistrations);
    }
//    login
    @Test
    public void test2() throws InterruptedException {
        // Ensure that registration test is run first to populate registeredEmails
        if (registeredEmails.isEmpty()) {
            System.err.println("No registered emails found. Please run the registration test first.");
            return;
        }

        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<Void>> futures = new ArrayList<>();
        Object lock = new Object();

        for (String email : registeredEmails) {
            futures.add(executorService.submit(() -> {
                String password = "password";
                String loginPayload = generateLoginPayload(email, password);

                try {
                    HttpResponse loginResponse = HttpUtil.sendPost("http://localhost:8080/api/user/auth/authenticate", loginPayload);
                    int loginResponseCode = loginResponse.getStatusLine().getStatusCode();
                    if (loginResponseCode == 200) {
                        synchronized (lock) {
                            successfulLogins++;
                            String responseBody = EntityUtils.toString(loginResponse.getEntity());
                            JSONObject json = new JSONObject(responseBody);
                            String token = json.getString("access_token");
                            tokens.add(token); // Store token for later use
                        }
                    }
                    System.out.println("Login Response Code for " + email + ": " + loginResponseCode);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }));
        }

        executorService.shutdown();
        if (!executorService.awaitTermination(10, TimeUnit.MINUTES)) {
            System.err.println("Tasks did not finish in the allotted time");
            executorService.shutdownNow();
        }

        for (Future<Void> future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Number of successful logins: " + successfulLogins);
        System.out.println("Tokens: " + tokens.get(tokens.size()-1));
    }
    //logout
    @Test
    public void test4() throws InterruptedException {
        // Ensure that login test is run first to populate tokens
        if (tokens.isEmpty()) {
            System.err.println("No tokens found. Please run the login test first.");
            return;
        }

        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<Void>> futures = new ArrayList<>();
        Object lock = new Object();

        for (String token : tokens) {
            futures.add(executorService.submit(() -> {
                try {
                    HttpResponse logoutResponse = HttpUtil.sendAuthorizedPost("http://localhost:8080/api/user/auth/logout", "", token);
                    int logoutResponseCode = logoutResponse.getStatusLine().getStatusCode();
                    if (logoutResponseCode == 200) {
                        synchronized (lock) {
                            successfulLogouts++;
                        }
                    }
                    System.out.println("Logout Response Code: " + logoutResponseCode);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }));
        }

        executorService.shutdown();
        if (!executorService.awaitTermination(10, TimeUnit.MINUTES)) {
            System.err.println("Tasks did not finish in the allotted time");
            executorService.shutdownNow();
        }

        for (Future<Void> future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Number of successful logouts: " + successfulLogouts);
    }
    //chang password
    @Test
    public void test3() throws InterruptedException {
        // Ensure that login test is run first to populate tokens
        if (tokens.isEmpty()) {
            System.err.println("No tokens found. Please run the login test first.");
            return;
        }
        System.out.println(tokens.size());
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<Void>> futures = new ArrayList<>();
        Object lock = new Object();

        for (String token : tokens) {
            futures.add(executorService.submit(() -> {
                String changePasswordPayload = generateChangePasswordPayload("password", "newpassword","newpassword");

                try {
                    HttpResponse changePasswordResponse = HttpUtil.sendAuthorizedPatch("http://localhost:8080/api/user/profile/change-password", changePasswordPayload, token);
                    int changePasswordResponseCode = changePasswordResponse.getStatusLine().getStatusCode();
                    if (changePasswordResponseCode == 200) {
                        synchronized (lock) {
                            successfulPasswordChanges++;
                        }
                    }
                    System.out.println("Change Password Response Code: " + changePasswordResponseCode);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }));
        }

        executorService.shutdown();
        if (!executorService.awaitTermination(10, TimeUnit.MINUTES)) {
            System.err.println("Tasks did not finish in the allotted time");
            executorService.shutdownNow();
        }

        for (Future<Void> future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Number of successful password changes: " + successfulPasswordChanges);
    }

    private String generateRegisterPayload(String email, String password) {
        return String.format("{\"firstname\":\"Test\",\"lastname\":\"User\",\"email\":\"%s\",\"password\":\"%s\",\"role\":\"USER\"}", email, password);
    }

    private String generateLoginPayload(String email, String password) {
        return String.format("{\"email\":\"%s\",\"password\":\"%s\"}", email, password);
    }

    private String generateChangePasswordPayload(String currentPassword, String newPassword, String confirmationPassword) {
        return String.format(
                "{\"currentPassword\":\"%s\",\"newPassword\":\"%s\",\"confirmationPassword\":\"%s\"}",
                currentPassword,
                newPassword,
                confirmationPassword
        );
    }


}