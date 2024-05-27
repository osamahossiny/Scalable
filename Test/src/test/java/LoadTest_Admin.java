import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LoadTest_Admin {

    private static final int NUM_THREADS = 10; // Number of concurrent threads
    private static final int NUM_REQUESTS = 100; // Total number of requests to send
    private int successfulRegistrations = 0;
    private int successfulLogins = 0;
    private int successfulPromotions = 0;
    private int successfulRefundsFetch = 0;
    private  int successfulRefundsPost=0;
    private int successfulLogouts = 0;

    static private List<String> registeredEmails = new ArrayList<>(); // Store registered emails for login test
    static private List<String> tokens = new ArrayList<>(); // Store tokens from successful login responses
    private static final AtomicInteger promotionCounter = new AtomicInteger(); // Counter for unique promotion codes

    //RegisterLoad
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
                    HttpResponse registerResponse = HttpUtil.sendPost("http://localhost:8084/api/v1/auth/register", registerPayload);
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
    //LoginforuserLoad
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
                    HttpResponse loginResponse = HttpUtil.sendPost("http://localhost:8084/api/v1/auth/authenticate", loginPayload);
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
        System.out.println("Tokens: " + tokens);
    }
    //AddPromotionforflightLoad
    @Test
    public void test3() throws InterruptedException {
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
                String promotionPayload = generatePromotionPayload();
                String flightId = "1"; // Example flight ID, replace with actual ID

                try {
                    HttpResponse promotionResponse = HttpUtil.sendAuthorizedPost("http://localhost:8083/admin/promotion/" + flightId, promotionPayload, token);
                    int promotionResponseCode = promotionResponse.getStatusLine().getStatusCode();
                    if (promotionResponseCode == 200) {
                        synchronized (lock) {
                            successfulPromotions++;
                        }
                    }
                    System.out.println("Promotion Response Code: " + promotionResponseCode);
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

        System.out.println("Number of successful promotions: " + successfulPromotions);
    }
    //FetchAllRefunds
    @Test
    public void test4() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<Void>> futures = new ArrayList<>();
        Object lock = new Object();

        for (String token : tokens) {
            futures.add(executorService.submit(() -> {
                try {
                    HttpResponse response = HttpUtil.sendAuthorizedGet("http://localhost:8083/admin/refunds", token);
                    int responseCode = response.getStatusLine().getStatusCode();
                    if (responseCode == 200) {
                        synchronized (lock) {
                            successfulRefundsFetch++;
                        }
                    }
                    System.out.println("Fetch Refunds Response Code: " + responseCode);
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

        System.out.println("Number of successful refunds fetch: " + successfulRefundsFetch);
    }
    //PostRefunds
    @Test
    public void test5() throws InterruptedException {
        // Register a user
        String email = "testuser" + System.currentTimeMillis() + "@example.com";
        String password = "password";
        String registerPayload = generateRegisterPayload(email, password);

        try {
            HttpResponse registerResponse = HttpUtil.sendPost("http://localhost:8084/api/v1/auth/register", registerPayload);
            int registerResponseCode = registerResponse.getStatusLine().getStatusCode();
            if (registerResponseCode != 200) {
                System.err.println("User registration failed with response code: " + registerResponseCode);
                return;
            }
            System.out.println("User registered successfully with response code: " + registerResponseCode);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Object lock = new Object();

        // Log in with the registered user to obtain a token
        String loginPayload = generateLoginPayload(email, password);
        String token = null;
        try {
            HttpResponse loginResponse = HttpUtil.sendPost("http://localhost:8084/api/v1/auth/authenticate", loginPayload);
            int loginResponseCode = loginResponse.getStatusLine().getStatusCode();
            if (loginResponseCode == 200) {
                synchronized (lock) {
                    successfulLogins++;
                    String responseBody = EntityUtils.toString(loginResponse.getEntity());
                    JSONObject json = new JSONObject(responseBody);
                    String token1 = json.getString("access_token");
                    token=token1;
                    tokens.add(token1); // Store token for later use
                }
            }
            System.out.println("Login Response Code for " + email + ": " + loginResponseCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        // Ensure that the token is available from the login step


        // Test the refunds endpoint using the obtained token
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<Void>> futures = new ArrayList<>();

        String refundsPayload = generateRefundsPayload();

        for (int i = 0; i < NUM_REQUESTS; i++) {
            futures.add(executorService.submit(() -> {
                try {
                    HttpResponse refundsResponse = HttpUtil.sendAuthorizedPost("http://localhost:8081/api/refunds", refundsPayload, tokens.get(tokens.size()-1));
                    int refundsResponseCode = refundsResponse.getStatusLine().getStatusCode();
                    synchronized (lock) {
                        if (refundsResponseCode == 200 || refundsResponseCode == 201) {
                            successfulRefundsPost++;
                        }
                    }
                    System.out.println("Post Refunds Response Code: " + refundsResponseCode);
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

        System.out.println("Number of successful refunds post: " + successfulRefundsPost);
    }
    //UpdateRefundStatusforadmin
    @Test
    public void test6() throws InterruptedException {
        // Ensure that the token is available from the login step
        if (tokens.isEmpty()) {
            System.err.println("No tokens found. Please run the login test first.");
            return;
        }

        // Use the first token for authorization
        String token = tokens.get(0);

        // Update refund status using the obtained token
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<Void>> futures = new ArrayList<>();
        Object lock = new Object();
        String updateStatusPayload = generateUpdateStatusPayload("completed");

        for (int i = 1; i <= NUM_REQUESTS; i++) {
            final Long refundId = (long) i; // Use the request number as the refund ID

            futures.add(executorService.submit(() -> {
                try {
                    HttpResponse updateStatusResponse = HttpUtil.sendAuthorizedPut("http://localhost:8083/admin/refund/" + refundId + "/status", updateStatusPayload, token);
                    int updateStatusResponseCode = updateStatusResponse.getStatusLine().getStatusCode();
                    synchronized (lock) {
                        if (updateStatusResponseCode == 200) {
                            successfulRefundsPost++;
                        }
                    }
                    System.out.println("Update Refund Status Response Code for refund ID " + refundId + ": " + updateStatusResponseCode);
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

        System.out.println("Number of successful refund status updates: " + successfulRefundsPost);
    }
    //Logout
    @Test
    public void test7() throws InterruptedException {
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
                    HttpResponse logoutResponse = HttpUtil.sendAuthorizedPost("http://localhost:8084/api/v1/auth/logout", "", token);
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

    private String generateUpdateStatusPayload(String status) {
        return String.format("{\"status\":\"%s\"}", status);
    }
    private String generateRegisterPayload(String email, String password) {
        return String.format("{\"firstname\":\"Test\",\"lastname\":\"User\",\"email\":\"%s\",\"password\":\"%s\",\"role\":\"ADMIN\"}", email, password);
    }

    private String generateLoginPayload(String email, String password) {
        return String.format("{\"email\":\"%s\",\"password\":\"%s\"}", email, password);
    }

    private String generatePromotionPayload() {
        String code = "code" + System.currentTimeMillis() + promotionCounter.incrementAndGet();
        return String.format("{\"code\":\"%s\",\"discount\":20}", code);
    }

    private String generateRefundsPayload() {
        return "{\"amount\":100,\"reason\":\"Flight cancellation\",\"status\":\"PENDING\"}";
    }




}
