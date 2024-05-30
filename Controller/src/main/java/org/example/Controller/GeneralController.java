package org.example.Controller;


import org.example.Service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/controller")
public class GeneralController {

    @Autowired
    private GeneralService generalService;

    @PostMapping("/set_mq")
    public ResponseEntity<String> setMessageQueue() {
        String result = generalService.setMessageQueue();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/set_max_thread_count")
    public ResponseEntity<String> setMaxThreadCount(@RequestParam String serviceName, @RequestParam int count) {
        String result = generalService.setMaxThreadCount();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/set_max_db_connections_count")
    public ResponseEntity<String> setMaxDbConnectionsCount(@RequestParam String serviceName, @RequestParam int count) {
        String result = generalService.setMaxDbConnectionsCount();
        return ResponseEntity.ok(result);}

        @PostMapping("/add_command")
        public ResponseEntity<String> addCommand() {
            String result = generalService.addCommand();
            return ResponseEntity.ok(result);
        }

        @DeleteMapping("/delete_command")
        public ResponseEntity<String> deleteCommand(@RequestParam String serviceName, @RequestParam String commandName) {
            String result = generalService.deleteCommand();
            return ResponseEntity.ok(result);
        }

        @PutMapping("/update_command")
        public ResponseEntity<String> updateCommand() {
            String result = generalService.updateCommand();
            return ResponseEntity.ok(result);
        }

        @PutMapping("/update_class")
        public ResponseEntity<String> updateClass() {
            String result = generalService.updateClass();
            return ResponseEntity.ok(result);
        }

        @PostMapping("/freeze")
        public ResponseEntity<String> freezeApp(@RequestParam String serviceName) {
            String result = generalService.freezeApp(serviceName);
            return ResponseEntity.ok(result);
        }

        @PostMapping("/continue")
        public ResponseEntity<String> continueApp(@RequestParam String serviceName) {
            String result = generalService.continueApp(serviceName);
            return ResponseEntity.ok(result);
        }

        @PostMapping("/set_error_reporting_level")
        public ResponseEntity<String> setErrorReportingLevel(@RequestParam String serviceName, @RequestParam String level) {
            String result = generalService.setErrorReportingLevel();
            return ResponseEntity.ok(result);
        }
    }
