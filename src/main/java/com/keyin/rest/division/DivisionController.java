package com.keyin.rest.division;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class DivisionController {
    @Autowired
    private DivisionService divisionService;

    @GetMapping("/division")
    public List<Division> getAllDivisions() {
        return divisionService.getAllDivisions();
    }

    @GetMapping("/division/{id}")
    public Division getDivisionById(@PathVariable long id) {
        return divisionService.getDivisionById(id);
    }

    @GetMapping("/division_search")
    public List<Division> searchDivisions(@RequestParam(value = "division_name", required = false) String divisionName,
                                          @RequestParam(value = "division_start_birth_year", required = false) String divisionStartBirthYear) {
        List<Division> results = new ArrayList<Division>();

        if (divisionName != null) {
            Division divisionFound = divisionService.findByName(divisionName);

            results.add(divisionFound);
        } else if (divisionStartBirthYear != null) {
            Division divisionFound = divisionService.findByStartBirthYear(divisionStartBirthYear);

            results.add(divisionFound);
        }

        return results;

    }

    @PostMapping("/division")
    public Division createDivision(@RequestBody Division division) {
       return divisionService.createDivision(division);
    }

    @PutMapping("/division/{id}")
    public ResponseEntity<Division> updateDivision(@PathVariable long id, @RequestBody Division division) {
        return ResponseEntity.ok(divisionService.updateDivision(id, division));
    }

    @DeleteMapping("/division/{id}")
    public void deleteDivisionById(@PathVariable long id) {
        divisionService.deleteDivisionById(id);
    }
}
