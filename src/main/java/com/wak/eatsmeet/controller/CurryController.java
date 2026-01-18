package com.wak.eatsmeet.controller;

import com.wak.eatsmeet.dto.ApiResponse;
import com.wak.eatsmeet.dto.food.curryDto.CreateCurryDto;
import com.wak.eatsmeet.dto.food.curryDto.CurryResponse;
import com.wak.eatsmeet.dto.food.curryDto.UpdateCurryDto;
import com.wak.eatsmeet.mapper.CurryMapper;
import com.wak.eatsmeet.model.food.Curry;
import com.wak.eatsmeet.service.CurryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curry")
public class CurryController {

    private final CurryService curryService;
    private final CurryMapper curryMapper;

    public CurryController(CurryService curryService, CurryMapper curryMapper) {
        this.curryService = curryService;
        this.curryMapper = curryMapper;
    }


    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUB_ADMIN')")
    public ResponseEntity<ApiResponse<CurryResponse>> addCurry(@Valid @RequestBody CreateCurryDto curryRequest) {
        Curry savedCurry = curryService.addCurry(curryMapper.toEntity(curryRequest));
        CurryResponse dto = curryMapper.toDto(savedCurry);

        ApiResponse<CurryResponse> response = new ApiResponse<>(
                "Curry added successfully.",
                dto
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hsAuthority('SUB_ADMIN')")
    public ResponseEntity<ApiResponse<CurryResponse>> updateCurry(@PathVariable int id, @Valid @RequestBody UpdateCurryDto updateCurryDto) {
        Curry updatedCurry = curryService.updateCurry(id, curryMapper.toEntity(updateCurryDto));

        ApiResponse<CurryResponse> response = new ApiResponse<>(
                "Curry updated successfully.",
                curryMapper.toDto(updatedCurry)
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteId/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable int id) {
        curryService.deleteById(id);
        ApiResponse<Void> response = new ApiResponse<>(
                "Curry deleted successfully.",
                null
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<CurryResponse>>> getAllCurry() {
        List<Curry> curries = curryService.getAll();

        List<CurryResponse> curryResponses = curries.stream()
                .map(curryMapper::toDto)
                .toList();

        ApiResponse<List<CurryResponse>> response = new ApiResponse<>(
                "Curry fetched successfully.",
                curryResponses
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/searchId/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUB_ADMIN')")
    public ResponseEntity<ApiResponse<CurryResponse>> getCurryById(@PathVariable int id) {
        Curry curry = curryService.getById(id);

        ApiResponse<CurryResponse> response = new ApiResponse<>(
                "Curry fetched successfully.",
                curryMapper.toDto(curry)
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/searchName/{name}")
    public ResponseEntity<ApiResponse<List<CurryResponse>>> getCurriesByName(@PathVariable String name) {
        List<Curry> curries = curryService.getByName(name);

        List<CurryResponse> curryResponses = curries.stream()
                .map(curryMapper::toDto)
                .toList();

        ApiResponse<List<CurryResponse>> response = new ApiResponse<>(
                "Curry fetched successfully.",
                curryResponses
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/searchPrice")
    public ResponseEntity<?> findAllCurryWithRangePrice(@RequestParam int min, @RequestParam int max) {
        List<Curry> curries = curryService.getAll(min, max);

        List<CurryResponse> curryResponses = curries.stream()
                .map(curryMapper::toDto)
                .toList();

        ApiResponse<List<CurryResponse>> response = new ApiResponse<>(
                "Curry fetched successfully.",
                curryResponses
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
