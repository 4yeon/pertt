package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class ModifyUserAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Integer user_num = (Integer) session.getAttribute("user_num");
		if (user_num == null) {// 로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}

		// 로그인 된 경우
		// 전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");

		// 자바빈(VO) 생성
		MemberVO member = new MemberVO();
		member.setMember_num(user_num);
		
		member.setPasswd(request.getParameter("passwd"));
		member.setName(request.getParameter("name"));/*
		member.setBirth(request.getParameter("birth1") + "-" + request.getParameter("birth2") + "-"
				+ request.getParameter("birth3"));*/
		member.setPhone(request.getParameter("phone1") + "-" + request.getParameter("phone2") + "-"
				+ request.getParameter("phone3"));
		member.setEmail(request.getParameter("email"));
		
		//현재 비밀번호
				String origin_passwd = 
						request.getParameter("origin_passwd");
				//새비밀번호
				String passwd = request.getParameter("passwd");
				
				//현재 로그인 한 아이디
				String user_id = 
						(String)session.getAttribute("user_id");
				
				MemberDAO dao = MemberDAO.getInstance();
				MemberVO member2 = dao.checkMember(user_id);
				boolean check = false;
				
				//사용자가 입력한 아이디가 존재하고 로그인한 아이디와 일치하는지 체크
				if(member2!=null ) {
					//비밀번호 일치 여부 체크
					check = member2.isCheckedPassword(origin_passwd);
				}
				
				if(check) {//인증 성공
					//비밀번호 변경      
					dao.updateMember(member);
				}
				
				request.setAttribute("check", check);
				
	 
		

		
		//전송된 데이터 반환
		//String id = request.getParameter("id");
		//현재 비밀번호
		String origin_passwd = request.getParameter("origin_passwd");
		//새 비밀번호
		String passwd = request.getParameter("passwd");
		
		//현재 로그인 한 아이디
		String member_id = (String)session.getAttribute("member_id");
		
		MemberVO member2 = dao.checkMember(member_id);
		boolean check = false;
		
		//사용자가 입력한 아이디가 존재하고 로그인한 아이디와 일치하는지 체크
		if(member2 != null) {
			//비밀번호 일치 여부 체크
			check = member2.isCheckedPassword(origin_passwd);
		}
		if(check) {
			//비밀번호 변경      새비밀번호  회원번호
			dao.updateMember(member);
		}
		
		request.setAttribute("check", check);

		return "/WEB-INF/views/member/modifyUser.jsp";
		
		

		//전송된 데이터 반환
		
		
	}

}
