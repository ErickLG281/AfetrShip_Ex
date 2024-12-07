package com.erick.aftership.controller;

import com.erick.aftership.service.PackageTrackingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrackingController {

    private final PackageTrackingService trackingService;

    public TrackingController(PackageTrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @GetMapping("/track")
    public String trackPackage(@RequestParam String trackingNumber) {
        return trackingService.trackPackage(trackingNumber);
    }
}