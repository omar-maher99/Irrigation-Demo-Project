package com.irrigationSystem.irrigationSystemApp.controller;


import com.irrigationSystem.irrigationSystemApp.dto.PlotOFLandDto;
import com.irrigationSystem.irrigationSystemApp.dto.ResponseDto;
import com.irrigationSystem.irrigationSystemApp.service.PlotOfLandService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@NoArgsConstructor
@RestController
@RequestMapping("/plotOfLand/")
public class PlotOfLandController {

    @Autowired
    private PlotOfLandService plotOfLandService;

    public PlotOfLandController(PlotOfLandService plotOfLandService) {
        this.plotOfLandService = plotOfLandService;
    }

    //The URI indicates the path to reach the API
    @PostMapping("/create-plot")
    public ResponseEntity<ResponseDto> createPlotOfLand(@RequestBody PlotOFLandDto plotOFLandDto) {

        return new ResponseEntity<>(plotOfLandService.createPlotOfLand(plotOFLandDto), HttpStatus.CREATED);

    }

    //The URI indicates the path to reach the API and includes a variable
    @PutMapping("/update-plot/{id}")
    public ResponseEntity<ResponseDto> updatePlotOfLand(@RequestBody PlotOFLandDto plotOFLandDto, @PathVariable(name = "id") long id ) {
        return ResponseEntity.ok(plotOfLandService.updatePlotOfLand(plotOFLandDto, id));
    }

    @GetMapping("/get-all-plot-of-lands")
    public ResponseEntity<ResponseDto> getAllPlotOfLands(){
        return ResponseEntity.ok(plotOfLandService.getAllPlotOfLand());
    }

    @GetMapping("/get-plots-need-irrigation")
    public ResponseEntity<ResponseDto> getPlotsNeedIrrigation(){
        return ResponseEntity.ok(plotOfLandService.getPlotsNeddsToBeIrrigated());
    }

    @PutMapping("/sensor-irrigate-all-plots")
    public ResponseEntity<ResponseDto> irrigatePlots(){
        return ResponseEntity.ok(plotOfLandService.irrigatePlots());
    }

    @PutMapping("/sensor-irrigate-a-plot/{id}")
    public ResponseEntity<ResponseDto> irrigateAPlot(@RequestBody PlotOFLandDto plotOFLandDto, @PathVariable(name = "id") long id ){
        return ResponseEntity.ok(plotOfLandService.irrigateAPlot(plotOFLandDto,id));
    }

    }
