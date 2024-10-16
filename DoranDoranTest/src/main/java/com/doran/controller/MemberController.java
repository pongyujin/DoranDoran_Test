package com.doran.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.doran.entity.Member;
import com.doran.mapper.MemberMapper;

@RestController
public class MemberController {

	@Autowired
	private MemberMapper memberMapper;

	// 1. 회원가입
	@PostMapping("/memberJoin")
	public String memberJoin(Member member, RedirectAttributes rttr, HttpSession session) {

		if (member.getMemId() == null || member.getMemId().equals("") 
				|| member.getMemPw() == null || member.getMemPw().equals("") 
				|| member.getMemNick() == null || member.getMemNick().equals("")
				|| member.getMemEmail() == null || member.getMemEmail().equals("") 
				|| member.getMemPhone() == null || member.getMemPhone().equals("")) {

			// 회원가입 실패
			rttr.addFlashAttribute("msgType", "실패");
			rttr.addFlashAttribute("msg", "모든 항목을 기입해주세요");

		} else {

			int cnt = memberMapper.memberJoin(member);

			if (cnt > 0) {
				
				rttr.addFlashAttribute("msgType", "성공");
				rttr.addFlashAttribute("msg", "회원가입에 성공했습니다");
				session.setAttribute("user", member);
			} else {
				
				rttr.addFlashAttribute("msgType", "실패");
				rttr.addFlashAttribute("msg", "회원가입에 실패했습니다");
			}
		}
		return "redirect:/main";
	}

	// 2. 로그인
	@PostMapping("/memberLogin")
	public String memberLogin(@RequestBody Member member, RedirectAttributes rttr, HttpSession session) {

		Member mvo = memberMapper.memberLogin(member);
		System.out.println(mvo);
		if (mvo == null) {

			rttr.addFlashAttribute("msgType", "실패");
			rttr.addFlashAttribute("msg", "아이디와 비밀번호를 확인해주세요");
			session.setAttribute("openLoginModal", true);
			System.out.println("실패");

			return "redirect:/main";
		} else {

			// 로그인 성공
			rttr.addFlashAttribute("msgType", "성공");
			rttr.addFlashAttribute("msg", "로그인을 성공했습니다");
			System.out.println("성공");
			// 로그인 정보 세션 저장
			session.setAttribute("user", member);

			return "redirect:/main";
		}
	}

	// 3. 아이디 중복 확인
	@RequestMapping("/registerCheck")
	public int registerCheck(@RequestParam("memID") String memID) {

		Member member = memberMapper.registerCheck(memID);
		if (member != null || memID.equals("")) {
			return 0;
		} else {
			return 1;
		}
	}

	// 4. 로그아웃
	@RequestMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate();
		return "redirect:/main";
	}
}
