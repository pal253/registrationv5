package com.api5.service;

import com.api5.entity.Registration;
import com.api5.exception.ResourceNotFoundException;
import com.api5.payload.RegistrationDto;
import com.api5.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
    private RegistrationRepository rp ;
    private ModelMapper mp ;


    public RegistrationService(RegistrationRepository rp, ModelMapper mp) {
        this.rp = rp;

        this.mp = mp;
    }

    public List <RegistrationDto> getRegistrations(){
        List <Registration> as = rp.findAll();
        List <RegistrationDto> dtos = as.stream().map(w->mapToDto(w)).collect(Collectors.toList());

        return dtos;
    }
    public RegistrationDto createReg(RegistrationDto dto){
       Registration registration = mapToEntity(dto);
       Registration jj =  rp.save(registration);
      RegistrationDto ty = mapToDto(jj);



       return ty;
    }
    public void deleteReg(long id1){
        rp.deleteById(id1);
    }
    public Registration updateReg(long id , Registration registration){
        Registration r = rp.findById(id).get();
        r.setName(registration.getName());
        r.setEmail(registration.getEmail());
        r.setMobile(registration.getMobile());
        Registration fg = rp.save(r);
        return fg ;
    }
    Registration mapToEntity(RegistrationDto dto){
       Registration registration = mp.map(dto ,Registration.class);
       // Registration registration = new Registration();
        //registration.setName(dto.getName());
        //registration.setEmail(dto.getEmail());
        //registration.setMobile(dto.getMobile());
        return registration;

    }
    RegistrationDto mapToDto(Registration registration){
        RegistrationDto dto = mp.map(registration , RegistrationDto.class);
      //  RegistrationDto dto = new RegistrationDto();
        //dto.setName(registration.getName());
       // dto.setEmail(registration.getEmail());
       // dto.setMobile(registration.getMobile());
        return dto;
    }

    public RegistrationDto getRegById(long id) {
       Registration reg = rp.findById(id).orElseThrow(
               ()->new RuntimeException("record not found")
       );
       return mapToDto(reg);
    }
}
