import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TransactionTest {

    private static final int NUM_THREADS = 10; // Number of concurrent threads
    private static final int NUM_REQUESTS = 100; // Total number of requests to send

    private int successfulTransactions = 0;
    private int successfulGetTransactions = 0;
    private int successfulUserTransactions = 0;
    private int successfulAddTransactions = 0;
    private int successfulUpdateTransactions = 0;
    private int successfulDeleteTransactions = 0;
    private int successfulRegistrations = 0;
    private int successfulLogins = 0;
    private static List<Integer> transactionIds = new ArrayList<>();
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
    //login
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
        System.out.println("Tokens: " + tokens.get(tokens.size()-1));
    }
    //AddTransactionLoad
    @Test
    public void test3() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<Void>> futures = new ArrayList<>();
        Object lock = new Object();

        for (int i = 0; i < NUM_REQUESTS; i++) {
            futures.add(executorService.submit(() -> {
                String transactionPayload = generateAddTransactionPayload(1, 1, "2024-01-11T00:00:00", "VISA", 50000.00, "PENDING");

                try {
                    HttpResponse transactionResponse = HttpUtil.sendAuthorizedPost("http://localhost:8081/api/v1/transaction", transactionPayload , tokens.get(tokens.size()-1));
                    int transactionResponseCode = transactionResponse.getStatusLine().getStatusCode();
                    if (transactionResponseCode == 200) {
                        synchronized (lock) {
                            successfulAddTransactions++;
                            String responseBody = EntityUtils.toString(transactionResponse.getEntity());
                            JSONObject json = new JSONObject(responseBody);
                            int transactionId = json.getInt("transactionId");
                            transactionIds.add(transactionId); // Store transactionId for further tests
                        }
                    }
                    System.out.println("Add Transaction Response Code: " + transactionResponseCode);
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

        System.out.println("Number of successful add transactions: " + successfulAddTransactions);
    }
    //GetAllTransactionsLoad
    @Test
    public void test4() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<Void>> futures = new ArrayList<>();

        for (int i = 0; i < NUM_REQUESTS; i++) {
            futures.add(executorService.submit(() -> {
                try {
                    HttpResponse getResponse = HttpUtil.sendAuthorizedGet("http://localhost:8081/api/v1/transaction", tokens.get(tokens.size()-1));
                    int getResponseCode = getResponse.getStatusLine().getStatusCode();
                    if (getResponseCode == 200) {
                        synchronized (this) {
                            successfulGetTransactions++;
                        }
                    }
                    System.out.println("Get All Transactions Response Code: " + getResponseCode);
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

        System.out.println("Number of successful get all transactions: " + successfulGetTransactions);
    }
    //GetUserTransactionsLoad
    @Test
    public void test5() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<Void>> futures = new ArrayList<>();

        for (int i = 0; i < NUM_REQUESTS; i++) {
            futures.add(executorService.submit(() -> {
                try {
                    HttpResponse getResponse = HttpUtil.sendAuthorizedGet("http://localhost:8081/api/v1/transaction/user?userId=1", tokens.get(tokens.size()-1));
                    int getResponseCode = getResponse.getStatusLine().getStatusCode();
                    if (getResponseCode == 200) {
                        synchronized (this) {
                            successfulUserTransactions++;
                        }
                    }
                    System.out.println("Get User Transactions Response Code: " + getResponseCode);
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

        System.out.println("Number of successful get user transactions: " + successfulUserTransactions);
    }
    //UpdateTransactionLoad
    @Test
    public void test6() throws InterruptedException {
        // Ensure that add transaction test is run first to populate transactionIds
        if (transactionIds.isEmpty()) {
            System.err.println("No transactions found. Please run the add transaction test first.");
            return;
        }

        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<Void>> futures = new ArrayList<>();
        Object lock = new Object();

        for (int transactionId : transactionIds) {
            futures.add(executorService.submit(() -> {
                String updatePayload = generateUpdateTransactionPayload(transactionId, 1, 1, "2024-01-11T00:00:00", "VISA", 50000.00, "COMPLETED");

                try {
                    HttpResponse updateResponse = HttpUtil.sendAuthorizedPut("http://localhost:8081/api/v1/transaction/" + transactionId, updatePayload, tokens.get(tokens.size()-1));
                    int updateResponseCode = updateResponse.getStatusLine().getStatusCode();
                    if (updateResponseCode == 200) {
                        synchronized (lock) {
                            successfulUpdateTransactions++;
                        }
                    }
                    System.out.println("Update Transaction Response Code: " + updateResponseCode);
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

        System.out.println("Number of successful update transactions: " + successfulUpdateTransactions);
    }
    //DeleteTransactionLoad
//    @Test
//    public void test7() throws InterruptedException {
//        // Ensure that add transaction test is run first to populate transactionIds
//        if (transactionIds.isEmpty()) {
//            System.err.println("No transactions found. Please run the add transaction test first.");
//            return;
//        }
//
//        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
//        List<Future<Void>> futures = new ArrayList<>();
//        Object lock = new Object();
//
//        for (int transactionId : transactionIds) {
//            futures.add(executorService.submit(() -> {
//                try {
//                    HttpResponse deleteResponse = HttpUtil.sendDelete("http://localhost:8081/api/v1/transaction/" + transactionId);
//                    int deleteResponseCode = deleteResponse.getStatusLine().getStatusCode();
//                    if (deleteResponseCode == 200) {
//                        synchronized (lock) {
//                            successfulDeleteTransactions++;
//                        }
//                    }
//                    System.out.println("Delete Transaction Response Code: " + deleteResponseCode);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                return null;
//            }));
//        }
//
//        executorService.shutdown();
//        if (!executorService.awaitTermination(10, TimeUnit.MINUTES)) {
//            System.err.println("Tasks did not finish in the allotted time");
//            executorService.shutdownNow();
//        }
//
//        for (Future<Void> future : futures) {
//            try {
//                future.get();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        System.out.println("Number of successful delete transactions: " + successfulDeleteTransactions);
//    }

    private String generateAddTransactionPayload(int userId, int reservationId, String transactionDateTime, String paymentMethod, double transactionAmount, String status) {
        return String.format("{\"userId\":%d,\"reservationId\":%d,\"transactionDateTime\":\"%s\",\"paymentMethod\":\"%s\",\"transactionAmount\":%.2f,\"status\":\"%s\"}",
                userId, reservationId, transactionDateTime, paymentMethod, transactionAmount, status);
    }

    private String generateUpdateTransactionPayload(int transactionId, int userId, int reservationId, String transactionDateTime, String paymentMethod, double transactionAmount, String status) {
        return String.format("{\"transactionId\":%d,\"userId\":%d,\"reservationId\":%d,\"transactionDateTime\":\"%s\",\"paymentMethod\":\"%s\",\"transactionAmount\":%.2f,\"status\":\"%s\"}",
                transactionId, userId, reservationId, transactionDateTime, paymentMethod, transactionAmount, status);
    }

    private String generateRegisterPayload(String email, String password) {
        return String.format("{\"firstname\":\"Test\",\"lastname\":\"User\",\"email\":\"%s\",\"password\":\"%s\",\"role\":\"USER\"}", email, password);
    }

    private String generateLoginPayload(String email, String password) {
        return String.format("{\"email\":\"%s\",\"password\":\"%s\"}", email, password);
    }
}
