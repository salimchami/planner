package io.edukativ.myskoolin.front.web.rest;

import io.edukativ.myskoolin.application.dto.PricingDTO;
import io.edukativ.myskoolin.application.mapper.PricingMapper;
import io.edukativ.myskoolin.infrastructure.commercial.dto.PricingDbDTO;
import io.edukativ.myskoolin.infrastructure.schooling.repository.PricingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Pricing.
 */
@RestController
@RequestMapping("/public")
class PricingResource {

    private final Logger log = LoggerFactory.getLogger(PricingResource.class);

    private final PricingRepository pricingRepository;
    private final PricingMapper pricingMapper;

    public PricingResource(PricingRepository pricingRepository, PricingMapper pricingMapper) {
        this.pricingRepository = pricingRepository;
        this.pricingMapper = pricingMapper;
    }

    /**
     * GET  /pricings -> get all the pricings.
     */
    @GetMapping(value = "/pricings", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PricingDTO> getAllPricings() {
        log.debug("REST request to get all Pricings");
        final List<PricingDbDTO> all = pricingRepository.findAll();
        return pricingMapper.pricingsDbDTOToPricingsDTO(all);
    }

    /**
     * GET  /pricings/:id -> get the "id" pricing.
     */
    @GetMapping(value = "/pricings/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PricingDTO> getPricing(@PathVariable String id) {
        log.debug("REST request to get Pricing : {}", id);
        Optional<PricingDbDTO> pricing = pricingRepository.findById(id);
        return pricing
            .map(result -> new ResponseEntity<>(
                pricingMapper.pricingDbDTOToPricingDTO(result),
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
