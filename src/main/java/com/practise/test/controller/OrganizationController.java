package com.practise.test.controller;

import com.practise.test.entity.Organization;
import com.practise.test.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/get-organizations")
    public Map<String, Object> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }

    @PostMapping("/create-organization")
    public ResponseEntity<Map<String, Object>> createOrganization(@RequestBody Organization organization) {
        Map<String, Object> response = organizationService.createOrganization(organization);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/update-organization/{orgId}")
    public ResponseEntity<Map<String, Object>> updateOrganization(@PathVariable String orgId, @RequestBody Organization organization) {
        Map<String, Object> response = organizationService.updateOrganization(orgId, organization);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-organization/{organizationId}")
    public Map<String, Object> deleteOrganization(@PathVariable("organizationId") String organizationId) {
        return organizationService.deleteOrganization(organizationId);
    }
}
