package me.rmndr.demoinflearnrestapi.events;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.net.URI;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_VALUE+";charset=UTF-8")
public class EventController {
    private final EventRepository eventRepository;

    private final ModelMapper modelMapper;

    private final EventValidator eventValidator;

    public EventController(EventRepository eventRepository, ModelMapper modelMapper, EventValidator eventValidator) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
        this.eventValidator = eventValidator;
    }

//    @Autowired
//    EventRepository eventRepository;

    @PostMapping
    public ResponseEntity createEvent(@RequestBody @Valid EventDto eventDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        eventValidator.validate(eventDto, errors);
//        Event event = Event.builder()
//            .name(eventDto.getName())
//            .description(eventDto.getDescription())
//            .beginEnrollmentDateTime(eventDto.getBeginEnrollmentDateTime())
//            .closeEnrollmentDateTime(eventDto.getCloseEnrollmentDateTime())
//            .beginEventDateTime(eventDto.getBeginEventDateTime())
//            .endEventDateTime(eventDto.getEndEventDateTime())
//            .location(eventDto.getLocation())
//            .basePrice(eventDto.getBasePrice())
//            .maxPrice(eventDto.getMaxPrice())
//            .limitOfEnrollment(eventDto.getLimitOfEnrollment())
//            //.eventStatus(EventStatus.DRAFT)
//            .build();
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Event event = modelMapper.map(eventDto, Event.class);
        Event newEvent = this.eventRepository.save(event);
        URI createdUri = linkTo(EventController.class).slash(newEvent.getId()).toUri();
        return ResponseEntity.created(createdUri).body(event);
    }
}
