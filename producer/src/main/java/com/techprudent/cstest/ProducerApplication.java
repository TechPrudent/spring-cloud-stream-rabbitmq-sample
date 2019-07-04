package com.techprudent.cstest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SpringBootApplication
@EnableBinding(MsgProducerSource.class)
@RestController
@RequestMapping("/data")
public class ProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

	@Autowired
	private MsgProducerSource source;

	@GetMapping("/send")
	public String send() {
		this.source.testChannel().send(MessageBuilder.withPayload(new Msg("adhi", "27", "M")).build());
		return "sent";
	}

}

interface MsgProducerSource {

	@Output("testChannel")
	MessageChannel testChannel();

}

@JsonIgnoreProperties(ignoreUnknown = true)
class Msg {
	private String name;
	private String age;
	private String gender;

	public Msg(String name, String age, String gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}