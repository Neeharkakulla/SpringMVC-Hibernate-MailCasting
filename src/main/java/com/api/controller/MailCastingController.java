package com.api.controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.api.model.SentBoxModel;
import com.api.model.UserModel;
import com.api.service.BinService;
import com.api.service.InBoxService;
import com.api.service.SendMessage;
import com.api.service.SentBoxService;
import com.api.service.UserService;

@ComponentScan(basePackages = {"com.api"})
@Controller
@SessionAttributes("usermail")
public class MailCastingController {
	
	@Autowired
	BinService binService;
	@Autowired
	InBoxService inboxService;
	@Autowired
	SentBoxService sentboxService;
	@Autowired
	UserService userService;
	@Autowired
	SendMessage messageService;

//Header Mapping	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView showIndex()  {
		return new ModelAndView("index","user",new UserModel());
	}
	
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public ModelAndView showHome(@ModelAttribute("usermail") String usermail) {
		return new ModelAndView("home","mails",inboxService.getAllMailsByEmail(usermail));
	}
	
	@RequestMapping(value="/bin",method=RequestMethod.GET)
	public ModelAndView showBin(@ModelAttribute("usermail") String usermail) {
		return new ModelAndView("bin","mails",binService.getBinMailsByMailId(usermail));
	}
	
	@RequestMapping(value="/sent",method=RequestMethod.GET)
	public ModelAndView showSentBox(@ModelAttribute("usermail") String usermail) {
		return new ModelAndView("sent","mails",sentboxService.getAllMailsByEmail(usermail));
	}
	
	@RequestMapping(value="/myProfile",method=RequestMethod.GET)
	public ModelAndView showProfile(@ModelAttribute("usermail") String usermail,Model m)  {
		m.addAttribute("success","");
		return new ModelAndView("myProfile","user",userService.getUserByEmail(usermail));
	}
	
	@RequestMapping(value="/compose",method=RequestMethod.GET)
	public ModelAndView showCompose(@ModelAttribute("usermail") String usermail) {
		return new ModelAndView("compose","mail",new SentBoxModel(usermail));
	}

	@RequestMapping(value="/register-page",method=RequestMethod.GET)
	public ModelAndView showRegisterPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return new ModelAndView("Register","user",new UserModel());
	}
	@RequestMapping(value="/contactus",method=RequestMethod.GET)
	public ModelAndView showContactUs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return new ModelAndView("contactus");
	}
	
	
//send mail	
	@RequestMapping(value="/composeEmail",method=RequestMethod.POST)
	private ModelAndView composeEmail(@ModelAttribute("usermail")String usermail,@ModelAttribute("mail")SentBoxModel mail)  {
		
			messageService.sendMsg(mail);
			return new ModelAndView("home","mails",inboxService.getAllMailsByEmail(usermail));
		
		}
	
	
//Show Message
	@RequestMapping(value="/getInBoxMail",method=RequestMethod.GET)
	public ModelAndView getInboxMail(@RequestParam("id") String id,Model m) {
		
		m.addAttribute("mail", inboxService.getMailById(Integer.parseInt(id)));
		return new ModelAndView("GetInBoxMail");
	}
	@RequestMapping(value="/getSentBoxMail",method=RequestMethod.GET)
	public ModelAndView getSentboxMail(@RequestParam("id") String id,Model m) {
		m.addAttribute("mail", sentboxService.getMailById(Integer.parseInt(id)));
		return new ModelAndView("GetSentMail");
	}
	@RequestMapping(value="/getBinMail",method=RequestMethod.GET)
	public ModelAndView geBinboxMail(@RequestParam("id") String id,Model m) {
		m.addAttribute("mail", binService.getMailById(Integer.parseInt(id)));
		return new ModelAndView("GetBinMail");
	}
	
	
//delete mails
	@RequestMapping(value="/deleteSentboxMail",method=RequestMethod.GET)
	public ModelAndView deleteSentboxMail(@ModelAttribute("usermail")String usermail,@RequestParam("id")String id) {
		binService.addSentBoxMailtoBin(Integer.parseInt(id));
		return new ModelAndView("sent","mails",sentboxService.getAllMailsByEmail(usermail));
	}
	@RequestMapping(value="/deleteInboxMail",method=RequestMethod.GET)
	public ModelAndView deleteinboxMail(@ModelAttribute("usermail")String usermail,@RequestParam("id")String id) {
		binService.addInboxMailtoBin(Integer.parseInt(id));
		return new ModelAndView("home","mails",inboxService.getAllMailsByEmail(usermail));

	}
	@RequestMapping(value="/deleteBinboxMail",method=RequestMethod.GET)
	public ModelAndView deleteBinboxMail(@ModelAttribute("usermail")String usermail,@RequestParam("id")String id) {
		binService.deleteByBinId(Integer.parseInt(id));
		return new ModelAndView("bin","mails",binService.getBinMailsByMailId(usermail));
	}

	
//retrive from bin
	@RequestMapping(value="/retriveMail",method=RequestMethod.GET)
	private ModelAndView retriveFromBin(@ModelAttribute("usermail")String usermail,@RequestParam("id")String id) {
			
			String type=binService.retriveFromBin(Integer.parseInt(id));
			
			if(type.equalsIgnoreCase("inbox")) 
				return new ModelAndView("home","mails",inboxService.getAllMailsByEmail(usermail));
			
			if(type.equalsIgnoreCase("sentbox")) 
				return new ModelAndView("sent","mails",sentboxService.getAllMailsByEmail(usermail));
				
		
			return new ModelAndView("index","user",new UserModel());
		
	}
	
		
		
//User login/logout and register
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ModelAndView  login(@ModelAttribute("user")UserModel user,Model m) {
		
		
		boolean status=userService.checkLogin(user.getEmail(),user.getPassword());
		if(status==true){
			m.addAttribute("usermail", user.getEmail());
			return showHome(user.getEmail());
			
		}
		else{
			String Error="Please check your Email and Password";
			return new ModelAndView("index","serverMessage",Error);					
		}
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	private ModelAndView logOut(Model m,@ModelAttribute("usermail") String usermail, WebRequest request, SessionStatus status)  {
		status.setComplete();
	    request.removeAttribute("user", WebRequest.SCOPE_SESSION);
			String Error="You have been sucessfully logged out";
			m.addAttribute("serverMessage",Error);
						
			return new ModelAndView("index","user",new UserModel());
		
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	private ModelAndView registerUser(@ModelAttribute("user") UserModel user)  {
			String register= "You are Successfully registered";
			
			return new ModelAndView("index","serverMessage",register);
		
	}
	
//password Change Request	
	@RequestMapping(value="/validate",method=RequestMethod.POST)
	private ModelAndView validatePassword(Model m,@RequestParam("id")String id,@ModelAttribute("usermail") String usermail,@RequestParam("password")String password) {

		if(userService.validatePassword(Integer.parseInt(id),password)) 
			m.addAttribute("success", "success");
		else 
			m.addAttribute("success", "Invalid");

		return new ModelAndView("myProfile","user",userService.getUserByEmail(usermail));
		
		
	}
	@RequestMapping(value="/newPasswordRequest",method=RequestMethod.POST)
	private ModelAndView changePassword(Model m,@RequestParam("id")String id,@ModelAttribute("usermail") String usermail,@RequestParam("password")String password)  {
		
		
		if(userService.changePassword(Integer.parseInt(id),password)) {
			m.addAttribute("newPassword", "Password SuccesFully Changed");
			m.addAttribute("success","");	
		}
		return new ModelAndView("myProfile","user",userService.getUserByEmail(usermail));
		
	
	}


		
}

