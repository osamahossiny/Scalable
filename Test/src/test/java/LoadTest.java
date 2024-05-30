import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class LoadTest {

    private static final int NUM_THREADS = 5; // Number of concurrent threads
    private static final int NUM_REQUESTS = 10000; // Total number of requests to send
    private static final int HEALTH_CHECK_TIMEOUT = 5000; // Health check timeout in milliseconds

    @Test
    public void testAirlineLoad() throws InterruptedException, IOException {
        String url = "http://localhost:8080/api/v1/airline";
        String payload = "{\"name\":\"Test Airline\"}";
        if (isServiceAvailable(url)) {
            loadTestEndpoint(url, payload);
        } else {
            System.err.println("Service is not available: " + url);
        }
    }

    @Test
    public void testUserLoad() throws InterruptedException, IOException {
        String url = "http://localhost:8080/api/v1/user";
        String payload = "{\"username\":\"testuser\",\"password\":\"testpass\"}";
        if (isServiceAvailable(url)) {
            loadTestEndpoint(url, payload);
        } else {
            System.err.println("Service is not available: " + url);
        }
    }

    @Test
    public void testFlightLoad() throws InterruptedException, IOException {
        String url = "http://localhost:8080/api/v1/flight";
        String payload = "{\"flightNumber\":\"12345\"}";
        if (isServiceAvailable(url)) {
            loadTestEndpoint(url, payload);
        } else {
            System.err.println("Service is not available: " + url);
        }
    }

    @Test
    public void testFlightPackageLoad() throws InterruptedException, IOException {
        String url = "http://localhost:8080/api/v1/flightPackage";
        String payload = "{\"packageName\":\"Test Package\"}";
        if (isServiceAvailable(url)) {
            loadTestEndpoint(url, payload);
        } else {
            System.err.println("Service is not available: " + url);
        }
    }

    @Test
    public void testFlightReservationLoad() throws InterruptedException, IOException {
        String url = "http://localhost:8080/api/v1/flightReservation";
        String payload = "{\"reservationId\":\"12345\"}";
        if (isServiceAvailable(url)) {
            loadTestEndpoint(url, payload);
        } else {
            System.err.println("Service is not available: " + url);
        }
    }

    @Test
    public void testPlaneLoad() throws InterruptedException, IOException {
        String url = "http://localhost:8080/api/v1/plane";
        String payload = "{\"planeId\":\"7890\"}";
        if (isServiceAvailable(url)) {
            loadTestEndpoint(url, payload);
        } else {
            System.err.println("Service is not available: " + url);
        }
    }

    @Test
    public void testPlaneSeatLoad() throws InterruptedException, IOException {
        String url = "http://localhost:8080/api/v1/planeSeat";
        String payload = "{\"seatNumber\":\"12A\"}";
        if (isServiceAvailable(url)) {
            loadTestEndpoint(url, payload);
        } else {
            System.err.println("Service is not available: " + url);
        }
    }

    private boolean isServiceAvailable(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(HEALTH_CHECK_TIMEOUT);
        connection.setReadTimeout(HEALTH_CHECK_TIMEOUT);

        int responseCode = connection.getResponseCode();
        connection.disconnect();
        return (responseCode == 200);
    }

    private void loadTestEndpoint(String url, String payload) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<Void>> futures = new ArrayList<>();

        for (int i = 0; i < NUM_REQUESTS; i++) {
            futures.add(executorService.submit(new Callable<Void>() {
                @Override
                public Void call() {
                    try {
                        HttpUtil.sendPost(url, payload);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }));
        }

        executorService.shutdown();
        if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
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
    }
}
