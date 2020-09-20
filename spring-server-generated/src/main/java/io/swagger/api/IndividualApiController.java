package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.Individual;
import io.swagger.model.IndividualCreate;
import io.swagger.model.IndividualUpdate;
import io.swagger.service.IndividualService;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-08T11:42:54.708Z")

@Controller
public class IndividualApiController implements IndividualApi {

    private static final Logger log = LoggerFactory.getLogger(IndividualApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    IndividualService individaulService;

    Error error=null;
    
    @org.springframework.beans.factory.annotation.Autowired
    public IndividualApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Object> createIndividual(@ApiParam(value = "The Individual to be created" ,required=true )
    @Valid @RequestBody IndividualCreate individual) throws NotFoundException{
        String accept = request.getHeader("Accept");
        String str=null;
        if (accept != null && accept.contains("application/json")) {
            try {
            	if(!individual.getId().isEmpty() && !individual.getFullName().isEmpty()) {
            		str=objectMapper.writeValueAsString(individual);
                	Individual ind=objectMapper.readValue(str, Individual.class);
                	Individual ind1=individaulService.createIndividual(ind);
                	return new ResponseEntity<Object>(ind1, HttpStatus.CREATED);
            	}else {
            		error=new Error();
            		error.setCode("400");
            		error.setMessage("ID or fullname are empty");
            		error.setReason("Bad request");
            		return new ResponseEntity<Object>(error,HttpStatus.BAD_REQUEST);
            	}
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                Error e1=new Error();
                e1.setCode("500");
                e1.setMessage(e.getMessage());
                return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Object>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteIndividual(@ApiParam(value = "Identifier of the Individual",required=true) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        individaulService.deleteIndividual(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<List<Individual>> listIndividual(@ApiParam(value = "Comma-separated properties to be provided in response") @Valid @RequestParam(value = "fields", required = false) String fields,@ApiParam(value = "Requested index for start of resources to be provided in response") @Valid @RequestParam(value = "offset", required = false) Integer offset,@ApiParam(value = "Requested number of resources to be provided in response") @Valid @RequestParam(value = "limit", required = false) Integer limit) {
        String accept = request.getHeader("Accept");
        List<Individual> list=new ArrayList<Individual>();
        if (accept != null && accept.contains("application/json")) {
            try {            	
            Iterable<Individual> itr=individaulService.listIndividual();
            itr.forEach(list::add);
                return new ResponseEntity<List<Individual>>(list, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Individual>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Individual>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Individual> patchIndividual(@ApiParam(value = "Identifier of the Individual",required=true) @PathVariable("id") String id,@ApiParam(value = "The Individual to be updated" ,required=true )  @Valid @RequestBody IndividualUpdate individual) {
        String accept = request.getHeader("Accept");
        String str=null;
        if (accept != null && accept.contains("application/json")) {
            try {
            	str=objectMapper.writeValueAsString(individual);
            	Individual ind=objectMapper.readValue(str, Individual.class);
            	Individual ind1=individaulService.patchIndividual(ind.getId(),ind.getFullName());
            	
                return new ResponseEntity<Individual>(ind1, HttpStatus.OK);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Individual>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            catch (NotFoundException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                 return new ResponseEntity<Individual>(HttpStatus.NOT_FOUND);
             }
        }

        return new ResponseEntity<Individual>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Object> retrieveIndividual(@ApiParam(value = "Identifier of the Individual",required=true) 
    @PathVariable("id") String id,@ApiParam(value = "Comma-separated properties to provide in response") 
    @Valid @RequestParam(value = "fields", required = false) String fields) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
            	Individual ind=individaulService.retrieveIndividual(id);  
                return new ResponseEntity<Object>(ind, HttpStatus.OK);
           }catch (NotFoundException e) {
               log.error("Couldn't serialize response for content type application/json", e);
               Error e1=new Error();
               e1.setCode("404");
               e1.setMessage(e.getMessage());
                return new ResponseEntity<Object>(e1,HttpStatus.NOT_FOUND);
            }
        }
 
        return new ResponseEntity<Object>(HttpStatus.NOT_IMPLEMENTED);
    }

}
