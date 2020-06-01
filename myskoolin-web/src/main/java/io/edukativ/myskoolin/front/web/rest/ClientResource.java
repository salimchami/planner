package io.edukativ.myskoolin.front.web.rest;

import io.edukativ.myskoolin.application.ClientApplication;
import io.edukativ.myskoolin.application.dto.ClientDTO;
import io.edukativ.myskoolin.domain.commons.AuthoritiesConstants;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api")
@RestController
class ClientResource {

    private final Logger log = LoggerFactory.getLogger(ClientResource.class);

    private final ClientApplication clientApplication;

    public ClientResource(ClientApplication clientApplication) {
        this.clientApplication = clientApplication;
    }

//    @Timed
//    @PostMapping(value = "/clients")
//    public ResponseEntity<Client> createClient(@RequestBody @Valid Client client, HttpServletRequest request) throws URISyntaxException {
//        log.debug("REST request to save Client : {}", client);
//        if (client.getId() != null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("client", "idexists", "A new client cannot already have an ID")).body(null);
//        }
//        Client result = clientService.createClient(client, WebUtil.baseUrl(request));
//        return ResponseEntity.created(new URI("/clients/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert("client", result.getId().toString()))
//            .body(result);
//    }

//    @Timed
//    @GetMapping(value = "/clients")
//    public List<Client> getAllClients() {
//        log.debug("REST request to get all clients");
//        return clientRepository.findAll();
//    }

//    @Timed
//    @GetMapping(value = "/clients/{id}")
//    @PreAuthorize("hasAnyAuthority("
//        + AuthoritiesConstants.SCHOOLME_ADMIN + ","
//        + AuthoritiesConstants.ADMINISTRATION +
//        " )")
//    public ResponseEntity<ClientDTO> getClient(@PathVariable String id) {
//        log.debug("REST request to get Client : {}", id);
//        Client client = clientRepository.findOne(id);
//        return Optional.ofNullable(client)
//            .map(result -> new ResponseEntity<>(
//                result,
//                HttpStatus.OK))
//            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    @Timed
    @GetMapping(value = "/clients/current")
    @Secured(AuthoritiesConstants.ADMINISTRATION)
    public ResponseEntity<ClientDTO> getCurrentClient() {
        return clientApplication.currentClient()
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @Timed
//    @DeleteMapping(value = "/clients/{id}")
//    public ResponseEntity<Void> deleteClient(@PathVariable String id) {
//        log.debug("REST request to delete Client : {}", id);
//        clientRepository.delete(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("client", id)).build();
//    }

}
