package com.ait.serice;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.ait.binding.Passenger;
import com.ait.binding.Ticket;

import reactor.core.publisher.Mono;

@Service
public class MakeMyTripService {

	//book ticket-get passenger data n connect to producer-app-irctc-app n get ticket, and send ticket to the passenger
	
	public Mono<Ticket> bookTicket(Passenger passenger) {  //provider will take passeneger data as input n give  ticket as output
		
		String URL = "http://13.60.15.7:8080/bookTicket";  // to this url(available in cloud) we wanna send req
		
		WebClient webClient = WebClient.create();
		
		Mono<Ticket> bodyToMono = webClient.post()                                  //wat method u wanna send
		.uri(URL)                                         //producer url 
		.body(BodyInserters.fromValue(passenger))       //wat u wanna send in body
		.retrieve()
		.bodyToMono(Ticket.class);                      //map data to ticket .class    
		 return bodyToMono;
		
		
		
	}
	
	public Mono<Ticket[]> getAllTickets(){
		String URL = "http://13.60.15.7:8080/getTickets";  
		
		WebClient webClient = WebClient.create();
		Mono<Ticket[]> bodyToMono = webClient.get().uri(URL).retrieve().bodyToMono(Ticket[].class);//get has no body
		return bodyToMono;
		
	}
		
	
	
	 
}
