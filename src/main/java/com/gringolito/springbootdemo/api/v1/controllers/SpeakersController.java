package com.gringolito.springbootdemo.api.v1.controllers;

import com.gringolito.springbootdemo.models.Speaker;
import com.gringolito.springbootdemo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {
    @Autowired
    SpeakerRepository repository;

    @GetMapping
    public List<Speaker> list() {
        return repository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id) {
        return repository.getOne(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Speaker create(@RequestBody final Speaker speaker) {
        return repository.saveAndFlush(speaker);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //TODO: Also need to check for children records before deleting.
        repository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Speaker update(@PathVariable Long id, @RequestBody final Speaker speaker) {
        //TODO: Add validations that all attributes are passed id, otherwise return a 400 bad request
        Speaker currentSpeaker = repository.getOne(id);
        BeanUtils.copyProperties(speaker, currentSpeaker, "speaker_id");
        return repository.saveAndFlush(currentSpeaker);
    }
}
