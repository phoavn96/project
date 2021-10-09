package com.freelancer.service;

import com.freelancer.model.Complexity;
import com.freelancer.model.ResponseObject;
import com.freelancer.model.User;
import com.freelancer.repository.ComplexityRepository;
import com.freelancer.utils.ConfigLog;
import com.freelancer.utils.Constant;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComplexityService {

    Logger logger = ConfigLog.getLogger(UserService.class);

    Gson gson = new Gson();
    @Autowired
    private ComplexityRepository complexityRepository;

    public List<Complexity> listAll(){
        return complexityRepository.findAll();

    }

    public ResponseObject save(Complexity complexity){
        String message = "not success";
        logger.info("call to Create complexity" + complexity.toString());
        Complexity result = complexityRepository.save(complexity);
        if (result!=null){
            message="success";
        }
        return new ResponseObject(Constant.STATUS_ACTION_SUCCESS, message, result);
    }

    public Complexity get(Long id) {
        return complexityRepository.findById(id).get();
    }

    public ResponseObject delete(Long id) {

        logger.info("call to get Complexity to delete by id: " + id);
        Optional<Complexity> optionalComplexity = complexityRepository.findById(id);
        String message = "can not find complexity";
        Complexity result = null;
        if (optionalComplexity.isPresent()) {
            complexityRepository.deleteById(id);
            message = "delete success";
            logger.info("delete complexity success");
            return new ResponseObject(Constant.STATUS_ACTION_SUCCESS, message, null);
        }else {
            return new ResponseObject(Constant.STATUS_ACTION_FAIL,message,null);
        }

    }
    public ResponseObject update(Complexity complexity,Long id){
        logger.info("call to get Complexity to update by id: " + id);
        Optional<Complexity> optionalComplexity = complexityRepository.findById(id);
        String message = "can not find complexity";
        Complexity result = null;
        if (optionalComplexity.isPresent()) {
            Complexity complexity1 = optionalComplexity.get();
            result = complexityRepository.save(complexity);
            message = "update success";
            logger.info("update complexity success");
            return new ResponseObject(Constant.STATUS_ACTION_SUCCESS, message, result);
        }else {
            return new ResponseObject(Constant.STATUS_ACTION_FAIL,message,null);
        }

    }

    public ResponseObject getById(Long id) {
        logger.info("call to get Complexity by id: " + id);
        Optional<Complexity> optionalComplexity = complexityRepository.findById(id);
        String message = "can not find complexity";
        Complexity complexity = null;
        if (optionalComplexity.isPresent()) {
            message = "success";
            complexity = optionalComplexity.get();
            logger.info("get complexity success");
        }
        return new ResponseObject(Constant.STATUS_ACTION_SUCCESS, message, complexity);
    }




    public ResponseObject search(String keysearch, int page, int size) {
        logger.info("call to search user with keysearch: " + keysearch);
        String message = "success";
        List<Complexity> list = complexityRepository.searchComplexity(keysearch, PageRequest.of(page - 1, size));
        Long total = complexityRepository.countComplexity(keysearch);
        return new ResponseObject(Constant.STATUS_ACTION_SUCCESS, message, total, list);
    }



}
