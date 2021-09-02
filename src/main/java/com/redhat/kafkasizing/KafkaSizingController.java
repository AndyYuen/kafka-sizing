package com.redhat.kafkasizing;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.kafkasizing.model.SizingParams;
import com.redhat.kafkasizing.model.SizingResults;
import com.redhat.kafkasizing.service.SizingService;

@Controller
public class KafkaSizingController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private static final String MY_SESSION_NAME = "KAFKA_SIZING_SESSION_PARAMS";
	
	@Autowired
	private SizingService sizingService;

	@GetMapping("/size")
	// GET operation to display sizing page
	public String showForm(Model model, final HttpSession session) {
		SizingParams params = (SizingParams) session.getAttribute(MY_SESSION_NAME);
		if (params == null) {
			params = new SizingParams();
			params.setMaxUtil((float)0.65);
			params.setNetSpeed((float) 1.0);
			params.setReplicas(3);
			params.setDiskThroughput(125);
			params.setZkFailures(1);
		}

		model.addAttribute("params", params);
		
		return "index";
	}
	
	@PostMapping("/size")
	// POST operation to perform Kafka sizing
	public String submitForm(@Valid @ModelAttribute("params") SizingParams params, 
			BindingResult bindingResult, Model model, final HttpServletRequest request) {
		LOG.info(params.toString());
		
	    if (bindingResult.hasErrors()) {       
	        return "index";
	    }
		
		SizingResults results = sizingService.sizeKafkaCluster(params);
		LOG.info(results.toString());
		
		request.getSession().setAttribute(MY_SESSION_NAME, params);
		
		model.addAttribute("results", results);
		
		return "sizingResult";
	}
}
