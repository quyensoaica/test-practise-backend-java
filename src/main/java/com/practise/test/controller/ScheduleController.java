package com.practise.test.controller;

import com.practise.test.entity.ExamSchedule;
import com.practise.test.entity.Organization;
import com.practise.test.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/schedules")

public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/get-schedules")
    public Map<String, Object> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    @PostMapping("/create-schedule")
    public ResponseEntity<Map<String, Object>> createSchedule(@RequestBody ExamSchedule examSchedule) {
        Map<String, Object> response = scheduleService.createSchedule(examSchedule);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/update-schedule/{scheduid}")
    public ResponseEntity<Map<String, Object>> updateSchedule(@PathVariable String scheduid, @RequestBody ExamSchedule examSchedule) {
        Map<String, Object> response = scheduleService.updateSchedule(scheduid, examSchedule);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-schedule/{scheduid}")
    public Map<String, Object> deleteSchedule(@PathVariable String scheduid) {
        return scheduleService.deleteSchedule(scheduid);
    }

}
