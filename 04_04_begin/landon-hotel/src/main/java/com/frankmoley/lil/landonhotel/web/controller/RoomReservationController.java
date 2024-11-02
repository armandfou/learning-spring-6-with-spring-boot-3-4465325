package com.frankmoley.lil.landonhotel.web.controller;

import com.frankmoley.lil.landonhotel.data.repository.RoomRepository;
import com.frankmoley.lil.landonhotel.service.RoomReservationService;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/room-reservation")
public class RoomReservationController {

  private final RoomReservationService roomReservationService;

  public RoomReservationController( RoomReservationService roomReservationService) {
    this.roomReservationService = roomReservationService;
  }

  @GetMapping
  public String getRooms(Model model, @RequestParam(value="date", required = false)String date){

    if (StringUtils.isNotEmpty(date )) {
      model.addAttribute("roomsReservation", this.roomReservationService.getRoomReservationsForDate(date));
    }
    else {
      date = String.valueOf(new Date());
      model.addAttribute("roomsReservation", this.roomReservationService.getRoomReservationsForDate(date));
    }
    return "room-reservation-list";
  }
}
