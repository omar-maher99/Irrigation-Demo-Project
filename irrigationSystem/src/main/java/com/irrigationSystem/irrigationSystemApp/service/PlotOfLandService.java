package com.irrigationSystem.irrigationSystemApp.service;

import com.irrigationSystem.irrigationSystemApp.dto.PlotOFLandDto;
import com.irrigationSystem.irrigationSystemApp.dto.ResponseDto;
import com.irrigationSystem.irrigationSystemApp.entity.PlotOfLandEntity;
import com.irrigationSystem.irrigationSystemApp.exception.ResourceNotFoundException;
import com.irrigationSystem.irrigationSystemApp.repository.PlotOfLandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlotOfLandService {

    @Autowired
    ModelMapper modelMapper;

    private PlotOfLandRepository plotOfLandRepository;

    public PlotOfLandService(PlotOfLandRepository plotOfLandRepository) {
        this.plotOfLandRepository = plotOfLandRepository;
    }

    public ResponseDto createPlotOfLand(PlotOFLandDto plotOFLandDto) {

        //convert dto to entity
        PlotOfLandEntity plotOfLandEntity = modelMapper.map(plotOFLandDto, PlotOfLandEntity.class);

        //save and returns entity
        PlotOfLandEntity newPlotOfLandEntity = plotOfLandRepository.save(plotOfLandEntity);

        //convert entity to dto
        PlotOFLandDto newPlotOFLandDto = modelMapper.map(newPlotOfLandEntity, PlotOFLandDto.class);

        //Preparing response for the user
        ResponseDto responseDto = new ResponseDto();
        responseDto.setSingleRecord(newPlotOFLandDto);
        responseDto.setMessage("Plot added successfully with ID: " + newPlotOFLandDto.getId());

        return responseDto;

    }
    public ResponseDto updatePlotOfLand(PlotOFLandDto plotOFLandDto, long id) {

        //Making sure that entity exists
        PlotOfLandEntity plotOfLandEntity = plotOfLandRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("This land","id",id));

        //Setting the id in the Dto to update instead of insert
        plotOFLandDto.setId(plotOfLandEntity.getId());
        plotOFLandDto.setCreatedDate(plotOfLandEntity.getCreatedDate());

        //Converting dto to entity
        plotOfLandEntity = mapToEntity(plotOFLandDto);

        //Fetching the returned data
        PlotOfLandEntity updatedPlotOfLandEntity = plotOfLandRepository.save(plotOfLandEntity);

        //Preparing response for the user
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("This land is updated successfully");
        responseDto.setSingleRecord(mapToDto(updatedPlotOfLandEntity));
        return responseDto;

    }

    public ResponseDto getAllPlotOfLand(){

        //Finding all plot of lands
        List<PlotOfLandEntity> plotOfLandEntityList = plotOfLandRepository.findAll();

        //Converting list of entities to list of dto's
        List<PlotOFLandDto> plotOFLandDtoList = plotOfLandEntityList.stream().map(plotOfLandEntity -> mapToDto(plotOfLandEntity)).collect(Collectors.toList());

        //Preparing the response
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMultiRecords(plotOFLandDtoList);
        responseDto.setMessage("List of all plot of lands ");

        return  responseDto;
    }

    public ResponseDto getPlotsNeddsToBeIrrigated(){

        //Gets plots needs to be irrigated (Native query)
        List<PlotOfLandEntity> plotOfLandEntityList = plotOfLandRepository.findPlotsNeedIrrigation();

        //Converting list of entities to list of dto's
        List<PlotOFLandDto> plotOFLandDtoList = plotOfLandEntityList.stream().map(plotOfLandEntity -> mapToDto(plotOfLandEntity)).collect(Collectors.toList());

        //Preparing the response
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMultiRecords(plotOFLandDtoList);
        responseDto.setMessage("List of all plot of lands ");

        return  responseDto;
    }

    public ResponseDto irrigatePlots(){

        //Gets plots needs to be irrigated (Native query)
        List<PlotOfLandEntity> plotOfLandEntityList = plotOfLandRepository.findPlotsNeedIrrigation();

        //Initializing a list to store returned data
        List<PlotOfLandEntity> updatedPlotOfLandEntityList = new ArrayList<PlotOfLandEntity>();

        for (PlotOfLandEntity plot:plotOfLandEntityList) {
            plot.setIrrigated("Y");
            updatedPlotOfLandEntityList.add(plotOfLandRepository.save(plot));
        }

        //Converting list of entities to list of dto's
        List<PlotOFLandDto> plotOFLandDtoList = updatedPlotOfLandEntityList.stream().map(plotOfLandEntity -> mapToDto(plotOfLandEntity)).collect(Collectors.toList());

        //Preparing the response
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMultiRecords(plotOFLandDtoList);
        responseDto.setMessage("List of all updated plots of lands ");

        return  responseDto;
    }

    public ResponseDto irrigateAPlot(PlotOFLandDto plotOFLandDto, long id) {

        //Making sure that entity exists
        PlotOfLandEntity plotOfLandEntity = plotOfLandRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("This land","id",id));

        //Setting the id in the Dto to update instead of insert
        plotOFLandDto.setId(plotOfLandEntity.getId());
        plotOFLandDto.setCreatedDate(plotOfLandEntity.getCreatedDate());
        plotOFLandDto.setIrrigated("Y");

        //Converting dto to entity
        plotOfLandEntity = mapToEntity(plotOFLandDto);

        //Fetching the returned data
        PlotOfLandEntity updatedPlotOfLandEntity = plotOfLandRepository.save(plotOfLandEntity);

        //Preparing response for the user
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("This land is irrigated successfully");
        responseDto.setSingleRecord(mapToDto(updatedPlotOfLandEntity));
        return responseDto;

    }

    //method to map entities to dto's
    public PlotOFLandDto mapToDto(PlotOfLandEntity plotOfLandEntity){
        PlotOFLandDto plotOFLandDto = new PlotOFLandDto();

        plotOFLandDto.setCreatedDate(plotOfLandEntity.getCreatedDate());
        plotOFLandDto.setUpdatedDate(plotOfLandEntity.getUpdatedDate());
        plotOFLandDto.setId(plotOfLandEntity.getId());
        plotOFLandDto.setIrrigated(plotOfLandEntity.getIrrigated());
        plotOFLandDto.setLength(plotOfLandEntity.getLength());
        plotOFLandDto.setWidth(plotOfLandEntity.getWidth());
        plotOFLandDto.setLocation(plotOfLandEntity.getLocation());
        plotOFLandDto.setSoil_type(plotOfLandEntity.getSoil_type());
        plotOFLandDto.setName(plotOfLandEntity.getName());

        return plotOFLandDto;
    }

    //method to map dto's to entities
    public PlotOfLandEntity mapToEntity(PlotOFLandDto plotOFLandDto){

        PlotOfLandEntity plotOfLandEntity = new PlotOfLandEntity();

        plotOfLandEntity.setCreatedDate(plotOFLandDto.getCreatedDate());
        plotOfLandEntity.setId(plotOFLandDto.getId());
        plotOfLandEntity.setIrrigated(plotOFLandDto.getIrrigated());
        plotOfLandEntity.setLength(plotOFLandDto.getLength());
        plotOfLandEntity.setLocation(plotOFLandDto.getLocation());
        plotOfLandEntity.setName(plotOFLandDto.getName());
        plotOfLandEntity.setSoil_type(plotOFLandDto.getSoil_type());
        plotOfLandEntity.setUpdatedDate(plotOFLandDto.getUpdatedDate());
        plotOfLandEntity.setWidth(plotOFLandDto.getWidth());

        return plotOfLandEntity;
    }
}
