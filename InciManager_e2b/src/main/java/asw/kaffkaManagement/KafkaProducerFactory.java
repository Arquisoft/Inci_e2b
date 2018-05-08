package asw.kaffkaManagement;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableKafka
public class KafkaProducerFactory {

	  @Bean
	    public ProducerFactory<String, String> producerFactory() {
	        return new DefaultKafkaProducerFactory<>(producerConfigs());
	    }

	    @Bean
	    public Map<String, Object> producerConfigs() {
			String usuario = "rjkjinkm";
			String password = "VjGoFUYjC7H22ZW5IeoywuYmcXAN6OfK";
			String jaasTemplate = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
			String jaasCfg = String.format(jaasTemplate, usuario, password);

	        Map<String, Object> props = new HashMap<>();
	        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "http://velomobile-01.srvs.cloudkafka.com:9094");
	        props.put(ProducerConfig.RETRIES_CONFIG, 0);
	        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
	        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
	        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
	        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
			props.put(ConsumerConfig.GROUP_ID_CONFIG, "es.uniovi");
            props.put("enable.auto.commit", "true");
            props.put("auto.commit.interval.ms", "1000");
            props.put("auto.offset.reset", "earliest");
            props.put("session.timeout.ms", "30000");
            props.put("key.deserializer", StringDeserializer.class);
            props.put("value.deserializer", StringDeserializer.class);
            props.put("key.serializer", StringSerializer.class);
            props.put("value.serializer", StringSerializer.class);
            props.put("security.protocol", "SASL_SSL");
            props.put("sasl.mechanism", "SCRAM-SHA-256");
			props.put("sasl.jaas.config", jaasCfg);
	        return props;
	    }

	    @Bean
	    public KafkaTemplate<String, String> kafkaTemplate() {
	        return new KafkaTemplate<String, String>(producerFactory());
	    }
}
