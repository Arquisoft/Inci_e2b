package inci.dashboard.kafka.producers;

public interface KafkaProducer {
    // Suggestions
    public void updateStatus(String incidenciaID, String estado);

}
