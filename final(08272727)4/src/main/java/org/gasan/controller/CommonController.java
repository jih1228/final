package org.gasan.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.gasan.domain.MemberVO;
import org.gasan.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/*")
public class CommonController {

	@Setter(onMethod_ = @Autowired)
	private CommonService commonService;
	
	
	@Inject
	BCryptPasswordEncoder pwEncoder;

	// 에러 페이지
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {

		log.info("access Denied: " + auth);

		model.addAttribute("msg", "Access Denied");

	}

	// 로그인
	@GetMapping("/customLogin")
	public String loginInput(String error, String logout, Model model) {

		log.info("error: " + error);
		log.info("logout: " + logout);

		if (error != null) {
			model.addAttribute("error", "Login Error Check Your Account");
		}

		if (logout != null) {
			model.addAttribute("logout", "Logout!!");
		}

		return "/users/customLogin";
	}

	// 로그아웃 페이지 접근
	@GetMapping("/customLogout")
	public String logoutGET() {

		log.info("custom logout");
		
		return "/users/customLogout";
	}
	
	//소셜 로그인 페이지 접근
	@GetMapping("/customLogin_social")
	public String login_social() {

		log.info("customLogin_social");
		
		return "/users/customLogin_social";
	}
	

	// 로그아웃 처리
	@PostMapping("/customLogout")
	public String logoutPost() {

		log.info("post custom logout");
		
		return "/users/customLogout";
	}

	// 회원가입 페이지 접근
	@GetMapping("/customSignup")
	public String signupGET() {

		log.info("회원가입 폼");
		
		return "/users/customSignup";
	}

	// 회원 가입 처리
	@PostMapping("/customSignup")
	public String signupPOST(MemberVO vo, RedirectAttributes rttr, HttpServletRequest request) throws Exception {
		log.info(vo);

		// 아이디 체크
		int result = commonService.idChk(vo);

		// 아이디체크 후 회원가입 처리
		try {

			if (result == 1) {
				return "/customSignup";
			} else if (result == 0) {

				commonService.customSignup(vo);
				rttr.addFlashAttribute("result", vo.getUserName());
			}

		} catch (Exception e) {

			throw new RuntimeException();
		}

		return "redirect:/";
	}

	// 아이디 체크
	@ResponseBody
	@RequestMapping(value = "/idChk", method = RequestMethod.GET)
	public int idChk(MemberVO vo) throws Exception {
		int result = commonService.idChk(vo);
		return result;
	}

	// 이메일 인증
	@RequestMapping(value = "/emailConfirm", method = RequestMethod.GET)
	public String emailConfirm(String userEmail, Model model) throws Exception { // �씠硫붿씪 �씤利� �솗�씤李�
		commonService.userAuth(userEmail);
		model.addAttribute("userEmail", userEmail);

		return "/emailConfirm"; // emailConfirm.jsp
	}

	// 회원수정 페이지 접근
	@RequestMapping(value = "/myInfo", method = RequestMethod.GET)
	public String myInfo() throws Exception {

		return "/users/myInfo";
	}
	@RequestMapping(value = "/myReservation", method = RequestMethod.GET)
	public String myReservation() throws Exception {

		return "/users/myReservation";
	}
	@RequestMapping(value = "/myBoard", method = RequestMethod.GET)
	public String myBoard() throws Exception {

		return "/users/myBoard";
	}
	

	// 회원 수정 처리
	@RequestMapping(value = "/memberUpdate", method = RequestMethod.POST)
	public String registerUpdate(MemberVO vo) throws Exception {
		log.info(vo.getUserpw());
		commonService.memberUpdate(vo);

		return "redirect:/";
	}

	// 회원탈퇴 페이지 접근
	@RequestMapping(value = "/memberDeleteView", method = RequestMethod.GET)
	public String memberDeleteView() throws Exception {
		return "/memberDeleteView";
	}

	
	
	// 회원 탈퇴 처리
	@RequestMapping(value = "/memberDelete", method = RequestMethod.POST)
	public String memberDelete(MemberVO vo) throws Exception{
		

		commonService.memberDelete(vo);
		
		
		return "redirect:/customLogout";
	}

	// 패스워드 체크 처리
	@ResponseBody
	@RequestMapping(value="/passChk", method = RequestMethod.POST)
	public boolean passChk(MemberVO vo) throws Exception {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		UserDetails userDetails = (UserDetails)principal; 
		String password = userDetails.getPassword();

		log.info(password);
		
		boolean pwdChk = pwEncoder.matches(vo.getUserpw(), userDetails.getPassword());
		return pwdChk;
			
			 
			
		}

	//작동안되는 로그인
	@RequestMapping(value = "/customLogin", method = RequestMethod.POST)
	public String login(MemberVO vo, HttpSession session, RedirectAttributes rttr) throws Exception {
		log.info("post login");

		session.getAttribute("member");
		MemberVO login = commonService.login(vo);
		boolean pwdMatch = pwEncoder.matches(vo.getUserpw(), login.getUserpw());

		if (login != null && pwdMatch == true) {
			session.setAttribute("member", login);
		} else {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
		}

		return "redirect:/";
	}
	
	

	// 이메일 중복 확인
	@ResponseBody
	@RequestMapping(value = "/emailChk", method = RequestMethod.GET)
	public int emailChk(MemberVO vo) throws Exception {
		int result = commonService.emailChk(vo);
		return result;
	}

	// 휴대폰 번호 중복 확인
	@ResponseBody
	@RequestMapping(value = "/phoneChk", method = RequestMethod.GET)
	public int phoneChk(MemberVO vo) throws Exception {
		int result = commonService.phoneChk(vo);
		return result;
	}

}
