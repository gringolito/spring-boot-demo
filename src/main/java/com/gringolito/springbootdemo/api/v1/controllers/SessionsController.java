package com.gringolito.springbootdemo.api.v1.controllers;

import com.gringolito.springbootdemo.models.Session;
import com.gringolito.springbootdemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {
    @Autowired
    private SessionRepository repository;

    @GetMapping
    public List<Session> list() {
        return repository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id) {
        return repository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Session create(@RequestBody final Session session) {
        return repository.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //TODO: Also need to check for children records before deleting.
        repository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody final Session session) {
        Session currentSession = repository.getOne(id);
        BeanUtils.copyProperties(session, currentSession, "session_id");
        return repository.saveAndFlush(currentSession);
    }
}
