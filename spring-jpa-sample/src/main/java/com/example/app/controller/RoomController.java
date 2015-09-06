package com.example.app.controller;

import com.example.domain.model.Room;
import com.example.domain.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by ikeya on 15/09/06.
 */
@Controller
public class RoomController {
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
        room.setName(form.getName());
        room.setCapacity(form.getCapacity());
        roomService.createRoom(room);
        return "redirect: /rooms";
    }
}
