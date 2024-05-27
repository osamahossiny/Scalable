package org.example.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

@Service
public class GeneralService {

    private static final String CONFIG_FILE_PATH = "C:/Users/Ahmed Elbltagy/Desktop/temp/Scalabe/ConfigServer/src/configration_files/application.yml";


    @Autowired
    private RestTemplate restTemplate;
    public String setMessageQueue() {
        // Implement the logic to update the message queue configuration
        return "Message Queue Updated for Service: ";
    }

    public String setMaxThreadCount() {
        // Implement the logic to set the max thread count
        return "Max Thread Count Set for Service: " ;
    }

    public String setMaxDbConnectionsCount() {
        // Implement the logic to set the max DB connections count
        return "Max DB Connections Count Set for Service: " ;
    }

    public String addCommand() {
        // Implement the logic to add a command
        return "Command Added to Service: ";
    }

    public String deleteCommand() {
        // Implement the logic to delete a command
        return "Command Deleted from Service: " ;
    }

    public String updateCommand() {
        // Implement the logic to update a command
        return "Command Updated in Service: " ;
    }

    public String updateClass() {
        // Implement the logic to update a class
        return "Class Updated in Service: ";
    }

    public String freezeApp(String serviceName) {
        try {
            updateAdminStatus("frozen");
            refreshConfiguration();
            return "App Frozen for Service: " + serviceName;
        } catch (IOException e) {
            return "Error freezing app for Service: " + serviceName;
        }
    }

    public String continueApp(String serviceName) {
        try {
            updateAdminStatus("running");
            refreshConfiguration();
            return "App Continued for Service: " + serviceName;

        } catch (IOException e) {
            return "Error continuing app for Service: " + serviceName;
        }
    }

    public String setErrorReportingLevel() {
        // Implement the logic to set the error reporting level
        return "Error Reporting Level Set for Service: " ;
    }
    private void updateAdminStatus(String status) throws IOException {
        // Read the YAML configuration file
        FileInputStream inputStream = new FileInputStream(CONFIG_FILE_PATH);
        Yaml yaml = new Yaml();
        Map<String, Object> config = yaml.load(inputStream);
        inputStream.close();

        // Update the admin.status property
        Map<String, Object> adminConfig = (Map<String, Object>) config.get("admin");
        adminConfig.put("status", status);

        // Save the updated configuration back to the file
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        yaml = new Yaml(options);
        FileWriter writer = new FileWriter(CONFIG_FILE_PATH);
        yaml.dump(config, writer);
        writer.close();
    }
    private void refreshConfiguration() {
        String actuatorUrl = "http://admin_service/actuator/refresh";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        restTemplate.postForObject(actuatorUrl, entity, String.class);
    }
}
