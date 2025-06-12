package com.keyin.rest.division;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisionRepository extends CrudRepository<Division, Long> {
    public Division findByName(String name);
    public Division findByStartBirthYear(String startBirthYear);
}
