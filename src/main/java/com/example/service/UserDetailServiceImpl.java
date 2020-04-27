package com.example.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.domain.User;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public UserDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException{
		User user = userRepository.findUserByEmail(mailAddress);
		if(user == null) {
			throw new UsernameNotFoundException("そのEmailは登録されていません");
		}
		
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));//ユーザー権限付与
		if(user.getAdmin()) {
           authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN")); // 管理者権限付与
        }
		
		session.setAttribute("userId", user.getId());
		
		if(user != null) {
			if(session.getAttribute("hashedOrder") != null) {
				Order order = (Order) session.getAttribute("hashedOrder");
				Long hashedOrderId = order.getOrderNumber();
				
				Order order2 = orderRepository.checkByUserIdAndStatus(user.getId());
				//未ログインで買い物→ログイン→ログイン時の買い物はしていない時(データベースでnull)
				//注文情報のuserIdをログインユーザで更新
				if (order2 == null) {
					order2 = new Order();
					order2.setUserId(user.getId());
					
					order2 = orderRepository.insert(order2);
				}
				
				//データベース上にログインユーザの注文情報があるときその注文Idを取得し
				//sessionIDの注文ID→ログインユーザの注文IDに更新する。
				Long loginUsersOrderId = order2.getOrderNumber();
				orderItemRepository.updateOrderIdByOrderId(hashedOrderId, loginUsersOrderId);
				//ハッシュ(sessionID)の注文情報をDBから消す
				orderRepository.delete(order.getUserId());
				//金額揃える
				order2.setTotalPrice(order2.getTotalPrice()+order.getTotalPrice());
				orderRepository.update(order2);				 
			}
		}
		
		return new LoginUser(user, authorityList);
	}
}
