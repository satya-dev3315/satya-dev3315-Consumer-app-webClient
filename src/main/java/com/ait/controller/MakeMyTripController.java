package com.ait.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ait.binding.Passenger;
import com.ait.binding.Ticket;
import com.ait.serice.MakeMyTripService;

import reactor.core.publisher.Mono;

@Controller
public class MakeMyTripController {

	@Autowired
	private MakeMyTripService makeMyTripService;
	
	
	
//controller to show all upcoming tickets data in a table ie. in UI,create a following controller to send data frm here to UI
	
	@GetMapping("/")
	public String index(Model model) {
		Mono<Ticket[]> allTickets = makeMyTripService.getAllTickets();
		model.addAttribute("tickets", allTickets);
		return "index";
		}
	
	//for booking a ticket, we require an empty form page,which is helpful to load passenger data , load that page, so use new passenger(), for binding
	
	@GetMapping("/bookTicket")
	public String bookTicketForm(Model model) {
		model.addAttribute("p", new Passenger());
		model.addAttribute("ticket", new Ticket());  //for line 48, we r sending this empty ticket obj
		return "bookTicket";      //for form which is popped by using book ticket button,check bookTicket.html
		}
	
	//when we press submit , ticket should b booked
	@PostMapping("/ticket")
	public String bookTicket(@ModelAttribute("p") Passenger p, Model model) {
		Mono<Ticket> bookTicket = makeMyTripService.bookTicket(p);
		model.addAttribute("ticket", bookTicket);
		model.addAttribute("msg", "Success : Your ticket is booked");
		return "bookTicket";
		
	}

}
