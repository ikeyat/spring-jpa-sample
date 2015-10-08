package com.example.app.controller;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.model.Room;
import com.example.domain.service.RoomService;

/**
 * Created by ikeya on 15/09/06.
 */
@Controller
@Slf4j
public class RoomController {
    @PersistenceContext
    EntityManager em;
    
    @Autowired
    private RoomService roomService;
    
    @ModelAttribute
    public RoomForm setUpRoomForm() {
        return new RoomForm();
    }

    @ModelAttribute("jpaMode")
    public String setUpJpaMode() {
        return roomService.getClass().getName();
    }

    @RequestMapping(value = "/rooms/{id}")
    public String getRoom(@PathVariable("id") Integer id, Model model) {
        Room room = roomService.getRoom(id);
        model.addAttribute("room", room);
        return "/roomDetail";
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public String getRoomsAll(Model model) {
        Collection<Room> rooms = roomService.getRoomsAll();
        model.addAttribute("rooms", rooms);
        return "/roomsList";
    }
    
    @RequestMapping(value = "/rooms", method = RequestMethod.GET, params = {"paging"})
    public String getRoomsAll(Model model, Pageable pageable) {
        Page<Room> rooms = roomService.getRoomsAll(pageable);
        model.addAttribute("rooms", rooms);
        return "/roomsList";
    }
    
    @RequestMapping(value = "/rooms", method = RequestMethod.GET, params = {"paging-manual"})
    public String getRoomsByRoomNameAsc(Model model, @RequestParam("paging-manual") int page) {
        List<Room> rooms = roomService.getRoomsAll(page, 3);
        model.addAttribute("rooms", rooms);
        return "/roomsList";
    }
    
    @RequestMapping(value = "/rooms/{id}/delete")
    public String deleteRoom(@PathVariable("id") Integer id) {
        roomService.deleteRoom(id);
        return "redirect:/rooms";
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.POST)
    public String createRoom(@ModelAttribute @Validated RoomForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return getRoomsAll(model);
        }
        Room room = new Room();
        room.setRoomName(form.getName());
        room.setCapacity(form.getCapacity());
        roomService.createRoom(room);
        return "redirect: /rooms";
    }
}
