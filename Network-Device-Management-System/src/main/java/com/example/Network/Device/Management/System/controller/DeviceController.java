package com.example.Network.Device.Management.System.controller;

import com.example.Network.Device.Management.System.model.Device;
import com.example.Network.Device.Management.System.repository.DeviceRepository;
import com.example.Network.Device.Management.System.response.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceRepository deviceRepository;

    @PostMapping
    public ResponseEntity<ApiResponse> addDevice(@RequestBody Device device) {
        Device savedDevice = deviceRepository.save(device);
        ApiResponse response = new ApiResponse(true, "Device added successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        List<Device> devices = deviceRepository.findAll();
        return ResponseEntity.ok(devices);
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<ApiResponse> getDeviceById(@PathVariable Long deviceId) {
        Optional<Device> optionalDevice = deviceRepository.findById(deviceId);
        if (optionalDevice.isPresent()) {
            ApiResponse response = new ApiResponse(true, "Device retrieved successfully");
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse(false, "Device not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("/{deviceId}")
    public ResponseEntity<ApiResponse> updateDevice(@PathVariable Long deviceId, @RequestBody Device updatedDevice) {
        Optional<Device> optionalDevice = deviceRepository.findById(deviceId);
        if (optionalDevice.isPresent()) {
            Device existingDevice = optionalDevice.get();
            existingDevice.setName(updatedDevice.getName());
            existingDevice.setVersion(updatedDevice.getVersion());
            existingDevice.setBrand(updatedDevice.getBrand());

            Device savedDevice = deviceRepository.save(existingDevice);
            ApiResponse response = new ApiResponse(true, "Device updated successfully");
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse(false, "Device not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{deviceId}")
    public ResponseEntity<ApiResponse> deleteDevice(@PathVariable Long deviceId) {
        Optional<Device> optionalDevice = deviceRepository.findById(deviceId);
        if (optionalDevice.isPresent()) {
            deviceRepository.delete(optionalDevice.get());
            ApiResponse response = new ApiResponse(true, "Device deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse(false, "Device not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }}

//    @GetMapping("/statistics/total")
//    public ResponseEntity<Long> getTotalDeviceCount() {
//        long totalDevices = deviceRepository.count();
//        return ResponseEntity.ok(totalDevices);
//    }
//
//    @GetMapping("/statistics/brand-distribution")
//    public ResponseEntity<ApiResponse> getDeviceBrandDistribution() {
//        List<Object[]> brandDistribution = deviceRepository.getDeviceBrandDistribution();
//        ApiResponse response = new ApiResponse(true, "Device brand distribution retrieved successfully");
//        // ... Process brandDistribution and update response ...
//
//        return ResponseEntity.ok(response);
//    }
//}
