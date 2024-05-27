import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SearchTest {

    private static final int NUM_THREADS = 10; // Number of concurrent threads
    private static final int NUM_REQUESTS = 100; // Total number of requests to send

    private int successfulRegistrations = 0;
    private int successfulLogins = 0;
    private int successfulFlightSearches = 0;
    private int successfulUserSearches = 0;
    private int successfulPackageSearches = 0;
    private int successfulOneWaySearches = 0;
    private int successfulTwoWaySearches = 0;
    private int successfulRoundTripSearches = 0;
    private int successfulFilteredSearches = 0;

    static private List<String> registeredEmails = new ArrayList<>(); // Store registered emails for login test
    static private List<String> tokens = new ArrayList<>(); // Store tokens from successful login responses

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
        System.out.println("Tokens: " + tokens);
    }

    //OneWayFlightSearchLoad
    @Test
    public void test3() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<Void>> futures = new ArrayList<>();
        Object lock = new Object();

        for (int i = 0; i < NUM_REQUESTS; i++) {
            futures.add(executorService.submit(() -> {
                String searchPayload = generateGenericOneWaySearchPayload("Egypt", "Germany", "2024-05-24");
                try {
                    HttpResponse searchResponse = HttpUtil.sendAuthorizedPost("http://localhost:8080/api/search/flight/OneWay", searchPayload,tokens.get(tokens.size()-1));
                    int searchResponseCode = searchResponse.getStatusLine().getStatusCode();
                    if (searchResponseCode == 200) {
                        synchronized (lock) {
                            successfulOneWaySearches++;
                        }
                    }
                    System.out.println("One Way Search Response Code: " + searchResponseCode);
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

        System.out.println("Number of successful one way searches: " + successfulOneWaySearches);
    }
    //TwoWayFlightSearchLoad
    @Test
    public void test4() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<Void>> futures = new ArrayList<>();
        Object lock = new Object();

        for (int i = 0; i < NUM_REQUESTS; i++) {
            futures.add(executorService.submit(() -> {
                String searchPayload = generateGenericTwoWaySearchPayload("Spain", "US", "2024-05-23");

                try {
                    HttpResponse searchResponse = HttpUtil.sendAuthorizedPost("http://localhost:8080/api/search/flight/TwoWay", searchPayload,tokens.get(tokens.size()-1));
                    int searchResponseCode = searchResponse.getStatusLine().getStatusCode();
                    if (searchResponseCode == 200) {
                        synchronized (lock) {
                            successfulTwoWaySearches++;
                        }
                    }
                    System.out.println("Two Way Search Response Code: " + searchResponseCode);
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

        System.out.println("Number of successful two way searches: " + successfulTwoWaySearches);
    }
    //RoundTripFlightSearchLoad
    @Test
    public void test5() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<Void>> futures = new ArrayList<>();
        Object lock = new Object();

        for (int i = 0; i < NUM_REQUESTS; i++) {
            futures.add(executorService.submit(() -> {
                String searchPayload = generateGenericRoundTripSearchPayload("UK", "US", "2024-05-24", "2024-05-29");

                try {
                    HttpResponse searchResponse = HttpUtil.sendAuthorizedPost("http://localhost:8080/api/search/flight/RoundTrip", searchPayload,tokens.get(tokens.size()-1));
                    int searchResponseCode = searchResponse.getStatusLine().getStatusCode();
                    if (searchResponseCode == 200) {
                        synchronized (lock) {
                            successfulRoundTripSearches++;
                        }
                    }
                    System.out.println("Round Trip Search Response Code: " + searchResponseCode);
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

        System.out.println("Number of successful round trip searches: " + successfulRoundTripSearches);
    }
    //FilteredFlightSearchLoad
    @Test
    public void test6() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<Void>> futures = new ArrayList<>();
        Object lock = new Object();

        for (int i = 0; i < NUM_REQUESTS; i++) {
            futures.add(executorService.submit(() -> {
                String searchPayload = generateGenericFilteredSearchPayload("Egypt", "Germany", "2024-05-23", "CAI", "BER", "13940", "10000000");

                try {
                    HttpResponse searchResponse = HttpUtil.sendAuthorizedPost("http://localhost:8080/api/search/flight/Filtered", searchPayload,tokens.get(tokens.size()-1));
                    int searchResponseCode = searchResponse.getStatusLine().getStatusCode();
                    if (searchResponseCode == 200) {
                        synchronized (lock) {
                            successfulFilteredSearches++;
                        }
                    }
                    System.out.println("Filtered Search Response Code: " + searchResponseCode);
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

        System.out.println("Number of successful filtered searches: " + successfulFilteredSearches);
    }

    private String generateRegisterPayload(String email, String password) {
        return String.format("{\"firstname\":\"Test\",\"lastname\":\"User\",\"email\":\"%s\",\"password\":\"%s\",\"role\":\"USER\"}", email, password);
    }

    private String generateLoginPayload(String email, String password) {
        return String.format("{\"email\":\"%s\",\"password\":\"%s\"}", email, password);
    }
    ////////////
    private String generateGenericOneWaySearchPayload(String from, String to, String depDate) {
        return String.format("{\"from\":\"%s\",\"depDate\":\"%s\",\"to\":\"%s\"}", from, depDate, to);
    }

    private String generateGenericTwoWaySearchPayload(String from, String to, String depDate) {
        return String.format("{\"from\":\"%s\",\"depDate\":\"%s\",\"to\":\"%s\"}", from, depDate, to);
    }

    private String generateGenericRoundTripSearchPayload(String from, String to, String depDate, String returnDate) {
        return String.format("{\"from\":\"%s\",\"depDate\":\"%s\",\"to\":\"%s\",\"returnDate\":\"%s\"}", from, depDate, to, returnDate);
    }

    private String generateGenericFilteredSearchPayload(String from, String to, String depDate, String depAirport, String arrAirport, String duration, String price) {
        return String.format("{\"from\":\"%s\",\"depDate\":\"%s\",\"to\":\"%s\",\"depAirport\":\"%s\",\"arrAirport\":\"%s\",\"duration\":\"%s\",\"price\":\"%s\"}", from, depDate, to, depAirport, arrAirport, duration, price);
    }
}
