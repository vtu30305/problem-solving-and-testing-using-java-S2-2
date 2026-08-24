import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

class SensorData {
    private final String sensorId;
    private final double temperature;
    private final long timestamp;

    public SensorData(String sensorId, double temperature) {
        this.sensorId = sensorId;
        this.temperature = temperature;
        this.timestamp = System.currentTimeMillis();
    }

    public String getSensorId() {
        return sensorId;
    }

    public double getTemperature() {
        return temperature;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format(
            "Sensor=%s, Temperature=%.2f°C, Time=%d",
            sensorId, temperature, timestamp
        );
    }
}

public class RealTimeStreamAnalytics {

    // Queue represents the incoming real-time data stream
    private static final BlockingQueue<SensorData> dataStream =
            new LinkedBlockingQueue<>();

    // Generate sensor data continuously
    static void generateData() {
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            String sensorId = "S" + ((i % 4) + 1);

            // Generate temperature between 20 and 100
            double temperature = 20 + random.nextDouble() * 80;

            dataStream.add(new SensorData(sensorId, temperature));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    // Process the incoming stream
    static void processStream() {

        List<SensorData> window = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            try {
                SensorData data = dataStream.take();

                System.out.println("\nReceived: " + data);

                window.add(data);

                // Detect high-temperature events
                if (data.getTemperature() > 80) {
                    System.out.println(
                        "ALERT: High temperature detected!"
                    );
                }

                // Perform analytics every 5 records
                if (window.size() == 5) {

                    double averageTemperature = window.stream()
                            .mapToDouble(SensorData::getTemperature)
                            .average()
                            .orElse(0);

                    double maximumTemperature = window.stream()
                            .mapToDouble(SensorData::getTemperature)
                            .max()
                            .orElse(0);

                    double minimumTemperature = window.stream()
                            .mapToDouble(SensorData::getTemperature)
                            .min()
                            .orElse(0);

                    System.out.println("\n===== WINDOW ANALYTICS =====");
                    System.out.printf(
                        "Average Temperature: %.2f°C%n",
                        averageTemperature
                    );
                    System.out.printf(
                        "Maximum Temperature: %.2f°C%n",
                        maximumTemperature
                    );
                    System.out.printf(
                        "Minimum Temperature: %.2f°C%n",
                        minimumTemperature
                    );
                    System.out.println("============================");

                    // Clear window for next batch
                    window.clear();
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public static void main(String[] args) {

        System.out.println(
            "===== REAL-TIME STREAM ANALYTICS ENGINE ====="
        );

        // Producer thread
        Thread producer = new Thread(() -> generateData());

        // Consumer/analytics thread
        Thread consumer = new Thread(() -> processStream());

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(
            "\n===== STREAM PROCESSING COMPLETED ====="
        );
    }
}
