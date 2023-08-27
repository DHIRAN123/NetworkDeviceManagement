package com.example.Network.Device.Management.System.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Network.Device.Management.System.model.Device;


@Repository
public interface DeviceRepository extends JpaRepository<Device,Long>{

}
