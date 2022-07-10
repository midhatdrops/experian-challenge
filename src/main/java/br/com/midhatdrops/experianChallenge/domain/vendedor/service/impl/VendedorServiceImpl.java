package br.com.midhatdrops.experianChallenge.domain.vendedor.service.impl;

import br.com.midhatdrops.experianChallenge.domain.atuacao.entity.Atuacao;
import br.com.midhatdrops.experianChallenge.domain.atuacao.infrasctructure.repository.AtuacaoRepository;
import br.com.midhatdrops.experianChallenge.domain.vendedor.entity.Vendedor;
import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.dto.VendedorRequestDTO;
import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.dto.VendedorResponseDTO;
import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.exceptions.MalformedCellphoneException;
import br.com.midhatdrops.experianChallenge.domain.vendedor.infrastructure.repository.VendedorRepository;
import br.com.midhatdrops.experianChallenge.domain.vendedor.service.VendedorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

import static br.com.midhatdrops.experianChallenge.domain.vendedor.util.DateFormatterUtil.formatDate;

@Service
@Slf4j
public class VendedorServiceImpl implements VendedorService {

    @Autowired
    private VendedorRepository repository;

    @Autowired
    private AtuacaoRepository atuacaoRepository;


    @Override
    public VendedorRequestDTO insert(VendedorRequestDTO requestDTO) {
        try {
            final Vendedor vendedor = new Vendedor(requestDTO);
            final Vendedor savedEntity = repository.save(vendedor);
            return new VendedorRequestDTO(savedEntity);
        } catch (MalformedCellphoneException malformedCellphoneException) {
            log.error("Malformed cellphone number: " + malformedCellphoneException.getMessage());
            throw malformedCellphoneException;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }

    }

    @Override
    public List<VendedorResponseDTO> getAll(Integer page) {

        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "name"));
        Pageable pageable = PageRequest.of(page,10,sort);
        Page<Vendedor> listofVendors = repository.findAll(pageable);
        ArrayList<VendedorResponseDTO> vendedorDTOS = new ArrayList<>();
        for(Vendedor vendedor : listofVendors.getContent()) {
             Optional<Atuacao> region = atuacaoRepository.findById(vendedor.getRegion());
             if(region.isPresent()) {
                  VendedorResponseDTO responseDTO = VendedorResponseDTO.builder()
                          .cidade(vendedor.getCity())
                          .dataInclusao(formatDate(vendedor.getCreatedAt()))
                         .idade(vendedor.getAge())
                          .telefone(vendedor.getCellphone())
                          .estados(region.get().getStates())
                          .estado(vendedor.getState().toString())
                         .build();
                 vendedorDTOS.add(responseDTO);
             } else {
                 VendedorResponseDTO responseDTO = VendedorResponseDTO.builder()
                         .cidade(vendedor.getCity())
                         .dataInclusao(formatDate(vendedor.getCreatedAt()))
                         .idade(vendedor.getAge())
                         .telefone(vendedor.getCellphone())
                         .estados(Collections.emptyList())
                         .estado(vendedor.getState().toString())
                         .build();
                 vendedorDTOS.add(responseDTO);
             }

            }
        return vendedorDTOS;
    }

    @Override
    public VendedorResponseDTO getOne(final Long id) {
         Vendedor vendedor = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found id:" + id));
        Optional<Atuacao> region = atuacaoRepository.findById(vendedor.getRegion());
        if(region.isPresent()) {
             return VendedorResponseDTO.builder()
                    .nome(vendedor.getName())
                    .dataInclusao(formatDate(vendedor.getCreatedAt()))
                    .estados(region.get().getStates()).build();
        } else {
           return VendedorResponseDTO.builder()
                    .nome(vendedor.getName())
                    .dataInclusao(formatDate(vendedor.getCreatedAt()))
                    .estados(List.of()).build();
        }

    }



}
