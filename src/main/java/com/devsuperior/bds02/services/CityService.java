package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundServiceException;
import com.devsuperior.bds02.services.exceptions.ViolationIntegrityServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;


    @Transactional(readOnly = true)
    public List<CityDTO> findAll() {
        return cityRepository.findAll(Sort.by("name")).stream().map(CityDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public CityDTO insert(CityDTO cityDTO) {

        City city = new City();
        city.setName(cityDTO.getName());
        city = cityRepository.save(city);

        return new CityDTO(city);
    }

    public void delete(Long id) {
        try {
            cityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundServiceException("Id not found! id: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new ViolationIntegrityServiceException("Violation Integrity! id: " + id);
        }

    }

}
