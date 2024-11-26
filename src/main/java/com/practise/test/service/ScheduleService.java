package com.practise.test.service;

import com.practise.test.entity.ExamSchedule;
import com.practise.test.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    // Lấy tất cả các Schedule
    public Map<String, Object> getAllSchedules() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<ExamSchedule> schedules = scheduleRepository.findAll();

            List<Map<String, Object>> schedudata = schedules.stream()
                    .map(sche -> {
                        Map<String, Object> data = new HashMap<>();
                        data.put("id", sche.getId());
                        data.put("examPeriod", sche.getExamPeriod());
                        data.put("organizationId", sche.getOrganizationId());
                        data.put("description", sche.getDescription());
                        data.put("note", sche.getNote());
                        data.put("startDate", sche.getStartDate());
                        data.put("endDate", sche.getEndDate());
                        return data;
                    }).collect(Collectors.toList());

            response.put("data", schedudata);
            response.put("message", "Get all schedules successfully");
            response.put("success", true);
            response.put("status", 200);
            response.put("error", null);
        } catch (Exception e) {
            response.put("data", null);
            response.put("message", "Internal server error");
            response.put("success", false);
            response.put("error", e.getMessage());
            response.put("status", 500);
        }
        return response;
    }

    public Map<String, Object> createSchedule(ExamSchedule data) {
        Map<String, Object> response = new HashMap<>();

        System.out.println("Organization ID: " + data.getOrganizationId());

        try {
            // Kiểm tra đầu vào
            if (data == null || data.getExamPeriod() == null || data.getStartDate() == null || data.getEndDate() == null ) {
                response.put("data", null);
                response.put("message", "examPeriod, startDate, endDate  are required");
                response.put("success", false);
                response.put("error", Map.of("message", "examPeriod, startDate, endDate and  are required", "errorDetail", "examPeriod, startDate, endDate   are required"));
                response.put("status", HttpStatus.BAD_REQUEST.value());
                return response;
            }

            // Tạo schedule mới
            ExamSchedule schedule = new ExamSchedule();
            schedule.setId(UUID.randomUUID().toString());

            schedule.setExamPeriod(data.getExamPeriod());
            schedule.setStartDate(data.getStartDate());
            schedule.setEndDate(data.getEndDate());
            schedule.setOrganizationId(data.getOrganizationId());
            schedule.setDescription(data.getDescription());

            // Lưu schedule vào cơ sở dữ liệu
            ExamSchedule schedu =scheduleRepository.save(schedule);

            // Tìm lại schedule sau khi lưu
            Optional<ExamSchedule> createdScheduleOpt = scheduleRepository.findById(schedule.getId());
            if (createdScheduleOpt.isEmpty()) {
                response.put("data", null);
                response.put("message", "Create schedule failed");
                response.put("success", false);
                response.put("error", Map.of("message", "Create schedule failed", "errorDetail", "Create schedule failed"));
                response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
                return response;
            }

            Map<String,Object> schedudata = new HashMap<>();
            schedudata.put("id", schedu.getId());
            schedudata.put("examPeriod", schedu.getExamPeriod());
            schedudata.put("organizationId", schedu.getOrganizationId());
            schedudata.put("description", schedu.getDescription());
            schedudata.put("note", schedu.getNote());
            schedudata.put("startDate", schedu.getStartDate());
            schedudata.put("endDate", schedu.getEndDate());
            // Trả về thông tin schedule đã tạo thành công

            response.put("data", schedudata);
            response.put("message", "Create schedule successfully");
            response.put("success", true);
            response.put("error", null);
            response.put("status", HttpStatus.CREATED.value());

            return response;

        }
        catch (Exception e) {
            response.put("data", null);
            response.put("message", "Internal server error");
            response.put("success", false);
            response.put("error", Map.of("message", e.getMessage(), "errorDetail", e.getMessage()));
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return response;
        }
    }

    // Cập nhật Schedule
    public Map<String, Object> updateSchedule(String scheduleId, ExamSchedule data) {
        Map<String, Object> response = new HashMap<>();

        try {
            Optional<ExamSchedule> existingSchedule = scheduleRepository.findById(scheduleId);
            if (!existingSchedule.isPresent()) {
                response.put("status", 404);
                response.put("success", false);
                response.put("message", "Schedule not found");
                response.put("data", null);
                response.put("error", "Schedule not found in the system");
                return response;
            }

            if (data == null || data.getExamPeriod() == null || data.getStartDate() == null || data.getEndDate() == null ) {
                response.put("data", null);
                response.put("message", "examPeriod, startDate, endDate  are required");
                response.put("success", false);
                response.put("error", Map.of("message", "examPeriod, startDate, endDate and  are required", "errorDetail", "examPeriod, startDate, endDate   are required"));
                response.put("status", HttpStatus.BAD_REQUEST.value());
                return response;
            }



            ExamSchedule schedule = existingSchedule.get();

            schedule.setOrganizationId(data.getOrganizationId());
            schedule.setExamPeriod(data.getExamPeriod());
            schedule.setDescription(data.getDescription());
            schedule.setNote(data.getNote());
            schedule.setStartDate(data.getStartDate());
            schedule.setEndDate(data.getEndDate());

            ExamSchedule updatedSchedule = scheduleRepository.save(schedule);

            Map<String,Object> schedudata = new HashMap<>();

            schedudata.put("id", updatedSchedule.getId());
            schedudata.put("examPeriod", updatedSchedule.getExamPeriod());
            schedudata.put("organizationId", updatedSchedule.getOrganizationId());
            schedudata.put("description", updatedSchedule.getDescription());
            schedudata.put("note", updatedSchedule.getNote());
            schedudata.put("startDate", updatedSchedule.getStartDate());
            schedudata.put("endDate", updatedSchedule.getEndDate());

            response.put("status", 200);
            response.put("success", true);
            response.put("message", "Schedule updated successfully");
            response.put("data", schedudata);
            response.put("error", null);
        } catch (Exception e) {
            response.put("status", 500);
            response.put("success", false);
            response.put("message", "Error updating schedule");
            response.put("data", null);
            response.put("error", e.getMessage());
        }
        return response;
    }

    // Xóa Schedule
    public Map<String, Object> deleteSchedule(String scheduleId) {
        Map<String, Object> response = new HashMap<>();

        try {
            if (scheduleId == null || scheduleId.isEmpty()) {
                response.put("status", 400);
                response.put("success", false);
                response.put("message", "Schedule ID is required");
                response.put("data", null);
                response.put("error", "Schedule ID is required");
                return response;
            }

            Optional<ExamSchedule> existingSchedule = scheduleRepository.findById(scheduleId);

            if (!existingSchedule.isPresent()) {
                response.put("status", 404);
                response.put("success", false);
                response.put("message", "Schedule not found");
                response.put("data", null);
                response.put("error", "Schedule not found in the system");
                return response;
            }

            scheduleRepository.deleteById(scheduleId);

            response.put("status", 200);
            response.put("success", true);
            response.put("message", "Schedule deleted successfully");
            response.put("data", Map.of("id", scheduleId));
            response.put("error", null);
        } catch (Exception e) {
            response.put("status", 500);
            response.put("success", false);
            response.put("message", "Error deleting schedule");
            response.put("data", null);
            response.put("error", e.getMessage());
        }
        return response;
    }
}
