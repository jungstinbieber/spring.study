package com.example.board.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.board.domain.User;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	private User user;
	
	//UserDetails객체에 담김
	public UserDetailsImpl(User user) {
		this.user=user;
	}
	
	// 해당사용자가 가지고있는 권한목록을 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		//권한 목록들을 저장할 컬렉션을 생성
		Collection<GrantedAuthority> roleList = new ArrayList<>();
		 
		roleList.add(()->{
			return "ROLE_" + user.getRole();
			
		});
		
		return roleList;
	}

	@Override
	public String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getUsername();
	}
	
	// 계정이 만료된 계정인지 아닌지 리턴시켜주는 메서드
	// 만료된 계정이 아니면 true를 리턴
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	// 계정이 잠겨있는지 아닌지를 리턴시켜주는 메서드
	// 잠겨 있지 않으면 true를 리턴
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	//비밀번호가 만료되지않았는지 리턴해주는 메서드
	// 만료안되면 true 리턴
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	//계정이 활성화되어있는지를 알려주는 메서드
	// 계정이 활성화 상태면 true
	@Override
	public boolean isEnabled() {
		
		return true;
	}

	
	
	
}
