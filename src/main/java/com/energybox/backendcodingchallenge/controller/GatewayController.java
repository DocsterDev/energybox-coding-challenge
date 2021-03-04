package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.service.GatewayService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/gateways")
public class GatewayController {

    private final GatewayService gatewayService;

    @ApiOperation( value = "Create a gateway", response = Gateway.class )
    @PostMapping
    public ResponseEntity<Gateway> create(@RequestBody Gateway gateway) {
        return new ResponseEntity<>(  HttpStatus.OK );
    }
}
